//Nombre: John Vallarino
//Cedula: 3-754-306
//Funcionamiento del codigo: El código implementa expresiones Lambda principalmente a través del uso del API Stream de Java.
// Este codigo gestiona un menú interactivo donde el usuario puede realizar varias operaciones sobre las colmenas.
//Permite al usuario registrar nuevas colmenas, con validaciones para evitar duplicados,cambiar el estado de salud de una colmena específica,
//Calcula y muestra el promedio de abejas entre todas las colmenas usando Lambda e identifica y muestra la colmena con mayor producción de miel.

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Colmena {
    private String id;
    private String ubicacion;
    private String estadoSalud; // Ejemplo: "Buena", "Regular", "Mala"
    private int cantidadAbejas;
    private int produccionMiel; // Producción en kilogramos

    // Constructor
    public Colmena(String id, String ubicacion, String estadoSalud, int cantidadAbejas, int produccionMiel) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.estadoSalud = estadoSalud;
        this.cantidadAbejas = cantidadAbejas;
        this.produccionMiel = produccionMiel;
    }

    // Getters y Setters
    public String getId() {
        return id;
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

    public int getProduccionMiel() {
        return produccionMiel;
    }

    @Override
    public String toString() {
        return "Colmena{" +
                "id='" + id + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", estadoSalud='" + estadoSalud + '\'' +
                ", cantidadAbejas=" + cantidadAbejas +
                ", produccionMiel=" + produccionMiel + " kg" +
                '}';
    }

    // Método principal que use
    public static void main(String[] args) {
        List<Colmena> colmenas = new ArrayList<>();
        colmenas.add(new Colmena("C01", "Zona Norte", "Buena", 15000, 120));
        colmenas.add(new Colmena("C02", "Zona Sur", "Mala", 8000, 50));
        colmenas.add(new Colmena("C03", "Zona Este", "Regular", 12000, 90));
        colmenas.add(new Colmena("C04", "Zona Oeste", "Mala", 5000, 30));

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nSistema de Gestión de Colmenas");
            System.out.println("1. Ver todas las colmenas");
            System.out.println("2. Ver colmenas en mal estado");
            System.out.println("3. Agregar nueva colmena");
            System.out.println("4. Actualizar estado de salud de una colmena");
            System.out.println("5. Calcular promedio de abejas");
            System.out.println("6. Encontrar colmena con mayor producción");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1:
                    System.out.println("\nLista de todas las colmenas:");
                    colmenas.forEach(System.out::println);
                    break;
                case 2:
                    System.out.println("\nColmenas que necesitan atención inmediata:");
                    List<Colmena> colmenasEnMalEstado = colmenas.stream()
                            .filter(c -> c.getEstadoSalud().equalsIgnoreCase("Mala"))
                            .collect(Collectors.toList());
                    colmenasEnMalEstado.forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("\nIngrese el ID de la nueva colmena: ");
                    String id = scanner.nextLine();
                    if (colmenas.stream().anyMatch(c -> c.getId().equalsIgnoreCase(id))) {
                        System.out.println("Error: Ya existe una colmena con ese ID.");
                        break;
                    }
                    System.out.print("Ingrese la ubicación: ");
                    String ubicacion = scanner.nextLine();
                    System.out.print("Ingrese el estado de salud (Buena/Regular/Mala): ");
                    String estadoSalud = scanner.nextLine();
                    System.out.print("Ingrese la cantidad de abejas: ");
                    int cantidadAbejas = scanner.nextInt();
                    System.out.print("Ingrese la producción de miel (kg): ");
                    int produccionMiel = scanner.nextInt();
                    colmenas.add(new Colmena(id, ubicacion, estadoSalud, cantidadAbejas, produccionMiel));
                    System.out.println("¡Colmena agregada exitosamente!");
                    break;
                case 4:
                    System.out.print("\nIngrese el ID de la colmena a actualizar: ");
                    String idActualizar = scanner.nextLine();
                    Optional<Colmena> colmenaOpt = colmenas.stream()
                            .filter(c -> c.getId().equalsIgnoreCase(idActualizar))
                            .findFirst();
                    if (colmenaOpt.isPresent()) {
                        Colmena colmena = colmenaOpt.get();
                        System.out.print("Ingrese el nuevo estado de salud (Buena/Regular/Mala): ");
                        String nuevoEstado = scanner.nextLine();
                        colmena.setEstadoSalud(nuevoEstado);
                        System.out.println("¡Estado de salud actualizado!");
                    } else {
                        System.out.println("Error: No se encontró una colmena con ese ID.");
                    }
                    break;
                case 5:
                    double promedioAbejas = colmenas.stream()
                            .mapToInt(Colmena::getCantidadAbejas)
                            .average()
                            .orElse(0.0);
                    System.out.println("\nEl promedio de abejas por colmena es: " + promedioAbejas);
                    break;
                case 6:
                    Colmena mayorProduccion = colmenas.stream()
                            .max((c1, c2) -> Integer.compare(c1.getProduccionMiel(), c2.getProduccionMiel()))
                            .orElse(null);
                    if (mayorProduccion != null) {
                        System.out.println("\nLa colmena con mayor producción es:");
                        System.out.println(mayorProduccion);
                    } else {
                        System.out.println("No hay colmenas registradas.");
                    }
                    break;
                case 7:
                    System.out.println("¡Gracias por usar el programa, adios!");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        scanner.close();
    }
}

