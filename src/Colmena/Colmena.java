package Colmena;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una colmena que contiene diferentes tipos de abejas.
 * Administra a las abejas y coordina sus actividades.
 */
public class Colmena {
  private List<Abeja> abejas; // Lista para almacenar las abejas en la colmena

  /**
   * Constructor que inicializa la colmena con una lista de abejas vacía.
   */
  public Colmena() {
    this.abejas = new ArrayList<>();
  }

  /**
   * Agrega una abeja a la colmena.
   *
   * @param abeja la abeja a agregar
   */
  public void agregarAbeja(Abeja abeja) {
    abejas.add(abeja);
    System.out.println(abeja.getNombre() + " se ha unido a la colmena como " + abeja.getTipo() + ".");
  }

  /**
   * Inicia el trabajo de todas las abejas en la colmena.
   */
  public void iniciarTrabajo() {
    System.out.println("\nComenzando las actividades en la colmena:");
    for (Abeja abeja : abejas) {
      abeja.trabajar();
    }
  }

  /**
   * Genera un reporte de las abejas en la colmena.
   */
  public void generarReporte() {
    System.out.println("\nReporte de la colmena:");
    System.out.println("Total de abejas: " + abejas.size());
    for (Abeja abeja : abejas) {
      System.out.println("- " + abeja.getNombre() + " (" + abeja.getTipo() + ")");
    }
  }

  /**
   * Simula un día en la vida de la colmena.
   * Incluye trabajo y un reporte de estado.
   */
  public void simularDia() {
    iniciarTrabajo();
    generarReporte();
  }
}
