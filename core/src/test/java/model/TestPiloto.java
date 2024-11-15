package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import piloto.exception.ExceptionAtibutoInvalido;
import piloto.exception.ExceptionNombreAbreviadoIncorrecto;
import piloto.exception.ExceptionNombreCompletoIncorrecto;
import piloto.modelo.Piloto;

import java.net.URI;
import java.net.URL;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import java.net.URI;
import java.net.URL;
import java.net.MalformedURLException;

public class TestPiloto {
    @Test
    void atributosObligatoriosCorrectamente() {
        try {
            URL dir = URI.create("http://localhost").toURL();
            Piloto p = Piloto.instance(UUID.randomUUID(),"Max","Verstappen","Max Verstappen","VER", dir );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void atributosObligatoriosIncorrectamente() {
        URL dir = null;
        Exception e;
        e = Assertions.assertThrows(ExceptionAtibutoInvalido.class, () -> {Piloto.instance(UUID.randomUUID(),"Max","Verstappen","Max Verstappen","VER", dir );});
        Assertions.assertEquals("alguno de los parametros no es valido", e.getMessage());
    }

    @Test
    void nombreCompletoIncorrecto() {
        try {
            URL dir = URI.create("http://localhost").toURL();
            Exception e;
            e = Assertions.assertThrows(ExceptionNombreCompletoIncorrecto.class, () -> {Piloto.instance(UUID.randomUUID(),"Max","Verstappen","MaxVerstappen","VER", dir );});
            Assertions.assertEquals("el nombre completo del piloto esta mal", e.getMessage());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void nombreAbreviadoIncorrecto() {
        try {
            URL dir = URI.create("http://localhost").toURL();
            Exception e;
            e = Assertions.assertThrows(ExceptionNombreAbreviadoIncorrecto.class, () -> {Piloto.instance(UUID.randomUUID(),"Max","Verstappen","Max Verstappen","MAX", dir );});
            Assertions.assertEquals("el nombre abreviado del piloto es incorrecto", e.getMessage());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

