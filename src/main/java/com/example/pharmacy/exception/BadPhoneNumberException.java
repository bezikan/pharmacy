package com.example.pharmacy.exception;

public class BadPhoneNumberException  extends RuntimeException {
    public BadPhoneNumberException(String phone) {
        super("invalid phone number: "+phone);
    }
}
