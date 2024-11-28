package piloto.output;

import piloto.modelo.Piloto;

import java.util.List;

public interface PersistenciaBuscarPilotos {
    List<Piloto> buscarPilotosPorNombreCompleto(String nombre);
    List<Piloto> buscarPilotosPorApellido(String apellido);
    List<Piloto> buscarPilotosPorAbreviatura(String abreviatura);
    List<Piloto> buscarTodosLosPilotos();
}
