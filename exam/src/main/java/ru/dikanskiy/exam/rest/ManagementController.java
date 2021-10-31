package ru.dikanskiy.exam.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dikanskiy.exam.core.Schedule;
import ru.dikanskiy.exam.core.entities.TimeSlot;
import ru.dikanskiy.exam.dto.PersonDTO;
import ru.dikanskiy.exam.dto.ReservationDTO;
import ru.dikanskiy.exam.persistance.entities.Reservation;
import ru.dikanskiy.exam.services.implementations.PersonServiceImplementation;
import ru.dikanskiy.exam.services.implementations.ReservationServiceImplementation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/admin/management/api/v1/")
@RequiredArgsConstructor
public class ManagementController {

    private final Schedule schedule;

    private final PersonServiceImplementation personServiceImplementation;

    private final ReservationServiceImplementation reservationServiceImplementation;

    @GetMapping("/persons")
    public List<PersonDTO> personList() {
        return personServiceImplementation.findAll();
    }

    @GetMapping("/reservations")
    public List<ReservationDTO> reservationList() {
        return reservationServiceImplementation.findAll();
    }

    @GetMapping("/reservations/schedule")
    public List<TimeSlot> schedule() {
        return schedule.getReservationTestList();
    }

    @GetMapping("/reservations/actual")
    public Optional<ReservationDTO> reservationListSortedByDateASC() {
        return reservationServiceImplementation.findReservationOrderByDateASC();
    }

    @PutMapping("/reservations/register/{id}")
    public Reservation registerReservation(@PathVariable(name = "id") String id) {
        return reservationServiceImplementation.register(UUID.fromString(id));
    }

    @PutMapping("/reservations/reset/{id}")
    public Reservation resetReservation(@PathVariable(name = "id") String id) {
        return reservationServiceImplementation.reset(UUID.fromString(id));
    }

    @PostMapping("/reservations/add")
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationServiceImplementation.save(reservation);
    }

}
