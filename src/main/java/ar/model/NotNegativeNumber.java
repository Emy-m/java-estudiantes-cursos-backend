package ar.model;

public class NotNegativeNumber {
    private static final String MSG = "Envíe un valor mayor a 0";
    private int value;

    public NotNegativeNumber(int value) {
        if (value < 0) {
            throw new CursoException(MSG);
        }

        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
