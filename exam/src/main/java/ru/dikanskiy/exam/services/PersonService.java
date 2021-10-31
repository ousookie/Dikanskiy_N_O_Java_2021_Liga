package ru.dikanskiy.exam.services;

import ru.dikanskiy.exam.persistance.entities.Person;
import ru.dikanskiy.exam.persistance.entities.Reservation;

import java.util.Optional;
import java.util.UUID;

public interface PersonService {

    Person getById(UUID id);

    Optional<Person> findPersonByUsername(String username);

    Person save(Person person);

    Reservation addReservation(UUID id);

    Person update(UUID id, Person person);

    void delete(UUID id);

}
