package Colmena;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class Utilidades {
    private static final Lock inputLock = new ReentrantLock();
    public static void printWithDelay(String text, int delayMs) {
        inputLock.lock();
        try {
            for (char c : text.toCharArray()) {
                System.out.print(c);
                try {
                    Thread.sleep(delayMs);
                } catch (InterruptedException ignored) {
                }
            }
            System.out.println();
        } finally {
            inputLock.unlock();
        }
    }

    public static void animacionAbejas() {
        inputLock.lock();
        try {
            String[] frames = {
                    "      🌺     ",
                    "   🐝         ",
                    "         🐝   ",
                    "      🍯     ",
            };

            for (int i = 0; i < 5; i++) {
                for (String frame : frames) {
                    System.out.print("\r" + frame);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException ignored) {
                    }
                }
            }
            System.out.println("\r🐝 Las abejas están listas para trabajar.\n");
        } finally {
            inputLock.unlock();
        }
    }

    public static void animacionBusqueda() {
        inputLock.lock();
        try {
            String[] frames = {
                    "      🔍     ",
                    "   🔎         ",
                    "         🔍   ",
                    "      🔎     ",
            };

            for (int i = 0; i < 5; i++) {
                for (String frame : frames) {
                    System.out.print("\r" + frame);
                    try {
                        TimeUnit.MILLISECONDS.sleep(300);
                    } catch (InterruptedException ignored) {}
                }
            }
        } finally {
            inputLock.unlock();
        }
    }

    public static String getValidInput(String prompt, String regex) {
        inputLock.lock();
        try {
            Scanner scanner = new Scanner(System.in);
            String input;
            while (true) {
                System.out.print(prompt);
                input = scanner.nextLine().trim();

                if (!input.isEmpty()) {
                    input = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
                }

                if (input.matches(regex)) {
                    return input;
                }
                System.out.println("⚠️ Entrada inválida. Inténtalo de nuevo.\n");
            }
        } finally {
            inputLock.unlock();
        }
    }

    public static void runWithBlock(Runnable task) {
        Thread thread = new Thread(task);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException ignored) {
        }
    }

    public static void verificarDatos(List<Colmena> colmenas, List<Apicultor> apicultores, Map<String, Apicultor> asignaciones) {
        Scanner scanner = new Scanner(System.in);

        runWithBlock(() -> printWithDelay("🗃️ Verificando integridad de datos...", 50));
        animacionBusqueda();
        for (Colmena colmena : colmenas) {
            if (colmena.id == null || !colmena.id.matches("C\\d{3}")) {
                printWithDelay("\r⚠️ Falta el ID de una colmena. Proporcione un nuevo ID (formato CXXX): ", 50);
                colmena.id = getValidInput("ID: ", "C\\d{3}");
            }
            if (colmena.ubicacion == null || colmena.ubicacion.isEmpty()) {
                printWithDelay("\r⚠️ Falta la ubicación de la colmena con ID " + colmena.id + ". Proporcione una ubicación: ", 50);
                colmena.ubicacion = scanner.nextLine();
            }
            if (colmena.estadoSalud == null || !colmena.estadoSalud.matches("Buena|Regular|Mala")) {
                printWithDelay("\r⚠️ Falta el estado de salud de la colmena con ID " + colmena.id + ". Proporcione un estado (Buena/Regular/Mala): ", 50);
                colmena.estadoSalud = getValidInput("Estado de Salud: ", "Buena|Regular|Mala");
            }
            if (!asignaciones.containsKey(colmena.id)) {
                printWithDelay("\r⚠️ No hay un apicultor asignado para la colmena con ID " + colmena.id + ".", 50);
                printWithDelay("\n🧑‍🌾 Asignando un apicultor existente:\n", 50);

                for (int i = 0; i < apicultores.size(); i++) {
                    System.out.printf("[%d] %s (Tel: %s)\n", i + 1, apicultores.get(i).nombre, apicultores.get(i).telefono);
                }

                int opcion = Integer.parseInt(getValidInput("\nSelecciona un apicultor por número: ", "\\d+")) - 1;
                if (opcion >= 0 && opcion < apicultores.size()) {
                    asignaciones.put(colmena.id, apicultores.get(opcion));
                    printWithDelay("\n✅ Apicultor asignado correctamente.\n", 50);
                } else {
                    printWithDelay("⚠️ Opción no válida. Se omitió la asignación.\n", 50);
                }
            }
        }

        for (Apicultor apicultor : apicultores) {
            if (apicultor.nombre == null || apicultor.nombre.isEmpty()) {
                printWithDelay("\r⚠️ Falta el nombre de un apicultor. Proporcione un nombre: ", 50);
                apicultor.nombre = scanner.nextLine();
            }
            if (apicultor.telefono == null || !apicultor.telefono.matches("\\d{10}")) {
                printWithDelay("\r⚠️ Falta el teléfono del apicultor " + apicultor.nombre + ". Proporcione un teléfono (10 dígitos): ", 50);

                String telefono = getValidInput("Teléfono: ", "\\d{10}");
                boolean telefonoExistente = apicultores.stream().anyMatch(a -> a.telefono != null && a.telefono.equals(telefono));

                if (telefonoExistente) {
                    printWithDelay("\n⚠️ El teléfono " + telefono + " ya está registrado por otro apicultor. Intenta con otro número.", 50);
                } else {
                    apicultor.telefono = telefono;
                }
            }
        }

        printWithDelay("\r✅ Verificación completa.\n", 50);
    }

    public static void cargarDatos(List<Colmena> colmenas, List<Apicultor> apicultores, Map<String, Apicultor> asignaciones, String dataFile) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFile))) {
            DatosApicola datos = (DatosApicola) ois.readObject();
            colmenas.addAll(datos.colmenas);
            apicultores.addAll(datos.apicultores);
            asignaciones.putAll(datos.asignaciones);
            verificarDatos(colmenas, apicultores, asignaciones);
        }
    }

    public static void guardarDatos(List<Colmena> colmenas, List<Apicultor> apicultores, Map<String, Apicultor> asignaciones, String dataFile) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile))) {
            oos.writeObject(new DatosApicola(colmenas, apicultores, asignaciones));
            printWithDelay("✅ Datos guardados correctamente.\n", 50);
        }
    }
}
