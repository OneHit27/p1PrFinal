package co.edu.uniquindio.poo;

public class Puesto {
    // Atributos de los puestos del parqueadero.
    private final byte i;
    private final byte j;
    private boolean isOcupado = false;
    private Vehiculo vehiculo = null;

    /**
     * Metodo constructor para crear instancias de la clase parqueadero.
     * @param i Posicion de columnas
     * @param j Posicion de filas
     */
    public Puesto(byte i, byte j){
        this.i = i;
        this.j = j;
        
    }

    /**
     * Metodo para obtener el indice de posicion 'i'.
     * @return posicion 'i'.
     */
    public byte getI() {
        return i;
    }
 
    /**
     * Metodo para obtener el indice de posicion 'j'.
     * @return posicion 'j'
     */
    public byte getJ() {
        return j;
    }
    
    /**
     * Metodo para indicar si el puesto esta ocupado
     * @return true si el puesto esta ocupado, false si el puesto no esta ocupado.
     */
    public boolean isOcupado() {
        return isOcupado;
    }

    /**
     * Meoto para cambiar el estado de ocupacion del puesto.
     * @param isOcupado Parametro indicador de cambios de ocupación
     */
    public void setOcupado(boolean isOcupado) {
        this.isOcupado = isOcupado;
    }


    /**
     * Metodo para obtener el vehiculo que esta asignado al puesto.
     * @return vehiculo que esta dentro de el puesto.
     */
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    /**
     * Metodo para cambiar el vehiculo que se encuentra dentro de un puesto
     * @param vehiculo  Modificar el vehiculo que esta en el puesto
     */
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }



}