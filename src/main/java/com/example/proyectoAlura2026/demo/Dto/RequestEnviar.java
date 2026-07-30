package com.example.proyectoAlura2026.demo.Dto;

import jakarta.validation.constraints.NotBlank;

public record RequestEnviar(
        @NotBlank(message = "El título es obligatorio y no puede estar vacío.")
        String titulo,

        @NotBlank(message = "El texto es obligatorio y no puede estar vacío.")
        String texto
) {
}