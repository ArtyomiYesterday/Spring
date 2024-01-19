package com.example.Spring.repositories;

import com.example.Spring.models.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    Resume findByUserId(Long id);
}