package ar.services;

import ar.model.Curso;
import ar.model.Estudiante;

import java.util.ArrayList;
import java.util.List;

public interface Estudiantes {
    List<Estudiante> estudiantes(String apellido);

    void crearEstudiante(String nombre, String apellido, String direccion, String[] telefonos, String[] cursos);

    void inscribirEnCursos(String apellido, ArrayList<Curso> cursosNuevos);
}
