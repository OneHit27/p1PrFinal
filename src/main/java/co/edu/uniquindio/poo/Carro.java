package co.edu.uniquindio.poo;

public class Carro extends Vehiculo {

    /**
     * Metodo constructor de la clase Carro que hereda todo de vehiculo.
     * @param placa placa del carro.
     * @param modelo modelo del carro.
     * @param propietario propietario del carro.
     */
    public Carro(String placa, String modelo, Propietario propietario){
        super(placa, modelo, propietario);
    }

}