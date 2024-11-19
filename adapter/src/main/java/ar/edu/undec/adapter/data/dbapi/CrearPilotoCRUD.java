package ar.edu.undec.adapter.data.dbapi;

import ar.edu.undec.adapter.data.model.PilotoData;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CrearPilotoCRUD extends CrudRepository <PilotoData, UUID> {
    boolean existePilotoNombreCompleto (String nombre);
    boolean existePilotoAbreviatura (String abreviatura);
}
