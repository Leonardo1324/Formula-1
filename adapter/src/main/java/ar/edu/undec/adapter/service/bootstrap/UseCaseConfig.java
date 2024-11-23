package ar.edu.undec.adapter.service.bootstrap;

import ar.edu.undec.adapter.data.dbapi.BuscarPilotosDesdeApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import piloto.input.BuscarPilotosDesdeAPI;
import piloto.modelo.Piloto;
import piloto.output.Persistencia;
import piloto.output.PersistenciaBuscarPilotos;
import piloto.usecase.BuscarPiloto;
import piloto.usecase.CrearPiloto;

import java.util.List;

@Configuration
public class UseCaseConfig {

    @Bean
    public CrearPiloto crearPilotoInput(Persistencia persistencia) {
        return new CrearPiloto(persistencia); // interfaz
    }
    @Bean
    public BuscarPiloto buscarPilotoinput(PersistenciaBuscarPilotos persistenciaBuscarPilotos) {
        return new BuscarPiloto(persistenciaBuscarPilotos);
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
    @Bean
    public BuscarPilotosDesdeAPI buscarPilotosDesdeAPI(RestTemplate restTemplate, ObjectMapper objectMapper) {
        return new ar.edu.undec.adapter.data.dbapi.BuscarPilotosDesdeApi(restTemplate, objectMapper);
    }

    @Bean
    public piloto.usecase.BuscarPilotoDesdeApi buscarPilotosCase(BuscarPilotosDesdeAPI buscarPilotosDesdeAPI) {
        return new piloto.usecase.BuscarPilotoDesdeApi(buscarPilotosDesdeAPI); // Caso de uso
    }

    @Bean
    public ar.edu.undec.adapter.data.dbapi.BuscarPilotosDesdeApi buscarPilotosDesdeApi(RestTemplate restTemplate, ObjectMapper objectMapper) {
        return new ar.edu.undec.adapter.data.dbapi.BuscarPilotosDesdeApi(restTemplate, objectMapper);
    }
}
