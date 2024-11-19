package piloto.input;

import piloto.modelo.Piloto;

import java.util.ArrayList;

public interface BuscarPiloto {
    ArrayList<Piloto> buscarPilotoPorNombre(String nombre);
    ArrayList<Piloto> buscarPilotosPorApellido(String apellido);
    ArrayList<Piloto> buscarPilotosPorAbreviatura(String abreviatura);
}
