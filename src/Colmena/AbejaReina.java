package Colmena;

public class AbejaReina extends Abeja {
  
  // Constructor
  public AbejaReina(String nombre) {
    super(nombre, "Abeja Reina");
  }

  // Método específico de la abeja reina
  public void supervisarColmena() {
    System.out.println(getNombre() + " supervisa el trabajo de las abejas obreras y asegura el orden en la colmena.");
  }

  // Sobrescritura del método trabajar
  @Override
  public void trabajar() {
    System.out.println(getNombre() + " no trabaja físicamente, pero supervisa la colmena.");
  }
}
