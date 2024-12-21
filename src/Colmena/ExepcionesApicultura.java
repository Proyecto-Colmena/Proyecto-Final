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
}
