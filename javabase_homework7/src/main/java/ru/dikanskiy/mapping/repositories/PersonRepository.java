package ru.dikanskiy.mapping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dikanskiy.mapping.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
