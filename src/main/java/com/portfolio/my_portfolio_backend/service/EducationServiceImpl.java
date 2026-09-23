package com.portfolio.my_portfolio_backend.service;

import com.portfolio.my_portfolio_backend.model.Education;
import com.portfolio.my_portfolio_backend.repository.IEducationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationServiceImpl implements  IEducationService{
    private final IEducationRepository educationRepository;

    public EducationServiceImpl(IEducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    @Override
    public List<Education> findAll() {
        return educationRepository.findAll();
    }

    @Override
    public Optional<Education> findById(Long id) {
        return educationRepository.findById(id);
    }

    @Override
    public Education save(Education education) {
        // Validación 1: Asegurar que la fecha de inicio no sea nula, como exige la DB
        if (education.getStartDate() == null) {
            throw new IllegalArgumentException("La fecha de inicio de la educación no puede estar vacía.");
        }

        // Validación 2: La fecha de inicio no puede ser posterior a la de fin
        if(education.getEndDate() != null && education.getStartDate().isAfter(education.getEndDate())) {
            throw new IllegalArgumentException("La fecha de inicio de la educación no puede ser posterior a la fecha de fin.");
        }

        return educationRepository.save(education);
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("Eliminando educación por ID: " + id + " en el servicio...");
        educationRepository.deleteById(id);
    }

    @Override
    public List<Education> findEducationByPersonalInfoId(Long personalInfoId) {
        return educationRepository.findByPersonalInfoId(personalInfoId);
    }
}
