package ru.dikanskiy.exam.persistance.entities.base;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class Identifiable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(generator = "MyUUIDGenerator")
    @GenericGenerator(name = "MyUUIDGenerator", strategy = "ru.dikanskiy.exam.util.MyUUIDGenerator")
    protected UUID id;

}
