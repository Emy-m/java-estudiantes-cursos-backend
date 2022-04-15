package ar.jdbc;

import ar.model.Curso;
import ar.model.Estudiante;
import ar.services.Estudiantes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JdbcEstudiantes implements Estudiantes {
    private List<Estudiante> estudiantes;

    public JdbcEstudiantes() {
        Estudiante p1 = new Estudiante("Joaquin", "Garcia");
        Estudiante p2 = new Estudiante("Emilio", "Peroz");
        Estudiante p3 = new Estudiante("Ernesto", "Perez");

        p1.addDireccion("San Martin 123");
        p2.addDireccion("Belgrano 34");
        p3.addDireccion("Bs As 908");

        p1.addTelefono("12345678");
        p1.addTelefono("45335678");
        p2.addTelefono("45633334");
        p3.addTelefono("11222356");

        Curso curso = new Curso("Angular", 10, 1);
        p1.addCurso(curso);
        p2.addCurso(curso);
        p3.addCurso(curso);

        this.estudiantes = new ArrayList<Estudiante>(List.of(p1, p2, p3));
    }

    @Override
    public List<Estudiante> estudiantes(String apellido) {
        if (apellido == null || apellido.isEmpty())
            return estudiantes;

        return estudiantes.stream().filter((p) -> {
            return p.containsApellido(apellido);
        }).collect(Collectors.toList());
    }

    @Override
    public void crearEstudiante(String nombre, String apellido, String direccion, String[] telefonos, String cursoId) {
        Estudiante e = new Estudiante(nombre, apellido);
        e.addDireccion(direccion);
        e.addTelefonos(telefonos);
        Curso curso = new JdbcCursos().curso(Integer.parseInt(cursoId));
        e.addCurso(curso);

        estudiantes.add(e);
    }
}
