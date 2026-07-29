package com.example.proyectoAlura2026.demo.Controller;


import com.example.proyectoAlura2026.demo.Dto.RequestEnviar;
import com.example.proyectoAlura2026.demo.Dto.ResponseContenido;
import com.example.proyectoAlura2026.demo.Service.ConsultarMetadatos;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/contenido")
public class ContenidoController {


    private final ConsultarMetadatos consultarMetadatos;

    public ContenidoController(ConsultarMetadatos consultarMetadatos) {
        this.consultarMetadatos = consultarMetadatos;
    }


    @PostMapping()
    public ResponseEntity<ResponseContenido> obtenerDatos(@RequestBody RequestEnviar request){
         ResponseContenido response =  this.consultarMetadatos.obtenerDatosResponse(request.titulo(), request.texto());
    return  ResponseEntity.ok(   response );

    }
    
}

