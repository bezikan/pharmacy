package com.example.pharmacy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DiseaseNotFoundExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(DiseaseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlerDiseaseNotFoundException(DiseaseNotFoundException ex) {
        return ex.getMessage();
    }

}
