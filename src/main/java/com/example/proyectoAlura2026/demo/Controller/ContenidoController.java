package com.example.proyectoAlura2026.demo.Controller;

import com.example.proyectoAlura2026.demo.Dto.RequestEnviar;
import com.example.proyectoAlura2026.demo.Dto.ResponseContenido;
import com.example.proyectoAlura2026.demo.Service.ConsultarMetadatos;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ContenidoController {

    private final ConsultarMetadatos consultarMetadatos;

    public ContenidoController(ConsultarMetadatos consultarMetadatos) {
        this.consultarMetadatos = consultarMetadatos;
    }

    @PostMapping("/contenido")
    public ResponseContenido procesarContenido(@Valid @RequestBody RequestEnviar request) {
        return this.consultarMetadatos.obtenerDatosResponse(request.titulo(), request.texto());
    }
}