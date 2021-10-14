package ru.dikanskiy.mapping.entities.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class Named extends Identifiable {

    @NotEmpty(message = "Name must be not empty")
    private String name;

}
