package com.example.proyectoAlura2026.demo.Controller;

import com.example.proyectoAlura2026.demo.Dto.RequestEnviar;
import com.example.proyectoAlura2026.demo.Dto.ResponseContenido;
import com.example.proyectoAlura2026.demo.Model.SistemaConsulta;
import com.example.proyectoAlura2026.demo.Service.ConsultarMetadatos;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ContenidoController {

    private final ConsultarMetadatos consultarMetadatos;

    public ContenidoController(ConsultarMetadatos consultarMetadatos) {
        this.consultarMetadatos = consultarMetadatos;
    }

    @GetMapping("/")
    public String inicio(Model model) {


        model.addAttribute("consultas", consultarMetadatos.obtenerConsultas()
        );
        return "index";
    }


    @PostMapping("/contenido")
    public String procesarContenido(@Valid @RequestParam String titulo,
                                    @RequestParam String texto, Model model) {

        ResponseContenido respuesta =
                consultarMetadatos.obtenerDatosResponse(titulo, texto);

        model.addAttribute("titulo", titulo);
        model.addAttribute("texto", texto);
        model.addAttribute("categoria", respuesta.getCategoria());
        model.addAttribute("confianza", respuesta.getConfianza());
        model.addAttribute("palabras_clave", respuesta.getPalabras_clave());



        // Si la categoría es desconocida
        if ("Desconocida".equals(respuesta.getCategoria())) {

            model.addAttribute(
                    "categoriaDesconocida",
                    true
            );

            model.addAttribute(
                    "mensajeCategoria",
                    "El contenido no pertenece a una categoría reconocida por el sistema."
            );
        } else {
            model.addAttribute(
                    "categoriaDesconocida",
                    false
            );
        }

        model.addAttribute(
                "consultas",
                consultarMetadatos.obtenerConsultas()

        );

        return "index";
    }


    @PostMapping("/guardar")
    public String guardarDatos(@RequestParam String titulo,
                               @RequestParam String texto,
                               @RequestParam String categoria,
                               @RequestParam Float confianza,
                               @RequestParam List<String> palabras_claves
    ) {

        SistemaConsulta sistemaConsulta = new SistemaConsulta();

        sistemaConsulta.setTitulo(titulo);
        sistemaConsulta.setTexto(texto);
        sistemaConsulta.setConfianza(confianza);
        sistemaConsulta.setCategoria(categoria);
        sistemaConsulta.setPalabras_clave(palabras_claves);
        consultarMetadatos.guardarDatos(sistemaConsulta);

        return "redirect:/";
    }

    @GetMapping("/consultas")
    public String mostrarConsultas(Model model) {

        List<SistemaConsulta> consultas =
                consultarMetadatos.obtenerConsultas();

        model.addAttribute("consultas", consultas);

        return "redirect:/";
    }

    @GetMapping("/seleccionar")
    public String filtrarPorSeleccion(
            @RequestParam(defaultValue = "false") boolean frontend,
            @RequestParam(defaultValue = "false") boolean backend,
            @RequestParam(defaultValue = "false") boolean data,
            @RequestParam(defaultValue = "false") boolean cloud,

            Model model) {

        List<SistemaConsulta> consultas =
                consultarMetadatos.obtenerConsultas();

        List<SistemaConsulta> filtradas;

        if (!frontend && !backend && !data && !cloud) {

            filtradas = consultas;

        } else {

            filtradas = consultas.stream()
                    .filter(e ->
                            (frontend && "Frontend".equals(e.getCategoria()))
                                    ||
                                    (backend && "Backend".equals(e.getCategoria()))
                                    ||
                                    (cloud && "Cloud".equals(e.getCategoria()))
                                    ||
                                    (data && "Data".equals(e.getCategoria()))

                    )
                    .toList();
        }

        model.addAttribute("consultas", filtradas);

        model.addAttribute("frontendSeleccionado", frontend);
        model.addAttribute("backendSeleccionado", backend);
        model.addAttribute("dataSeleccionado", data);
        model.addAttribute("cloudSeleccionado",cloud);

        return "index";
    }


    @GetMapping("/download")
    public ResponseEntity<List<SistemaConsulta>> download() {

        List<SistemaConsulta> consultas =
                consultarMetadatos.obtenerConsultas();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
         //       .header(
                //                        HttpHeaders.CONTENT_DISPOSITION,
                //                        "attachment; filename=\"consultas.json\""
                //                )
                .body(consultas);
    }


}