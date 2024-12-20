package Colmena;

public class ValidacionDatos {

    public static void validarCapacidadColmena(int capacidad) throws IllegalArgumentException {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad de la colmena debe ser mayor a 0.");
        }
    }

    public static void validarNombre(String nombre) throws IllegalArgumentException {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío o nulo.");
        }
    }
}
