package ru.dikanskiy.exam.dto.mapper;

import ru.dikanskiy.exam.dto.PersonDTO;
import ru.dikanskiy.exam.persistance.entities.Person;

public class PersonMapper {

    public static PersonDTO toPersonDTO(Person person) {
        return new PersonDTO(person.getId(), person.getUsername());
    }

}
