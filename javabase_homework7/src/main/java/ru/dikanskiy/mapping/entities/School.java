package ru.dikanskiy.mapping.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.dikanskiy.mapping.entities.base.Named;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "school")
@EqualsAndHashCode(callSuper = true)
public class School extends Named {

    @NotEmpty(message = "School address must be not null")
    @Column(name = "address", columnDefinition = "varchar(255)")
    private String address;

}
