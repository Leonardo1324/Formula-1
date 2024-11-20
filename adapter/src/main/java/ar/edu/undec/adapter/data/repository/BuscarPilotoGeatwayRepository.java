package ar.edu.undec.adapter.data.repository;

import ar.edu.undec.adapter.data.dbapi.BuscarPilotoCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import piloto.modelo.Piloto;
import piloto.output.PersistenciaBuscarPilotos;

import java.util.ArrayList;

@Repository
public class BuscarPilotoGeatwayRepository implements PersistenciaBuscarPilotos {

    private BuscarPilotoCRUD buscarPilotoCRUD;

    @Autowired
    public BuscarPilotoGeatwayRepository(BuscarPilotoCRUD buscarPilotoCRUD) {
        this.buscarPilotoCRUD = buscarPilotoCRUD;
    }

    @Override
    public ArrayList<Piloto> buscarPilotosPorNombre(String nombre) {
        ArrayList<Piloto> pilotos;
        pilotos = buscarPilotoCRUD.findBynombreCompleto(nombre);
        return pilotos;
    }
    @Override
    public ArrayList<Piloto> buscarPilotosPorApellido(String apellido) {
        ArrayList<Piloto> pilotos;
        pilotos = buscarPilotoCRUD.findByApellido(apellido);
        return pilotos;
    }
    @Override
    public ArrayList<Piloto> buscarPilotosPorAbreviatura(String abreviatura) {
        ArrayList<Piloto> pilotos;
        pilotos = buscarPilotoCRUD.findBynombreAbreviado(abreviatura);
        return pilotos;
    }
}
