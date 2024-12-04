package ar.edu.undec.adapter.service.rest;

import ar.edu.undec.adapter.service.domain.PilotoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import piloto.modelo.Piloto;
import piloto.usecase.BuscarPiloto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "https://leonardo1324.github.io")
@RestController
@RequestMapping("/pilotos")
public class BuscarPilotoController {

    private BuscarPiloto input;

    public BuscarPilotoController(BuscarPiloto buscarPiloto) {
        this.input = buscarPiloto;
    }

    @GetMapping()
    public ResponseEntity<?> buscarPiloto() {
        try {
            List<Piloto> pilotos = this.input.buscarTodasLosPilotos();

            if (pilotos.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(convertirAPilotoDTO(pilotos));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/apellido")
    public ResponseEntity<?> buscarPilotoPorApellido(@RequestParam String apellido) {
        try {
            List<Piloto> pilotos = this.input.buscarPilotosPorApellido(apellido);

            if (pilotos.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(convertirAPilotoDTO(pilotos));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/abreviatura")
    public ResponseEntity<?> buscarPilotoPorAbreviatura(@RequestParam String abreviatura) {
        try {
            List<Piloto> pilotos = this.input.buscarPilotosPorAbreviatura(abreviatura);

            if (pilotos.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(convertirAPilotoDTO(pilotos));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private List<PilotoDTO> convertirAPilotoDTO(List<Piloto> pilotos) {
        return pilotos.stream()
                .map(piloto -> new PilotoDTO(piloto.getId(), piloto.getNombre(),
                        piloto.getApellido(), piloto.getNombreCompleto(),
                        piloto.getNombreAbreviado(), piloto.getFotoPiloto()))
                .collect(Collectors.toList());
    }

}
