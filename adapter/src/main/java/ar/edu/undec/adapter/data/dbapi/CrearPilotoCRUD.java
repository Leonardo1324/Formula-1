package ar.edu.undec.adapter.data.dbapi;

import ar.edu.undec.adapter.data.model.PilotoData;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CrearPilotoCRUD extends CrudRepository <PilotoData, UUID> {
    boolean existsBynombreCompleto (String nombre);
    boolean existsByNombreAbreviado (String abreviatura);
}
