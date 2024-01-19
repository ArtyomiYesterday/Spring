package com.example.Spring.controllers;

import com.example.Spring.models.Resume;
import com.example.Spring.models.ResumeCreateRequest;
import com.example.Spring.models.User;
import com.example.Spring.services.ResumeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {

    private final ResumeService resumeService;



    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping("/get")
    public ResponseEntity<Resume> getUserResume(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Resume resume = resumeService.getResumeByUserId(user.getId());

        if (resume != null) {
            return ResponseEntity.ok(resume);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resume> createResume(@RequestBody ResumeCreateRequest request) {
        // Получите текущего пользователя из вашей системы аутентификации
        User currentUser = UserController.getCurrentUser();

        // Создайте объект Resume и установите значения полей из запроса
        Resume resume = new Resume();
        resume.setUser(currentUser);
        resume.setName(request.getName());
        resume.setSurname(request.getSurname());
        resume.setPatronymic(request.getPatronymic());
        resume.setDate(request.getDate());
        resume.setEmail(request.getEmail());
        resume.setMyPhoto(request.getMyPhoto());
        resume.setAboutMe(request.getAboutMe());

        Resume savedResume = resumeService.save(resume);

        return ResponseEntity.ok(savedResume);
    }

    @DeleteMapping("/{resumeId}")
    public ResponseEntity<String> deleteResume(@PathVariable Long resumeId) {
        try {
            resumeService.deleteResumeById(resumeId);
            return ResponseEntity.ok("Resume deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete resume.");
        }
    }



}












