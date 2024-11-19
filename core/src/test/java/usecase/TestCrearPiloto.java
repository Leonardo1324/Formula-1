package usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import piloto.exception.ExceptionPilotoConElMismoNombre;
import piloto.exception.ExceptionPilotoConElMismoNombreAbreviado;
import piloto.modelo.Piloto;
import piloto.output.Persistencia;
import piloto.usecase.CrearPiloto;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestCrearPiloto {

    @Mock
    Persistencia BD;

    @Test
    void CrearPilotoNoExiste() { // crear correctamente
        String dir = "http://localhost";
        CrearPiloto crearPiloto = new CrearPiloto(BD);
        when(BD.existePilotoNombreCompleto("Franco Colapinto")).thenReturn(false);//el piloto no existe
        when(BD.existePilotoNombreAbreviado("COL")).thenReturn(false);// no existe esa abreviatura
        when(BD.guardarPiloto(Mockito.any())).thenReturn(true); // guardar un piloto
        Assertions.assertDoesNotThrow(() -> crearPiloto.crearPiloto("Franco", "Colapinto", dir));
    }

    @Test
    void CrearPilotoNoExistePeroFalla() { // crear correctamente
        String dir = "http://localhost";
        CrearPiloto crearPiloto = new CrearPiloto(BD);
        when(BD.existePilotoNombreCompleto("Franco Colapinto")).thenReturn(false);// el piloto no existe
        when(BD.guardarPiloto(Mockito.any())).thenReturn(false); // falla el registro
        when(BD.existePilotoNombreAbreviado("COL")).thenReturn(false);// no existe esa abreviatura
        Assertions.assertDoesNotThrow(() -> crearPiloto.crearPiloto("Franco", "Colapinto", dir));
        Assertions.assertFalse(crearPiloto.crearPiloto("Franco", "Colapinto", dir));
    }

    @Test
    void CrearPilotoExiste() { // crear correctamente
        String dir = "http://localhost";
        CrearPiloto crearPiloto = new CrearPiloto(BD);
        Exception e;
        when(BD.existePilotoNombreCompleto("Franco Colapinto")).thenReturn(true);// el piloto existe
        e = Assertions.assertThrows(ExceptionPilotoConElMismoNombre.class,() -> crearPiloto.crearPiloto("Franco", "Colapinto", dir));
        verify(BD,never()).guardarPiloto(Mockito.any());
        Assertions.assertEquals("Ya existe el piloto: Franco Colapinto",e.getMessage());
    }

    @Test
    void CrearPilotoNoExisteNombreAbreviadoCorrecto() { // crear correctamente
        String dir = "http://localhost";
        CrearPiloto crearPiloto = new CrearPiloto(BD);
        when(BD.existePilotoNombreCompleto("Franco Colapinto")).thenReturn(false);//el piloto no existe
        when(BD.existePilotoNombreAbreviado("COL")).thenReturn(false);// no existe esa abreviatura
        when(BD.guardarPiloto(Mockito.any())).thenReturn(true); // guardar un piloto
        Assertions.assertDoesNotThrow(() -> crearPiloto.crearPiloto("Franco", "Colapinto", dir));
    }


    @Test
    void CrearPilotoNoExisteNombreAbreviadoIncorrecto() { // crear correctamente
        String dir = "http://localhost";
        Exception e;
        CrearPiloto crearPiloto = new CrearPiloto(BD);
        when(BD.existePilotoNombreCompleto("Franco Colapinto")).thenReturn(false);//el piloto no existe
        when(BD.existePilotoNombreAbreviado("COL")).thenReturn(true);// existe esa abreviatura
        e = Assertions.assertThrows(ExceptionPilotoConElMismoNombreAbreviado.class,() -> crearPiloto.crearPiloto("Franco", "Colapinto", dir));
        verify(BD,never()).guardarPiloto(Mockito.any());
        Assertions.assertEquals("Ya existe el piloto: Franco Colapinto",e.getMessage());
    }

}
