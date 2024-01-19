package com.example.Spring.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacancyRequest {
    private String jobTitle;
    private String location;
    private String description;
    private String img;
    private int salary;
}
