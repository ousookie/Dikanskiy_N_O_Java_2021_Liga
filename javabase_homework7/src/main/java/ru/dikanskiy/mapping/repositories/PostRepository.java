package ru.dikanskiy.mapping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dikanskiy.mapping.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
