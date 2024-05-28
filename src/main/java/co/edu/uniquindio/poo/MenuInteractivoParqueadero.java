package co.edu.uniquindio.poo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MenuInteractivoParqueadero {
    private Parqueadero parqueadero;
    private Scanner scanner;

    public MenuInteractivoParqueadero() {
        scanner = new Scanner(System.in);
        inicializarParqueadero();
    }

    private void inicializarParqueadero() {
        System.out.print("Ingrese la cantidad de puestos del parqueadero: ");
        int nPuestos = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea
        parqueadero = new Parqueadero(nPuestos);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\nMenu Parqueadero");
            System.out.println("1. Asignar vehículo a puesto");
            System.out.println("2. Verificar estado de un puesto");
            System.out.println("3. Ver propietario de un vehículo en un puesto");
            System.out.println("4. Calcular costo total de estacionamiento");
            System.out.println("5. Generar reporte diario");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    asignarVehiculoAPuesto();
                    break;
                case 2:
                    verificarEstadoPuesto();
                    break;
                case 3:
                    obtenerPropietarioPorPuesto();
                    break;
                case 4:
                    calcularCostoTotalEstacionamiento();
                    break;
                case 5:
                    generarReporteDiario();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 6);
    }

    private void asignarVehiculoAPuesto() {
        System.out.print("Ingrese la placa del vehículo: ");
        String placa = scanner.nextLine();
        System.out.print("Ingrese el modelo del vehículo: ");
        String modelo = scanner.nextLine();
        System.out.print("Ingrese el nombre del propietario: ");
        String nombrePropietario = scanner.nextLine();
        System.out.print("Ingrese la cédula del propietario: ");
        String cedulaPropietario = scanner.nextLine();

        Propietario propietario = new Propietario(nombrePropietario, cedulaPropietario);
        Vehiculo vehiculo = new Carro(placa, modelo, propietario);

        System.out.print("Ingrese la fila del puesto: ");
        int fila = scanner.nextInt();
        System.out.print("Ingrese la columna del puesto: ");
        int columna = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        System.out.print("Ingrese la fecha y hora de ingreso (yyyy-MM-dd HH:mm): ");
        String fechaHoraIngresoStr = scanner.nextLine();
        LocalDateTime fechaHoraIngreso = LocalDateTime.parse(fechaHoraIngresoStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        boolean asignado = parqueadero.asignarVehiculoAPuesto(vehiculo, fila, columna, fechaHoraIngreso);
        if (asignado) {
            System.out.println("Vehículo asignado correctamente.");
        } else {
            System.out.println("No se pudo asignar el vehículo al puesto.");
        }
    }

    private void verificarEstadoPuesto() {
        System.out.print("Ingrese la fila del puesto: ");
        int fila = scanner.nextInt();
        System.out.print("Ingrese la columna del puesto: ");
        int columna = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        String estado = parqueadero.verificarEstadoPuesto(fila, columna);
        System.out.println(estado);
    }

    private void obtenerPropietarioPorPuesto() {
        System.out.print("Ingrese la fila del puesto: ");
        int fila = scanner.nextInt();
        System.out.print("Ingrese la columna del puesto: ");
        int columna = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        Propietario propietario = parqueadero.obtenerPropietarioPorPuesto(fila, columna);
        if (propietario != null) {
            System.out.println("Propietario: " + propietario.getNombre());
        } else {
            System.out.println("No se encontró un vehículo en el puesto especificado.");
        }
    }

    private void calcularCostoTotalEstacionamiento() {
        System.out.print("Ingrese la placa del vehículo: ");
        String placa = scanner.nextLine();

        System.out.print("Ingrese la fecha y hora de salida (yyyy-MM-dd HH:mm): ");
        String fechaHoraSalidaStr = scanner.nextLine();
        LocalDateTime fechaHoraSalida = LocalDateTime.parse(fechaHoraSalidaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        String costo = parqueadero.calcularCostoTotalEstacionamiento(placa, fechaHoraSalida);
        System.out.println(costo);
    }

    private void generarReporteDiario() {
        System.out.print("Ingrese la fecha del reporte (yyyy-MM-dd): ");
        String fechaStr = scanner.nextLine();
        LocalDate fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String reporte = parqueadero.generarReporteDiario(fecha);
        System.out.println(reporte);
    }

    public static void main(String[] args) {
        MenuInteractivoParqueadero menu = new MenuInteractivoParqueadero();
        menu.mostrarMenu();
    }
}
