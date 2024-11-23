package ar.edu.undec.adapter.service.rest;

import ar.edu.undec.adapter.service.domain.PilotoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import piloto.input.CrearPiloto;

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
    public ResponseEntity<?> crearPiloto(@RequestBody PilotoDTO pilotoDTO){
        try {
            boolean result = this.input.crearPiloto(pilotoDTO.getNombre(),pilotoDTO.getApellido(),pilotoDTO.getFotoPiloto());
            if(result) {
                return ResponseEntity.ok().build();
            }
            else {
                return ResponseEntity.internalServerError().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
