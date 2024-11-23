package piloto.output;

import piloto.modelo.Piloto;

public interface Persistencia {
    boolean existePilotoNombreCompleto(String nombre); // que un piloto existe no impide que se siguen revisando los otros
    boolean existePilotoNombreAbreviado(String nombre);
    boolean guardarPiloto(Piloto piloto);
}
