package co.edu.uniquindio.poo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Parqueadero {
    //Atributos clase parqueadero.
    private int nPuestos;
    private Puesto[][] matrizPuestos;
    private List<Registro> registros; // Lista para mantener los registros
    private List<Vehiculo> vehiculosIngresados; // Lista para mantener los vehículos únicos ingresados
    private Map<Class<? extends Vehiculo>, Double> tarifasPorHora;



    /**
     * Metodo constructor clase parqueadero
     * @param nPuestos //Cantidad de puestos para crear una matriz con n cantidad de puestos.
     */
    public Parqueadero(int nPuestos){
        this.nPuestos = nPuestos;
        this.registros = new ArrayList<>(); // Inicializar la lista de registros
        this.vehiculosIngresados = new ArrayList<>(); // Inicializar la lista de vehículos ingresados
        crearMatrizPuestos(); //Esto hace que se cree automaticamente la matriz de puestos al crear el parqueadero
    }

    /**
     * Metodo para obtener la cantidad de puestos en el parqueadero.
     * @return La cantidad de puestos en el parqueadero.
     */
    public int getnPuestos() {
        return nPuestos;
    }

    /**
     * Metodo para modificar la cantidad de puestos que tiene el parqueadero, en caso de que se le añadan más.
     * @param nPuestos cantidad de puestos nueva.
     */
    public void setnPuestos(int nPuestos) {
        this.nPuestos = nPuestos;
    }

    /**
     * Metodo para obtener la matriz de puestos del parqueadero.
     * @return la matriz de puestos del parquedero.
     */
    public Puesto[][] getMatrizPuestos(){
        return matrizPuestos;
    }

    /**
     * Metodo que me crea la matriz de puestos de nCantidad de puestos dentro del parqueadero, donde este metodo instancia los puestos necesarios dentro de la matriz.
     */
    private void crearMatrizPuestos() {
        // Calcular el tamaño de la matriz.
        int filas = (int) Math.ceil(Math.sqrt(nPuestos));
        int columnas = (int) Math.ceil((double) nPuestos / filas);
    
        // Crear la matriz de puestos.
        matrizPuestos = new Puesto[filas][columnas];
    
        // Contador para llevar el seguimiento del número de puestos creados para que a pesar de que me de una matriz mas grande no me genere la cantidad exacta de esa matrix.
        int puestosCreados = 0;
    
        // Instanciar cada puesto en la matriz hasta alcanzar nPuestos.
        for (int i = 0; i < filas && puestosCreados < nPuestos; i++) {
            for (int j = 0; j < columnas && puestosCreados < nPuestos; j++) {
                matrizPuestos[i][j] = new Puesto((byte) i, (byte) j);
                puestosCreados++;
            }
        }
    }

    //Retorna la cantidad de puestos instanciados para verificar con la cantidad de puestos  del parqueadero (no es un requisito es una prueba que queria realizar para comprobar la correcta instancia de todo, las prueba esta en AppTest ).
    public int getPuestosInstanciados() {
        int contador = 0;
        // Recorrer la matriz de puestos y contar los puestos instanciados.
        for (int i = 0; i < matrizPuestos.length; i++) {
            for (int j = 0; j < matrizPuestos[i].length; j++) {
                if (matrizPuestos[i][j] != null) {
                    contador++;
                }
            }
        }
        return contador;
    }

    /**
     * Metodo para asignar un vehículo a un puesto específico en el parqueadero.
     * @param vehiculo El vehículo a asignar.
     * @param i Fila del puesto
     * @param j true si se asignó el vehículo correctamente, false si el puesto está ocupado o las coordenadas son inválidas.
     * @param fechaHoraIngreso La fecha y hora de ingreso del vehículo.
     * @return true si se asignó el vehículo correctamente, false si el puesto está ocupado o las coordenadas son inválidas.
     */

    public boolean asignarVehiculoAPuesto(Vehiculo vehiculo, int i, int j, LocalDateTime fechaHoraIngreso) {
        // Verificar si el vehículo ya está en otro puesto
        if (vehiculoEnOtroPuesto(vehiculo)) {
            System.out.println("El vehículo ya está asignado a otro puesto.");
            return false; // El vehículo ya está en otro puesto
        }

        Puesto puesto = obtenerPuesto(i, j);
        if (puesto != null) {
            if (!puesto.isOcupado()) { // Verifica que el puesto no está ocupado
                puesto.setVehiculo(vehiculo); // Asigna el vehículo al puesto
                puesto.setOcupado(true); // Marca el puesto como ocupado
                registros.add(new Registro(vehiculo, puesto, fechaHoraIngreso)); // Crea un nuevo registro

                // Añadir el vehículo a la lista de vehículos ingresados si no está presente
                if (!vehiculosIngresados.contains(vehiculo)) {
                    vehiculosIngresados.add(vehiculo);
                }

                return true; // Asignación exitosa
            } else {
                System.out.println("El puesto está ocupado.");
            }
        } else {
            System.out.println("Las coordenadas son inválidas.");
        }
        return false; // El puesto está ocupado o las coordenadas son inválidas
    }

    // Método auxiliar para verificar si un vehículo ya está en otro puesto

    /**
     * El metodo aux para le metodo asignar vehiculo que me asegura que un vehiculo no este ocupando dos puestos al mismo tiempo.
     * @param vehiculo el vehiculo que se piensa en asignar
     * @return un Boolean que indica si el vehiculo esta en otro puesto
     */
    private boolean vehiculoEnOtroPuesto(Vehiculo vehiculo) {
        for (Puesto[] fila : matrizPuestos) {
            for (Puesto puesto : fila) {
                if (puesto != null && puesto.isOcupado() && puesto.getVehiculo().equals(vehiculo)) {
                    return true; // El vehículo ya está en otro puesto
                }
            }
        }
        return false; // El vehículo no está en otro puesto
    }


    // Método auxiliar para obtener el puesto dado por las coordenadas
    private Puesto obtenerPuesto(int i, int j) {
        if (i >= 0 && i < matrizPuestos.length && j >= 0 && j < matrizPuestos[i].length) {
            return matrizPuestos[i][j];
        }
        return null;
    }


    //Metodos para cumplir el requisito "El sistema debe permitir verificar si un puesto está ocupado por un vehículo y si un puesto está disponible."

    /**
     * Metodo para verificar si un puesto está ocupado.
     * @param i Fila del puesto.
     * @param j Columna del puesto.
     * @return true si el puesto está ocupado, false si está disponible o las coordenadas son inválidas.
     */
    public boolean verificarPuestoOcupado(int i, int j) {
        // Verificar que las coordenadas están dentro de los límites de la matriz
        if (i >= 0 && i < matrizPuestos.length && j >= 0 && j < matrizPuestos[i].length) {
            return matrizPuestos[i][j].isOcupado();
        }
        return false; // Coordenadas inválidas.
    }

    /**
     * Metodo para verificar si un puesto está disponible es la misma mrd que ocupado.
     * @param i Fila del puesto.
     * @param j Columna del puesto.
     * @return true si el puesto está disponible, false si está ocupado o las coordenadas son inválidas.
     */
    public boolean verificarPuestoDisponible(int i, int j) {
        // Verificar que las coordenadas están dentro de los límites de la matriz.
        if (i >= 0 && i < matrizPuestos.length && j >= 0 && j < matrizPuestos[i].length) {
            return !matrizPuestos[i][j].isOcupado();
        }
        System.out.println("Coordenadas invalidas.");
        return false;
        // Coordenadas inválidas.
    }

    // Aqui terminan los metodos para ese requisito.

    //Metodos para el requisito "Se debe poder identificar al propietario de un vehículo ubicado en un puesto dado."
    /**
     * Metodo para obtener el propietario de un vehiculo ubicado en un puesto dado
     * @param i posicion i del puesto
     * @param j posicion j del puesto
     * @return propietario del vehiculo dado un puesto
     */
    public Propietario obtenerPropietarioPorPuesto(int i, int j) {
        if (i >= 0 && i < matrizPuestos.length && j >= 0 && j < matrizPuestos[i].length) {
            Puesto puesto = matrizPuestos[i][j];
            if (puesto.isOcupado() && puesto.getVehiculo() != null) {
                return puesto.getVehiculo().getPropietario();
            }
        }
        return null;
    }


    /**
     * Método independiente para verificar si un puesto está ocupado (Consultar el estado de un puesto, imprimiendo en pantalla su estado de ocupación).
     * @param i Fila del puesto.
     * @param j Columna del puesto.
     * @return Mensaje indicando si el puesto está ocupado o no.
     */
    public String verificarEstadoPuesto(int i, int j) {
        Puesto puesto = obtenerPuesto(i, j);
        if (puesto != null) {
            return puesto.isOcupado() ? "El puesto está ocupado" : "El puesto está disponible";
        }
        return "Las coordenadas son inválidas";
    }


    /**
     * Método para obtener la lista de vehículos ingresados en el parqueadero sin duplicados.
     * @return Una lista con todos los vehículos ingresados en el parqueadero sin duplicados.
     */
    public List<Vehiculo> getVehiculosIngresados() {
        return vehiculosIngresados;
    }
    
    /**
     * Metodo para modificar la tarifa por hora donde la clave es cualquiera clase que herede de vehiculo y le asigna un valor para la hora de parqueo
     * @param tipoVehiculo
     * @param tarifa
     */
    public void setTarifaPorHora(Class<? extends Vehiculo> tipoVehiculo, double tarifa) {
        tarifasPorHora.put(tipoVehiculo, tarifa);
    }

    /**
     * Metodo para obtener la tarifa por hora ingresando la clase a la que esta pertenece
     * @param tipoVehiculo
     * @return
     */
    public double getTarifaPorHora(Class<? extends Vehiculo> tipoVehiculo) {
        return tarifasPorHora.getOrDefault(tipoVehiculo, 0.0);
    }

    
    // Método para calcular el costo total del estacionamiento de un vehículo identificado por su placa
    public String calcularCostoTotalEstacionamiento(String placa, LocalDateTime fechaHoraSalida) {
        for (Registro registro : registros) {
            if (registro.getVehiculo().getPlaca().equals(placa)) {
                LocalDateTime fechaHoraIngreso = registro.getMomentoIngreso();
                Duration duracion = Duration.between(fechaHoraIngreso, fechaHoraSalida);
                long horas = duracion.toHours();
                
                double tarifaPorHora = getTarifaPorHora(registro.getVehiculo().getClass());
                double costoTotal = horas * tarifaPorHora;

                return "El costo total del estacionamiento para el vehículo con placa " + placa + " es: " + costoTotal;
            }
        }
        return "No se encontró el vehículo con placa " + placa;
    }



    // Método para generar el reporte diario
    public String generarReporteDiario(LocalDate fecha) {
        double totalRecaudado = 0;
        List<String> detallesVehiculos = new ArrayList<>();

        for (Registro registro : registros) {
            LocalDateTime ingreso = registro.getMomentoIngreso();
            LocalDateTime salida = registro.getMomentoSalida();

            if (salida != null && ingreso.toLocalDate().equals(fecha)) {
                Duration duracion = Duration.between(ingreso, salida);
                long horas = duracion.toHours();
                double tarifaPorHora = getTarifaPorHora(registro.getVehiculo().getClass());
                double costo = horas * tarifaPorHora;
                totalRecaudado += costo;

                String tipoVehiculo = registro.getVehiculo().getClass().getSimpleName();
                detallesVehiculos.add("Placa: " + registro.getVehiculo().getPlaca() + ", Tipo: " + tipoVehiculo + ", Costo: " + costo);
            }
        }

        StringBuilder reporte = new StringBuilder();
        reporte.append("Reporte Diario - Fecha: ").append(fecha).append("\n");
        reporte.append("Total Recaudado: ").append(totalRecaudado).append("\n");
        reporte.append("Detalles de Vehículos:\n");
        for (String detalle : detallesVehiculos) {
            reporte.append(detalle).append("\n");
        }

        return reporte.toString();
    }

    // Método para obtener la tarifa por hora de un tipo de vehículo
    

    

}