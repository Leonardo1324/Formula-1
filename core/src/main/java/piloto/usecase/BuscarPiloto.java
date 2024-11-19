package piloto.usecase;

import piloto.exception.ExceptionNoHayPilotos;
import piloto.modelo.Piloto;
import piloto.output.Persistencia;
import piloto.output.PersistenciaBuscarPilotos;

import java.util.ArrayList;

public class BuscarPiloto implements piloto.input.BuscarPiloto {
    private PersistenciaBuscarPilotos myBD;

    public BuscarPiloto(PersistenciaBuscarPilotos myBD) {
        this.myBD = myBD;
    }

    @Override
    public ArrayList<Piloto> buscarPilotoPorNombre(String nombre) {
        ArrayList<Piloto> pilotos = myBD.buscarPilotosPorNombre(nombre);
        if (pilotos == null) {
            throw new ExceptionNoHayPilotos("No encontraron pilotos con ese nombre");
        }
        return pilotos;
    }

    @Override
    public ArrayList<Piloto> buscarPilotosPorApellido(String apellido) {
        ArrayList<Piloto> pilotos = myBD.buscarPilotosPorApellido(apellido);
        if (pilotos == null) {
            throw new ExceptionNoHayPilotos("No encontraron pilotos con ese apellido");
        }
        return pilotos;
    }

    @Override
    public ArrayList<Piloto> buscarPilotosPorAbreviatura(String abreviatura) {
        ArrayList<Piloto> pilotos = myBD.buscarPilotosPorApellido(abreviatura);
        if (pilotos == null) {
            throw new ExceptionNoHayPilotos("No encontraron pilotos con esa abreviatura");
        }
        return pilotos;
    }
}
