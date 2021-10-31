package ru.dikanskiy.exam.core.util;

import ru.dikanskiy.exam.core.entities.TimeSlot;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public final class ScheduleGenerator {

    private ScheduleGenerator() {
    }

    public static List<TimeSlot> reservationTestList(LocalTime startTime,
                                                     Long ReservationTimeInMinutes,
                                                     Long reservationCount) {

        List<TimeSlot> timeSlots = new ArrayList<>();

        for (int i = 0; i <= reservationCount; i++) {

            LocalTime localTime = startTime;

            TimeSlot timeSlot = new TimeSlot(localTime);

            timeSlot.setReservationTime(timeSlot.getReservationTime().truncatedTo(ChronoUnit.SECONDS));

            timeSlots.add(timeSlot);

            startTime = localTime.plus(ReservationTimeInMinutes, ChronoUnit.MINUTES);

        }
        return timeSlots;
    }

}
