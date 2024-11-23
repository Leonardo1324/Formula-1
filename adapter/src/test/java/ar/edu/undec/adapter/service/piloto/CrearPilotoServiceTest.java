package ar.edu.undec.adapter.service.piloto;


import ar.edu.undec.adapter.service.domain.PilotoDTO;
import ar.edu.undec.adapter.service.rest.CrearPilotoController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import piloto.exception.ExceptionPilotoConElMismoNombre;
import piloto.input.CrearPiloto;

import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CrearPilotoServiceTest {

    @Mock
    CrearPiloto input;

    @InjectMocks
    CrearPilotoController controller;

    @Test
    void crearPilotoCorrectamenteReturn200() {

    when(input.crearPiloto("Franco","Colapinto","LocalHost/8080")).thenReturn(true);

        PilotoDTO pilotoDTO = new PilotoDTO(UUID.randomUUID(),"Franco","Colapinto","Franco Colapinto","COL","LocalHost/8080");

        ResponseEntity resultado = controller.crearPiloto(pilotoDTO);

        Assertions.assertEquals(HttpStatus.OK, resultado.getStatusCode());
    }

    @Test
    void crearPilotoIncorrectamenteReturn500() {

        when(input.crearPiloto("Franco","Colapinto","LocalHost/8080")).thenReturn(false);

        PilotoDTO pilotoDTO = new PilotoDTO(UUID.randomUUID(),"Franco","Colapinto","Franco Colapinto","COL","LocalHost/8080");

        ResponseEntity resultado = controller.crearPiloto(pilotoDTO);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, resultado.getStatusCode());
    }

    @Test
    void crearPilotoYaExistenteReturn400() {

        when(input.crearPiloto("Franco","Colapinto","LocalHost/8080")).thenThrow(new ExceptionPilotoConElMismoNombre("Ya existe el piloto: Franco Colapinto"));

        PilotoDTO pilotoDTO = new PilotoDTO(UUID.randomUUID(),"Franco","Colapinto","Franco Colapinto","COL","LocalHost/8080");

        ResponseEntity resultado = controller.crearPiloto(pilotoDTO);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, resultado.getStatusCode());
        Assertions.assertEquals("Ya existe el piloto: Franco Colapinto", resultado.getBody());
    }
}
