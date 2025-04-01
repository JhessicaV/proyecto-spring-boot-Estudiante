package com.universidad.controller; // Define el paquete al que pertenece esta clase

import com.universidad.dto.EstudianteDTO; // Importa la clase EstudianteDTO del paquete dto
import com.universidad.service.IEstudianteService; // Importa la interfaz IEstudianteService del paquete service

import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación Autowired de Spring
import org.springframework.http.ResponseEntity; // Importa la clase ResponseEntity de Spring para manejar respuestas HTTP
import org.springframework.web.bind.annotation.*; // Importa las anotaciones de Spring para controladores web
import org.springframework.http.HttpStatus; // Importa HttpStatus para usar los códigos de estado HTTP

import java.util.List; // Importa la interfaz List para manejar listas

@RestController // Anotación que indica que esta clase es un controlador REST de Spring
@RequestMapping("/api") // Define la ruta base para las solicitudes HTTP a este controlador
public class EstudianteController { // Define la clase EstudianteController

    private final IEstudianteService estudianteService; // Declara una variable final para el servicio de estudiantes

    @Autowired // Anotación que indica que el constructor debe ser usado para inyección de dependencias
    public EstudianteController(IEstudianteService estudianteService) { // Constructor que recibe el servicio de estudiantes
        this.estudianteService = estudianteService; // Asigna el servicio de estudiantes a la variable de instancia
    }

    // Método para obtener todos los estudiantes (este ya lo tenías)
    @GetMapping("/estudiantes") // Anotación que indica que este método maneja solicitudes GET
    public ResponseEntity<List<EstudianteDTO>> obtenerTodosLosEstudiantes() { // Método para obtener una lista de todos los EstudianteDTO
        List<EstudianteDTO> estudiantes = estudianteService.obtenerTodosLosEstudiantes(); // Llama al servicio para obtener todos los estudiantes
        return ResponseEntity.ok(estudiantes); // Retorna una respuesta HTTP 200 OK con la lista de estudiantes
    }

    // Método para crear un nuevo estudiante
    @PostMapping("/estudiantes") // Define el endpoint para solicitudes POST
    @ResponseStatus(HttpStatus.CREATED) // Devuelve el estado 201 (Creado) si la operación es exitosa
    public ResponseEntity<Void> crearEstudiante(@RequestBody EstudianteDTO estudianteDTO) { // Recibe los datos del estudiante en formato JSON
        estudianteService.crearEstudiante(estudianteDTO); // Llama al servicio para crear un estudiante
        return ResponseEntity.status(HttpStatus.CREATED).build(); // Devuelve una respuesta con el código 201 (Creado)
    }
    //Metodo para eliminar a un estudiante mediante el id
    @DeleteMapping("/estudiantes/{id}") // Define un endpoint para manejar solicitudes DELETE en "/estudiantes/{id}"
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Long id) { // Método para eliminar un estudiante por su ID
        estudianteService.eliminarEstudiante(id); // Llama al servicio para eliminar el estudiante
        return ResponseEntity.noContent().build(); // Retorna una respuesta con código 204 (No Content) si la eliminación fue exitosa
    }
    // Método para actualizar un estudiante
    @PutMapping("/estudiantes/{id}") // Define un endpoint para manejar solicitudes PUT en "/estudiantes/{id}"
    public ResponseEntity<EstudianteDTO> actualizarEstudiante(@PathVariable Long id, @RequestBody EstudianteDTO estudianteDTO) { 
        EstudianteDTO estudianteActualizado = estudianteService.actualizarEstudiante(id, estudianteDTO); // Llama al servicio para actualizar el estudiante
        if (estudianteActualizado != null) { // Si el estudiante fue actualizado con éxito
            return ResponseEntity.ok(estudianteActualizado); // Retorna una respuesta HTTP 200 OK con el estudiante actualizado
        }
        return ResponseEntity.notFound().build(); // Si el estudiante no existe, retorna una respuesta 404 (Not Found)
    }


}
