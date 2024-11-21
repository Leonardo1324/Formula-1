package ar.edu.undec.adapter.data.dbapi;

import ar.edu.undec.adapter.service.domain.PilotoDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;
import piloto.input.BuscarPilotosDesdeAPI;
import piloto.modelo.Piloto;

import java.util.List;
import java.util.stream.Collectors;

public class BuscarPilotosDesdeApi implements BuscarPilotosDesdeAPI {

    private static final String URL_API = "https://api.openf1.org/v1/drivers";
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    public BuscarPilotosDesdeApi(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Piloto> buscarPilotosDesdeAPI() {
        try{
            String respuesta = restTemplate.getForObject(URL_API, String.class);

            List<PilotoDTO> pilotosDTO = objectMapper.readValue(respuesta, new TypeReference<List<PilotoDTO>>() {});

            return pilotosDTO.stream().map(PilotoDTO::toDomain).collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
