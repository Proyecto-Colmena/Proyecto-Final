import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
//Inicio de clase Apiario

public class Apiario {

    static class Colmena {
        private String id;
        private double produccionMiel; // En kilogramos
        private String estadoSalud;    // "Saludable", "Enferma", "Crítica"

        public Colmena(String id, double produccionMiel, String estadoSalud) {
            this.id = id;
            this.produccionMiel = produccionMiel;
            this.estadoSalud = estadoSalud;
        }
        //LLama la clase de validacion id
        public String getId() {
            return id;
        }

        public double getProduccionMiel() {
            return produccionMiel;
        }
            
        public String getEstadoSalud() {
            return estadoSalud;
        }

        @Override
        public String toString() {
            return "Colmena{id='" + id + "', produccionMiel=" + produccionMiel + ", estadoSalud='" + estadoSalud + "'}";
        }
    }

    public static void main(String[] args) {
        // Lista de colmenas
        List<Colmena> colmenas = new ArrayList<>();
        colmenas.add(new Colmena("C1", 30.5, "Saludable"));
        colmenas.add(new Colmena("C2", 15.2, "Enferma"));
        colmenas.add(new Colmena("C3", 45.7, "Saludable"));
        colmenas.add(new Colmena("C4", 5.0, "Crítica"));
        colmenas.add(new Colmena("C5", 20.0, "Enferma"));

        // Filtrar colmenas saludables
        List<Colmena> colmenasSaludables = colmenas.stream()
                .filter(colmena -> "Saludable".equals(colmena.getEstadoSalud()))
                .collect(Collectors.toList());
        System.out.println("Colmenas Saludables:");
        colmenasSaludables.forEach(System.out::println);

        // Obtener la producción total de miel
        double produccionTotal = colmenas.stream()
                .mapToDouble(Colmena::getProduccionMiel)
                .sum();
        System.out.println("\nProducción Total de Miel: " + produccionTotal + " kg");

        // Mapear colmenas críticas para mostrar solo sus IDs
        List<String> idsColmenasCriticas = colmenas.stream()
                .filter(colmena -> "Crítica".equals(colmena.getEstadoSalud()))
                .map(Colmena::getId)
                .collect(Collectors.toList());
        System.out.println("\nIDs de Colmenas Críticas:");
        idsColmenasCriticas.forEach(System.out::println);
    }
}
