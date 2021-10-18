package ru.dikanskiy.mapping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dikanskiy.mapping.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
