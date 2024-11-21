package ar.edu.undec.adapter.data.dbapi;

import ar.edu.undec.adapter.data.model.PilotoData;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PilotosApiCRUD extends CrudRepository<PilotoData, UUID> {
}
