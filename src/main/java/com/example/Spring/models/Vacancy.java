package com.example.Spring.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "vacancies")
@Data
@NoArgsConstructor
public class Vacancy {
    @Id
    @SequenceGenerator(
            name = "vacancy_sequence",
            sequenceName = "vacancy_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vacancy_sequence"
    )
    private Long id;

    public Vacancy(String jobTitle, String location) {
        this.jobTitle = jobTitle;
        this.location = location;
    }

    public Vacancy(String jobTitle, String location, int salary) {
        this.jobTitle = jobTitle;
        this.location = location;
        this.salary = salary;
    }



    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String img;

    public Vacancy(String img, String jobTitle, String location, int salary) {
        this.img = img;
        this.jobTitle = jobTitle;
        this.location = location;
        this.salary = salary;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Column(nullable = false)
    private String jobTitle;

    @Column
    private String location;

    @Column(length = 1000)
    private String description;

    @Column
    private int salary;

    @ManyToMany
    @JoinTable(
            name = "vacancy_applicants",
            joinColumns = @JoinColumn(name = "vacancy_id"),
            inverseJoinColumns = @JoinColumn(name = "resume_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"vacancy_id", "resume_id"})
    )
    private List<Resume> applicants;



}
