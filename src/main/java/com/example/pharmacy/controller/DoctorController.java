package com.example.pharmacy.controller;

import com.example.pharmacy.exception.BadPhoneNumberException;
import com.example.pharmacy.exception.DoctorNotFoundException;
import com.example.pharmacy.model.Doctor;
import com.example.pharmacy.model.Patient;
import com.example.pharmacy.repository.DoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import javax.validation.Valid;
import java.util.List;

@RestController
public class DoctorController {

    @Autowired
    DoctorRepository doctorRepository;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/phones")
    public ResponseEntity<?> getPhones() {
        return new ResponseEntity<>(doctorRepository.allPhones().stream().map(doctor -> doctor.getPhone()), HttpStatus.OK);
    }

    @GetMapping("/doctors/{id}")
    @PreAuthorize("#id == principal.id")
    public ResponseEntity<?> getDoctor(@PathVariable Long id){
        Doctor doctor = doctorRepository.findById(id).orElseThrow(()->new DoctorNotFoundException(id));
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @GetMapping("/doctors")
    public ResponseEntity<?> getAll() {
        List<Doctor> doctors = doctorRepository.findAll();
        if(doctors.isEmpty()) {
            logger.info("lista jest pusta !");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return  new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @PostMapping("/doctor")
    public ResponseEntity<?> createDoctor(@RequestBody @Valid Doctor doctor, BindingResult result) {
        if(doctorRepository.findById(doctor.getId()).isPresent()) {
            logger.info("doktor istnieje w bazie danych");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        if(result.hasErrors()) {
            logger.info("bad phone number!!!");
            throw new BadPhoneNumberException(doctor.getPhone());
        }
        doctorRepository.save(doctor);
        return new ResponseEntity<>(doctor, HttpStatus.CREATED);
    }

    @PutMapping("/doctors/{id}")
    public ResponseEntity<?> editDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        return doctorRepository.findById(id).map((theDoctor) -> {
            theDoctor.setId(doctor.getId());
            theDoctor.setFirstName(doctor.getFirstName());
            theDoctor.setLastName(doctor.getLastName());
            doctorRepository.save(theDoctor);
            return new ResponseEntity(theDoctor, HttpStatus.ACCEPTED);
        }).orElseThrow(()->new DoctorNotFoundException(id));
    }

    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long id) {
        doctorRepository.findById(id).orElseThrow(()->new DoctorNotFoundException(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }





























}
