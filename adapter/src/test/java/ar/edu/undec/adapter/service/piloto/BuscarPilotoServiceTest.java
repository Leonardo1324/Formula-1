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

        Piloto piloto1 = Piloto.instance(UUID.randomUUID(), "Franco", "Colapinto", "Franco Colapinto", "COL", "LocalHost/8080");
        List<Piloto> pilotos = List.of(piloto1);

        when(input.buscarTodasLosPilotos()).thenReturn(pilotos);

        ResponseEntity<?> response = controller.buscarPiloto();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        List<PilotoDTO> resultado = (List<PilotoDTO>) response.getBody();
        Assertions.assertEquals(1, resultado.size());
        verify(input,times(1)).buscarTodasLosPilotos();
    }

    @Test
    void BuscarPilotosSinResultadosReturn400() {

        when(input.buscarTodasLosPilotos()).thenReturn(Collections.emptyList());

        ResponseEntity<?> response = controller.buscarPiloto();

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNull(response.getBody());
        verify(input,times(1)).buscarTodasLosPilotos();

    }

    @Test
    void BuscarPilotoConError() {
        String apellido = "Error";
        when(input.buscarTodasLosPilotos()).thenThrow(RuntimeException.class);

        ResponseEntity<?> response = controller.buscarPiloto();

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertNull(response.getBody());
        verify(input,times(1)).buscarTodasLosPilotos();
    }

    // por apellido
    @Test
    void BuscarPilotoPorApellidoCorrectoReturnOk() {
        String apellido = "Colapinto";
        Piloto piloto1 = Piloto.instance(UUID.randomUUID(), "Franco", "Colapinto", "Franco Colapinto", "COL", "LocalHost/8080");
        List<Piloto> pilotos = List.of(piloto1);

        when(input.buscarPilotosPorApellido(apellido)).thenReturn(pilotos);

        ResponseEntity<?> response = controller.buscarPilotoPorApellido(apellido);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        List<PilotoDTO> resultado = (List<PilotoDTO>) response.getBody();
        Assertions.assertEquals(1, resultado.size());
        verify(input,times(1)).buscarPilotosPorApellido(apellido);
    }
    @Test
    void BuscarPilotosPorApellidoSinResultadosReturn400() {
        String apellido = "Colapinto";
        when(input.buscarPilotosPorApellido(apellido)).thenReturn(Collections.emptyList());

        ResponseEntity<?> response = controller.buscarPilotoPorApellido(apellido);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNull(response.getBody());
        verify(input,times(1)).buscarPilotosPorApellido(apellido);
    }

    @Test
    void BuscarPilotoPorApellidoConError() {
        String apellido = "Error";
        when(input.buscarPilotosPorApellido(apellido)).thenThrow(RuntimeException.class);

        ResponseEntity<?> response = controller.buscarPilotoPorApellido(apellido);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertNull(response.getBody());
        verify(input,times(1)).buscarPilotosPorApellido(apellido);
    }

    // por apellido
    @Test
    void BuscarPilotoPorAbreviaturaCorrectoReturnOk() {
        String abre = "COL";
        Piloto piloto1 = Piloto.instance(UUID.randomUUID(), "Franco", "Colapinto", "Franco Colapinto", "COL", "LocalHost/8080");
        List<Piloto> pilotos = List.of(piloto1);

        when(input.buscarPilotosPorAbreviatura(abre)).thenReturn(pilotos);

        ResponseEntity<?> response = controller.buscarPilotoPorAbreviatura(abre);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        List<PilotoDTO> resultado = (List<PilotoDTO>) response.getBody();
        Assertions.assertEquals(1, resultado.size());
        verify(input,times(1)).buscarPilotosPorAbreviatura(abre);
    }
    @Test
    void BuscarPilotosPorAbreviaturaSinResultadosReturn400() {
        String abre = "COL";
        when(input.buscarPilotosPorAbreviatura(abre)).thenReturn(Collections.emptyList());

        ResponseEntity<?> response = controller.buscarPilotoPorAbreviatura(abre);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNull(response.getBody());
        verify(input,times(1)).buscarPilotosPorAbreviatura(abre);
    }

    @Test
    void BuscarPilotoPorAbreviaturaConError() {
        String abre = "Error";
        when(input.buscarPilotosPorAbreviatura(abre)).thenThrow(RuntimeException.class);

        ResponseEntity<?> response = controller.buscarPilotoPorAbreviatura(abre);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertNull(response.getBody());
        verify(input,times(1)).buscarPilotosPorAbreviatura(abre);
    }
}
