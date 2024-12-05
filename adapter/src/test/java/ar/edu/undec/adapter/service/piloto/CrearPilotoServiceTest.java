package ar.edu.undec.adapter.service.piloto;

import ar.edu.undec.adapter.service.domain.PilotoDTO;
import ar.edu.undec.adapter.service.rest.CrearPilotoController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import piloto.input.CrearPiloto;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CrearPilotoServiceTest {

    @Mock
    CrearPiloto input;

    @InjectMocks
    CrearPilotoController controller;

    @Test
    void crearPilotosCorrectamenteRetorna200() {
        when(input.crearPiloto("Franco", "Colapinto", "COL","Franco Colapinto","LocalHost/8080")).thenReturn(true);

        PilotoDTO pilotoDTO = new PilotoDTO(UUID.randomUUID(), "Franco", "Colapinto", "Franco Colapinto", "COL", "LocalHost/8080");

        ResponseEntity<?> resultado = controller.crearPiloto(List.of(pilotoDTO));

        Assertions.assertEquals(200, resultado.getStatusCode().value());
        Assertions.assertEquals("Todos los pilotos fueron creados exitosamente.", resultado.getBody());
    }

    @Test
    void crearPilotoConErroresParcialesRetorna200ConErrores() {
        when(input.crearPiloto("Franco", "Colapinto", "COL","Franco Colapinto","LocalHost/8080")).thenReturn(false);

        PilotoDTO pilotoDTO = new PilotoDTO(UUID.randomUUID(), "Franco", "Colapinto", "Franco Colapinto", "COL", "LocalHost/8080");

        ResponseEntity<?> resultado = controller.crearPiloto(List.of(pilotoDTO));

        Assertions.assertEquals(200, resultado.getStatusCode().value());
        Assertions.assertTrue(resultado.getBody().toString().contains("Algunos pilotos no fueron creados por"));
    }

    @Test
    void crearPilotoLanzaExcepcionGlobalRetorna400() {
        when(input.crearPiloto("Franco", "Colapinto", "COL","Franco Colapinto","LocalHost/8080")).thenThrow(new RuntimeException("Error inesperado"));

        PilotoDTO pilotoDTO = new PilotoDTO(UUID.randomUUID(), "Franco", "Colapinto", "Franco Colapinto", "COL", "LocalHost/8080");

        ResponseEntity<?> resultado = controller.crearPiloto(List.of(pilotoDTO));

        Assertions.assertEquals(400, resultado.getStatusCode().value());
        Assertions.assertEquals("Errores críticos detectados: Error crítico con un piloto Franco Colapinto: Error inesperado", resultado.getBody());
    }
}
