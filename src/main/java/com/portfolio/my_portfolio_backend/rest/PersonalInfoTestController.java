package com.portfolio.my_portfolio_backend.rest;

import com.portfolio.my_portfolio_backend.model.PersonalInfo;
import com.portfolio.my_portfolio_backend.service.IPersonalInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test-personal-info")
public class PersonalInfoTestController {

    private final IPersonalInfoService iPersonalInfoService;

    public PersonalInfoTestController(IPersonalInfoService iPersonalInfoService){
        this.iPersonalInfoService = iPersonalInfoService;
    }
    @GetMapping("/all")
    public List<PersonalInfo> getAllPersonalInfo(){
        return this.iPersonalInfoService.findAll();
    }

    @GetMapping("/{id}")
    public PersonalInfo getPersonalInfoById(@PathVariable Long id){
        Optional<PersonalInfo>  info = this.iPersonalInfoService.findById(id);
        if(info.isPresent()){
            return info.get();
        }else{
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "\"Información personal no disponible en el ID: \" " +id);
        }
    }

    @PostMapping
    public ResponseEntity<PersonalInfo> createPersonalInfo(@RequestBody PersonalInfo personalInfo){
        PersonalInfo newPersonalInfo = iPersonalInfoService.save(personalInfo);
        return  new ResponseEntity<>(newPersonalInfo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public PersonalInfo update( @PathVariable Long id,@RequestBody PersonalInfo personalInfo){
        personalInfo.setId(id);
        return iPersonalInfoService.save(personalInfo);
    }

    @DeleteMapping("/{id}")
    public void deleteBy(@PathVariable long id ){
        iPersonalInfoService.deletById(id);
    }

}
