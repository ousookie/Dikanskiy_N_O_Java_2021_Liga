package ru.dikanskiy.exam.persistance.entities.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class Named extends Identifiable {

    @Column(name = "name", columnDefinition = "varchar(255)")
    protected String name;

}
