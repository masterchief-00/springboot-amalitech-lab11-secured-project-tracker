package com.kwizera.springbootlab11securedprojecttracker.Exceptions;

public class DuplicateRecordException extends Exception {
    public DuplicateRecordException(String message) {
        super(message);
    }
}
