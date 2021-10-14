package ru.dikanskiy.mapping.entities;

import lombok.*;
import ru.dikanskiy.mapping.entities.base.Named;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "person")
@EqualsAndHashCode(callSuper = true)
public class Person extends Named {

    @OneToMany(fetch = FetchType.LAZY)
    private List<Person> friend;

    @Email(message = "Email is not valid")
    @Column(name = "email", columnDefinition = "varchar(255)")
    private String email;

    @NotNull(message = "School_id must be not null")
    @OneToOne
    @JoinColumn(name = "school_id", columnDefinition = "bigint")
    private School school;

}
