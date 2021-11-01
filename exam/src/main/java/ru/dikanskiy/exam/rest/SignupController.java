package ru.dikanskiy.exam.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dikanskiy.exam.dto.PersonDTO;
import ru.dikanskiy.exam.dto.mapper.PersonMapper;
import ru.dikanskiy.exam.persistance.entities.Person;
import ru.dikanskiy.exam.services.implementations.PersonServiceImplementation;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignupController {

    private final PersonServiceImplementation personServiceImplementation;

    @PostMapping
    public PersonDTO savePerson(@RequestBody Person person) {
        return PersonMapper.toPersonDTO(personServiceImplementation.save(person));
    }

}
