package ar.web;

public class InscripcionDto {
    private String apellido;
    private String[] idCursos;

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String[] getIdCursos() {
        return idCursos;
    }

    public void setIdCursos(String[] idCursos) {
        this.idCursos = idCursos;
    }
}
