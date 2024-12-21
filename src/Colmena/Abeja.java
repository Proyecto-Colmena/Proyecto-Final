package Colmena;

import java.io.Serializable;

public class Abeja implements Serializable {
    public String estadoSalud;

    public Abeja(String estadoSalud) {
        this.estadoSalud = estadoSalud;
    }

    public String getInfo() {
        return String.format("❤️ Estado de Salud: %s%n", estadoSalud);
    }
}
