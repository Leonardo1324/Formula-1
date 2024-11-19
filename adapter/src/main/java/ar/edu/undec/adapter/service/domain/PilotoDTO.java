package ar.edu.undec.adapter.service.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import piloto.modelo.Piloto;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PilotoDTO {

    @JsonProperty("id")
    private UUID id;
    @JsonProperty
    private String nombre;
    @JsonProperty
    private String apellido;
    @JsonProperty
    private String nombreCompleto;
    @JsonProperty
    private String nombreAbreviado;
    @JsonProperty
    private String fotoPiloto;

    public PilotoDTO(UUID id, String nombre, String apellido, String nombreCompleto, String nombreAbreviado, String fotoPiloto) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreCompleto = nombreCompleto;
        this.nombreAbreviado = nombreAbreviado;
        this.fotoPiloto = fotoPiloto;
    }

    public UUID getId() {return id;}
    public String getNombre() {return nombre;}
    public String getApellido() {return apellido;}
    public String getNombreCompleto() {return nombreCompleto;}
    public String getNombreAbreviado() {return nombreAbreviado;}
    public String getFotoPiloto() {return fotoPiloto;}

    public static Piloto toDomain(PilotoDTO piloto) {
        return Piloto.instance(piloto.getId(), piloto.getNombre(),piloto.getApellido(),piloto.getNombreCompleto(),
                piloto.getNombreAbreviado(),piloto.getFotoPiloto());
    }
    //hacer from domain

}
