package ar.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Estudiante {

    private String nombre;
    private String apellido;
    private Direccion direccion;
    private List<Telefono> telefonos;

    private List<Curso> cursos;

    public Estudiante(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefonos = new ArrayList<Telefono>();
        this.cursos = new ArrayList<Curso>();
    }

    public void addDireccion(String direccion) {
        this.direccion = new Direccion(new NotNullNotEmpty(direccion).value());
    }

    public void addCurso(Curso curso) {
        this.cursos.add(curso);
    }

    public void addTelefono(String numero) {
        this.telefonos.add(new Telefono(new NotNullNotEmpty(numero).value()));
    }

    public void addTelefonos(String[] numeros) {

        String[] telefonos = new NotNullNotEmpty(numeros).values();

        var tel = List.of(telefonos).stream().map((n) -> {
            return new Telefono(n);
        }).collect(Collectors.toList());

        this.telefonos.addAll(tel);
    }

    public String nombre() {
        return nombre + " " + apellido;
    }

    public Map<String, Object> toMap() {
        var map = new HashMap<String, Object>(
                Map.of("nombre", nombre, "apellido", apellido));

        if (this.direccion != null) {
            map.put("direccion", this.direccion.toMap());
        }

        if (this.telefonos != null && this.telefonos.size() > 0) {
            map.put("telefonos", telefonos.stream().map((e) -> e.toMap())
                    .collect(Collectors.toList()));
        }

        if (this.cursos != null && this.cursos.size() > 0) {
            map.put("cursos", cursos.stream().map((e) -> e.toMap())
                    .collect(Collectors.toList()));
        }

        return map;
    }

    public boolean containsApellido(String apellido) {
        return this.apellido.contains(apellido);
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", direccion=" + direccion +
                ", telefonos=" + telefonos +
                ", cursos=" + cursos +
                '}';
    }
}
