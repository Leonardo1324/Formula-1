package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import piloto.exception.ExceptionAtibutoInvalido;
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
}

