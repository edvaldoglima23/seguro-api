package com.example.seguro.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Classe que representa erros de validação de campos
 */
@Getter
@Setter
public class ValidationErrorResponse extends ErrorResponse {
    private Map<String, String> fieldErrors;

    public ValidationErrorResponse(int status, String error, String message, 
                                  LocalDateTime timestamp, Map<String, String> fieldErrors) {
        super(status, error, message, timestamp);
        this.fieldErrors = fieldErrors;
    }
} 