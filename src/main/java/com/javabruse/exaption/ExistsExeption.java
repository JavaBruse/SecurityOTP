package com.javabruse.exaption;

public class ExistsExeption extends RuntimeException {
    public ExistsExeption(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return null;
    }
}