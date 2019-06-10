package com.example.pharmacy.exception;

public class MeetingsNotFoundException extends RuntimeException {

    public MeetingsNotFoundException(Long id) {
        super("could not find meeting "+id);
    }

}
