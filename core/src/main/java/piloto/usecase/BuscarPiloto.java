package piloto.usecase;

import piloto.modelo.Piloto;
import piloto.output.Persistencia;
import piloto.output.PersistenciaBuscarPilotos;

import java.util.ArrayList;

public class BuscarPiloto implements  {
    private PersistenciaBuscarPilotos myBD;

    public BuscarPiloto() {

    }

    @Override
    public ArrayList<Piloto> buscarPilotosPorNombre(String nombre) {
        return null;
    }

    @Override
    public ArrayList<Piloto> buscarPilotosPorApellido(String apellido) {
        return null;
    }

    @Override
    public ArrayList<Piloto> buscarPilotosPorAbreviatura(String abreviatura) {
        return null;
    }
}
