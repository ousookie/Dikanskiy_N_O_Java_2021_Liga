package ru.dikanskiy.mapping.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.dikanskiy.mapping.entities.Post;
import ru.dikanskiy.mapping.services.PostService;

import javax.validation.Valid;

@Controller
@RequestMapping("/posts")
public class PostController {

    PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String getPostList(Model model) {
        postService.findAllView(model);
        return "posts";
    }

    @GetMapping("/{id}")
    public String getPost(@PathVariable Long id,
                          Model model) {
        postService.findPostByIdView(id, model);
        return "post";
    }

    @GetMapping("/new")
    public String getPostCreateForm(@ModelAttribute(name = "post") Post post) {
        return "new_post";
    }

    @PostMapping("/new")
    public String createPost(@Valid Post post,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new_post";
        }
        postService.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/{id}/edit")
    public String getPostPatchForm(@PathVariable(name = "id") Long id,
                                   Model model) {
        postService.patchPostByIdView(id, model);
        return "edit_post";
    }

    @PatchMapping("/{id}/edit")
    public String patchPost(@PathVariable(name = "id") Long id,
                            @ModelAttribute @Valid Post post,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit_post";
        }
        postService.save(post);
        return "redirect:/posts";
    }

    @DeleteMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id) {
        postService.deletePostById(id);
        return "redirect:/posts";
    }

}
