package ru.dikanskiy.mapping.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import ru.dikanskiy.mapping.entities.School;
import ru.dikanskiy.mapping.repositories.SchoolRepository;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class SchoolService {

    SchoolRepository schoolRepository;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public void findAllView(Model model) {
        model.addAttribute("schools", findAll());
    }

    public void findSchoolByIdView(Long id, Model model) {
        model.addAttribute("school", findSchoolById(id));
    }

    @Transactional
    public void save(School school) {
        schoolRepository.save(school);
    }

    public void patchSchoolByIdView(Long id, Model model) {

        School currentSchool = null;

        if (findSchoolById(id).isPresent()) {
            currentSchool = findSchoolById(id).get();
        }

        model.addAttribute("school", currentSchool);
    }

    @Transactional
    public void deleteSchoolById(Long id) {
        schoolRepository.deleteById(id);
    }

    Optional<School> findSchoolById(Long id) {
        return schoolRepository.findById(id);
    }

    List<School> findAll() {
        return schoolRepository.findAll();
    }

}

