package usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import piloto.exception.ExceptionNoHayPilotos;
import piloto.modelo.Piloto;
import piloto.output.PersistenciaBuscarPilotos;
import piloto.usecase.BuscarPiloto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestBuscarPiloto {

    @Mock
    PersistenciaBuscarPilotos BD;

    @Test
    void BuscarPorNombre() {
        Piloto piloto = Piloto.instance(UUID.randomUUID(),"Franco","Colapinto","Franco Colapinto","COL","LocalHost");

        List<Piloto> pilotosCorrectos = List.of(piloto);

        when(BD.buscarPilotosPorNombre("Franco")).thenReturn(pilotosCorrectos);

        BuscarPiloto BP = new BuscarPiloto(BD);

        Assertions.assertDoesNotThrow(() -> BP.buscarPilotoPorNombre("Franco"));
        Assertions.assertEquals(pilotosCorrectos, BP.buscarPilotoPorNombre("Franco"));
    }

    @Test
    void BuscarPorNombreNoEncontrado() {
        Piloto piloto = Piloto.instance(UUID.randomUUID(),"Franco","Colapinto","Franco Colapinto","COL","LocalHost");

        List<Piloto> pilotosCorrectos = List.of(piloto);

        Exception e;

        when(BD.buscarPilotosPorNombre("Max")).thenReturn(List.of());

        BuscarPiloto BP = new BuscarPiloto(BD);

        e = Assertions.assertThrows(ExceptionNoHayPilotos.class,() -> BP.buscarPilotoPorNombre("Max"));
        Assertions.assertEquals("No encontraron pilotos con ese nombre", e.getMessage());
    }

    @Test
    void BuscarPorApellido() {
        Piloto piloto = Piloto.instance(UUID.randomUUID(),"Franco","Colapinto","Franco Colapinto","COL","LocalHost");

        List<Piloto> pilotosCorrectos = List.of(piloto);

        when(BD.buscarPilotosPorApellido("Colapinto")).thenReturn(pilotosCorrectos);

        BuscarPiloto BP = new BuscarPiloto(BD);

        Assertions.assertDoesNotThrow(() -> BP.buscarPilotosPorApellido("Colapinto"));
        Assertions.assertEquals(pilotosCorrectos, BP.buscarPilotosPorApellido("Colapinto"));
    }

    @Test
    void BuscarPorApellidoNoEncontrado() {
        Piloto piloto = Piloto.instance(UUID.randomUUID(),"Franco","Colapinto","Franco Colapinto","COL","LocalHost");

        List<Piloto> pilotosCorrectos = List.of(piloto);

        Exception e;

        when(BD.buscarPilotosPorApellido("Verstappen")).thenReturn(List.of());

        BuscarPiloto BP = new BuscarPiloto(BD);

        e = Assertions.assertThrows(ExceptionNoHayPilotos.class,() -> BP.buscarPilotosPorApellido("Verstappen"));
        Assertions.assertEquals("No encontraron pilotos con ese apellido", e.getMessage());
    }

    @Test
    void BuscarPorAbreviatura() {
        Piloto piloto = Piloto.instance(UUID.randomUUID(),"Franco","Colapinto","Franco Colapinto","COL","LocalHost");

        List<Piloto> pilotosCorrectos = List.of(piloto);

        when(BD.buscarPilotosPorAbreviatura("COL")).thenReturn(pilotosCorrectos);

        BuscarPiloto BP = new BuscarPiloto(BD);

        Assertions.assertDoesNotThrow(() -> BP.buscarPilotosPorAbreviatura("COL"));
        Assertions.assertEquals(pilotosCorrectos, BP.buscarPilotosPorAbreviatura("COL"));
    }

    @Test
    void BuscarPorAbreviaturaNoEncontrado() {
        Piloto piloto = Piloto.instance(UUID.randomUUID(),"Franco","Colapinto","Franco Colapinto","COL","LocalHost");

        List<Piloto> pilotosCorrectos = List.of(piloto);

        Exception e;

        when(BD.buscarPilotosPorAbreviatura("VER")).thenReturn(List.of());

        BuscarPiloto BP = new BuscarPiloto(BD);

        e = Assertions.assertThrows(ExceptionNoHayPilotos.class,() -> BP.buscarPilotosPorAbreviatura("VER"));
        Assertions.assertEquals("No encontraron pilotos con esa abreviatura", e.getMessage());
    }
}