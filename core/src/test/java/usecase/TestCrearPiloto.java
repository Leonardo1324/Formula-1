package usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import piloto.exception.ExceptionPilotoConElMismoNombre;
import piloto.modelo.Piloto;
import piloto.output.Persistencia;
import piloto.usecase.CrearPiloto;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestCrearPiloto {

    @Mock
    Persistencia BD;

    @Test
    void CrearPilotoNoExiste() { // crear correctamente
        try {
            URL dir = URI.create("http://localhost").toURL();
            CrearPiloto crearPiloto = new CrearPiloto(BD);
            when(BD.existePiloto("Franco")).thenReturn(false);
            when(BD.guardarPiloto(Mockito.any())).thenReturn(true); // que guardar reciba un piloto
            Assertions.assertDoesNotThrow(() -> crearPiloto.crearPiloto("Franco", "Colapinto", dir));
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    @Test
    void CrearPilotoExiste() { // crear correctamente
        try {
            URL dir = URI.create("http://localhost").toURL();
            CrearPiloto crearPiloto = new CrearPiloto(BD);
            Exception e;
            when(BD.existePiloto("Franco")).thenReturn(true);// el piloto existe
            e = Assertions.assertThrows(ExceptionPilotoConElMismoNombre.class,() -> crearPiloto.crearPiloto("Franco", "Colapinto", dir));
            Assertions.assertEquals("Ya existe el piloto: Franco",e.getMessage());
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
