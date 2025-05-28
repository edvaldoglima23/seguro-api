package com.example.seguro.exception;

/**
 * Exceção personalizada para erros de regra de negócio
 */
public class NegocioException extends RuntimeException {

    public NegocioException(String message) {
        super(message);
    }

    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }
} 