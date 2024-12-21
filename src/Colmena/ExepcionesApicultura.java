package Colmena;

public class ExcepcionesApicultura {

    // Excepción para colmenas inválidas
    public static class ColmenaInvalidaException extends Exception {
        public ColmenaInvalidaException(String mensaje) {
            super(mensaje);
        }
    }

    // Excepción para apicultores inválidos
    public static class ApicultorInvalidoException extends Exception {
        public ApicultorInvalidoException(String mensaje) {
            super(mensaje);
        }
    }

    // Excepción para datos de producción inválidos
    public static class ProduccionInvalidaException extends Exception {
        public ProduccionInvalidaException(String mensaje) {
            super(mensaje);
        }
    }

    // Excepción para acceso no autorizado
    public static class AccesoNoAutorizadoException extends Exception {
        public AccesoNoAutorizadoException(String mensaje) {
            super(mensaje);
        }
    }

    // Excepción para operaciones no permitidas en la colmena
    public static class OperacionNoPermitidaException extends Exception {
        public OperacionNoPermitidaException(String mensaje) {
            super(mensaje);
        }
    }

    // Excepción para problemas de conectividad con dispositivos de monitoreo
    public static class DispositivoConectividadException extends Exception {
        public DispositivoConectividadException(String mensaje) {
            super(mensaje);
        }
    }

    // Excepción para datos climáticos no disponibles
    public static class DatosClimaticosNoDisponiblesException extends Exception {
        public DatosClimaticosNoDisponiblesException(String mensaje) {
            super(mensaje);
        }
    }
}
