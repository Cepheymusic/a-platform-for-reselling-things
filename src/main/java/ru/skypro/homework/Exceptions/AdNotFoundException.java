package ru.skypro.homework.Exceptions;

public class AdNotFoundException extends RuntimeException{
    public AdNotFoundException(String message) {
        super(message);
    }
}
