package ar.edu.undec.adapter.service.piloto;

import ar.edu.undec.adapter.service.domain.PilotoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class TestPilotoDTO {

    @Test
    void serializacionJsonEsCorrecta() throws Exception {
        // Arrange
        UUID id = UUID.randomUUID();
        PilotoDTO pilotoDTO = new PilotoDTO(id, "Franco", "Colapinto", "Franco Colapinto", "COL", "LocalHost/8080");
        ObjectMapper objectMapper = new ObjectMapper();

        // Act
        String json = objectMapper.writeValueAsString(pilotoDTO);

        // Assert
        Assertions.assertTrue(json.contains("\"session_key\":\"" + id + "\""));
        Assertions.assertTrue(json.contains("\"first_name\":\"Franco\""));
        Assertions.assertTrue(json.contains("\"last_name\":\"Colapinto\""));
        Assertions.assertTrue(json.contains("\"full_name\":\"Franco Colapinto\""));
        Assertions.assertTrue(json.contains("\"name_acronym\":\"COL\""));
        Assertions.assertTrue(json.contains("\"headshot_url\":\"LocalHost/8080\""));
    }

    @Test
    void deserializacionJsonEsCorrecta() throws Exception {
        // Arrange
        String json = """
                {
                    "session_key": "123e4567-e89b-12d3-a456-426614174000",
                    "first_name": "Franco",
                    "last_name": "Colapinto",
                    "full_name": "Franco Colapinto",
                    "name_acronym": "COL",
                    "headshot_url": "LocalHost/8080"
                }
                """;
        ObjectMapper objectMapper = new ObjectMapper();

        // Act
        PilotoDTO pilotoDTO = objectMapper.readValue(json, PilotoDTO.class);

        // Assert
        Assertions.assertNotNull(pilotoDTO);
// el UUID es generado de forma random //Assertions.assertEquals(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"), pilotoDTO.getId());
        Assertions.assertNotNull(pilotoDTO.getId());
        Assertions.assertEquals("Franco", pilotoDTO.getNombre());
        Assertions.assertEquals("Colapinto", pilotoDTO.getApellido());
        Assertions.assertEquals("Franco Colapinto", pilotoDTO.getNombreCompleto());
        Assertions.assertEquals("COL", pilotoDTO.getNombreAbreviado());
        Assertions.assertEquals("LocalHost/8080", pilotoDTO.getFotoPiloto());
    }
}
