package com.portfolio.my_portfolio_backend.service;

import com.portfolio.my_portfolio_backend.model.PersonalInfo;
import com.portfolio.my_portfolio_backend.repository.IPersonalInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalInfoServiceImpl implements  IPersonalInfoService{

    private final IPersonalInfoRepository personalInfoRepository;

    public PersonalInfoServiceImpl(IPersonalInfoRepository personalInfoRepository){
        this.personalInfoRepository = personalInfoRepository;
    }

    @Override
    public PersonalInfo save(PersonalInfo personalInfo) {
       return this.personalInfoRepository.save(personalInfo);
    }

    @Override
    public Optional<PersonalInfo> findById(Long id) {
        return this.personalInfoRepository.findById(id);
    }

    @Override
    public List<PersonalInfo> findAll() {

      return  this.personalInfoRepository.findAll();
    }

    @Override
    public void deletById(Long id) {
        this.personalInfoRepository.deletById(id);
    }
}
