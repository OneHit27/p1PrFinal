package co.edu.uniquindio.poo;
import java.time.LocalDateTime;

public class Registro {

    //Atributos necesarios para generar un registro de visita al parqueadero. 
    private  Vehiculo vehiculo;
    private  Puesto puesto;
    private  LocalDateTime momentoIngreso;
    private  LocalDateTime momentoSalida;


    /**
     * Constructor para crear instancia  la clase registro.
     * @param vehiculo
     * @param puesto
     * @param momentoIngreso
     */
    public Registro(Vehiculo vehiculo,  Puesto puesto, LocalDateTime momentoIngreso) {
        this.vehiculo = vehiculo;
        this.puesto = puesto;
        this.momentoIngreso = momentoIngreso;
        this.momentoSalida = null;
    }

    /**
     * Metodo para obtener el vehiculo del registro.
     * @return EL vehiculo que esta en estre registro.
     */
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    /**
     * Metodo para obtener el puesto en el que esta ubicado el vehiculo en al generar el registro.
     * @return La ubicacion del vehiculo dentro del registro.
     */
    public Puesto getPuesto() {
        return puesto;
    }

    /**
     * Metodo para registrar la hora de ingreso del vehiculo al parqueadero esto luego servira para calcular las horas que estubo el vehiculo dentro del parqueadero y generar un costo de tiempo dentro de este.
     * @return el hora en la que fue ingresado el vehiculo.
     */
    public LocalDateTime getMomentoIngreso() {
        return momentoIngreso;
    }

    /**
     * Metodo para mostrar en forma de cadena toda la informacion de un registro, esta enfocado para la obtencion de forma impresa de la info del registro.
     */
    @Override
    public String toString() {
        return "Registro{" +
                "vehiculo=" + vehiculo.getPlaca() +
                ", propietario=" + vehiculo.getPropietario().getNombre() +
                ", puesto=(" + puesto.getI() + "," + puesto.getJ() + ")" +
                ", momentoIngreso=" + momentoIngreso +
                '}';
    }


    /**
     * Metodo para obtener el momento de salida del vehiculo dentro del registro
     * @return
     */
    public LocalDateTime getMomentoSalida() {
        return momentoSalida;
    }

    /**
     * Meoto para  modificar el momento de salida
     * @param momentoSalida
     */
    public void registrarSalida(LocalDateTime momentoSalida) {
        this.momentoSalida = momentoSalida;
    }

}