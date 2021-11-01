package ru.dikanskiy.exam.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dikanskiy.exam.dto.PersonDTO;
import ru.dikanskiy.exam.dto.ReservationDTO;
import ru.dikanskiy.exam.dto.mapper.PersonMapper;
import ru.dikanskiy.exam.dto.mapper.ReservationMapper;
import ru.dikanskiy.exam.exceptions.ForbiddenException;
import ru.dikanskiy.exam.persistance.entities.Person;
import ru.dikanskiy.exam.persistance.entities.Reservation;
import ru.dikanskiy.exam.services.implementations.PersonServiceImplementation;
import ru.dikanskiy.exam.services.implementations.ReservationServiceImplementation;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user/api/v1/")
@RequiredArgsConstructor
public class UserController {

    private final ReservationServiceImplementation reservationServiceImplementation;

    private final PersonServiceImplementation personServiceImplementation;

    @GetMapping("/persons/{id}")
    public PersonDTO personDTO(@PathVariable(name = "id") String id) {
        if (personServiceImplementation.isCurrentAuthenticationContainsRequestedPersonById(UUID.fromString(id))) {
            Person person = personServiceImplementation.getById(UUID.fromString(id));
            return PersonMapper.toPersonDTO(person);
        } else {
            throw new ForbiddenException();
        }
    }

    @PutMapping("/persons/edit/{id}")
    public Person personDTO(@PathVariable(name = "id") String id, @RequestBody Person person) {
        if (personServiceImplementation.isCurrentAuthenticationContainsRequestedPersonById(UUID.fromString(id))) {
            return personServiceImplementation.update(UUID.fromString(id), person);
        } else {
            throw new ForbiddenException();
        }
    }

    @GetMapping("/reservations")
    public List<ReservationDTO> getReservationList(@RequestParam(name = "page") Integer page,
                                                   @RequestParam(name = "reservationCount") Integer reservationCount) {
        return reservationServiceImplementation.findAllAvailablePageable(page, reservationCount);
    }

    @GetMapping("/persons/{id}/reservations")
    public List<ReservationDTO> reservationDTOList(@PathVariable(name = "id") String id,
                                                   @RequestParam(name = "page") Integer page,
                                                   @RequestParam(name = "reservationCount") Integer reservationCount) {
        if (personServiceImplementation.isCurrentAuthenticationContainsRequestedPersonById(UUID.fromString(id))) {
            Person person = personServiceImplementation.getById(UUID.fromString(id));
            return reservationServiceImplementation.findByPersonUsernamePageable(person.getUsername(), page, reservationCount);
        } else {
            throw new ForbiddenException();
        }
    }

    @GetMapping("/persons/{person_id}/reservations/{reservation_id}")
    public ReservationDTO reservationDTO(@PathVariable(name = "person_id") String person_id,
                                         @PathVariable(name = "reservation_id") String reservation_id) {
        if (personServiceImplementation.isCurrentAuthenticationContainsRequestedPersonById(UUID.fromString(person_id))) {
            Reservation reservation = reservationServiceImplementation.getById(UUID.fromString(reservation_id));
            return ReservationMapper.toReservationDTO(reservation);
        } else {
            throw new ForbiddenException();
        }
    }

    @PostMapping("/persons/{person_id}/reservations/add/{reservation_id}")
    public Reservation addReservation(@PathVariable(name = "person_id") String person_id,
                                      @PathVariable(name = "reservation_id") String reservation_id) {
        if (personServiceImplementation.isCurrentAuthenticationContainsRequestedPersonById(UUID.fromString(person_id))) {
            return personServiceImplementation.addReservation(UUID.fromString(reservation_id));
        } else {
            throw new ForbiddenException();
        }
    }

    @PostMapping("/persons/{person_id}/reservations/confirm/{reservation_id}")
    public Reservation confirmReservation(@PathVariable(name = "person_id") String person_id,
                                          @PathVariable(name = "reservation_id") String reservation_id) {
        if (personServiceImplementation.isCurrentAuthenticationContainsRequestedPersonById(UUID.fromString(person_id))) {
            return reservationServiceImplementation.confirm(UUID.fromString(reservation_id));
        } else {
            throw new ForbiddenException();
        }
    }

}
