package com.universidad.service; 

import com.universidad.dto.EstudianteDTO;

import java.util.List; 

public interface IEstudianteService {
    //Listado de estudiantes
    List<EstudianteDTO> obtenerTodosLosEstudiantes(); 

    // INICISO A: Nuevo método para actualizar un estudiante
    EstudianteDTO actualizarEstudiante(Long id, EstudianteDTO estudianteDTO); 
    
    // INICSO B:  Método para crear un estudiante y devolver un EstudianteDTO
    EstudianteDTO crearEstudiante(EstudianteDTO estudianteDTO); 
    
    // INCISO C: Método para eliminar un estudiante dado su ID
    void eliminarEstudiante(Long id); 

}
