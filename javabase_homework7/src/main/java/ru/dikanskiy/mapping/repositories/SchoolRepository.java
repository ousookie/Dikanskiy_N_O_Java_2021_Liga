package ru.dikanskiy.mapping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dikanskiy.mapping.entities.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
}
