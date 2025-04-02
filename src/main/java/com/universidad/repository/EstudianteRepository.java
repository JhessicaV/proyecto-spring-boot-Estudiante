package com.universidad.repository; 

import com.universidad.model.Estudiante; 
import org.springframework.stereotype.Repository;

import java.time.LocalDate; 
import java.util.ArrayList;
import java.util.List; 
import java.util.Map; 
import java.util.concurrent.ConcurrentHashMap; 
import java.util.concurrent.atomic.AtomicLong; 


@Repository 
public class EstudianteRepository {
    private final Map<Long, Estudiante> estudiantes = new ConcurrentHashMap<>(); 
    private final AtomicLong idContador = new AtomicLong(1); 

    // Método para obtener todos los estudiantes LISTADO
    public List<Estudiante> findAll() { 
        return new ArrayList<>(estudiantes.values()); 
    }
    // INICISO A: Métodos necesarios para actualizar un estudiante
    public Estudiante update(Estudiante estudiante) {
        estudiantes.put(estudiante.getId(), estudiante); 
        return estudiante; 
    }
    public Estudiante findById(Long id) {
        return estudiantes.get(id); 
    }

    // INICISO B : Método para guardar un estudiante en el repositorio
    public Estudiante save(Estudiante estudiante) { 
        if (estudiante.getId() == null) { 
            estudiante.setId(idContador.getAndIncrement()); 
        }
        estudiantes.put(estudiante.getId(), estudiante); 
        return estudiante; 
    }

    // INICISO C:  Método para eliminar un estudiante por su ID
    public void deleteById(Long id) { 
        estudiantes.remove(id); 




    
    // Método para inicializar algunos datos de ejemplo
    public void init() {
        Estudiante estudiante1 = Estudiante.builder() // Crea un estudiante usando el patrón builder
                .nombre("Juan") // Asigna el nombre
                .apellido("Pérez") // Asigna el apellido
                .email("juan.perez@example.com") // Asigna el email
                .fechaNacimiento(LocalDate.of(2000, 5, 15)) // Asigna la fecha de nacimiento
                .numeroInscripcion("S001") // Asigna el número de inscripción
                .build(); // Construye el objeto Estudiante
                
        Estudiante estudiante2 = Estudiante.builder() // Crea otro estudiante usando el patrón builder
                .nombre("María") // Asigna el nombre
                .apellido("González") // Asigna el apellido
                .email("maria.gonzalez@example.com") // Asigna el email
                .fechaNacimiento(LocalDate.of(2001, 8, 22)) // Asigna la fecha de nacimiento
                .numeroInscripcion("S002") // Asigna el número de inscripción
                .build(); // Construye el objeto Estudiante
                
        save(estudiante1); // Guarda el primer estudiante en el repositorio
        save(estudiante2); // Guarda el segundo estudiante en el repositorio
    }
}