package ar.edu.undec.adapter.service.rest;


import ar.edu.undec.adapter.data.dbapi.BuscarPilotosDesdeApi;
import ar.edu.undec.adapter.data.repository.CrearPilotoGeatwayRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import piloto.modelo.Piloto;

import java.util.List;

@RestController
@RequestMapping
public class BuscarPilotosApiController {


    private BuscarPilotosDesdeApi buscarPilotosDesdeApi;
    private CrearPilotoGeatwayRepository crearPilotoGeatwayRepository;

    public BuscarPilotosApiController(BuscarPilotosDesdeApi buscarPilotosDesdeApi,
                                      CrearPilotoGeatwayRepository crearPilotoGeatwayRepository) {
        this.buscarPilotosDesdeApi = buscarPilotosDesdeApi;
        this.crearPilotoGeatwayRepository = crearPilotoGeatwayRepository;
    }

    @PostConstruct
    public void cargarPilotosAlIniciar() throws Exception {
        // Obtener los pilotos desde la API
        List<Piloto> pilotos = buscarPilotosDesdeApi.buscarPilotosDesdeAPI();

        // Guardar cada piloto en la base de datos
        for (Piloto piloto : pilotos) {
            crearPilotoGeatwayRepository.guardarPiloto(piloto);
        }
    }

    @GetMapping("/api")
    public List<Piloto> obtenerPilotos() throws Exception {
        List<Piloto> pilotos;
        pilotos = buscarPilotosDesdeApi.buscarPilotosDesdeAPI();
        return pilotos;
    }
}
