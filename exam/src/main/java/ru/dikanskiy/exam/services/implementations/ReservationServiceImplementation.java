package ru.dikanskiy.exam.services.implementations;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public List<ReservationDTO> findAllPageable(Integer page, Integer reservationCount) {
        Pageable pageable = PageRequest.of(page, reservationCount);
        return reservationRepository.findAll(pageable).map(ReservationMapper::toReservationDTO).getContent();
    }

    public Reservation getById(final UUID id) {
        return reservationRepository.getById(id);
    }

    public List<ReservationDTO> findAllAvailablePageable(Integer page, Integer reservationCount) {
        Pageable pageable = PageRequest.of(page, reservationCount);
        return reservationRepository.findByAvailableTrue(pageable).map(ReservationMapper::toReservationDTO).getContent();
    }

    public List<ReservationDTO> findByPersonUsernamePageable(final String username, Integer page, Integer reservationCount) {
        Pageable pageable = PageRequest.of(page, reservationCount);
        return reservationRepository.findByPersonUsername(username, pageable)
                .map(ReservationMapper::toReservationDTO)
                .getContent();
    }

    public Optional<ReservationDTO> findReservationOrderByDateAsc() {
        List<Reservation> reservations = reservationRepository.findReservationByValidTrueOrderByReservationTimeAsc();
        Optional<Reservation> reservationOptional = reservations.stream().findFirst();
        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            return Optional.of(ReservationMapper.toReservationDTO(reservation));
        } else {
            throw new ReservationNotFoundException();
        }
    }

    @Transactional
    public Reservation save(@RequestBody Reservation reservation) {

        List<TimeSlot> timeSlotList = schedule.getReservationTestList();
        List<ReservationDTO> reservations = findAll().stream()
                .map(ReservationMapper::toReservationDTO)
                .collect(Collectors.toList());

        Optional<TimeSlot> current = timeSlotList.stream()
                .filter(timeSlot -> timeSlot.getReservationTime()
                        .equals(reservation.getReservationTime())).findAny();

        Optional<ReservationDTO> reservationDTOOptional
                = reservations.stream().filter(reservationDTO -> reservationDTO.getReservationDate()
                .equals(current.get().getReservationTime())).findAny();

        if (current.isPresent() && reservationDTOOptional.isEmpty()) {
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
    public Reservation confirm(final UUID id) {
        if (reservationRepository.existsById(id)) {
            Reservation currentReservation = reservationRepository.getById(id);
            currentReservation.setValid(true);
            return reservationRepository.save(currentReservation);
        } else {
            throw new ReservationNotFoundException();
        }
    }

    @Transactional
    public Reservation register(final UUID id) {
        if (reservationRepository.existsById(id)) {
            Reservation currentReservation = reservationRepository.getById(id);
            currentReservation.setRegistered(true);
            return reservationRepository.save(currentReservation);
        } else {
            throw new ReservationNotFoundException();
        }
    }

    @Transactional
    public Reservation reset(final UUID id) {
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
    public void delete(final UUID id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
        } else {
            throw new ReservationNotFoundException();
        }
    }

}
