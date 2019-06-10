package com.example.pharmacy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DoctorNotFoundExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(DoctorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlerDoctorNotFoundException(DoctorNotFoundException ex) {
        return ex.getMessage();
    }

}
