package ru.dikanskiy.exam.services;

import ru.dikanskiy.exam.dto.ReservationDTO;
import ru.dikanskiy.exam.persistance.entities.Reservation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservationService {

    List<ReservationDTO> findAll();

    Reservation getById(UUID id);

    List<ReservationDTO> findAllAvailable();

    List<ReservationDTO> findByPersonUsername();

    Optional<ReservationDTO> findReservationOrderByDateASC();

    Reservation save(Reservation reservation);

    Reservation confirm(UUID id);

    Reservation register(UUID id);

    Reservation reset(UUID id);

    void delete(UUID id);

}
