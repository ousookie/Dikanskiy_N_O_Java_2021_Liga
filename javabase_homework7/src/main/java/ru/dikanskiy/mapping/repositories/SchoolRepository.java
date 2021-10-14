package ru.dikanskiy.mapping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dikanskiy.mapping.entities.School;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
