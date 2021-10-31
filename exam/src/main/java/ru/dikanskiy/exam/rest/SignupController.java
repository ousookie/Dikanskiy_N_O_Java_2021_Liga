package ru.dikanskiy.exam.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.dikanskiy.exam.dto.PersonDTO;
import ru.dikanskiy.exam.persistance.entities.Person;
import ru.dikanskiy.exam.services.implementations.PersonServiceImplementation;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignupController {

    private final PersonServiceImplementation personServiceImplementation;

    @PostMapping
    public Person savePerson(@RequestBody Person person) {
        return personServiceImplementation.save(person);
    }

}
