package ru.dikanskiy.exam.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.dikanskiy.exam.persistance.entities.Person;
import ru.dikanskiy.exam.persistance.entities.Reservation;
import ru.dikanskiy.exam.services.PersonService;
import ru.dikanskiy.exam.services.ReservationService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/api/v1/")
public class PersonController {

    private final ReservationService reservationService;

    private final PersonService personService;

    @Autowired
    public PersonController(ReservationService reservationService, PersonService personService) {
        this.reservationService = reservationService;
        this.personService = personService;
    }

    @GetMapping("/me")
    public Authentication getPerson() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/reservations")
    public List<Reservation> getReservationList() {
        return reservationService.findAllAvailable();
    }

    @GetMapping("/me/reservations")
    public List<Reservation> getMyReservationList() {
        return reservationService.findByPersonUsername();
    }

    @PostMapping("/me/reservations/add/{id}")
    public Reservation addReservation(@PathVariable(name = "id") String id) {
        return personService.addReservation(id);
    }

    @PutMapping("/me/reservations/confirm")
    public Reservation confirmReservation(@RequestParam(name = "id") String id) {
        return reservationService.confirm(id);
    }

}
