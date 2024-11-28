package ar.edu.undec.adapter.data.repository;

import ar.edu.undec.adapter.data.dbapi.BuscarPilotoCRUD;
import ar.edu.undec.adapter.data.model.PilotoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import piloto.modelo.Piloto;
import piloto.output.PersistenciaBuscarPilotos;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class BuscarPilotoGetawayRepository implements PersistenciaBuscarPilotos {

    private BuscarPilotoCRUD buscarPilotoCRUD;

    @Autowired
    public BuscarPilotoGetawayRepository(BuscarPilotoCRUD buscarPilotoCRUD) {
        this.buscarPilotoCRUD = buscarPilotoCRUD;
    }

    @Override
    public List<Piloto> buscarPilotosPorNombreCompleto(String nombre) {
        List<Piloto> pilotos;
        pilotos = buscarPilotoCRUD.findBynombreCompleto(nombre).stream()
                .map(PilotoData::toDomain).collect(Collectors.toList());
        return pilotos;
    }
    @Override
    public List<Piloto> buscarPilotosPorApellido(String apellido) {
        List<Piloto> pilotos;
        pilotos = buscarPilotoCRUD.findByapellido(apellido).stream()
                .map(PilotoData::toDomain).collect(Collectors.toList());
        return pilotos;
    }
    @Override
    public List<Piloto> buscarPilotosPorAbreviatura(String abreviatura) {
        List<Piloto> pilotos;
        pilotos = buscarPilotoCRUD.findBynombreAbreviado(abreviatura).stream()
                .map(PilotoData::toDomain).collect(Collectors.toList());
        return pilotos;
    }

    @Override
    public List<Piloto> buscarTodosLosPilotos() {
        List<Piloto> pilotos;
        pilotos = StreamSupport.stream(buscarPilotoCRUD.findAll().spliterator(), false)  // Usamos stream() para convertir la lista en un flujo
                .map(PilotoData::toDomain)  // Mapeamos cada elemento
                .collect(Collectors.toList());  // Recogemos los elementos en una lista
        return pilotos;
    }
}
