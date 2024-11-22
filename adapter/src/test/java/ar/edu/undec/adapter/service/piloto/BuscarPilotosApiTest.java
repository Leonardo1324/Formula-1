package ar.edu.undec.adapter.service.piloto;

import ar.edu.undec.adapter.data.dbapi.BuscarPilotosDesdeApi;
import ar.edu.undec.adapter.data.repository.CrearPilotoGeatwayRepository;
import ar.edu.undec.adapter.service.rest.BuscarPilotosApiController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import piloto.modelo.Piloto;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuscarPilotosApiTest {

    @Mock
    private BuscarPilotosDesdeApi buscarPilotosDesdeApi;

    @Mock
    private CrearPilotoGeatwayRepository crearPilotoGeatwayRepository;

    @InjectMocks
    private BuscarPilotosApiController buscarPilotosApiController;


    @Test
    void CargarPilotosInicio() {
        Piloto piloto1 = Piloto.instance(UUID.randomUUID(),"Franco","Colapinto","Franco Colapinto","COL","LocalHost/8080");

        List<Piloto> pilotos = List.of(piloto1);

        when(buscarPilotosDesdeApi.buscarPilotosDesdeAPI()).thenReturn(pilotos);

        ResponseEntity<?> resultado = buscarPilotosApiController.obtenerPilotos();

        Assertions.assertEquals(HttpStatus.OK, resultado.getStatusCode());
        Assertions.assertNotNull(resultado.getBody());
        Assertions.assertInstanceOf(List.class, resultado.getBody());
        List<Piloto> pilotos2 = (List<Piloto>) resultado.getBody();
        Assertions.assertEquals(1, pilotos2.size());
        verify(buscarPilotosDesdeApi,times(1)).buscarPilotosDesdeAPI();
    }

    @Test
    void noSeEncontraronPilotos() {
        when(buscarPilotosDesdeApi.buscarPilotosDesdeAPI()).thenReturn(Collections.emptyList());

        ResponseEntity<?> resultado = buscarPilotosApiController.obtenerPilotos();

        Assertions.assertEquals(HttpStatus.NOT_FOUND, resultado.getStatusCode());
        Assertions.assertNull(resultado.getBody());
        verify(buscarPilotosDesdeApi,times(1)).buscarPilotosDesdeAPI();
    }


    @Test
    void falloLaCargaDePilotos() {
        when(buscarPilotosDesdeApi.buscarPilotosDesdeAPI()).thenThrow(new RuntimeException("Error en el acceso a la API"));

        ResponseEntity<?> resultado = buscarPilotosApiController.obtenerPilotos();

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, resultado.getStatusCode());
        Assertions.assertEquals("Error en el acceso a la API", resultado.getBody());
        verify(buscarPilotosDesdeApi,times(1)).buscarPilotosDesdeAPI();
    }

    //Test de cargar pilotos (no deberia ir en esta parte viola el principio de responsabilidad unica por clase)

    @Test
    void cargaDePilotosDesdeLaApi() throws Exception {
        Piloto piloto1 = Piloto.instance(UUID.randomUUID(),"Franco","Colapinto","Franco Colapinto","COL","LocalHost/8080");
        List<Piloto> pilotos = List.of(piloto1);

        when(buscarPilotosDesdeApi.buscarPilotosDesdeAPI()).thenReturn(pilotos);

        buscarPilotosApiController.cargarPilotosAlIniciar();

        verify(buscarPilotosDesdeApi,times(1)).buscarPilotosDesdeAPI();
        verify(crearPilotoGeatwayRepository,times(1)).guardarPiloto(any(Piloto.class));
    }
}
