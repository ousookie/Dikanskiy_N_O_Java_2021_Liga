package ru.dikanskiy.exam.persistance.entities.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class Named extends Identifiable {

    @Column(name = "name", columnDefinition = "varchar(255)")
    protected String name;

}
