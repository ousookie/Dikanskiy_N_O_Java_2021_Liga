package ru.dikanskiy.mapping.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import ru.dikanskiy.mapping.entities.Person;
import ru.dikanskiy.mapping.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional(readOnly = true)
    public void findAllView(Model model) {
        model.addAttribute("people", findAll());
    }

    @Transactional(readOnly = true)
    public void findPersonByIdView(Long id, Model model) {
        model.addAttribute("person", findPersonById(id));
    }

    @Transactional
    public void save(Person person) {
        personRepository.save(person);
    }

    @Transactional(readOnly = true)
    public void patchPersonByIdView(Long id, Model model) {

        Person currentPerson = null;

        if (findPersonById(id).isPresent()) {
            currentPerson = findPersonById(id).get();
        }

        model.addAttribute("person", currentPerson);
    }

    @Transactional
    public void deletePersonById(Long id) {
        personRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    List<Person> findAll() {
        return personRepository.findAll();
    }

    @Transactional(readOnly = true)
    Optional<Person> findPersonById(Long id) {
        return personRepository.findById(id);
    }

}
