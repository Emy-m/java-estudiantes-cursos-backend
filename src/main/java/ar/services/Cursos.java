package ar.services;

import ar.model.Curso;

import java.util.List;

public interface Cursos {
    List<Curso> cursos();

    Curso curso(int id);

    void crearCurso(String nombre, int horasSemanales, int id);
}
