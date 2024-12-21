package Colmena;
import java.io.Serializable;
import java.util.*;
import static Colmena.Utilidades.animacionAbejas;
import static Colmena.Utilidades.runWithBlock;

public class Inspeccion implements Serializable {
    public Date fecha;
    public String resultado;
    public String acciones;

    public Inspeccion(Date fecha, String resultado, String acciones) {
        this.fecha = fecha;
        this.resultado = resultado;
        this.acciones = acciones;
    }

    public static String getInfo(List<Inspeccion> inspecciones, int nivel) {
        if (inspecciones.isEmpty()) {
            return "";
        }
        Inspeccion inspeccion = inspecciones.getFirst();
        String result = " ".repeat(nivel * 4) +
                String.format("📅 Fecha: %s\n", inspeccion.fecha) +
                " ".repeat(nivel * 4) +
                String.format("🔍 Resultado: %s\n", inspeccion.resultado) +
                " ".repeat(nivel * 4) +
                String.format("🛠️ Acciones: %s\n", inspeccion.acciones);

        if (inspecciones.size() > 1) {
            result += " ".repeat(nivel * 4) + "----------------------------------\n";
        }

        return result + getInfo(inspecciones.subList(1, inspecciones.size()), nivel + 1);
    }

    public static void realizarInspecciones(List<Colmena> colmenas) {
        if (colmenas.isEmpty()) {
            runWithBlock(() -> Utilidades.printWithDelay("⚠️ No hay colmenas registradas para inspeccionar.\n", 50));
            return;
        }

        List<Thread> hilos = new ArrayList<>();
        List<String> mensajesResultado = Collections.synchronizedList(new ArrayList<>());

        for (Colmena colmena : colmenas) {
            Thread hilo = new Thread(() -> {
                try {
                    runWithBlock(() -> Utilidades.printWithDelay("🕵️ Inspeccionando la colmena " + colmena.id + "...", 50));

                    Random random = new Random();
                    int tiempoInspeccion = random.nextInt(1000) + 500;
                    Thread.sleep(tiempoInspeccion);

                    int estadoPuntos = (colmena.abejaReina != null && colmena.abejaReina.estadoSalud.equals("Buena")) ? 2 : 0;
                    estadoPuntos += switch (colmena.estadoSalud) {
                        case "Buena" -> 2;
                        case "Regular" -> 1;
                        default -> 0;
                    };

                    String resultado = estadoPuntos >= 3 ? "Estado óptimo" :
                            estadoPuntos == 2 ? "Necesita monitoreo" : "Urgente atención";

                    String acciones = switch (resultado) {
                        case "Estado óptimo" -> "Revisión en 6 meses";
                        case "Necesita monitoreo" -> "Revisar en 3 meses";
                        default -> "Reparaciones urgentes y suplementar alimentación";
                    };

                    Inspeccion inspeccion = new Inspeccion(new Date(), resultado, acciones);
                    colmena.agregarInspeccion(inspeccion);

                    String mensajeFin = resultado.equals("Estado óptimo")
                            ? "\r✅ Inspección completada con éxito para la colmena " + colmena.id + "."
                            : "\r⚠️ La inspección terminó con algunos problemas para la colmena " + colmena.id + ".";

                    mensajesResultado.add("\n" + mensajeFin);
                } catch (InterruptedException e) {
                    runWithBlock(() -> Utilidades.printWithDelay("\r⚠️ Error durante la inspección de la colmena " + colmena.id + ".\n", 50));
                }
            });

            hilos.add(hilo);
            hilo.start();
        }

        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                runWithBlock(() -> Utilidades.printWithDelay("\r⚠️ Error esperando la finalización de un hilo.\n", 50));
            }
        }

        runWithBlock(() -> {
            mensajesResultado.forEach(mensaje -> Utilidades.printWithDelay(mensaje, 50));
            animacionAbejas();
        });
    }
}
