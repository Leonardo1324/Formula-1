package piloto.output;

import piloto.modelo.Piloto;

import java.util.ArrayList;
import java.util.List;

public interface PersistenciaBuscarPilotos {
    List<Piloto> buscarPilotosPorNombre(String nombre);
    List<Piloto> buscarPilotosPorApellido(String apellido);
    List<Piloto> buscarPilotosPorAbreviatura(String abreviatura);
}
