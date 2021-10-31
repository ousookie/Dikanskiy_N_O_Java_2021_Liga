package ru.dikanskiy.exam.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.dikanskiy.exam.core.entities.TimeSlot;
import ru.dikanskiy.exam.core.util.ScheduleGenerator;

import javax.annotation.PostConstruct;
import java.time.LocalTime;
import java.util.List;

@Component
public class Schedule {

    @Value("${DAILY_WORKING_MINUTES_COUNT}")
    private long DAILY_WORKING_MINUTES_COUNT;

    @Value("${DELAY_PER_RESERVATION_IN_MINUTES}")
    private long DELAY_PER_RESERVATION_IN_MINUTES;

    private final LocalTime DAILY_RESERVATION_INIT_LOCAL_TIME =
            LocalTime.of(9, 0, LocalTime.now().getSecond());

    private List<TimeSlot> timeSlotList;

    @PostConstruct
    public void createSchedule() {
        timeSlotList = ScheduleGenerator.reservationTestList(
                DAILY_RESERVATION_INIT_LOCAL_TIME,
                DELAY_PER_RESERVATION_IN_MINUTES,
                DAILY_WORKING_MINUTES_COUNT / DELAY_PER_RESERVATION_IN_MINUTES
        );
    }

    public List<TimeSlot> getReservationTestList() {
        return timeSlotList;
    }

}
