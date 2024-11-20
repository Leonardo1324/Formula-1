package ar.edu.undec.adapter.data.dbapi;

import ar.edu.undec.adapter.data.model.PilotoData;
import org.springframework.data.repository.CrudRepository;
import piloto.modelo.Piloto;

import java.util.ArrayList;
import java.util.UUID;

public interface BuscarPilotoCRUD extends CrudRepository<PilotoData, UUID> {
    ArrayList<Piloto> findBynombreAbreviado(String nombreAbreviado);
    ArrayList<Piloto> findBynombreCompleto(String nombreCompleto);
    ArrayList<Piloto> findByApellido(String nombre);
}
