package com.example.proyectoAlura2026.demo.Service;

import com.example.proyectoAlura2026.demo.Dto.RequestEnviar;
import com.example.proyectoAlura2026.demo.Dto.ResponseContenido;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsultarMetadatos {

    private final RestTemplate restTemplate;

    public ConsultarMetadatos(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseContenido obtenerDatosResponse(String titulo, String texto){
        // Limpieza de texto
        String tituloLimpio = sanitizarTexto(titulo);
        String textoLimpio = sanitizarTexto(texto);

        RequestEnviar enviar = new RequestEnviar(tituloLimpio, textoLimpio);

        // Consulta servidor Python
        ResponseContenido contenido = this.restTemplate.postForObject(
                "http://127.0.0.1:8000/predict", enviar,
                ResponseContenido.class);

        // NIvel de confianza <20
        if (contenido != null && contenido.getConfianza() < 0.20f) {
            contenido.setCategoria("Desconocida");
        }

        return contenido;
    }

    // Metodo para limpiar el texto
    private String sanitizarTexto(String input) {
        if (input == null || input.isBlank()) {
            return "";
        }
        // Minusculas y borrar espacios a los lados
        String textoLimpio = input.toLowerCase().trim();
        // Quitar caracteres especiales (mantiene letras, números, tildes, ñ y espacios)
        textoLimpio = textoLimpio.replaceAll("[^a-záéíóúñ0-9\\s]", "");
        // Cambiar varios espacios por uno.
        return textoLimpio.replaceAll("\\s+", " ");
    }
}