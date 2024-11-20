package ar.edu.undec.adapter.service.rest;

import ar.edu.undec.adapter.service.domain.PilotoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import piloto.modelo.Piloto;
import piloto.usecase.BuscarPiloto;

import java.util.ArrayList;

@RestController
@RequestMapping("/pilotos")
public class BuscarPilotoController {

    private BuscarPiloto input;

    public BuscarPilotoController(BuscarPiloto buscarPiloto) {
        this.input = buscarPiloto;
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> buscarPiloto(@PathVariable String name) {
        try {
            ArrayList<Piloto> pilotos = this.input.buscarPilotoPorNombre(name);

            if (pilotos.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            ArrayList<PilotoDTO> pilotoDTOs = (ArrayList<PilotoDTO>) pilotos.stream()
                    .map(piloto -> new PilotoDTO(piloto.getId(),piloto.getNombre(),
                            piloto.getApellido(),piloto.getNombreCompleto(),piloto.getNombreAbreviado(),
                            piloto.getFotoPiloto()));

            return ResponseEntity.ok(pilotoDTOs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
