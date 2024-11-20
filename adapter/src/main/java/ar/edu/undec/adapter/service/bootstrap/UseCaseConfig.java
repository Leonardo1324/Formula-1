package ar.edu.undec.adapter.service.bootstrap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import piloto.output.Persistencia;
import piloto.output.PersistenciaBuscarPilotos;
import piloto.usecase.BuscarPiloto;
import piloto.usecase.CrearPiloto;

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
}
