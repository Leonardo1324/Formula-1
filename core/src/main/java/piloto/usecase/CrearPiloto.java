package piloto.usecase;

import piloto.exception.ExceptionPilotoConElMismoNombre;
import piloto.exception.ExceptionPilotoConElMismoNombreAbreviado;
import piloto.modelo.Piloto;
import piloto.output.Persistencia;

import java.net.URL;
import java.util.UUID;

public class CrearPiloto implements piloto.input.CrearPiloto {

    private Persistencia myDB;
    public CrearPiloto(Persistencia myDB) {
        this.myDB = myDB;
    }
    @Override
    public boolean crearPiloto(String nombrePiloto, String apellidoPiloto, String imagenPiloto) {

        Piloto miPiloto = Piloto.instance(UUID.randomUUID(),nombrePiloto,apellidoPiloto
                ,nombrePiloto.concat(" "+apellidoPiloto),apellidoPiloto.substring(0,3).toUpperCase(),imagenPiloto);

        if (myDB.existePilotoNombreCompleto(miPiloto.getNombreCompleto())) {
            throw new ExceptionPilotoConElMismoNombre("Ya existe el piloto: " + miPiloto.getNombreCompleto());
        }
        if (myDB.existePilotoNombreAbreviado(miPiloto.getNombreAbreviado())) {
            throw new ExceptionPilotoConElMismoNombreAbreviado("Ya existe el piloto: " + miPiloto.getApellido() + " abreviado como: "+miPiloto.getNombreAbreviado());
        }
        return myDB.guardarPiloto(miPiloto);
    }
}
