package ru.dikanskiy.exam.persistance.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.dikanskiy.exam.persistance.entities.base.Named;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "reservation")
@EqualsAndHashCode(callSuper = true)
public class Reservation extends Named {

    @Column(name = "created_at", columnDefinition = "timestamp")
    private Date createdAt;

    @Column(name = "reservation_time", columnDefinition = "timestamp")
    private Date reservationTime;

    @Column(name = "is_available", columnDefinition = "boolean")
    private boolean isAvailable;

    @Column(name = "is_valid", columnDefinition = "boolean")
    private boolean isValid;

    @Column(name = "is_registered", columnDefinition = "boolean")
    private boolean isRegistered;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "person_id", columnDefinition = "bigint")
    private Person person;

}




