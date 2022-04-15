package ar.jdbc;

import ar.model.Curso;
import ar.services.Cursos;

import java.util.List;

public class JdbcCursos implements Cursos {
    private List<Curso> cursos;

    public JdbcCursos() {
        Curso c1 = new Curso("Angular", 10, 1);
        Curso c2 = new Curso("React", 8, 2);
        Curso c3 = new Curso("Vue", 6, 3);

        this.cursos = List.of(c1, c2, c3);
    }

    @Override
    public List<Curso> cursos() {
        return this.cursos;
    }

    @Override
    public Curso curso(int id) {
        for(Curso c : this.cursos) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void crearCurso(String nombre, int horasSemanales, int id) {
        this.cursos.add(new Curso(nombre, horasSemanales, id));
    }
}
