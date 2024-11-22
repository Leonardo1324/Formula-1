package piloto.usecase;

import piloto.exception.ExceptionNoHayPilotos;
import piloto.modelo.Piloto;
import piloto.output.PersistenciaBuscarPilotos;

import java.util.List;

public class BuscarPiloto implements piloto.input.BuscarPiloto {
    private PersistenciaBuscarPilotos myBD;

    public BuscarPiloto(PersistenciaBuscarPilotos myBD) {
        this.myBD = myBD;
    }

    @Override
    public List<Piloto> buscarPilotoPorNombre(String nombre) {
        List<Piloto> pilotos = myBD.buscarPilotosPorNombre(nombre);
        if (pilotos.isEmpty()) {
            throw new ExceptionNoHayPilotos("No encontraron pilotos con ese nombre");
        }
        return pilotos;
    }

    @Override
    public List<Piloto> buscarPilotosPorApellido(String apellido) {
        List<Piloto> pilotos = myBD.buscarPilotosPorApellido(apellido);
        if (pilotos.isEmpty()) {
            throw new ExceptionNoHayPilotos("No encontraron pilotos con ese apellido");
        }
        return pilotos;
    }

    @Override
    public List<Piloto> buscarPilotosPorAbreviatura(String abreviatura) {
        List<Piloto> pilotos = myBD.buscarPilotosPorAbreviatura(abreviatura);
        if (pilotos.isEmpty()) {
            throw new ExceptionNoHayPilotos("No encontraron pilotos con esa abreviatura");
        }
        return pilotos;
    }
}
