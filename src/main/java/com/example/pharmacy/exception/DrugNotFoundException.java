package com.example.pharmacy.exception;

public class DrugNotFoundException  extends RuntimeException{

    public DrugNotFoundException(Long id) {
        super("could not find drug "+id);
    }

}
