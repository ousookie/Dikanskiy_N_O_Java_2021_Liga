package ru.dikanskiy.exam.persistance.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.dikanskiy.exam.persistance.entities.base.Named;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reservation")
public class Reservation extends Named {

    @Column(name = "created_at", columnDefinition = "timestamp")
    private Date createdAt;

    @Column(name = "reservation_time", columnDefinition = "time")
    private LocalTime reservationTime;

    @Column(name = "is_available", columnDefinition = "boolean")
    private boolean available;

    @Column(name = "is_valid", columnDefinition = "boolean")
    private boolean valid;

    @Column(name = "is_registered", columnDefinition = "boolean")
    private boolean registered;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "person_id", columnDefinition = "uuid")
    private Person person;

}




