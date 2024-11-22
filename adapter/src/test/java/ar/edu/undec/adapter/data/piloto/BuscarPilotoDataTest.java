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
    void buscarPilotosPorNombre_DevuelveListaDePilotos() {
        // Configuración
        String nombre = "Max Verstappen";
        PilotoData pilotoData = new PilotoData(
                UUID.randomUUID(),
                "Max",
                "Verstappen",
                "Max Verstappen",
                "VER",
                "https://example.com/max.jpg"
        );

        when(buscarPilotoCRUD.findBynombreCompleto(nombre)).thenReturn(List.of(pilotoData));

        // Ejecución
        List<Piloto> resultado = buscarPilotoGetawayRepository.buscarPilotosPorNombre(nombre);

        // Verificación
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(1, resultado.size());
        Assertions.assertEquals("Max", resultado.get(0).getNombre());
        Assertions.assertEquals("Verstappen", resultado.get(0).getApellido());
        Mockito.verify(buscarPilotoCRUD, times(1)).findBynombreCompleto(nombre);
    }

    @Test
    void buscarPilotosPorApellido_DevuelveListaDePilotos() {
        // Configuración
        String apellido = "Hamilton";
        PilotoData pilotoData1 = new PilotoData(
                UUID.randomUUID(),
                "Lewis",
                "Hamilton",
                "Lewis Hamilton",
                "HAM",
                "https://example.com/lewis.jpg"
        );

        when(buscarPilotoCRUD.findByapellido(apellido)).thenReturn(List.of(pilotoData1));

        // Ejecución
        List<Piloto> resultado = buscarPilotoGetawayRepository.buscarPilotosPorApellido(apellido);

        // Verificación
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(1, resultado.size());
        Assertions.assertEquals("Hamilton", resultado.get(0).getApellido());
        Mockito.verify(buscarPilotoCRUD, times(1)).findByapellido(apellido);
    }

    @Test
    void buscarPilotosPorAbreviatura_DevuelveListaDePilotos() {
        // Configuración
        String abreviatura = "ALO";
        PilotoData pilotoData = new PilotoData(
                UUID.randomUUID(),
                "Fernando",
                "Alonso",
                "Fernando Alonso",
                "ALO",
                "https://example.com/fernando.jpg"
        );

        when(buscarPilotoCRUD.findBynombreAbreviado(abreviatura)).thenReturn(List.of(pilotoData));

        // Ejecución
        List<Piloto> resultado = buscarPilotoGetawayRepository.buscarPilotosPorAbreviatura(abreviatura);

        // Verificación
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(1, resultado.size());
        Assertions.assertEquals("ALO", resultado.get(0).getNombreAbreviado());
        Mockito.verify(buscarPilotoCRUD, times(1)).findBynombreAbreviado(abreviatura);
    }

    @Test
    void buscarPilotosPorNombre_DevuelveListaVacia() {
        // Configuración
        String nombre = "Sebastian Vettel";
        when(buscarPilotoCRUD.findBynombreCompleto(nombre)).thenReturn(List.of());

        // Ejecución
        List<Piloto> resultado = buscarPilotoGetawayRepository.buscarPilotosPorNombre(nombre);

        // Verificación
        Assertions.assertNotNull(resultado);
        Assertions.assertTrue(resultado.isEmpty());
        Mockito.verify(buscarPilotoCRUD, times(1)).findBynombreCompleto(nombre);
    }
}
