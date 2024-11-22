package usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import piloto.input.BuscarPilotosDesdeAPI;
import piloto.modelo.Piloto;
import piloto.usecase.BuscarPilotoDesdeApi;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestBuscarPilotoDesdeApi {

    @Mock
    BuscarPilotosDesdeAPI buscarPilotosDesde;

    @Test
    void PilotosCorrecto() {
        Piloto piloto = Piloto.instance(UUID.randomUUID(),"Franco","Colapinto","Franco Colapinto","COL","LocalHost/8080");

        List<Piloto> pilotosEsperados = List.of(piloto);

        when(buscarPilotosDesde.buscarPilotosDesdeAPI()).thenReturn(pilotosEsperados);

        BuscarPilotoDesdeApi buscarPilotoDesdeApi = new BuscarPilotoDesdeApi(buscarPilotosDesde);

        List<Piloto> resultado = buscarPilotoDesdeApi.buscarPilotos();

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(pilotosEsperados.size(), resultado.size());
        Assertions.assertEquals(pilotosEsperados, resultado);
        verify(buscarPilotosDesde,times(1)).buscarPilotosDesdeAPI();
    }

    @Test
    void PilotosListaVacia() {

        List<Piloto> pilotosEsperados = List.of();

        when(buscarPilotosDesde.buscarPilotosDesdeAPI()).thenReturn(pilotosEsperados);

        BuscarPilotoDesdeApi buscarPilotoDesdeApi = new BuscarPilotoDesdeApi(buscarPilotosDesde);

        List<Piloto> resultado = buscarPilotoDesdeApi.buscarPilotos();

        Assertions.assertNotNull(resultado);
        Assertions.assertTrue(resultado.isEmpty());
        verify(buscarPilotosDesde,times(1)).buscarPilotosDesdeAPI();
    }
}
