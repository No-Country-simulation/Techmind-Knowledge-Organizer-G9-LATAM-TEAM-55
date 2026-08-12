package com.example.proyectoAlura2026.demo.Model;


import java.util.List;

public class SistemaConsulta {

    private String categoria;  //
    private float confianza;
    private String titulo;
    private String texto;
    private List<String> palabras_clave;


    public SistemaConsulta() {
    }

    public SistemaConsulta(String categoria, float confianza, String titulo, String texto, List<String> palabras_clave) {
        this.categoria = categoria;
        this.confianza = confianza;
        this.titulo = titulo;
        this.texto = texto;
        this.palabras_clave = palabras_clave;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public List<String> getPalabras_clave() {
        return palabras_clave;
    }

    public void setPalabras_clave(List<String> palabras_clave) {
        this.palabras_clave = palabras_clave;
    }

    @Override
    public String toString() {
        return "SistemaConsulta{" +
                "categoria='" + categoria + '\'' +
                ", confianza=" + confianza +
                ", titulo='" + titulo + '\'' +
                ", texto='" + texto + '\'' +
                ", palabras_clave=" + palabras_clave +
                '}';
    }
}
