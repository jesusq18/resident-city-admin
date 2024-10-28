package com.resident_city_admin.resident_city_admin.exception;

public class InvalidCityException extends RuntimeException{
    public InvalidCityException(String mensaje) {
        super(mensaje);
    }
}
