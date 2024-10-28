package com.resident_city_admin.resident_city_admin.exception;

public class InvalidEmailFormatException extends RuntimeException {
    public InvalidEmailFormatException(String message) {
        super(message);
    }
}
