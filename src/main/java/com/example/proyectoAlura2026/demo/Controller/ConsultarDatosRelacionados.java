package com.example.proyectoAlura2026.demo.Controller;

import com.example.proyectoAlura2026.demo.Service.ContenidoRelacionadoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConsultarDatosRelacionados {


    private  ContenidoRelacionadoService contenidoRelacionadoService;

    public ConsultarDatosRelacionados(ContenidoRelacionadoService contenidoRelacionadoService) {
        this.contenidoRelacionadoService = contenidoRelacionadoService;
    }

    @GetMapping("/consultar")
    public String consultarDatos(Model model){

        model.addAttribute("contenidos", contenidoRelacionadoService.ObtenerContenidos());

        return "consultar";
    }


    @GetMapping("/regresar")
    public String regresar(){
        return "index";
    }
}
