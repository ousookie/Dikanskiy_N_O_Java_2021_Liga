package ru.dikanskiy.exam.auth;

import ru.dikanskiy.exam.persistance.entities.Person;

import java.util.List;
import java.util.Optional;

public interface PersonDAO {

    List<Person> getPersons();

    Optional<Person> getPersonByUsername(String username);

}
