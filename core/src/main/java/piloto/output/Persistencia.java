package piloto.output;

public interface Persistencia {
    boolean existePiloto(String nombre); // que un piloto existe no impide que se siguen revisando los otros
    boolean guardarPiloto(String nombre);
}
