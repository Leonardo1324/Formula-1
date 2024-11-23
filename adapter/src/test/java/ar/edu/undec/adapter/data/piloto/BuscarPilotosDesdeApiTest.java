package ar.edu.undec.adapter.data.piloto;

import ar.edu.undec.adapter.data.dbapi.BuscarPilotosDesdeApi;
import ar.edu.undec.adapter.service.domain.PilotoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import piloto.modelo.Piloto;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuscarPilotosDesdeApiTest {
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ObjectMapper objectMapper;
    @InjectMocks
    private BuscarPilotosDesdeApi buscarPilotosDesdeApi;

    @Test
    void buscarPilotosDesdeAPIDevuelveListaDePilotos() throws Exception {
        String respuestaJson = """
                [
                        {
                            "session_key": "123e4567-e89b-12d3-a456-426614174000",
                            "first_name": "Max",
                            "last_name": "Verstappen",
                            "full_name": "Max VERSTAPPEN",
                            "name_acronym": "VER",
                            "headshot_url": "https://www.formula1.com/content/dam/fom-website/drivers/M/MAXVER01_Max_Verstappen/maxver01.png.transform/1col/image.png"
                        }
                ]
                """;

        List<PilotoDTO> pilotosDTO = List.of(
                new PilotoDTO(UUID.randomUUID(), "Max", "Verstappen",
                        "Max Verstappen", "VER",
                        "https://www.formula1.com/content/dam/fom-website/drivers/M/MAXVER01_M" +
                                "ax_Verstappen/maxver01.png.transform/1col/image.png"));

        String url = "https://api.openf1.org/v1/drivers";
        when(restTemplate.getForObject(url, String.class)).thenReturn(respuestaJson);
        when(objectMapper.readValue(any(String.class), any(TypeReference.class)))
                .thenReturn(pilotosDTO);


        List<Piloto> pilotos = buscarPilotosDesdeApi.buscarPilotosDesdeAPI();

        Assertions.assertNotNull(pilotos);
        Assertions.assertEquals(1, pilotos.size());
        Assertions.assertEquals("Max", pilotos.getFirst().getNombre());
        Assertions.assertEquals("Verstappen", pilotos.getFirst().getApellido());
    }

    @Test
    void buscarPilotosDesdeAPI_LanzaExcepcion() {

        String respuestaInvalida = "respuesta no válida";
        when(restTemplate.getForObject("https://api.openf1.org/v1/drivers", String.class))
                .thenReturn(respuestaInvalida);

        try {
            when(objectMapper.readValue(eq(respuestaInvalida), any(TypeReference.class)))
                    .thenThrow(new JsonProcessingException("Error al procesar JSON") {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            buscarPilotosDesdeApi.buscarPilotosDesdeAPI();
        });

        Assertions.assertNotNull(exception);
        Assertions.assertTrue(exception.getMessage().contains("Error al procesar JSON"));
        Assertions.assertTrue(exception.getCause() instanceof JsonProcessingException);
    }

}
