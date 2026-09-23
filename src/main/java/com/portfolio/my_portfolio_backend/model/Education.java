package com.portfolio.my_portfolio_backend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    private Long id;
    private String degree; // Ej: "Ingeniería en Sistemas"
    private String institution; // Ej: "Universidad Nacional de Mar del Plata"
    private LocalDate startDate;
    private LocalDate endDate; // Puede ser null si está en curso
    private String description; // Breve descripción de logros o cursos
    private Long personalInfoId; // Clave foránea a PersonalInfo
}