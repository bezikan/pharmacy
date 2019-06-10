package com.example.pharmacy.exception;

public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(Long id) {
        super("could not find the patient "+id);
    }
}
