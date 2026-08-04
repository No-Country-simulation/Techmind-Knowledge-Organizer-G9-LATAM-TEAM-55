package com.example.proyectoAlura2026.demo.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestEnviar(
        @NotBlank(message = "El título es obligatorio y no puede estar vacío.")
        @Size(max = 75, message = "El título no puede exceder los 75 caracteres.")
        String titulo,

        @NotBlank(message = "El texto es obligatorio y no puede estar vacío.")
        @Size(max = 150, message = "El texto no puede exceder los 150 caracteres.")
        String texto
) {}