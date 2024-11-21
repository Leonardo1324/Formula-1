package ar.edu.undec.adapter.service.piloto;


import ar.edu.undec.adapter.service.domain.PilotoDTO;
import ar.edu.undec.adapter.service.rest.BuscarPilotoController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import piloto.modelo.Piloto;
import piloto.usecase.BuscarPiloto;

import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuscarPilotoServiceTest {

    @Mock
    BuscarPiloto input;

    @InjectMocks
    BuscarPilotoController controller;


    @Test
    void BuscarPilotoCorrectoReturnOk() {
        // Arrange

//        BuscarPiloto input = mock(piloto.usecase.BuscarPiloto.class);
//        BuscarPilotoController controller = new BuscarPilotoController(input);

        String apellido = "Perez";
        Piloto piloto1 = Piloto.instance(UUID.randomUUID(), "Juan", "Perez", "Juan Perez", "PER", "foto1.jpg");
        List<Piloto> pilotos = List.of(piloto1);

        when(input.buscarPilotosPorApellido(apellido)).thenReturn(pilotos);

        ResponseEntity<?> response = controller.buscarPiloto(apellido);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        List<PilotoDTO> resultado = (List<PilotoDTO>) response.getBody();
        Assertions.assertEquals(1, resultado.size());
        verify(input,times(1)).buscarPilotosPorApellido(apellido);
    }

    @Test
    void BuscarPilotosSinResultadosReturn400() {
        String apellido = "Desconocido";
        when(input.buscarPilotosPorApellido(apellido)).thenReturn(Collections.emptyList());

        ResponseEntity<?> response = controller.buscarPiloto(apellido);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNull(response.getBody());
        verify(input,times(1)).buscarPilotosPorApellido(apellido);

    }

    @Test
    void BuscarPilotoConError() {
        String apellido = "Error";
        when(input.buscarPilotosPorApellido(apellido)).thenThrow(RuntimeException.class);

        ResponseEntity<?> response = controller.buscarPiloto(apellido);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertNull(response.getBody());
        verify(input,times(1)).buscarPilotosPorApellido(apellido);
    }
}
