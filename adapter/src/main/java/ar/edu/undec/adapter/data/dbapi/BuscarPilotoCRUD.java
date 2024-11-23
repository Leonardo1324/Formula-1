package ar.edu.undec.adapter.data.dbapi;

import ar.edu.undec.adapter.data.model.PilotoData;
import org.springframework.data.repository.CrudRepository;
import piloto.modelo.Piloto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BuscarPilotoCRUD extends CrudRepository<PilotoData, UUID> {
    List<PilotoData> findBynombreAbreviado(String nombreAbreviado);
    List<PilotoData> findBynombreCompleto(String nombreCompleto);
    List<PilotoData> findByapellido(String apellido);
}
