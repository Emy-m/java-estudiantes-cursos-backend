package ar.model;

import java.util.HashMap;
import java.util.Map;

public class Curso {

    private int id;
    private String nombre;
    private int horasSemanales;

    public Curso(String nombre, int horasSemanales, int id) {
        this.nombre = new NotNullNotEmpty(nombre).value();
        this.horasSemanales = new NotNegativeNumber(horasSemanales).value();
        this.id = id;
    }

    public String nombre() {
        return nombre;
    }

    public int horasSemanales() {
        return horasSemanales;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<String, Object> toMap() {
        var map = new HashMap<String, Object>(
                Map.of("nombre", nombre, "horas", horasSemanales, "id", id));

        return map;
    }
}
