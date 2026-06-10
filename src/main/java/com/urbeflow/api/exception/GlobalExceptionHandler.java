package com.urbeflow.api.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
    
    // Atrapa errores especificos
    @ExceptionHandler(PeajeException.class)
    public ResponseEntity<Map<String, Object>> manejarPeajeException(PeajeException ex) {
        Map<String, Object> cuerpoError = new HashMap<>();
        cuerpoError.put("timestamp", LocalDateTime.now());
        cuerpoError.put("error", "Error en el sistema");
        cuerpoError.put("mensaje", ex.getMessage());
        cuerpoError.put("codigo", HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(cuerpoError, HttpStatus.BAD_REQUEST);
    }

    // Atrapa errores inesperados
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> manejarExceptionGeneral(Exception ex) {
        Map<String, Object> cuerpoError = new HashMap<>();
        cuerpoError.put("timestamp", LocalDateTime.now());
        cuerpoError.put("error", "Error inesperado");
        cuerpoError.put("mensaje", ex.getMessage());
        cuerpoError.put("codigo", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(cuerpoError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
