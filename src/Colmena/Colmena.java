package Colmena;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static Colmena.Utilidades.printWithDelay;

public class Colmena implements Serializable {
    public String id;
    public String ubicacion;
    public String estadoSalud;
    public int cantidadAbejas;
    public double produccionMiel;
    public AbejaReina abejaReina;
    public List<Inspeccion> inspecciones;

    public Colmena(String id, String ubicacion, String estadoSalud, int cantidadAbejas, double produccionMiel) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.estadoSalud = estadoSalud;
        this.cantidadAbejas = cantidadAbejas;
        this.produccionMiel = produccionMiel;
        this.inspecciones = new ArrayList<>();
    }

    public void agregarAbejaReina(AbejaReina abejaReina) {
        this.abejaReina = abejaReina;
    }

    public void agregarInspeccion(Inspeccion inspeccion) {
        inspecciones.add(inspeccion);
    }

    public static void editarApicultor(Apicultor apicultor, List<Apicultor> apicultores) {
        printWithDelay("""
    📝 ¿Qué deseas editar?
    1. Nombre
    2. Teléfono
    """, 50);
        String opcion = Utilidades.getValidInput("Selecciona una opción: ", "[1-2]");
        switch (opcion) {
            case "1" -> {
                apicultor.nombre = Utilidades.getValidInput("🧑‍🌾 Nuevo nombre: ", ".+");
                printWithDelay("✅ Nombre actualizado.\n", 50);
            }
            case "2" -> {
                String nuevoTelefono = Utilidades.getValidInput("📞 Nuevo teléfono (10 dígitos): ", "\\d{10}");
                boolean telefonoExistente = apicultores.stream().anyMatch(a -> a.telefono != null && a.telefono.equals(nuevoTelefono) && !a.equals(apicultor));

                if (telefonoExistente) {
                    printWithDelay("\n⚠️ El teléfono " + nuevoTelefono + " ya está registrado por otro apicultor. Intenta con otro número.", 50);
                } else {
                    apicultor.telefono = nuevoTelefono;
                    printWithDelay("✅ Teléfono actualizado.\n", 50);
                }
            }
            default -> printWithDelay("⚠️ Opción no válida. No se realizaron cambios.\n", 50);
        }
    }

    public static void editarColmena(Colmena colmena) {
        printWithDelay("""
        📝 ¿Qué deseas editar?
        1. Ubicación
        2. Estado de Salud
        3. Cantidad de Abejas
        4. Producción de Miel
        """, 50);
        String opcion = Utilidades.getValidInput("Selecciona una opción: ", "[1-4]");
        switch (opcion) {
            case "1" -> {
                colmena.ubicacion = Utilidades.getValidInput("📍 Nueva ubicación: ", ".+");
                printWithDelay("✅ Ubicación actualizada.\n", 50);
            }
            case "2" -> {
                colmena.estadoSalud = Utilidades.getValidInput("❤️ Nuevo estado de salud (Buena/Regular/Mala): ", "Buena|Regular|Mala");
                printWithDelay("✅ Estado de salud actualizado.\n", 50);
            }
            case "3" -> {
                colmena.cantidadAbejas = Integer.parseInt(Utilidades.getValidInput("🐝 Nueva cantidad de abejas: ", "[1-9]\\d*"));
                printWithDelay("✅ Cantidad de abejas actualizada.\n", 50);
            }
            case "4" -> {
                colmena.produccionMiel = Double.parseDouble(Utilidades.getValidInput("🍯 Nueva producción de miel (kg): ", "^(?!0(\\.0+)?$)(\\d+(\\.\\d+)?|\\.\\d+)$"));
                printWithDelay("✅ Producción de miel actualizada.\n", 50);
            }
            default -> printWithDelay("⚠️ Opción no válida. No se realizaron cambios.\n", 50);
        }
    }

    public void mostrarInfo(int numeroColmena, Map<String, Apicultor> asignaciones) {
        StringBuilder colmenaInfo = new StringBuilder();
        colmenaInfo.append(String.format("""
        \r===============================
        # Colmena %d
        ===============================
        🐝 ID: %s
        📍 Ubicación: %s
        ❤️ Estado de Salud: %s
        🐝 Cantidad de Abejas: %d
        🍯 Producción de Miel: %.2f kg
        """, numeroColmena, id, ubicacion, estadoSalud, cantidadAbejas, produccionMiel)).append("\n");

        if (abejaReina != null) {
            colmenaInfo.append("👑 Información de la Abeja Reina:").append("\n");
            colmenaInfo.append(abejaReina.getInfo()).append("\n");
        }

        if (asignaciones.containsKey(id)) {
            Apicultor apicultor = asignaciones.get(id);
            colmenaInfo.append(String.format("🧑‍🌾 Apicultor Asignado: %s (Tel: %s)\n", apicultor.nombre, apicultor.telefono)).append("\n");
        }

        if (!inspecciones.isEmpty()) {
            colmenaInfo.append("📜 Historial de Inspecciones:").append("\n");
            colmenaInfo.append(Inspeccion.getInfo(inspecciones, 1)).append("\n");
        } else {
            colmenaInfo.append("⚠️ No hay inspecciones registradas para esta colmena.\n");
        }
        System.out.println(colmenaInfo);
    }
}
