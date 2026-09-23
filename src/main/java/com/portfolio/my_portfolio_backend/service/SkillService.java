package com.portfolio.my_portfolio_backend.service;

import com.portfolio.my_portfolio_backend.model.Skill;
import com.portfolio.my_portfolio_backend.repository.ISkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService implements ISkillService{

    private  final ISkillRepository iSkillRepository;

    public  SkillService(ISkillRepository iSkillRepository){
        this.iSkillRepository = iSkillRepository;
    }
    @Override
    public Skill save(Skill skill) {
        if(skill.getLevelPercentage() < 0 || skill.getLevelPercentage() > 100){
            throw new IllegalArgumentException("El porcentaje es incorrecto debe estar en 0 y 100");
        }
        return this.iSkillRepository.save(skill);
    }

    @Override
    public Optional<Skill> findById(Long id) {
        return this.iSkillRepository.findById(id);
    }

    @Override
    public List<Skill> findAll() {
        return this.iSkillRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        this.iSkillRepository.deletById(id);
    }

    @Override
    public List<Skill> findSkillsByPersonalInfoId(Long personalInfoId) {
        return this.iSkillRepository.findSkillsByPersonalInfoId(personalInfoId);
    }
}
