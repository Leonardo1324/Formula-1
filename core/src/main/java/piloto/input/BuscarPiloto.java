package piloto.input;

import piloto.modelo.Piloto;

import java.util.List;

public interface BuscarPiloto {
    List<Piloto> buscarPilotoPorNombreCompleto(String nombre);
    List<Piloto> buscarPilotosPorApellido(String apellido);
    List<Piloto> buscarPilotosPorAbreviatura(String abreviatura);
}
