package ar.edu.undec.adapter.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import piloto.modelo.Piloto;

import java.util.UUID;

@Entity
@Table(name = "Pilotos")
public class PilotoData {

    @Id
    private UUID id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "nombre completo")
    private String nombreCompleto;
    @Column(name = "abreviatura")
    private String nombreAbreviado;
    @Column(name = "foto del piloto")
    private String fotoPiloto;

    public PilotoData() {}

    public PilotoData(UUID id, String nombre, String apellido, String nombreCompleto, String nombreAbreviado, String fotoPiloto) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreCompleto = nombreCompleto;
        this.nombreAbreviado = nombreAbreviado;
        this.fotoPiloto = fotoPiloto;
    }

    public static PilotoData fromDomain(Piloto piloto) {
        return new PilotoData(piloto.getId(),piloto.getNombre(),piloto.getApellido(),piloto.getNombreCompleto(),
                piloto.getNombreAbreviado(),piloto.getFotoPiloto());
    }

    public static Piloto toDomain(PilotoData pilotoData){
        return Piloto.instance(pilotoData.getId(),pilotoData.getNombre(),pilotoData.getApellido(),
                pilotoData.getNombreCompleto(),pilotoData.getNombreAbreviado(),
                pilotoData.getFotoPiloto());
    }

    public UUID getId() {return id;}
    public String getNombre() {return nombre;}
    public String getApellido() {return apellido;}
    public String getNombreCompleto() {return nombreCompleto;}
    public String getNombreAbreviado() {return nombreAbreviado;}
    public String getFotoPiloto() {return fotoPiloto;}
}
