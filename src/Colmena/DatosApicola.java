package Colmena;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class DatosApicola implements Serializable {
    public List<Colmena> colmenas;
    public List<Apicultor> apicultores;
    public Map<String, Apicultor> asignaciones;

    public DatosApicola(List<Colmena> colmenas, List<Apicultor> apicultores, Map<String, Apicultor> asignaciones) {
        this.colmenas = colmenas;
        this.apicultores = apicultores;
        this.asignaciones = asignaciones;
    }
}
