class Inspeccion {
    private String fecha;
    private String resultado;

    public Inspeccion(String fecha, String resultado) {
        this.fecha = fecha;
        this.resultado = resultado;
    }

    public String getFecha() {
        return fecha;
    }

    public String getResultado() {
        return resultado;
    }
}

class HistorialInspecciones {
    private List<Inspeccion> inspecciones;

    public HistorialInspecciones() {
        this.inspecciones = new ArrayList<>();
    }

    public void agregarInspeccion(Inspeccion inspeccion) {
        inspecciones.add(inspeccion);
    }

    public void buscarPorFecha(String fecha) {
        buscarRecursivo(fecha, 0);
    }

    private void buscarRecursivo(String fecha, int index) {
        if (index >= inspecciones.size()) {
            System.out.println("No se encontró una inspección con la fecha: " + fecha);
            return;
        }

        Inspeccion inspeccion = inspecciones.get(index);
        if (inspeccion.getFecha().equals(fecha)) {
            System.out.println("Inspección encontrada: " + inspeccion.getResultado());
            return;
        }

        buscarRecursivo(fecha, index + 1);
    }
}
