
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Sistema de Gestión Apícola - Validación de Entradas
 * Este programa valida nombres e identificadores ingresados por el usuario
 * según criterios específicos utilizando expresiones regulares.
 */
public class ExpresionesRegulares {

    // Constantes para los patrones de validación
    private static final String PATRON_NOMBRE = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{2,50}$";
    private static final String PATRON_IDENTIFICADOR = "^[a-zA-Z][a-zA-Z0-9]{4,14}$";

    public static void main(String[] args) {
        // Crear el escáner para entradas del usuario
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Bienvenido al Sistema de Gestión Apícola ===");
        System.out.println("Este sistema valida nombres e identificadores.\n");

        // Validar nombre
        validarEntradaUsuario(scanner, "nombre", PATRON_NOMBRE, 
                "El nombre solo puede contener letras y espacios, y debe tener entre 2 y 50 caracteres.");

        // Validar identificador
        validarEntradaUsuario(scanner, "identificador", PATRON_IDENTIFICADOR, 
                "El identificador debe ser alfanumérico, comenzar con una letra y tener entre 5 y 15 caracteres.");

        // Cerrar el escáner
        scanner.close();
    }

    /**
     * Valida una entrada del usuario según un patrón proporcionado.
     *
     * @param scanner Escáner para leer la entrada del usuario.
     * @param tipoEntrada Tipo de dato que se está validando (e.g., "nombre").
     * @param patron Expresión regular que define el criterio de validación.
     * @param mensajeError Mensaje de error que se muestra si la validación falla.
     */
    private static void validarEntradaUsuario(Scanner scanner, String tipoEntrada, String patron, String mensajeError) {
        System.out.printf("Por favor, ingresa un %s:%n", tipoEntrada);
        String entrada = scanner.nextLine();

        if (entrada == null || entrada.isBlank()) {
            System.out.println("Entrada inválida. El valor no puede estar vacío.");
            return;
        }

        if (Pattern.matches(patron, entrada)) {
            System.out.printf("%s válido.%n", capitalize(tipoEntrada));
        } else {
            System.out.printf("%s inválido. %s%n", capitalize(tipoEntrada), mensajeError);
        }
    }

    /**
     * Capitaliza la primera letra de una cadena.
     *
     * @param texto Texto a capitalizar.
     * @return Texto con la primera letra en mayúscula.
     */
    private static String capitalize(String texto) {
        if (texto == null || texto.isEmpty()) {
            return texto;
        }
        return texto.substring(0, 1).toUpperCase() + texto.substring(1);
    }
}
