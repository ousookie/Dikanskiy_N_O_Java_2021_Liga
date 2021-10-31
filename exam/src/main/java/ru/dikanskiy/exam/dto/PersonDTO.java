package ru.dikanskiy.exam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class PersonDTO {

    private UUID id;

    private String username;

}
