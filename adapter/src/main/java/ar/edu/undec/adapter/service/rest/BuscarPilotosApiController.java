package ar.edu.undec.adapter.service.rest;


import ar.edu.undec.adapter.data.dbapi.BuscarPilotosDesdeApi;
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

    public BuscarPilotosApiController(BuscarPilotosDesdeApi buscarPilotosDesdeApi) {
        this.buscarPilotosDesdeApi = buscarPilotosDesdeApi;
    }

    @GetMapping("/api")
    public List<Piloto> obtenerPilotos() throws Exception {
        return buscarPilotosDesdeApi.buscarPilotosDesdeAPI();
    }
}
