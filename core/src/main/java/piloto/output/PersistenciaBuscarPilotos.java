package piloto.output;

import piloto.modelo.Piloto;

import java.util.ArrayList;

public interface PersistenciaBuscarPilotos {
    ArrayList<Piloto> buscarPilotosPorNombre(String nombre);
    ArrayList<Piloto> buscarPilotosPorApellido(String apellido);
    ArrayList<Piloto> buscarPilotosPorAbreviatura(String abreviatura);
}
