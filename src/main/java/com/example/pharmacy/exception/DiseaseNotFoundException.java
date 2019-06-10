package com.example.pharmacy.exception;

public class DiseaseNotFoundException  extends  RuntimeException {

    public DiseaseNotFoundException(Long id) {
        super("could not find disease "+id);
    }

}
