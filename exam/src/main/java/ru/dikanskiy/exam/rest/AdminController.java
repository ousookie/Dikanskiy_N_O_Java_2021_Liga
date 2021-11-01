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
public class AdminController {

    private final Schedule schedule;

    private final PersonServiceImplementation personServiceImplementation;

    private final ReservationServiceImplementation reservationServiceImplementation;

    @GetMapping("/persons")
    public List<PersonDTO> personList(@RequestParam(name = "page") String page,
                                      @RequestParam(name = "personCount") String personCount) {
        return personServiceImplementation.findAllPageable(Integer.parseInt(page), Integer.parseInt(personCount));
    }

    @DeleteMapping("/persons/{id}")
    public void deletePerson(@PathVariable(name = "id") String id) {
        personServiceImplementation.delete(UUID.fromString(id));
    }

    @GetMapping("/reservations")
    public List<ReservationDTO> reservationPage(@RequestParam(name = "page") String page,
                                                @RequestParam(name = "reservationCount") String reservationCount) {
        return reservationServiceImplementation.findAllPageable(Integer.parseInt(page), Integer.parseInt(reservationCount));
    }

    @GetMapping("/reservations/actual")
    public Optional<ReservationDTO> reservationListSortedByDateAsc() {
        return reservationServiceImplementation.findReservationOrderByDateAsc();
    }

    @GetMapping("/reservations/schedule")
    public List<TimeSlot> schedule() {
        return schedule.getReservationTestList();
    }

    @PostMapping("/reservations/add")
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationServiceImplementation.save(reservation);
    }

    @PutMapping("/reservations/register/{id}")
    public Reservation registerReservation(@PathVariable(name = "id") String id) {
        return reservationServiceImplementation.register(UUID.fromString(id));
    }

    @PutMapping("/reservations/reset/{id}")
    public Reservation resetReservation(@PathVariable(name = "id") String id) {
        return reservationServiceImplementation.reset(UUID.fromString(id));
    }

    @DeleteMapping("/reservations/delete/{id}")
    public void deleteReservation(@PathVariable(name = "id") String id) {
        reservationServiceImplementation.delete(UUID.fromString(id));
    }

}
