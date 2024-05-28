package co.edu.uniquindio.poo;

public abstract class Vehiculo {
    //Atributos de la clase vehiculo.
    private final String placa;
    private final String modelo;
    Propietario Propietario;

    /**
     * Metodo constructor de la clase abstracta Vehiculo.
     * @param placa
     * @param modelo
     * @param propietario
     */
    public Vehiculo(String placa, String modelo, Propietario propietario) {
        this.placa = placa;
        this.modelo = modelo;
        Propietario = propietario;
    }

    /**
     * Metodo para obtener la placa del vehiculo.
     * @return placa del vehiculo.
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Metodo para obtener el modelo del vehiculo.
     * @return año de modelo del vehiculo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Metodo para obtener el propietario del vehiculo.
     * @return propietario del vehiculo.
     */
    public Propietario getPropietario() {
        return Propietario;
    }

    /**
     * Metodo para modificar el propietario del vehiculo.
     * @param propietario
     */
    public void setPropietario(Propietario propietario) {
        Propietario = propietario;
    }

    


}