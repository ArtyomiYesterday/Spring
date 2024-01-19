package com.example.Spring.controllers;


import com.example.Spring.models.Resume;
import com.example.Spring.models.User;
import com.example.Spring.models.Vacancy;
import com.example.Spring.models.VacancyRequest;
import com.example.Spring.services.UserService;
import com.example.Spring.services.VacancyService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/vacancies")
public class VacancyController {

    private final VacancyService vacancyService;

    private final UserService userService;

    @Autowired
    public VacancyController(VacancyService vacancyService,UserService userService) {
        this.vacancyService = vacancyService;
        this.userService = userService;
    }

    @GetMapping()
    public List<Vacancy> getVacancies(
            @RequestParam(value = "jobTitle", required = false) String jobTitle,
            @RequestParam(value = "location", required = false) String location) {
        if (jobTitle != null && location != null) {
            return vacancyService.getVacanciesByJobTitleAndLocation(jobTitle, location);
        } else if (jobTitle != null) {
            return vacancyService.getVacanciesByJobTitle(jobTitle);
        } else if (location != null) {
            return vacancyService.getVacanciesByLocation(location);
        } else {
            return vacancyService.getVacancies();
        }
    }

    @GetMapping("/myVacancies")
    public List<Vacancy> getVacanciesByUserId(@AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = userService.getUserByUsername(userDetails.getUsername());
        return vacancyService.getVacanciesByUserId(currentUser.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vacancy> getVacancyById(@PathVariable Long id) {
        Vacancy vacancy = vacancyService.getVacancyById(id);
        if (vacancy != null) {
            return ResponseEntity.ok(vacancy);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vacancy> createResume(@RequestBody VacancyRequest vacancyRequest) {
        User currentUser = UserController.getCurrentUser();

        Vacancy vacancy = new Vacancy();
        vacancy.setJobTitle(vacancyRequest.getJobTitle());
        vacancy.setLocation(vacancyRequest.getLocation());
        vacancy.setDescription(vacancyRequest.getDescription());
        vacancy.setImg(vacancyRequest.getImg());
        vacancy.setSalary(vacancyRequest.getSalary());
        vacancy.setUser(currentUser);
        // Сохраните объект Resume в базе данных
        Vacancy savedVacancy=vacancyService.addNewVacancy(vacancy);

        return ResponseEntity.ok(savedVacancy);
    }

    @PostMapping("/{vacancyId}/apply")
    public ResponseEntity<String> applyToVacancy(@PathVariable Long vacancyId, @AuthenticationPrincipal UserDetails userDetails) {
        Vacancy vacancy = vacancyService.getVacancyById(vacancyId);

        if (vacancy == null) {
            return ResponseEntity.notFound().build();
        }

        User currentUser = userService.getUserByUsername(userDetails.getUsername());


        Resume resume = currentUser.getResume();



        vacancy.getApplicants().add(resume);
        vacancyService.addNewVacancy(vacancy);

        return ResponseEntity.ok("Резюме успешно добавлено");
    }

    @GetMapping("/{vacancyId}/applicants")
    public List<Resume> getApplicantsByVacancyId(@PathVariable Long vacancyId) {
        return vacancyService.getApplicantsByVacancyId(vacancyId);
    }

    @DeleteMapping("/{vacancyId}")
    public ResponseEntity<String> deleteVacancy(@PathVariable Long vacancyId) {
        vacancyService.deleteVacancyById(vacancyId);
        return new ResponseEntity<>("Vacancy deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{vacancyId}")
    public ResponseEntity<String> updateVacancy(@PathVariable Long vacancyId, @RequestBody VacancyRequest vacancyRequest) {
        try {
            vacancyService.updateVacancy(vacancyId, vacancyRequest);
            return ResponseEntity.ok("Vacancy updated successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping("/{vacancyId}/applicants/{resumeId}")
    public ResponseEntity<String> deleteApplicantFromVacancy(
            @PathVariable Long vacancyId,
            @PathVariable Long resumeId
    ) {
        vacancyService.deleteByResumeIdAndVacancyId(resumeId, vacancyId);
        return new ResponseEntity<>("Applicant deleted from vacancy", HttpStatus.OK);
    }



}
