package ar.model;

public class NotNullNotEmpty {

    private static final String MSG = "Envíe un valor distinto de nulo o vacío";
    public String value;
    public String[] values;

    public NotNullNotEmpty(String value) {
        if (value == null)
            throw new EstudianteException(MSG);
        if (value.isEmpty())
            throw new EstudianteException(MSG);
        if (value.isBlank())
            throw new EstudianteException(MSG);
        this.value = value;
    }

    public NotNullNotEmpty(String[] numeros) {
        if (numeros == null) {
            throw new EstudianteException(MSG);
        }
        if (numeros.length == 0) {
            throw new EstudianteException(MSG);
        }
        if (numeros[0] == null || numeros[0].isBlank() || numeros[0].isEmpty()) {
            throw new EstudianteException(MSG);
        }
        this.values = numeros;
    }

    public String value() {
        return this.value;
    }

    public String[] values() {
        return this.values;
    }
}