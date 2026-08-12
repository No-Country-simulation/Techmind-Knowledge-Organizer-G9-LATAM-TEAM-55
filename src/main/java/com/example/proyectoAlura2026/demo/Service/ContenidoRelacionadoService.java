package com.example.proyectoAlura2026.demo.Service;

import com.example.proyectoAlura2026.demo.Dto.ContenidoRelaDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContenidoRelacionadoService {


    private final List<ContenidoRelaDTO> contenidos = new ArrayList<>();

    public ContenidoRelacionadoService() {
        agregarContenido();
    }

 public void agregarContenido(){
     contenidos.add(new ContenidoRelaDTO(
             "Desarrollo de APIs REST con Spring Boot",
             "Creación de servicios web utilizando Spring Boot."
     ));

     contenidos.add(new ContenidoRelaDTO(
             "Limpieza y transformación de datos",
             "Técnicas para preparar datos antes de realizar un análisis."
           
     ));

     contenidos.add(new ContenidoRelaDTO(
             "Introducción a la Inteligencia Artificial",
             "Conceptos fundamentales de inteligencia artificial y aprendizaje automático."
     ));

     contenidos.add(new ContenidoRelaDTO(
             "CSS moderno y diseño responsive",
             "Técnicas para crear interfaces adaptables a diferentes dispositivos."
     ));

     contenidos.add(new ContenidoRelaDTO(
             "Consultas SQL avanzadas",
             "Uso de consultas SQL para obtener y analizar información."
     ));
 }

 public List<ContenidoRelaDTO> ObtenerContenidos(){
        return  this.contenidos;
 }

}
