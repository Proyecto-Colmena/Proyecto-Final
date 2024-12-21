package Colmena;

public class ValidacionDatos {

    // Valida que la capacidad de la colmena sea mayor a 0
    public static void validarCapacidadColmena(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad de la colmena debe ser mayor a 0.");
        }
    }

    // Valida que el nombre no sea nulo ni esté vacío
    public static void validarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío o nulo.");
        }
    }
}
