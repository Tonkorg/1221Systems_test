package com._Systems.calorietracker.exception;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidationErrorResponse extends ErrorResponse {
    private Map<String, String> errors;

    public ValidationErrorResponse(int status, String error, String message, String timestamp, Map<String, String> errors) {
        super(status, error, message, timestamp);
        this.errors = errors;
    }

    public Map<String, String> getErrors() { return errors; }
}