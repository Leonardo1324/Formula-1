package ar.edu.undec.adapter.data.repository;

import ar.edu.undec.adapter.data.dbapi.PilotosApiCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BuscarPilotosDeApiGeatwayRepository {

    private PilotosApiCRUD pilotosApiCRUD;

    @Autowired
    public BuscarPilotosDeApiGeatwayRepository(PilotosApiCRUD pilotosApiCRUD) {
        this.pilotosApiCRUD = pilotosApiCRUD;
    }



}
