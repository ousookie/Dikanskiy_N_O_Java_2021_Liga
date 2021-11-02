package ru.dikanskiy.exam.services;

import org.springframework.data.domain.Page;
import ru.dikanskiy.exam.dto.ReservationDTO;
import ru.dikanskiy.exam.persistance.entities.Reservation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservationService {

    List<Reservation> findAll();

    Page<ReservationDTO> findAllPageable(Integer page, Integer reservationCount);

    Reservation getById(final UUID id);

    Page<ReservationDTO> findAllAvailablePageable(Integer page, Integer reservationCount);

    Page<ReservationDTO> findByPersonUsernamePageable(final String username, Integer page, Integer reservationCount);

    Optional<ReservationDTO> findReservationOrderByDateAsc();

    Reservation save(Reservation reservation);

    Reservation confirm(final UUID id);

    Reservation register(final UUID id);

    Reservation reset(final UUID id);

    void delete(final UUID id);

}
