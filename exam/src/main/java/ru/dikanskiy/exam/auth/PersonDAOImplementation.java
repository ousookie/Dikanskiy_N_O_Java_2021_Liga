package ru.dikanskiy.exam.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dikanskiy.exam.persistance.entities.Person;
import ru.dikanskiy.exam.persistance.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonDAOImplementation implements PersonDAO {

    final PersonRepository personRepository;

    @Autowired
    public PersonDAOImplementation(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    @Override
    public Optional<Person> getPersonByUsername(String username) {
        return personRepository.findPersonByUsername(username);
    }

}
