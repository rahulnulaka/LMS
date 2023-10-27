package com.example.Library.Management.System.Exceptions;

public class BookIsNotAvailable extends Exception{
    public BookIsNotAvailable(String message) {
        super(message);
    }
}
