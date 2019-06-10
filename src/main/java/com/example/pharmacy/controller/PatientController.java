package com.example.pharmacy.controller;

import com.example.pharmacy.exception.PatientNotFoundException;
import com.example.pharmacy.model.Patient;
import com.example.pharmacy.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
public class PatientController {

    Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/patients/{id}")
    public ResponseEntity<?> getPatient(@PathVariable Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(()->new PatientNotFoundException(id));
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @GetMapping("/patients")
    public ResponseEntity<?> getAll() {
        List<Patient> patientList = patientRepository.findAll();
        if(patientList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(patientList, HttpStatus.OK);
    }

    @PostMapping("/patient")
    public ResponseEntity<?> createPatient(@RequestBody Patient patient) {
        if(patientRepository.findById(patient.getId()).isPresent()){
            logger.info("patient exist");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        patientRepository.save(patient);
        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    @PutMapping("/patients/{id}")
    public ResponseEntity<?> editPatient(@PathVariable Long id, @RequestBody Patient patient){
        return patientRepository.findById(id)
                .map(patient1 -> {
                    patient1.setId(patient.getId());
                    patient1.setFirstName(patient.getFirstName());
                    patient1.setLastName(patient.getLastName());
                    patientRepository.save(patient1);
                    return  new ResponseEntity(patient1, HttpStatus.ACCEPTED);
                })
                .orElseThrow(()->new PatientNotFoundException(id));
    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) {
        patientRepository.findById(id).orElseThrow(()->new PatientNotFoundException(id));
        patientRepository.deleteById(id);
        return new ResponseEntity<Patient>(HttpStatus.NO_CONTENT);
    }


}
