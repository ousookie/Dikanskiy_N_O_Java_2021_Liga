package ru.dikanskiy.exam.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import ru.dikanskiy.exam.exceptions.ReservationNotFoundException;
import ru.dikanskiy.exam.persistance.entities.Reservation;
import ru.dikanskiy.exam.persistance.repositories.ReservationRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ReservationService {

    @Getter
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Reservation getById(String id) {
        return reservationRepository.getById(Long.valueOf(id));
    }

    public List<Reservation> findAllAvailable() {
        return reservationRepository.findAll().stream().filter(Reservation::isAvailable).collect(Collectors.toList());
    }

    public List<Reservation> findByPersonUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return reservationRepository.findByPersonUsername(username);
    }

    public Optional<Reservation> findReservationOrderByDateASC() {
        Sort sort = Sort.by(Sort.Direction.ASC, "reservationTime");
        final List<Reservation> reservationList = reservationRepository.findAll(sort);
        Optional<Reservation> reservation = reservationList.stream().filter(Reservation::isValid).findFirst();
        if (reservation.isPresent()) {
            return reservation;
        } else {
            throw new ReservationNotFoundException();
        }
    }

    @Transactional
    public Reservation save(@RequestBody Reservation reservation) {
        reservation.setCreatedAt(new Date(new Date().getTime()));
        reservation.setAvailable(true);
        reservation.setValid(false);
        reservation.setRegistered(false);
        reservation.setPerson(null);
        return reservationRepository.save(reservation);
    }

    @Transactional
    public Reservation confirm(String id) {
        if (reservationRepository.existsById(Long.valueOf(id))) {
            Reservation currentReservation = reservationRepository.getById(Long.valueOf(id));
            currentReservation.setValid(true);
            return reservationRepository.save(currentReservation);
        } else {
            throw new ReservationNotFoundException();
        }
    }

    @Transactional
    public Reservation register(String id) {
        if (reservationRepository.existsById(Long.valueOf(id))) {
            Reservation currentReservation = reservationRepository.getById(Long.valueOf(id));
            currentReservation.setRegistered(true);
            return reservationRepository.save(currentReservation);
        } else {
            throw new ReservationNotFoundException();
        }
    }

    @Transactional
    public Reservation reset(String id) {
        if (reservationRepository.existsById(Long.valueOf(id))) {
            Reservation currentReservation = reservationRepository.getById(Long.valueOf(id));
            currentReservation.setPerson(null);
            currentReservation.setAvailable(true);
            currentReservation.setRegistered(false);
            currentReservation.setValid(false);
            return reservationRepository.save(currentReservation);
        } else {
            throw new ReservationNotFoundException();
        }
    }

    @Transactional
    public void delete(String id) {
        if (reservationRepository.existsById(Long.valueOf(id))) {
            reservationRepository.deleteById(Long.valueOf(id));
        } else {
            throw new ReservationNotFoundException();
        }
    }

}
