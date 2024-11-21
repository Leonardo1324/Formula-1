package ar.edu.undec.adapter.data.repository;

import ar.edu.undec.adapter.data.dbapi.CrearPilotoCRUD;
import ar.edu.undec.adapter.data.model.PilotoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import piloto.modelo.Piloto;
import piloto.output.Persistencia;
@Repository
public class CrearPilotoGeatwayRepository implements Persistencia {


    private CrearPilotoCRUD crearPilotoCRUD;

    @Autowired
    public CrearPilotoGeatwayRepository(CrearPilotoCRUD crearPilotoCRUD) {
        this.crearPilotoCRUD = crearPilotoCRUD;
    }
    @Override
    public boolean existePilotoNombreCompleto(String nombre) {
        return crearPilotoCRUD.existsBynombreCompleto(nombre);
    }
    @Override
    public boolean existePilotoNombreAbreviado(String abreviatura) {
        return crearPilotoCRUD.existsByNombreAbreviado(abreviatura);
    }
    @Override
    public boolean guardarPiloto(Piloto piloto) {
        try {
            if (crearPilotoCRUD.existsBynombreCompleto(piloto.getNombreCompleto())) {
                return false;
            }
            crearPilotoCRUD.save(PilotoData.fromDomain(piloto));
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
