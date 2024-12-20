package Colmena;

public class AbejaReina extends Abeja {
  public AbejaReina(String nombre){
    super(nombre, "Abeja Reina");
    
  }
  public void supervisarColmena() {
    System.out.println(nombre + "Supervisa el trabajo de las abejas obreras. ")
  }
}
