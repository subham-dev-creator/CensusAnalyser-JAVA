package com.training.censusanalyser;

public class CSVException extends Throwable {
    public ExceptionType type;

    public CSVException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    enum ExceptionType {
        UNABLE_TO_PARSE
    }
}
