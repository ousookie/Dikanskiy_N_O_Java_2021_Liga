package ru.dikanskiy.exam.services;

import ru.dikanskiy.exam.dto.ReservationDTO;
import ru.dikanskiy.exam.persistance.entities.Reservation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservationService {

    List<Reservation> findAll();

    List<ReservationDTO> findAllPageable(Integer page, Integer reservationCount);

    Reservation getById(final UUID id);

    List<ReservationDTO> findAllAvailablePageable(Integer page, Integer reservationCount);

    List<ReservationDTO> findByPersonUsernamePageable(final String username, Integer page, Integer reservationCount);

    Optional<ReservationDTO> findReservationOrderByDateAsc();

    Reservation save(Reservation reservation);

    Reservation confirm(final UUID id);

    Reservation register(final UUID id);

    Reservation reset(final UUID id);

    void delete(final UUID id);

}
