package ru.dikanskiy.mapping.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.dikanskiy.mapping.entities.School;
import ru.dikanskiy.mapping.services.SchoolService;

import javax.validation.Valid;

@Controller
@RequestMapping("/schools")
public class SchoolController {

    SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping
    public String getSchoolList(Model model) {
        schoolService.findAllView(model);
        return "schools";
    }

    @GetMapping("/{id}")
    public String getSchool(@PathVariable(name = "id") Long id, Model model) {
        schoolService.findSchoolByIdView(id, model);
        return "school";
    }

    @GetMapping("/new")
    public String getSchoolCreateForm(@ModelAttribute(name = "school") School school) {
        return "new_school";
    }

    @PostMapping("/new")
    public String createSchool(@Valid School school,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new_school";
        }
        schoolService.save(school);
        return "redirect:/schools";
    }

    @GetMapping("/{id}/edit")
    public String getSchoolPatchForm(@PathVariable(name = "id") Long id,
                                     Model model) {
        schoolService.patchSchoolByIdView(id, model);
        return "edit_school";
    }

    @PatchMapping("/{id}/edit")
    public String patchSchool(@PathVariable(name = "id") Long id,
                              @ModelAttribute @Valid School school,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit_school";
        }
        schoolService.save(school);
        return "redirect:/schools";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteSchool(@PathVariable Long id) {
        schoolService.deleteSchoolById(id);
        return "redirect:/schools";
    }

}
