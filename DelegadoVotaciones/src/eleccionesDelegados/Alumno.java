package eleccionesDelegados;

import java.util.Objects;

public class Alumno {

    private String name;

    public Alumno(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alumno)) return false;
        Alumno alumno = (Alumno) o;
        return name.equalsIgnoreCase(alumno.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
