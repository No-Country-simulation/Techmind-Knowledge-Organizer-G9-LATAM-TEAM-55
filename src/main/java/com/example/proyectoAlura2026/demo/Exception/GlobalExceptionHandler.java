package com.example.proyectoAlura2026.demo.Exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler {

    // Errores de validación
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String manejarValidaciones(
            MethodArgumentNotValidException ex,
            Model model) {

        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errores.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        model.addAttribute("error", errores);

        return "index";
    }

    // Error cuando el servicio de IA no está disponible
    @ExceptionHandler(RestClientException.class)
    public String manejarErrorDeConexion(
            RestClientException ex,
            Model model) {

        model.addAttribute(
                "errorIA",
                "El servicio de Inteligencia Artificial no está disponible en este momento. Inténtalo nuevamente más tarde."
        );

        return "index";
    }
}