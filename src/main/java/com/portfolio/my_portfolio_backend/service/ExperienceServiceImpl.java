package com.portfolio.my_portfolio_backend.service;

import com.portfolio.my_portfolio_backend.model.Experience;
import com.portfolio.my_portfolio_backend.repository.IExperienceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceServiceImpl implements IExperienceService{

    private final IExperienceRepository experienceRepository;

    public ExperienceServiceImpl(IExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    @Override
    public List<Experience> findAll() {
        return experienceRepository.findAll();
    }

    @Override
    public Optional<Experience> findById(Long id) {
        return experienceRepository.findById(id);
    }

    @Override
    public Experience save(Experience experience) {
        // Validación 1: Asegurar que la fecha de inicio no sea nula
        if(experience.getStartDate() == null){
            throw new IllegalArgumentException("La fecha de inicio de la experiencia no puede estar vacía.");
        }

        // Validación 2: La fecha de inicio no puede ser posterior a la de fin (solo si end_date no es nula)
        if(experience.getEndDate()!=null
                && experience.getStartDate().isAfter(experience.getEndDate())
        ){
            throw new IllegalArgumentException("La fecha de inicio de la experiencia no puede ser posterior a la fecha de fin.");
        }

        // Validaciones 3 y 4 (ya estaban bien):
        if(experience.getJobTitle()==null || experience.getJobTitle().trim().isEmpty()){
            throw new IllegalArgumentException("El título del trabajo no puede estar vacío.");
        }
        if(experience.getCompanyName()==null || experience.getCompanyName().trim().isEmpty()){
            throw new IllegalArgumentException("El nombre de la compañía no puede estar vacío.");
        }
        return experienceRepository.save(experience);
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("Eliminando experiencia por ID: " + id + " en el servicio...");
        experienceRepository.deleteById(id);
    }

    @Override
    public List<Experience> findExperienceByPersonalInfoId(Long personalInfoId) {
        return experienceRepository.findByPersonalInfoId(personalInfoId);
    }
}
