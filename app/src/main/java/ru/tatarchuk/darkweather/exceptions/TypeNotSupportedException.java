package ru.tatarchuk.darkweather.exceptions;

public class TypeNotSupportedException extends RuntimeException {

    private TypeNotSupportedException(String message){
        super(message);
    }

    public static TypeNotSupportedException create(String message){
        return new TypeNotSupportedException(message);
    }
}
