package Colmena;

public class AbejaReina extends Abeja {
    int edad;
    double productividad;

    public AbejaReina(int edad, String estadoSalud, double productividad) {
        super(estadoSalud);
        this.edad = edad;
        this.productividad = productividad;
    }

    public String getInfo() {
        return super.getInfo() + String.format("""
                👑 Abeja Reina
                🕰️ Edad: %d años
                🍯 Productividad: %.2f kg de miel
                """, edad, productividad);
    }
}
