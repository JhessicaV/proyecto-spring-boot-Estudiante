package com.universidad.controller; 

import com.universidad.dto.EstudianteDTO; 
import com.universidad.service.IEstudianteService; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*; 
import org.springframework.http.HttpStatus; 

import java.util.List; 

@RestController 
@RequestMapping("/api") 
public class EstudianteController { 

    private final IEstudianteService estudianteService; 

    // Constructor que recibe el servicio de estudiantes
    @Autowired 
    public EstudianteController(IEstudianteService estudianteService) { 
        this.estudianteService = estudianteService; 
    }

    // Método para obtener todos los estudiantes LISTADO
    @GetMapping("/estudiantes") 
    public ResponseEntity<List<EstudianteDTO>> obtenerTodosLosEstudiantes() { 
        List<EstudianteDTO> estudiantes = estudianteService.obtenerTodosLosEstudiantes(); 
        return ResponseEntity.ok(estudiantes); 
    }
    // ---------------------------------------------------------------------------------------------------------------------------------
    // INICISO A : La actualización de un estudiante existente:
    // Implemente un endpoint en el controlador para actualizar un estudiante. Mediante PUT y obtener un estudiante por su ID con GET.
    @PutMapping("/estudiantes/{id}") 
    public ResponseEntity<EstudianteDTO> actualizarEstudiante(@PathVariable Long id, @RequestBody EstudianteDTO estudianteDTO) { 
        EstudianteDTO estudianteActualizado = estudianteService.actualizarEstudiante(id, estudianteDTO); 
        if (estudianteActualizado != null) { 
            return ResponseEntity.ok(estudianteActualizado); 
        }
        return ResponseEntity.notFound().build(); 
    }
    
    // ---------------------------------------------------------------------------------------------------------------------------------
    // INCISO B:  Método para crear un nuevo estudiante 
    // Implemente un endpoint en el controlador para registrar un estudiante en el sistema.
    @PostMapping("/estudiantes") 
    @ResponseStatus(HttpStatus.CREATED) 
    public ResponseEntity<Void> crearEstudiante(@RequestBody EstudianteDTO estudianteDTO) { 
        estudianteService.crearEstudiante(estudianteDTO); 
        return ResponseEntity.status(HttpStatus.CREATED).build(); 
    }

    // ---------------------------------------------------------------------------------------------------------------------------------
    // INCIDO C: Metodo para eliminar a un estudiante mediante el id
    // Implemente un endpoint en el controlador para eliminar un estudiante específico.
    // Asegúrese de recibir el ID del estudiante como parámetro en la URL.
    @DeleteMapping("/estudiantes/{id}") // <- Aca nos aseguramos de recibir un ID mediante la URL
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Long id) {
        estudianteService.eliminarEstudiante(id); 
        return ResponseEntity.noContent().build(); 
    }


}
