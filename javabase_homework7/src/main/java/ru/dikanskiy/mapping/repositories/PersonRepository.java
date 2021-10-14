package ru.dikanskiy.mapping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dikanskiy.mapping.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
