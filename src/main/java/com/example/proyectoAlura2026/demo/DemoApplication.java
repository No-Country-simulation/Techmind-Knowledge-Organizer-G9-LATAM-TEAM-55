package com.example.proyectoAlura2026.demo;

import com.example.proyectoAlura2026.demo.Dto.ResponseContenido;
import com.example.proyectoAlura2026.demo.Service.ConsultarMetadatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private ConsultarMetadatos consultarMetadatos;

	public DemoApplication(ConsultarMetadatos consultarMetadatos) {
		this.consultarMetadatos = consultarMetadatos;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
/*
		ResponseContenido contented =
				consultarMetadatos.obtenerDatosResponse(
						"Introducción a Spring Boot  java ",
						"En este contenido se presentan los conceptos básicos para la creación de APIs REST utilizando Java y Spring Boot.");

		System.out.println("-------------------------------------------------------- ");
		System.out.println();
		System.out.println("Categoría Predicha: "    + " >>> "+ contented.getCategoria() + " <<< ");
		String porcentaje = String.format("Nivel de Confianza: " + "%.2f%%", contented.getConfianza() );
		System.out.println(porcentaje);
		System.out.println();
		System.out.println("---------------------------------------------------------");



*/

	}
}
