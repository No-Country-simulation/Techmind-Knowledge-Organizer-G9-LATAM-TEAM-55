package com.example.proyectoAlura2026.demo.Dto;

import java.util.List;

public class ResponseContenido {

    private String categoria;  //
    private float confianza;



    /*
     *
     * Proyecto funciona
     * imprime 1 categoria por 1 consulta por titulo y texto
     * se podria con un List<> para arrojar un palabrasClaves para mostrar una lista de categorias
     *
     *
     * Manejo de Excepciones,
     * realizar condicion para arrojar probabilidades desconocidas si fuera menos < de 0.2 la categoria
     * seria desconocida de lo contrario seria la categoria adecuada
     *
     * */
    public ResponseContenido() {

    }

    public ResponseContenido(String categoria, float confianza) {
        this.categoria = categoria;
        this.confianza = confianza;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getConfianza() {
        return confianza;
    }

    public void setConfianza(float confianza) {
        this.confianza = confianza;
    }
}
