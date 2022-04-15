package ar.services;

import ar.model.Estudiante;

import java.util.List;

public interface Estudiantes {
    List<Estudiante> estudiantes(String apellido);

    void crearEstudiante(String nombre, String apellido, String direccion, String[] telefonos, String cursoId);
}
