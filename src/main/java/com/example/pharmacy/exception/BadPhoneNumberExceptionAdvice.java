package com.example.pharmacy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BadPhoneNumberExceptionAdvice {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    @ExceptionHandler(BadPhoneNumberException.class)
    public String handleBadPhoneNumberException(BadPhoneNumberException ex) {
        return ex.getMessage();
    }

}
