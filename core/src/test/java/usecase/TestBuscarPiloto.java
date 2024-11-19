package usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import piloto.modelo.Piloto;
import piloto.output.PersistenciaBuscarPilotos;
import piloto.usecase.BuscarPiloto;

import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestBuscarPiloto {

    @Mock
    PersistenciaBuscarPilotos BD;

    @Test
    void BuscarPorNombre() {
        Piloto piloto = Piloto.instance(UUID.randomUUID(),"Franco","Colapinto","Franco Colapinto","COL","LocalHost");

        ArrayList<Piloto> pilotosCorrectos = new ArrayList<Piloto>();

        pilotosCorrectos.add(piloto);

        BuscarPiloto BP = new BuscarPiloto(BD);
        when(BP.buscarPilotoPorNombre("Franco")).thenReturn(pilotosCorrectos);
        Assertions.assertDoesNotThrow(() -> BP.buscarPilotoPorNombre("Franco"));
        Assertions.assertEquals(pilotosCorrectos, BP.buscarPilotoPorNombre("Franco"));
    }

    @Test
    void BuscarPorApellido() {
        Piloto piloto = Piloto.instance(UUID.randomUUID(),"Franco","Colapinto","Franco Colapinto","COL","LocalHost");

        ArrayList<Piloto> pilotosCorrectos = new ArrayList<Piloto>();

        pilotosCorrectos.add(piloto);

        BuscarPiloto BP = new BuscarPiloto(BD);
        when(BP.buscarPilotosPorApellido("Colapinto")).thenReturn(pilotosCorrectos);
        Assertions.assertDoesNotThrow(() -> BP.buscarPilotosPorApellido("Colapinto"));
        Assertions.assertEquals(pilotosCorrectos, BP.buscarPilotosPorApellido("Colapinto"));
    }

    @Test
    void BuscarPorAbreviatura() {
        Piloto piloto = Piloto.instance(UUID.randomUUID(),"Franco","Colapinto","Franco Colapinto","COL","LocalHost");

        ArrayList<Piloto> pilotosCorrectos = new ArrayList<Piloto>();

        pilotosCorrectos.add(piloto);

        BuscarPiloto BP = new BuscarPiloto(BD);
        when(BP.buscarPilotosPorAbreviatura("COL")).thenReturn(pilotosCorrectos);
        Assertions.assertDoesNotThrow(() -> BP.buscarPilotosPorAbreviatura("COL"));
        Assertions.assertEquals(pilotosCorrectos, BP.buscarPilotosPorAbreviatura("COL"));
    }
}
