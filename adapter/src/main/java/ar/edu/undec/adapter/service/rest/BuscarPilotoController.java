package ar.edu.undec.adapter.service.rest;

import ar.edu.undec.adapter.service.domain.PilotoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import piloto.modelo.Piloto;
import piloto.usecase.BuscarPiloto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pilotos")
public class BuscarPilotoController {

    private BuscarPiloto input;

    public BuscarPilotoController(BuscarPiloto buscarPiloto) {
        this.input = buscarPiloto;
    }

    @GetMapping("/{apellido}")
    public ResponseEntity<?> buscarPiloto(@PathVariable String apellido) {
        try {
            List<Piloto> pilotos = this.input.buscarPilotosPorApellido(apellido);

            if (pilotos.isEmpty()) {
                return ResponseEntity.notFound().build();
            }


            List<PilotoDTO> pilotoDTOs = pilotos.stream()
                    .map(piloto -> new PilotoDTO(piloto.getId(), piloto.getNombre(),
                            piloto.getApellido(), piloto.getNombreCompleto(), piloto.getNombreAbreviado(),
                            piloto.getFotoPiloto()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(pilotoDTOs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
