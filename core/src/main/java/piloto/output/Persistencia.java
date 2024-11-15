package piloto.output;

import piloto.modelo.Piloto;

public interface Persistencia {
    boolean existePiloto(String nombre); // que un piloto existe no impide que se siguen revisando los otros
    boolean guardarPiloto(Piloto piloto);
}
