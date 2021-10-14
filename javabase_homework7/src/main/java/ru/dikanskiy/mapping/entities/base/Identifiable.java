package ru.dikanskiy.mapping.entities.base;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

}
