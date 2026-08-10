package com.example.proyectoAlura2026.demo.Controller;


import com.example.proyectoAlura2026.demo.Model.SistemaConsulta;
import com.example.proyectoAlura2026.demo.Service.ConsultarMetadatos;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/contenido")
public class ContenidoControllerRest {


    private ConsultarMetadatos consultarMetadatos;

    public ContenidoControllerRest(ConsultarMetadatos consultarMetadatos) {
        this.consultarMetadatos = consultarMetadatos;
    }

    @GetMapping
    public ResponseEntity<List<SistemaConsulta>> listaSistemaConsulta(){
        List<SistemaConsulta> consulta = this.consultarMetadatos.obtenerConsultas();
        return ResponseEntity.ok(consulta);
    }

}
