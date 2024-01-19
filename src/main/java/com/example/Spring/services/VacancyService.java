package com.example.Spring.services;

import com.example.Spring.models.Resume;
import com.example.Spring.models.Vacancy;
import com.example.Spring.models.VacancyRequest;
import com.example.Spring.repositories.VacancyRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacancyService {
    private final VacancyRepository vacancyRepository;

    @Autowired
    public VacancyService(VacancyRepository vacancyRepository) {
        this.vacancyRepository = vacancyRepository;
    }

    public List<Vacancy> getVacancies() {
        return vacancyRepository.findAll();
    }

    public List<Vacancy> getVacanciesByLocation(String location) {
        return vacancyRepository.findByLocation(location);
    }

    public List<Vacancy> getVacanciesByJobTitle(String jobTitle) {
        return vacancyRepository.findByJobTitle(jobTitle);
    }

    public List<Vacancy> getVacanciesByJobTitleAndLocation(String jobTitle, String location) {
        return vacancyRepository.findByJobTitleAndLocation(jobTitle, location);
    }

    public Vacancy addNewVacancy(Vacancy vacancy) {
        vacancyRepository.save(vacancy);
        return vacancy;
    }

    public Vacancy getVacancyById(Long id) {
        return vacancyRepository.findById(id).orElse(null);
    }

    public List<Vacancy> getVacanciesByUserId(Long id) {
        return vacancyRepository.findByUserId(id);
    }

    public List<Resume> getApplicantsByVacancyId(Long vacancyId) {
        return vacancyRepository.findApplicantsByVacancyId(vacancyId);
    }

    public void deleteVacancyById(Long vacancyId) {
        vacancyRepository.deleteById(vacancyId);
    }

    public void updateVacancy(Long vacancyId, VacancyRequest vacancyRequest) {
        Vacancy vacancy = vacancyRepository.findById(vacancyId)
                .orElseThrow(() -> new EntityNotFoundException("Vacancy not found with ID: " + vacancyId));

        vacancy.setJobTitle(vacancyRequest.getJobTitle());
        vacancy.setLocation(vacancyRequest.getLocation());
        vacancy.setSalary(vacancyRequest.getSalary());
        vacancy.setDescription(vacancyRequest.getDescription());

        vacancyRepository.save(vacancy);
    }
    @Transactional
    public void deleteByResumeIdAndVacancyId(Long resumeId, Long vacancyId) {
        vacancyRepository.deleteApplicantByVacancyIdAndResumeId(resumeId, vacancyId);
    }
}