// -------------------------------------------------------------
// Autor: Pedro Grant
// Cédula: 3-725-1359
// Comentario: Este código es importante porque permite simular
// la gestión de una colmena mediante el uso de hilos para la
// concurrencia y recursividad para la simulación del crecimiento
// de la cantidad de abejas y la producción de miel. Al incluir
// estos elementos, el código demuestra técnicas avanzadas en
// programación que son útiles para simular procesos reales en
// un entorno multitarea y recursivo.
// -------------------------------------------------------------public class Colmena implements Runnable {


    private String identificacion;
    private String ubicacion;
    private String estadoSalud;
    private int cantidadAbejas;
    private double produccionMiel;

    // Constructor
    public Colmena(String identificacion, String ubicacion, String estadoSalud, int cantidadAbejas, double produccionMiel) {
        this.identificacion = identificacion;
        this.ubicacion = ubicacion;
        this.estadoSalud = estadoSalud;
        this.cantidadAbejas = cantidadAbejas;
        this.produccionMiel = produccionMiel;
    }

    // Getters y Setters
    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEstadoSalud() {
        return estadoSalud;
    }

    public void setEstadoSalud(String estadoSalud) {
        this.estadoSalud = estadoSalud;
    }

    public int getCantidadAbejas() {
        return cantidadAbejas;
    }

    public void setCantidadAbejas(int cantidadAbejas) {
        this.cantidadAbejas = cantidadAbejas;
    }

    public double getProduccionMiel() {
        return produccionMiel;
    }

    public void setProduccionMiel(double produccionMiel) {
        this.produccionMiel = produccionMiel;
    }

    // Metodo para mostrar información de la colmena
    public void mostrarInformacion() {
        System.out.println("Identificación: " + identificacion);
        System.out.println("Ubicación: " + ubicacion);
        System.out.println("Estado de Salud: " + estadoSalud);
        System.out.println("Cantidad de Abejas: " + cantidadAbejas);
        System.out.println("Producción de Miel: " + produccionMiel + " kg");
    }

    // Método recursivo para simular la producción de miel
    public double calcularProduccionMiel(int abeja) {
        if (abeja <= 0) {
            return 0;
        }
        // Asumimos que cada abeja produce 0.05 kg de miel
        return 0.05 + calcularProduccionMiel(abeja - 1);
    }

    // Método recursivo para simular la cantidad de abejas que crecen en la colmena
    public int aumentarAbejas(int abeja) {
        if (abeja >= 1000) {  // Límite máximo de abejas
            return abeja;
        }
        // Aumenta una abeja recursivamente
        return aumentarAbejas(abeja + 1);
    }

    // Método de la interfaz Runnable para ejecutar la colmena en un hilo
    @Override
    public void run() {
        System.out.println("Iniciando la gestión de la colmena " + identificacion + "...");
        // Ejemplo de cálculo recursivo de producción de miel
        double miel = calcularProduccionMiel(cantidadAbejas);
        System.out.println("Producción total de miel (recursiva): " + miel + " kg");

        // Ejemplo de aumento recursivo de abejas
        int nuevasAbejas = aumentarAbejas(cantidadAbejas);
        System.out.println("Cantidad de abejas después de crecimiento (recursiva): " + nuevasAbejas);

        // Actualizamos la cantidad de abejas y producción de miel
        setCantidadAbejas(nuevasAbejas);
        setProduccionMiel(miel);

        // Mostramos la información actualizada de la colmena
        mostrarInformacion();
    }

    public static void main(String[] args) {
        // Creación de varias colmenas
        Colmena colmena1 = new Colmena("Colmena1", "Ubicacion1", "Saludable", 50, 2.5);
        Colmena colmena2 = new Colmena("Colmena2", "Ubicacion2", "Enferma", 30, 1.5);

        // Ejecutamos las colmenas en hilos separados
        Thread hiloColmena1 = new Thread(colmena1);
        Thread hiloColmena2 = new Thread(colmena2);

        // Iniciamos los hilos
        hiloColmena1.start();
        hiloColmena2.start();

        try {
            // Esperamos a que ambos hilos terminen su ejecución
            hiloColmena1.join();
            hiloColmena2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}