package ru.dikanskiy.exam.services.implementations;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import ru.dikanskiy.exam.core.Schedule;
import ru.dikanskiy.exam.core.entities.TimeSlot;
import ru.dikanskiy.exam.dto.ReservationDTO;
import ru.dikanskiy.exam.dto.mapper.ReservationMapper;
import ru.dikanskiy.exam.exceptions.ReservationNotFoundException;
import ru.dikanskiy.exam.persistance.entities.Reservation;
import ru.dikanskiy.exam.persistance.repositories.ReservationRepository;
import ru.dikanskiy.exam.services.ReservationService;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationServiceImplementation implements ReservationService {

    @Getter
    private final ReservationRepository reservationRepository;

    private final Schedule schedule;

    public List<ReservationDTO> findAll() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream().map(ReservationMapper::toReservationDTO).collect(Collectors.toList());
    }

    public Reservation getById(UUID id) {
        return reservationRepository.getById(id);
    }

    public List<ReservationDTO> findAllAvailable() {
        List<Reservation> reservations = reservationRepository
                .findAll()
                .stream()
                .filter(Reservation::isAvailable)
                .collect(Collectors.toList());
        return reservations.stream().map(ReservationMapper::toReservationDTO).collect(Collectors.toList());
    }

    public List<ReservationDTO> findByPersonUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<Reservation> reservations = reservationRepository.findByPersonUsername(username);
        return reservations.stream().map(ReservationMapper::toReservationDTO).collect(Collectors.toList());
    }

    public List<ReservationDTO> findByPersonUsername2(String username) {
        List<Reservation> reservations = reservationRepository.findByPersonUsername(username);
        return reservations.stream().map(ReservationMapper::toReservationDTO).collect(Collectors.toList());
    }

    public Optional<ReservationDTO> findReservationOrderByDateASC() {
        Sort sort = Sort.by(Sort.Direction.ASC, "reservationTime");
        final List<Reservation> reservationList = reservationRepository.findAll(sort);
        Optional<ReservationDTO> reservation = reservationList
                .stream().filter(Reservation::isValid)
                .findFirst()
                .map(ReservationMapper::toReservationDTO);
        if (reservation.isPresent()) {
            return reservation;
        } else {
            throw new ReservationNotFoundException();
        }
    }

    @Transactional
    public Reservation save(@RequestBody Reservation reservation) {

        List<TimeSlot> timeSlotList = schedule.getReservationTestList();

        Optional<TimeSlot> current = timeSlotList.stream()
                .filter(timeSlot -> timeSlot.getReservationTime()
                        .equals(reservation.getReservationTime())).findAny();

        if (current.isPresent()) {
            Reservation currentReservation = new Reservation(
                    new Date(new java.util.Date().getTime()),
                    reservation.getReservationTime(),
                    true,
                    false,
                    false,
                    null);
            currentReservation.setName(reservation.getName());
            return reservationRepository.save(currentReservation);

        } else {
            throw new ReservationNotFoundException();
        }
    }

    @Transactional
    public Reservation confirm(UUID id) {
        if (reservationRepository.existsById(id)) {
            Reservation currentReservation = reservationRepository.getById(id);
            currentReservation.setValid(true);
            return reservationRepository.save(currentReservation);
        } else {
            throw new ReservationNotFoundException();
        }
    }

    @Transactional
    public Reservation register(UUID id) {
        if (reservationRepository.existsById(id)) {
            Reservation currentReservation = reservationRepository.getById(id);
            currentReservation.setRegistered(true);
            return reservationRepository.save(currentReservation);
        } else {
            throw new ReservationNotFoundException();
        }
    }

    @Transactional
    public Reservation reset(UUID id) {
        if (reservationRepository.existsById(id)) {
            Reservation currentReservation = reservationRepository.getById(id);
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
    public void delete(UUID id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
        } else {
            throw new ReservationNotFoundException();
        }
    }

}
