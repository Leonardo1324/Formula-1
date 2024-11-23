package ar.edu.undec.adapter.data.piloto;


import ar.edu.undec.adapter.data.dbapi.CrearPilotoCRUD;
import ar.edu.undec.adapter.data.model.PilotoData;
import ar.edu.undec.adapter.data.repository.CrearPilotoGeatwayRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import piloto.modelo.Piloto;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CrearPilotoDataTest {

    @Mock
    CrearPilotoCRUD crud;

    @InjectMocks
    CrearPilotoGeatwayRepository repository;

    @Test
    void guardarPilotoCorrecto() {
    Piloto piloto = Piloto.instance(UUID.randomUUID(),"Franco","Colapinto","Franco Colapinto","COL","LocalHost/8080");
    when(crud.save(Mockito.any(PilotoData.class))).thenReturn(new PilotoData());
    boolean resultado = repository.guardarPiloto(piloto);

    Assertions.assertTrue(resultado);
    }

    @Test
    void guardarPilotoIncorrecto() {
        Piloto piloto = Piloto.instance(UUID.randomUUID(),"Franco","Colapinto","Franco Colapinto","COL","LocalHost/8080");
        when(crud.save(Mockito.any(PilotoData.class))).thenThrow(RuntimeException.class);
        boolean resultado = repository.guardarPiloto(piloto);

        Assertions.assertFalse(resultado);
    }
}
