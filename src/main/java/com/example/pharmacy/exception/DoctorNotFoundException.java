package com.example.pharmacy.exception;

import com.example.pharmacy.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public class DoctorNotFoundException extends RuntimeException {

    public DoctorNotFoundException(Long id) {
        super("could not find doctor id "+id);
    }
}
