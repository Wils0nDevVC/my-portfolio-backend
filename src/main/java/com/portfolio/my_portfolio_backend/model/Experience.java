package com.portfolio.my_portfolio_backend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Experience {
    private Long id;
    private String jobTitle; // Ej: "Full Stack Developer"
    private String companyName;
    private LocalDate startDate;
    private LocalDate endDate; // Puede ser null si es el puesto actual
    private String description; // Responsabilidades y logros
    private Long personalInfoId; // Clave foránea a PersonalInfo
}