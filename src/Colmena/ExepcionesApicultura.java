package Colmena;

public class ExcepcionesApicultura {

    public static class ColmenaInvalidaException extends Exception {
        public ColmenaInvalidaException(String mensaje) {
            super(mensaje);
        }
    }

    public static class ApicultorInvalidoException extends Exception {
        public ApicultorInvalidoException(String mensaje) {
            super(mensaje);
        }
    }
}

