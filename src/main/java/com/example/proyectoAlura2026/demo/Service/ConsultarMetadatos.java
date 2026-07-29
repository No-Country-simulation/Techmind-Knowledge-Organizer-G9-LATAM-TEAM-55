package com.example.proyectoAlura2026.demo.Service;

import com.example.proyectoAlura2026.demo.Config.ConfiguracionRest;
import com.example.proyectoAlura2026.demo.Dto.RequestEnviar;
import com.example.proyectoAlura2026.demo.Dto.ResponseContenido;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class ConsultarMetadatos {

  private final RestTemplate restTemplate;

    public ConsultarMetadatos(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public ResponseContenido obtenerDatosResponse(String titulo, String texto){

        RequestEnviar enviar = new RequestEnviar(titulo, texto);

        ResponseContenido contenido = this.restTemplate.postForObject(
                "http://127.0.0.1:8000/predict",enviar,
                ResponseContenido.class);


    return contenido;

    }




}
