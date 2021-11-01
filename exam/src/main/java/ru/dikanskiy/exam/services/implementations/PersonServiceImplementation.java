package ru.dikanskiy.exam.services.implementations;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dikanskiy.exam.dto.PersonDTO;
import ru.dikanskiy.exam.dto.mapper.PersonMapper;
import ru.dikanskiy.exam.exceptions.PersonNotFoundException;
import ru.dikanskiy.exam.persistance.entities.Person;
import ru.dikanskiy.exam.persistance.entities.Reservation;
import ru.dikanskiy.exam.persistance.repositories.PersonRepository;
import ru.dikanskiy.exam.services.PersonService;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PersonServiceImplementation implements PersonService {

    private final PersonRepository personRepository;

    private final ReservationServiceImplementation reservationServiceImplementation;

    private final PasswordEncoder passwordEncoder;

    public List<PersonDTO> findAllPageable(Integer page, Integer personsCount) {
        Pageable pageable = PageRequest.of(page, personsCount);
        return personRepository.findAll(pageable).map(PersonMapper::toPersonDTO).getContent();
    }

    public Person getById(final UUID id) {
        if (personRepository.existsById(id)) {
            return personRepository.getById(id);
        } else {
            throw new PersonNotFoundException();
        }
    }

    public Optional<Person> findPersonByUsername(final String username) {
        return personRepository.findPersonByUsername(username);
    }

    @Transactional
    public Person save(Person person) {
        Person currentPerson = new Person(
                person.getUsername(),
                passwordEncoder.encode(person.getPassword()),
                new Date(new java.util.Date().getTime()),
                "USER",
                true,
                true,
                true,
                true);
        return personRepository.save(currentPerson);
    }

    @Transactional
    public Reservation addReservation(final UUID id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<Person> person = findPersonByUsername(username);
        Reservation reservation = reservationServiceImplementation.getById(id);
        if (person.isPresent()) {
            reservation.setPerson(person.get());
            reservation.setAvailable(false);
            return reservationServiceImplementation.getReservationRepository().save(reservation);
        } else {
            throw new PersonNotFoundException();
        }
    }

    @Transactional
    public Person update(final UUID id, Person person) {
        if (personRepository.existsById(id)) {
            Person currentPerson = personRepository.getById(id);
            currentPerson.setUsername(person.getUsername());
            currentPerson.setPassword(person.getPassword());
            return personRepository.save(currentPerson);
        } else {
            throw new PersonNotFoundException();
        }
    }

    @Transactional
    public void delete(final UUID id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
        } else {
            throw new PersonNotFoundException();
        }
    }

    public boolean isCurrentAuthenticationContainsRequestedPersonById(final UUID id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<Person> person = findPersonByUsername(username);
        return person.map(value -> value.getId().equals(id)).orElse(false);
    }

}
