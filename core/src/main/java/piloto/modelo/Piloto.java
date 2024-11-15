package piloto.modelo;

import java.net.URL;
import java.util.UUID;

public class Piloto {
    private UUID id;
    private String nombre;
    private String apellido;
    private String nombreCompleto;
    private String nombreAbreviado;
    private URL fotoPiloto;

    private Piloto(UUID id, String nombre, String apellido, String nombreCompleto, String nombreAbreviado, URL fotoPiloto) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreCompleto = nombreCompleto;
        this.nombreAbreviado = nombreAbreviado;
        this.fotoPiloto = fotoPiloto;
    }

    public static  Piloto instance(UUID id, String nombre, String apellido, String nombreCompleto, String nombreAbreviado, URL fotoPiloto){
        return new Piloto(id,nombre,apellido,nombreCompleto,nombreAbreviado,fotoPiloto);
    }
}
