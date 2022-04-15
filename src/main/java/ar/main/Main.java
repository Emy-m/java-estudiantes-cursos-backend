package ar.main;
import ar.jdbc.JdbcCursos;
import ar.jdbc.JdbcEstudiantes;
import ar.web.WebAPI;

public class Main {
    public static void main(String[] args) {
        WebAPI servicio = new WebAPI(new JdbcEstudiantes(), new JdbcCursos(), 1234);
        servicio.start();
    }
}
