package piloto.usecase;

import piloto.input.BuscarPilotosDesdeAPI;
import piloto.modelo.Piloto;

import java.util.List;

public class BuscarPilotoDesdeApi {
    private BuscarPilotosDesdeAPI buscarPilotosDesdeAPI;

    public BuscarPilotoDesdeApi(BuscarPilotosDesdeAPI buscarPilotosDesdeAPI) {
        this.buscarPilotosDesdeAPI = buscarPilotosDesdeAPI;
    }

    public List<Piloto> buscarPilotos() {
        return buscarPilotosDesdeAPI.buscarPilotosDesdeAPI();
    }
}
