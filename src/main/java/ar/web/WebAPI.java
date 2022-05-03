package ar.web;

import ar.model.Curso;
import ar.model.Estudiante;
import ar.model.EstudianteException;
import ar.services.Cursos;
import ar.services.Estudiantes;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WebAPI {

    private Estudiantes estudiantes;
    private Cursos cursos;
    private int webPort;

    public WebAPI(Estudiantes estudiantes, Cursos cursos, int webPort) {
        this.estudiantes = estudiantes;
        this.cursos = cursos;
        this.webPort = webPort;
    }

    public void start() {
        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
        }).start(this.webPort);
        app.get("/estudiantes", traerEstudiantes());
        app.get("/cursos", traerCursos());
        app.post("/estudiantes", crearEstudiante());
        app.post("/inscribir", inscribirEnCursos());

        app.exception(EstudianteException.class, (e, ctx) -> {
            ctx.json(Map.of("result", "error", "message", e.getMessage()));
            // log error in a stream...
        });

        app.exception(Exception.class, (e, ctx) -> {
            ctx.json(Map.of("result", "error", "message", "Ups... algo se rompió.: " + e.getMessage()));
            // log error in a stream...
        });
    }

    private Handler traerCursos() {
        return ctx -> {
            List<Curso> cursos = this.cursos.cursos();

            var list = new ArrayList<Map<String, Object>>();

            for (Curso c : cursos) {
                list.add(c.toMap());
            }

            ctx.json(Map.of("result", "success", "cursos", list));
        };
    }

    private Handler crearEstudiante() {
        return ctx -> {
            EstudianteDto dto = ctx.bodyAsClass(EstudianteDto.class);
            this.estudiantes.crearEstudiante(dto.getNombre(), dto.getApellido(),
                    dto.getDireccion(), dto.getTelefonos(), dto.getCursos());
            ctx.json(Map.of("result", "success"));
        };
    }

    private Handler traerEstudiantes() {
        return ctx -> {
            String apellido = ctx.queryParam("apellido");
            List<Estudiante> estudiantes = this.estudiantes.estudiantes(apellido);

            var list = new ArrayList<Map<String, Object>>();

            for (Estudiante e : estudiantes) {
                list.add(e.toMap());
            }

            ctx.json(Map.of("result", "success", "estudiantes", list));
        };
    }

    private Handler inscribirEnCursos() {
        return ctx -> {
            InscripcionDto dto = ctx.bodyAsClass(InscripcionDto.class);
            ArrayList<Curso> cursosNuevos = new ArrayList<Curso>();

            for(String curso : dto.getIdCursos()) {
                cursosNuevos.add(cursos.curso(Integer.parseInt(curso)));
            }

            this.estudiantes.inscribirEnCursos(dto.getApellido(), cursosNuevos);
            ctx.json(Map.of("result", "success"));
        };
    }
}
