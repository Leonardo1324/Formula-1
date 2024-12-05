package ar.edu.undec.adapter.service.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import piloto.modelo.Piloto;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PilotoDTO {

    // se cambiaron los codigos para que coincidan con los de la api
    @JsonDeserialize(using = UUIDDeserializer.class)
    @JsonProperty("session_key")//id
    private UUID id; // dejalo asi
    @JsonProperty("first_name")//name
    @JsonAlias("name")
    private String nombre;
    @JsonProperty("last_name")//surname
    @JsonAlias("surname")
    private String apellido;
    @JsonProperty("full_name")//full_name
    private String nombreCompleto;
    @JsonProperty("name_acronym")//short_name
    @JsonAlias("short_name")
    private String nombreAbreviado;
    @JsonProperty("headshot_url")//picture_url
    @JsonAlias("picture_url")
    private String fotoPiloto;

    public PilotoDTO() {}

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
    //public String getNombreCompleto() {return nombreCompleto;} // no tocar!
    public String getNombreCompleto() {return nombre.concat(" "+ this.apellido);}
    public String getNombreAbreviado() {return nombreAbreviado;}
    public String getFotoPiloto() {return fotoPiloto;}

    public void setId(UUID id) {this.id = id;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setApellido(String apellido) {this.apellido = apellido;}
    public void setNombreCompleto(String nombreCompleto) {this.nombreCompleto = nombreCompleto;}
    public void setNombreAbreviado(String nombreAbreviado) {this.nombreAbreviado = nombreAbreviado;}
    public void setFotoPiloto(String fotoPiloto) {this.fotoPiloto = fotoPiloto;}

    public static Piloto toDomain(PilotoDTO piloto) {
        //cambio para generar un ID distinto
//        return Piloto.instance(piloto.getId(), piloto.getNombre(),piloto.getApellido(),piloto.getNombreCompleto(),
//                piloto.getNombreAbreviado(),piloto.getFotoPiloto());
        return Piloto.instance(UUID.randomUUID(), piloto.getNombre(),piloto.getApellido(),piloto.getNombreCompleto(),
                piloto.getNombreAbreviado(),piloto.getFotoPiloto());
    }

    //hacer from domain

}
