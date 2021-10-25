package ru.dikanskiy.exam.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.dikanskiy.exam.persistance.entities.Person;
import ru.dikanskiy.exam.services.PersonService;

@RestController
@RequestMapping("/signup")
public class PersonRegistrationController {

    private final PersonService personService;

    @Autowired
    public PersonRegistrationController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public Person savePerson(@RequestBody Person person) {
        return personService.save(person);
    }

}
