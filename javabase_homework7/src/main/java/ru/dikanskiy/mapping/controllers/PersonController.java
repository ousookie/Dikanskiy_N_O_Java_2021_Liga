package ru.dikanskiy.mapping.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.dikanskiy.mapping.entities.Person;
import ru.dikanskiy.mapping.services.PersonService;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PersonController {

    PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String getPersonList(Model model) {
        personService.findAllView(model);
        return "people";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable Long id,
                            Model model) {
        personService.findPersonByIdView(id, model);
        return "person";
    }

    @GetMapping("/new")
    public String getPersonCreateForm(@ModelAttribute(name = "person") Person person) {
        return "new_person";
    }

    @PostMapping("/new")
    public String createPerson(@Valid Person person,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new_person";
        }
        personService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String getPersonPatchForm(@PathVariable(name = "id") Long id,
                                     Model model) {
        personService.patchPersonByIdView(id, model);
        return "edit_person";
    }

    @PatchMapping("/{id}/edit")
    public String patchPerson(@PathVariable(name = "id") Long id,
                              @ModelAttribute @Valid Person person,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit_person";
        }
        personService.save(person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}/delete")
    public String deletePerson(@PathVariable Long id) {
        personService.deletePersonById(id);
        return "redirect:/people";
    }

}
