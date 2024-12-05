package ar.edu.undec.adapter.service.rest;

import ar.edu.undec.adapter.service.domain.PilotoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import piloto.input.CrearPiloto;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pilotos")
public class CrearPilotoController {

    private CrearPiloto input;

    @Autowired
    public CrearPilotoController(CrearPiloto crearPiloto) {
        this.input = crearPiloto;
    }

    @PostMapping
    //@RequestBody
    public ResponseEntity<?> crearPiloto(@RequestBody List<PilotoDTO> pilotosDTO) {
        List<String> errores = new ArrayList<>();

        for (PilotoDTO pilotoDTO : pilotosDTO) {
            try {
                boolean result = this.input.crearPiloto(
                        pilotoDTO.getNombre(),
                        pilotoDTO.getApellido(),
                        pilotoDTO.getFotoPiloto()
                );

                if (!result) {
                    errores.add("No se pudo crear el piloto: " + pilotoDTO.getNombreCompleto());
                }
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
            catch (Exception e) {
                errores.add("Error con el piloto " + pilotoDTO.getNombreCompleto() + ": " + e.getMessage());
            }
        }
        if (errores.isEmpty()) {
            return ResponseEntity.ok("Todos los pilotos fueron creados exitosamente.");
        } else {
            return ResponseEntity.ok("Algunos pilotos no fueron creados por:  " + String.join("; ", errores));
        }
    }
}
