package ru.dikanskiy.exam.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@AllArgsConstructor
@Getter
@Setter
public class TimeSlot {

    private LocalTime reservationTime;

}
