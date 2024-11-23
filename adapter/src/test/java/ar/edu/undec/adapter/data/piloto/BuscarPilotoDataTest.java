package ar.edu.undec.adapter.data.piloto;

import ar.edu.undec.adapter.data.dbapi.BuscarPilotoCRUD;
import ar.edu.undec.adapter.data.model.PilotoData;
import ar.edu.undec.adapter.data.repository.BuscarPilotoGetawayRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import piloto.modelo.Piloto;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuscarPilotoDataTest {

    @Mock
    private BuscarPilotoCRUD buscarPilotoCRUD;

    @InjectMocks
    private BuscarPilotoGetawayRepository buscarPilotoGetawayRepository;

    @Test
    void buscarPilotosPorNombreCompletoDevuelveListaDePilotos() {
        String nombre = "Max Verstappen";
        PilotoData pilotoData = new PilotoData(UUID.randomUUID(), "Max", "Verstappen", "Max Verstappen", "VER", "LocalHost/8080");

        when(buscarPilotoCRUD.findBynombreCompleto(nombre)).thenReturn(List.of(pilotoData));

        List<Piloto> resultado = buscarPilotoGetawayRepository.buscarPilotosPorNombreCompleto(nombre);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(1, resultado.size());
        Assertions.assertEquals("Max", resultado.getFirst().getNombre());
        Assertions.assertEquals("Verstappen", resultado.getFirst().getApellido());
        Mockito.verify(buscarPilotoCRUD, times(1)).findBynombreCompleto(nombre);
    }

    @Test
    void buscarPilotosPorApellidoDevuelveListaDePilotos() {
        String apellido = "Verstappen";
        PilotoData pilotoData1 = new PilotoData(UUID.randomUUID(), "Max", "Verstappen", "Max Verstappen", "VER", "LocalHost/8080");

        when(buscarPilotoCRUD.findByapellido(apellido)).thenReturn(List.of(pilotoData1));

        List<Piloto> resultado = buscarPilotoGetawayRepository.buscarPilotosPorApellido(apellido);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(1, resultado.size());
        Assertions.assertEquals("Verstappen", resultado.getFirst().getApellido());
        Mockito.verify(buscarPilotoCRUD, times(1)).findByapellido(apellido);
    }

    @Test
    void buscarPilotosPorAbreviaturaDevuelveListaDePilotos() {

        String abreviatura = "VER";
        PilotoData pilotoData = new PilotoData(UUID.randomUUID(), "Max", "Verstappen", "Max Verstappen", "VER", "LocalHost/8080");

        when(buscarPilotoCRUD.findBynombreAbreviado(abreviatura)).thenReturn(List.of(pilotoData));

        List<Piloto> resultado = buscarPilotoGetawayRepository.buscarPilotosPorAbreviatura(abreviatura);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(1, resultado.size());
        Assertions.assertEquals("VER", resultado.getFirst().getNombreAbreviado());
        Mockito.verify(buscarPilotoCRUD, times(1)).findBynombreAbreviado(abreviatura);
    }

    @Test
    void buscarPilotosPorNombreCompletoDevuelveListaVacia() {
        String nombre = "Sebastian Vettel";
        when(buscarPilotoCRUD.findBynombreCompleto(nombre)).thenReturn(List.of());

        List<Piloto> resultado = buscarPilotoGetawayRepository.buscarPilotosPorNombreCompleto(nombre);

        Assertions.assertNotNull(resultado);
        Assertions.assertTrue(resultado.isEmpty());
        Mockito.verify(buscarPilotoCRUD, times(1)).findBynombreCompleto(nombre);
    }


    @Test
    void buscarPilotosPorAbreviaturaDevuelveListaVacia() {
        String abreviatura = "VET";
        when(buscarPilotoCRUD.findBynombreAbreviado(abreviatura)).thenReturn(List.of());

        List<Piloto> resultado = buscarPilotoGetawayRepository.buscarPilotosPorAbreviatura(abreviatura);

        Assertions.assertNotNull(resultado);
        Assertions.assertTrue(resultado.isEmpty());
        Mockito.verify(buscarPilotoCRUD, times(1)).findBynombreAbreviado(abreviatura);
    }

    @Test
    void buscarPilotosPorApellidoDevuelveListaVacia() {
        String apellido = "Vettel";
        when(buscarPilotoCRUD.findByapellido(apellido)).thenReturn(List.of());

        List<Piloto> resultado = buscarPilotoGetawayRepository.buscarPilotosPorApellido(apellido);

        Assertions.assertNotNull(resultado);
        Assertions.assertTrue(resultado.isEmpty());
        Mockito.verify(buscarPilotoCRUD, times(1)).findByapellido(apellido);
    }
}
