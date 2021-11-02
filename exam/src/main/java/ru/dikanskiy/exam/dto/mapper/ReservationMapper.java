package ru.dikanskiy.exam.dto.mapper;

import ru.dikanskiy.exam.dto.ReservationDTO;
import ru.dikanskiy.exam.persistance.entities.Reservation;

public class ReservationMapper {

    public static ReservationDTO toReservationDTO(Reservation reservation) {
        return new ReservationDTO(reservation.getId(), reservation.getName(), reservation.getReservationTime());
    }

}
