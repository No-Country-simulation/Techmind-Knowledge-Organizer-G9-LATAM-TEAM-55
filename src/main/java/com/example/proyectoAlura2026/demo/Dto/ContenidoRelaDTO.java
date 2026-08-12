package com.example.proyectoAlura2026.demo.Dto;

// CONTENIDO RELACIONADO COMO MEMORIA TEMPORAL PARA ENTRAR A LA OTRA VENTANA ILUSTRAR DATOS

public class ContenidoRelaDTO {

    private String titulo;
    private String Descripcion;


    public ContenidoRelaDTO() {
    }


    public ContenidoRelaDTO(String titulo, String descripcio) {
        this.titulo = titulo;
        Descripcion = descripcio;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }



}
