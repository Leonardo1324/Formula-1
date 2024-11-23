package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import piloto.exception.ExceptionAtibutoInvalido;
import piloto.exception.ExceptionCamposVacios;
import piloto.exception.ExceptionNombreAbreviadoIncorrecto;
import piloto.exception.ExceptionNombreCompletoIncorrecto;
import piloto.modelo.Piloto;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class TestPiloto {
    @Test
    void atributosObligatoriosCorrectamente() {
        String dir = "http://localhost";
        Piloto p = Piloto.instance(UUID.randomUUID(),"Max","Verstappen","Max Verstappen","VER", dir );
    }

    @Test
    void atributosObligatoriosIncorrectamente() {
        String dir = null;
        Exception e;
        e = Assertions.assertThrows(ExceptionAtibutoInvalido.class, () -> {Piloto.instance(UUID.randomUUID(),"Max","Verstappen","Max Verstappen","VER", dir );});
        Assertions.assertEquals("alguno de los parametros no es valido", e.getMessage());
    }

    @Test
    void nombreVacio() {
        String dir = "http://localhost";
        Exception e;
        e = Assertions.assertThrows(ExceptionCamposVacios.class, () -> {Piloto.instance(UUID.randomUUID(),"","Verstappen","Max Verstappen","VER", dir );});
        Assertions.assertEquals("alguno de los campos esta vacio", e.getMessage());
    }

    @Test
    void nombreCompletoIncorrecto() {

        String dir = "http://localhost";
        Exception e;
        e = Assertions.assertThrows(ExceptionNombreCompletoIncorrecto.class, () -> {Piloto.instance(UUID.randomUUID(),"Max","Verstappen","MaxVerstappen","VER", dir );});
        Assertions.assertEquals("el nombre completo del piloto esta mal", e.getMessage());
    }

    @Test
    void nombreAbreviadoIncorrecto() {
        String dir = "http://localhost";
        Exception e;
        e = Assertions.assertThrows(ExceptionNombreAbreviadoIncorrecto.class, () -> {Piloto.instance(UUID.randomUUID(),"Max","Verstappen","Max Verstappen","MAX", dir );});
        Assertions.assertEquals("el nombre abreviado del piloto es incorrecto es: VER", e.getMessage());
    }
}

