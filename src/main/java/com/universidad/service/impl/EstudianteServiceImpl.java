package com.universidad.service.impl; // Define el paquete al que pertenece esta clase

import com.universidad.dto.EstudianteDTO; // Importa la clase EstudianteDTO del paquete dto
import com.universidad.model.Estudiante; // Importa la clase Estudiante del paquete model
import com.universidad.repository.EstudianteRepository; // Importa la clase EstudianteRepository del paquete repository
import com.universidad.service.IEstudianteService; // Importa la interfaz IEstudianteService del paquete service

import jakarta.annotation.PostConstruct; // Importa la anotación PostConstruct de Jakarta
import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación Autowired de Spring
import org.springframework.stereotype.Service; // Importa la anotación Service de Spring

import java.util.ArrayList; // Importa la clase ArrayList para manejar listas
import java.util.List; // Importa la interfaz List para manejar listas

@Service // Anotación que indica que esta clase es un servicio de Spring
public class EstudianteServiceImpl implements IEstudianteService { // Define la clase EstudianteServiceImpl que implementa la interfaz IEstudianteService

    private final EstudianteRepository estudianteRepository; // Declara una variable final para el repositorio de estudiantes
    private List<Estudiante> estudiantesEnMemoria = new ArrayList<>(); // Contenedor en memoria para estudiantes

    @Autowired // Anotación que indica que el constructor debe ser usado para inyección de dependencias
    public EstudianteServiceImpl(EstudianteRepository estudianteRepository) { // Constructor que recibe el repositorio de estudiantes
        this.estudianteRepository = estudianteRepository; // Asigna el repositorio de estudiantes a la variable de instancia
    }
    
    @PostConstruct // Anotación que indica que este método debe ejecutarse después de la construcción del bean
    public void init() { // Método para inicializar datos de ejemplo
        estudianteRepository.init(); // Llama al método init del repositorio de estudiantes
    }

    @Override // Anotación que indica que este método sobrescribe un método de la interfaz
    public List<EstudianteDTO> obtenerTodosLosEstudiantes() { // Método para obtener una lista de todos los EstudianteDTO
        List<EstudianteDTO> estudiantesDTO = new ArrayList<>(); // Crea una nueva lista para los EstudianteDTO
        
        for (Estudiante estudiante : estudiantesEnMemoria) { // Itera sobre la lista de estudiantes en memoria
            estudiantesDTO.add(convertToDTO(estudiante)); // Convierte cada estudiante a EstudianteDTO y lo agrega a la lista
        }
        return estudiantesDTO; // Retorna la lista de EstudianteDTO
    }

    // Nuevo método para crear un estudiante
    @Override
    public EstudianteDTO crearEstudiante(EstudianteDTO estudianteDTO) {
        Estudiante estudiante = convertToEntity(estudianteDTO); // Convierte el DTO a entidad
        
        // Guarda el estudiante en el repositorio (esto generará el ID)
        estudiante = estudianteRepository.save(estudiante); 
        
        // También agrega el estudiante a la lista en memoria para poder acceder a él rápidamente
        estudiantesEnMemoria.add(estudiante);
        
        // Devuelve el DTO del estudiante creado
        return convertToDTO(estudiante); 
    }
    // metodo para borrar un estudiante
    @Override
    public void eliminarEstudiante(Long id) {
        Estudiante estudianteAEliminar = null;
        
        // Buscamos el estudiante en la lista en memoria
        for (Estudiante estudiante : estudiantesEnMemoria) {
            if (estudiante.getId().equals(id)) {
                estudianteAEliminar = estudiante; // Encontramos al estudiante
                break;
            }
        }
        
        // Si encontramos al estudiante, lo eliminamos de la lista
        if (estudianteAEliminar != null) {
            estudiantesEnMemoria.remove(estudianteAEliminar); // Elimina el estudiante de la lista
        } else {
            // Si no lo encontramos, lanzamos una excepción
            throw new RuntimeException("Estudiante no encontrado con el ID: " + id);
        }
    }

    //metodo para modificar un estudiante
    // Método para actualizar un estudiante
    @Override
    public EstudianteDTO actualizarEstudiante(Long id, EstudianteDTO estudianteDTO) {
        Estudiante estudianteExistente = null;
        
        // Buscamos el estudiante en la lista en memoria
        for (Estudiante estudiante : estudiantesEnMemoria) {
            if (estudiante.getId().equals(id)) {
                estudianteExistente = estudiante; // Encontramos al estudiante
                break;
            }
        }
        
        // Si no encontramos al estudiante, lanzamos una excepción
        if (estudianteExistente == null) {
            throw new RuntimeException("Estudiante no encontrado con el ID: " + id);
        }
        
        // Si encontramos al estudiante, actualizamos sus datos
        estudianteExistente.setNombre(estudianteDTO.getNombre());
        estudianteExistente.setApellido(estudianteDTO.getApellido());
        estudianteExistente.setEmail(estudianteDTO.getEmail());
        
        // Aquí manejamos la fecha correctamente, ya que el DTO tiene LocalDate
        estudianteExistente.setFechaNacimiento(estudianteDTO.getFechaNacimiento());
        
        estudianteExistente.setNumeroInscripcion(estudianteDTO.getNumeroInscripcion());
        
        // Devuelve el estudiante actualizado convertido a DTO
        return convertToDTO(estudianteExistente);
    }


    

    // Método auxiliar para convertir entidad a DTO
    private EstudianteDTO convertToDTO(Estudiante estudiante) { // Método para convertir un Estudiante a EstudianteDTO
        return EstudianteDTO.builder() // Usa el patrón builder para crear un EstudianteDTO
                .id(estudiante.getId()) // Asigna el ID
                .nombre(estudiante.getNombre()) // Asigna el nombre
                .apellido(estudiante.getApellido()) // Asigna el apellido
                .email(estudiante.getEmail()) // Asigna el email
                .fechaNacimiento(estudiante.getFechaNacimiento()) // Asigna la fecha de nacimiento
                .numeroInscripcion(estudiante.getNumeroInscripcion()) // Asigna el número de inscripción
                .build(); // Construye el objeto EstudianteDTO
    }
    
    // Método auxiliar para convertir DTO a entidad
    private Estudiante convertToEntity(EstudianteDTO estudianteDTO) { // Método para convertir un EstudianteDTO a Estudiante
        return Estudiante.builder() // Usa el patrón builder para crear un Estudiante
                .id(estudianteDTO.getId()) // Asigna el ID
                .nombre(estudianteDTO.getNombre()) // Asigna el nombre
                .apellido(estudianteDTO.getApellido()) // Asigna el apellido
                .email(estudianteDTO.getEmail()) // Asigna el email
                .fechaNacimiento(estudianteDTO.getFechaNacimiento()) // Asigna la fecha de nacimiento
                .numeroInscripcion(estudianteDTO.getNumeroInscripcion()) // Asigna el número de inscripción
                .build(); // Construye el objeto Estudiante
    }
}
