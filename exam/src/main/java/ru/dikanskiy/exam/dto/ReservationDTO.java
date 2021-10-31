package ru.dikanskiy.exam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@Setter
@Getter
public class ReservationDTO {

    private UUID id;

    private String name;

    private LocalTime reservationDate;

}
