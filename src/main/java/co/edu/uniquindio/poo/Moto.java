package co.edu.uniquindio.poo;

public abstract class Moto extends Vehiculo {
    //Atributos propios de la clase vehiculo.
    private final int velocidadMax;
    
    /**
     *  Metodo constructor de la clase Moto que hereda de la clase vehiculo.
     * @param placa
     * @param modelo
     * @param propietario
     * @param tipoMoto
     * @param velocidadMax
     */
    public Moto(String placa, String modelo, Propietario propietario, int velocidadMax){
        super(placa, modelo, propietario);
        this.velocidadMax = velocidadMax;
    }
    
    /**
     * Metodo para Consultar velocidad Maxima de la moto.
     * @return la velocidada maxima que alcanza la moto.
     */
    public int consultarVelocidadMaxMoto() {
        return velocidadMax;
    }
    
}