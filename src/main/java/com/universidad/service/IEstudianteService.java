package com.universidad.service; // Define el paquete al que pertenece esta interfaz

import com.universidad.dto.EstudianteDTO; // Importa la clase EstudianteDTO del paquete dto

import java.util.List; // Importa la interfaz List para manejar listas

public interface IEstudianteService {
    List<EstudianteDTO> obtenerTodosLosEstudiantes(); // Este ya está bien definido
    EstudianteDTO crearEstudiante(EstudianteDTO estudianteDTO); // Método para crear un estudiante y devolver un EstudianteDTO
    
    // Método para eliminar un estudiante dado su ID
    void eliminarEstudiante(Long id); // Método para eliminar un estudiante, no necesita devolver nada

    // Nuevo método para actualizar un estudiante
    EstudianteDTO actualizarEstudiante(Long id, EstudianteDTO estudianteDTO); // Método para actualizar un estudiante dado su ID
}
