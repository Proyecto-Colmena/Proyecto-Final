package Colmena;
import java.io.Serializable;

//Clase para representar un apicultor
public class Apicultor implements Serializable {
    public String nombre;
    public String telefono;

    public Apicultor(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }
}
