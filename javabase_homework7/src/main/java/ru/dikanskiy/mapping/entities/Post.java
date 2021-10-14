package ru.dikanskiy.mapping.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.dikanskiy.mapping.entities.base.Named;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "post")
@EqualsAndHashCode(callSuper = true)
public class Post extends Named {

    @NotEmpty(message = "Message must be not empty")
    @Column(name = "content", columnDefinition = "varchar(255)")
    private String content;

    @NotNull(message = "Person_id must be not null")
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "person_id", columnDefinition = "bigint")
    private Person person;

}
