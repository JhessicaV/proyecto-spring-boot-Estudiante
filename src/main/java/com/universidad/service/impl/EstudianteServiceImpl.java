package com.universidad.service.impl; 

import com.universidad.dto.EstudianteDTO;
import com.universidad.model.Estudiante; 
import com.universidad.repository.EstudianteRepository; 
import com.universidad.service.IEstudianteService; 

import jakarta.annotation.PostConstruct; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 

import java.util.ArrayList; 
import java.util.List; 
@Service 
public class EstudianteServiceImpl implements IEstudianteService { 

    private final EstudianteRepository estudianteRepository; 
    private List<Estudiante> estudiantesEnMemoria = new ArrayList<>(); 

    // Anotación que indica que el constructor debe ser usado para inyección de dependencias
    @Autowired 
    public EstudianteServiceImpl(EstudianteRepository estudianteRepository) { 
        this.estudianteRepository = estudianteRepository; 
    }
    // Anotación que indica que este método debe ejecutarse después de la construcción del bean
    @PostConstruct 
    public void init() {
        estudianteRepository.init(); 
    }

    // Método para obtener una lista de todos los EstudianteDTO
    @Override 
    public List<EstudianteDTO> obtenerTodosLosEstudiantes() { 
        List<EstudianteDTO> estudiantesDTO = new ArrayList<>(); 
        
        for (Estudiante estudiante : estudiantesEnMemoria) { 
            estudiantesDTO.add(convertToDTO(estudiante)); 
        }
        return estudiantesDTO; 
    }

    // INCISO A: Método para actualizar un estudiante
    @Override
    public EstudianteDTO actualizarEstudiante(Long id, EstudianteDTO estudianteDTO) {
        Estudiante estudianteExistente = null;
        for (Estudiante estudiante : estudiantesEnMemoria) {
            if (estudiante.getId().equals(id)) {
                estudianteExistente = estudiante; 
                break;
            }
        }
        if (estudianteExistente == null) {
            throw new RuntimeException("Estudiante no encontrado con el ID: " + id);
        }
        estudianteExistente.setNombre(estudianteDTO.getNombre());
        estudianteExistente.setApellido(estudianteDTO.getApellido());
        estudianteExistente.setEmail(estudianteDTO.getEmail());
        estudianteExistente.setFechaNacimiento(estudianteDTO.getFechaNacimiento());
        estudianteExistente.setNumeroInscripcion(estudianteDTO.getNumeroInscripcion());
        return convertToDTO(estudianteExistente);
    }

    // INICISO B: método para crear un estudiante
    @Override
    public EstudianteDTO crearEstudiante(EstudianteDTO estudianteDTO) {
        Estudiante estudiante = convertToEntity(estudianteDTO); 
        estudiante = estudianteRepository.save(estudiante); 
        estudiantesEnMemoria.add(estudiante);
        return convertToDTO(estudiante); 
    }

    // INCISO C: metodo para borrar un estudiante
    @Override
    public void eliminarEstudiante(Long id) {
        Estudiante estudianteAEliminar = null;
        for (Estudiante estudiante : estudiantesEnMemoria) {
            if (estudiante.getId().equals(id)) {
                estudianteAEliminar = estudiante; 
                break;
            }
        }
        if (estudianteAEliminar != null) {
            estudiantesEnMemoria.remove(estudianteAEliminar);
        } else {
            throw new RuntimeException("Estudiante no encontrado con el ID: " + id);
        }
    }

    // Método auxiliar para convertir entidad a DTO
    private EstudianteDTO convertToDTO(Estudiante estudiante) { 
        return EstudianteDTO.builder() 
                .id(estudiante.getId()) 
                .nombre(estudiante.getNombre()) 
                .apellido(estudiante.getApellido()) 
                .email(estudiante.getEmail()) 
                .fechaNacimiento(estudiante.getFechaNacimiento()) 
                .numeroInscripcion(estudiante.getNumeroInscripcion()) 
                .build(); 
    }
    
    // Método auxiliar para convertir DTO a entidad
    private Estudiante convertToEntity(EstudianteDTO estudianteDTO) { 
        return Estudiante.builder() 
                .id(estudianteDTO.getId()) 
                .nombre(estudianteDTO.getNombre()) 
                .apellido(estudianteDTO.getApellido()) 
                .email(estudianteDTO.getEmail()) 
                .fechaNacimiento(estudianteDTO.getFechaNacimiento()) 
                .numeroInscripcion(estudianteDTO.getNumeroInscripcion()) 
                .build(); 
    }
}
