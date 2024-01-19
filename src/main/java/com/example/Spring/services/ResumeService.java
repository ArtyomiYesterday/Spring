package com.example.Spring.services;


import com.example.Spring.models.Resume;
import com.example.Spring.repositories.ResumeRepository;
import com.example.Spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResumeService {
    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    @Autowired
    public ResumeService(ResumeRepository resumeRepository, UserRepository userRepository) {
        this.resumeRepository = resumeRepository;
        this.userRepository = userRepository;
    }



    public void deleteResume(Long id){
        boolean exists = resumeRepository.existsById(id);
        if (!exists){
            throw new IllegalStateException("student with id " + id+" does not exists");
        }
        resumeRepository.deleteById(id);
    }

    public Resume save(Resume resume) {
        resumeRepository.save(resume);
        return resume;
    }

    public Resume getResumeByUserId(Long id) {
        return resumeRepository.findByUserId(id);
    }

    public void deleteResumeById(Long resumeId) {
        resumeRepository.deleteById(resumeId);
    }

}

