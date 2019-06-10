package com.example.pharmacy.controller;

import com.example.pharmacy.exception.DiseaseNotFoundException;
import com.example.pharmacy.model.Disease;
import com.example.pharmacy.repository.DiseaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiseaseController {

    @Autowired
    public DiseaseRepository diseaseRepository;

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/diseases/{id}")
    public ResponseEntity<?> getDisease(@PathVariable Long id) {
        Disease theDisease = diseaseRepository.findById(id).orElseThrow(()->new DiseaseNotFoundException(id));
        return new ResponseEntity<>(theDisease, HttpStatus.OK);
    }

    @GetMapping("/diseases")
    public ResponseEntity<?> getAll() {
        List<Disease> diseaseList = diseaseRepository.findAll();
        if(diseaseList.isEmpty()) {
            logger.info("lista jest pusta");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(diseaseList, HttpStatus.ACCEPTED);
    }

    @PostMapping("/diseases")
    public ResponseEntity<?> createDisease(@RequestBody Disease disease) {
        if(diseaseRepository.findById(disease.getId()).isPresent()) {
            logger.info("choroba istnieje w bazie");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/diseases/{id}")
    public ResponseEntity<?> editDisease(@PathVariable Long id, @RequestBody Disease disease){
        return diseaseRepository.findById(id).map(theDisease->{
            theDisease.setId(disease.getId());
            theDisease.setDiseaseName(disease.getDiseaseName());
            diseaseRepository.save(theDisease);
            return new ResponseEntity(theDisease, HttpStatus.ACCEPTED);
        }).orElseThrow(()->new DiseaseNotFoundException(disease.getId()));
    }

    @DeleteMapping("/diseases/{id}")
    public ResponseEntity<?> deleteDisease(@PathVariable Long id) {
        diseaseRepository.findById(id).orElseThrow(()->new DiseaseNotFoundException(id));
        diseaseRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

























}
