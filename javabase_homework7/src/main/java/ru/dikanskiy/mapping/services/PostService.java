package ru.dikanskiy.mapping.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import ru.dikanskiy.mapping.entities.Post;
import ru.dikanskiy.mapping.repositories.PostRepository;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class PostService {

    PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void findAllView(Model model) {
        model.addAttribute("posts", findAll());
    }

    public void findPostByIdView(Long id, Model model) {
        model.addAttribute("post", findPostById(id));
    }

    @Transactional
    public void save(Post post) {
        postRepository.save(post);
    }

    public void patchPostByIdView(Long id, Model model) {

        Post currentPost = null;

        if (findPostById(id).isPresent()) {
            currentPost = findPostById(id).get();
        }

        model.addAttribute("post", currentPost);
    }

    @Transactional
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }

    List<Post> findAll() {
        return postRepository.findAll();
    }

    Optional<Post> findPostById(Long id) {
        return postRepository.findById(id);
    }

}
