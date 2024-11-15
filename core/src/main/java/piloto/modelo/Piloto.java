package piloto.modelo;

import piloto.exception.ExceptionAtibutoInvalido;
import piloto.exception.ExceptionCamposVacios;
import piloto.exception.ExceptionNombreAbreviadoIncorrecto;
import piloto.exception.ExceptionNombreCompletoIncorrecto;

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

        if (id == null || nombre == null || apellido == null || nombreCompleto == null || nombreAbreviado == null || fotoPiloto == null) {
            throw new ExceptionAtibutoInvalido("alguno de los parametros no es valido");
        }
        if (nombre.isEmpty() || apellido.isEmpty() || nombreCompleto.isEmpty() || nombreAbreviado.isEmpty()) {
            throw new ExceptionCamposVacios("alguno de los campos esta vacio");
        }
        if (!nombre.concat(" "+apellido).equals(nombreCompleto)) {
            throw new ExceptionNombreCompletoIncorrecto("el nombre completo del piloto esta mal");
        }
        if (!apellido.substring(0,3).toUpperCase().equals(nombreAbreviado)){
            throw new ExceptionNombreAbreviadoIncorrecto("el nombre abreviado del piloto es incorrecto");
        }
        return new Piloto(id,nombre,apellido,nombreCompleto,nombreAbreviado,fotoPiloto);
    }

    public String getNombre() {
        return nombre;
    }
}
