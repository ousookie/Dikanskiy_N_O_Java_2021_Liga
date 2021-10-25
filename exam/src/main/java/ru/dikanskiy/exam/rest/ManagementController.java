package ru.dikanskiy.exam.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.dikanskiy.exam.persistance.entities.Person;
import ru.dikanskiy.exam.persistance.entities.Reservation;
import ru.dikanskiy.exam.services.PersonService;
import ru.dikanskiy.exam.services.ReservationService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/management/api/v1/")
public class ManagementController {

    private final PersonService personService;

    private final ReservationService reservationService;

    @Autowired
    public ManagementController(PersonService personService, ReservationService reservationService) {
        this.personService = personService;
        this.reservationService = reservationService;
    }

    @GetMapping("/persons")
    public List<Person> personList() {
        return personService.findAll();
    }

    @GetMapping("/reservations")
    public List<Reservation> reservationList() {
        return reservationService.findAll();
    }

    @GetMapping("/reservations/actual")
    public Optional<Reservation> reservationListSortedByDateASC() {
        return reservationService.findReservationOrderByDateASC();
    }

    @PutMapping("/reservations/register/{id}")
    public Reservation registerReservation(@PathVariable(name = "id") String id) {
        return reservationService.register(id);
    }

    @PutMapping("/reservations/reset/{id}")
    public Reservation resetReservation(@PathVariable(name = "id") String id) {
        return reservationService.reset(id);
    }

    @PostMapping("/reservations/add")
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.save(reservation);
    }

}
