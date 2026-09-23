package com.portfolio.my_portfolio_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {
    private Long id;
    private String name; // Ej: "Java", "HTML", "CSS"
    private Integer levelPercentage; // Ej: 90, 85 (para barras de progreso)
    private String iconClass; // Ej: "fab fa-java" para FontAwesome
    private Long personalInfoId; // Clave foránea a PersonalInfo
}
