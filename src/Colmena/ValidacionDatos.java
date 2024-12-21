package Colmena;

//Clase para validar datos relacionados con la colmena
public class ValidacionDatos {

    
    // Valida que la capacidad de la colmena sea mayor a 0
    public static void validarCapacidadColmena(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad de la colmena debe ser mayor a 0.");
        }
    }

    
    // Valida que el nombre no esté vacío, nulo o contenga caracteres no permitidos
    public static void validarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {      // Solo letras y espacios
            throw new IllegalArgumentException("El nombre no puede estar vacío o nulo.");
        }
    }
}
