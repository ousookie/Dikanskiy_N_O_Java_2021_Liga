package ru.dikanskiy.exam.services;

import ru.dikanskiy.exam.dto.PersonDTO;
import ru.dikanskiy.exam.persistance.entities.Person;
import ru.dikanskiy.exam.persistance.entities.Reservation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonService {

    List<PersonDTO> findAllPageable(Integer page, Integer reservationCount);

    Person getById(final UUID id);

    Optional<Person> findPersonByUsername(final String username);

    Person save(Person person);

    Reservation addReservation(final UUID id);

    Person update(final UUID id, Person person);

    void delete(final UUID id);

}
