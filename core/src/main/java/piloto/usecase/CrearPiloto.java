package piloto.usecase;

import piloto.exception.ExceptionPilotoConElMismoNombre;
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
    public boolean crearPiloto(String nombrePiloto, String apellidoPiloto, URL imagenPiloto) {
        Piloto miPiloto = Piloto.instance(UUID.randomUUID(),nombrePiloto,apellidoPiloto
                ,nombrePiloto.concat(" "+apellidoPiloto),apellidoPiloto.substring(0,3).toUpperCase(),imagenPiloto);
        if (myDB.existePiloto(miPiloto.getNombre())) {
            throw new ExceptionPilotoConElMismoNombre("Ya existe el piloto: " + miPiloto.getNombre());
        }
        return myDB.guardarPiloto(miPiloto);
    }
}
