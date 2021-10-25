package ru.dikanskiy.exam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dikanskiy.exam.exceptions.PersonNotFoundException;
import ru.dikanskiy.exam.persistance.entities.Person;
import ru.dikanskiy.exam.persistance.entities.Reservation;
import ru.dikanskiy.exam.persistance.repositories.PersonRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;

    private final ReservationService reservationService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder,
                         ReservationService reservationService) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
        this.reservationService = reservationService;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person getById(String id) {
        if (personRepository.existsById(Long.valueOf(id))) {
            return personRepository.getById(Long.valueOf(id));
        } else {
            throw new PersonNotFoundException();
        }
    }

    public Optional<Person> findPersonByUsername(String username) {
        return personRepository.findPersonByUsername(username);
    }

    @Transactional
    public Person save(Person person) {
        person.setCreatedAt(new Date(new java.util.Date().getTime()));
        person.setAuthority("USER");
        person.setEnabled(true);
        person.setCredentialsNonExpired(true);
        person.setAccountNonLocked(true);
        person.setAccountNonExpired(true);
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }

    @Transactional
    public Reservation addReservation(final String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<Person> person = findPersonByUsername(username);
        Reservation reservation = reservationService.getById(id);
        reservation.setPerson(person.get());
        reservation.setAvailable(false);
        return reservationService.getReservationRepository().save(reservation);
    }

    @Transactional
    public Person update(String id, Person person) {
        Person currentPerson = null;
        if (personRepository.existsById(Long.valueOf(id))) {
            currentPerson = personRepository.getById(Long.valueOf(id));
            currentPerson.setUsername(person.getUsername());
            currentPerson.setPassword(person.getPassword());
        } else {
            throw new PersonNotFoundException();
        }
        return personRepository.save(currentPerson);
    }

    @Transactional
    public void delete(String id) {
        if (personRepository.existsById(Long.valueOf(id))) {
            personRepository.deleteById(Long.valueOf(id));
        } else {
            throw new PersonNotFoundException();
        }
    }

}
