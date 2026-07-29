[README (1).md](https://github.com/user-attachments/files/30484630/README.1.md)
Markdown
# 🧠 TechMind – Organización Inteligente del Conocimiento Técnico
> **ONE Alura Latam + Oracle | Hackathon G9**  
> **Equipo:** TEAM 55  
---
## 📌 Descripción del Proyecto
TechMind es una solución inteligente diseñada para clasificar, enriquecer y organizar automáticamente contenidos técnicos (artículos, notas de estudio, documentación). 
A través de técnicas de **Ciencia de Datos / Procesamiento de Lenguaje Natural** y un microservicio Back-End en **Spring Boot**, la plataforma analiza textos y 
devuelve categorías, niveles de confianza y etiquetas clave en formato **JSON** para ser consumido por cualquier aplicación.
---
## 🛠️ Arquitectura y Tecnologías
* **Back-End:** Java 21/22, Spring Boot 3.3.x, Maven.
* **Ciencia de Datos:** Python.
* **Infraestructura Cloud (OCI):**
---
## 🚀 Guía de Uso de la API (REST Endpoints)

### `POST /contenido`
Procesa un texto técnico y devuelve su clasificación y etiquetas.

#### 📩 Ejemplo de Solicitud (Request)
```json
{
  "titulo": "Introducción a Spring Boot",
  "texto": "En este contenido se presentan los conceptos básicos para la creación de APIs REST utilizando Java y Spring Boot."
}
