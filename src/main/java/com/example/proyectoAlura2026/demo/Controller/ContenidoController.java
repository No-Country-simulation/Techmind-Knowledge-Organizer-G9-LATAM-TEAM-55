package com.example.proyectoAlura2026.demo.Controller;


import com.example.proyectoAlura2026.demo.Dto.RequestEnviar;
import com.example.proyectoAlura2026.demo.Dto.ResponseContenido;
import com.example.proyectoAlura2026.demo.Service.ConsultarMetadatos;
import jakarta.validation.Valid; //Es la librería para el interruptor
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ContenidoController {

    private final ConsultarMetadatos consultarMetadatos;


    public ContenidoController(ConsultarMetadatos consultarMetadatos) {
        this.consultarMetadatos = consultarMetadatos;

    }

    @GetMapping("/")
    public String inicio() {
        return "index";   // Busca templates/index.html
    }


    //
    @PostMapping("contenido")
    //El Valid es el interruptor que enciende la seguridad.
    public String obtenerDatos(@Valid    @RequestParam String titulo,
                                                          @RequestParam String texto, Model model){

        RequestEnviar request  = new RequestEnviar(titulo, texto);
        ResponseContenido response =  this.consultarMetadatos.obtenerDatosResponse(request.titulo(), request.texto());
        model.addAttribute("titulo", titulo);
        model.addAttribute("texto", texto);
        model.addAttribute("categoria", response.getCategoria());
        model.addAttribute("confianza", response.getConfianza());
        return "index";
    }



}