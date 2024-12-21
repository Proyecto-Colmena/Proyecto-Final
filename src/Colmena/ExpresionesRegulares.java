import java.util.Scanner;
import java.util.regex.Pattern;

public class ExpresionesRegulares {

        // Método principal
        public static void main(String[] args) {
            // Creamos un escáner para recibir entradas del usuario.
            Scanner scanner = new Scanner(System.in);

            System.out.println("Bienvenido al Sistema de Gestión Apícola - Validación de Entradas");

            // Validación de nombres
            System.out.println("\nPor favor, ingresa un nombre:");
            String nombre = scanner.nextLine();
            if (validarNombre(nombre)) {
                System.out.println("Nombre válido.");
            } else {
                System.out.println("Nombre inválido. El nombre solo puede contener letras y espacios, y debe tener entre 2 y 50 caracteres.");
            }

            // Validación de identificadores
            System.out.println("\nPor favor, ingresa un identificador (alfanumérico, comienza con letra, 5-15 caracteres):");
            String identificador = scanner.nextLine();
            if (validarIdentificador(identificador)) {
                System.out.println("Identificador válido.");
            } else {
                System.out.println("Identificador inválido. Debe ser alfanumérico, comenzar con una letra y tener entre 5 y 15 caracteres.");
            }

            // Cerramos el escáner.
            scanner.close();
        }

        /**
         * Método para validar nombres.
         * Un nombre válido contiene únicamente letras (mayúsculas o minúsculas) y espacios.
         * Debe tener entre 2 y 50 caracteres.
         *
         * @param nombre String ingresado por el usuario.
         * @return true si el nombre es válido, false de lo contrario.
         */
        public static boolean validarNombre(String nombre) {
            // Expresión regular para nombres.
            String patronNombre = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{2,50}$";
            return Pattern.matches(patronNombre, nombre);
        }

        /**
         * Método para validar identificadores.
         * Un identificador válido es alfanumérico, debe comenzar con una letra
         * y tener entre 5 y 15 caracteres.
         *
         * @param identificador String ingresado por el usuario.
         * @return true si el identificador es válido, false de lo contrario.
         */
        public static boolean validarIdentificador(String identificador) {
            // Expresión regular para identificadores.
            String patronIdentificador = "^[a-zA-Z][a-zA-Z0-9]{4,14}$";
            return Pattern.matches(patronIdentificador, identificador);
        }
    }