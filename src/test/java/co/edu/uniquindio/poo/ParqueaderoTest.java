package co.edu.uniquindio.poo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;


public class ParqueaderoTest {
    private static final Logger LOG = LoggerFactory.getLogger(ParqueaderoTest.class);



    @Test
    public void testConstructorParqueadero() {
        LOG.info("Inicio prueba testConstructorParqueadero");

        int nPuestos = 10;
        Parqueadero parqueadero = new Parqueadero(nPuestos);
        assertEquals(nPuestos, parqueadero.getnPuestos());
        assertNotNull(parqueadero.getMatrizPuestos());
        assertEquals(nPuestos, parqueadero.getPuestosInstanciados());

        LOG.info("Fin prueba testConstructorParqueadero");
    }





    @Test
    public void testSetnPuestos() {
        LOG.info("Inicio prueba testSetnPuestos");

        Parqueadero parqueadero = new Parqueadero(10);
        int nuevosPuestos = 20;
        parqueadero.setnPuestos(nuevosPuestos);
        assertEquals(nuevosPuestos, parqueadero.getnPuestos());

        LOG.info("Fin prueba testSetnPuestos");
    }





    @Test
    public void testAsignarVehiculoAPuesto() {
        LOG.info("Inicio prueba testAsignarVehiculoAPuesto");

        Parqueadero parqueadero = new Parqueadero(10);
        Vehiculo vehiculo = new Vehiculo("ABC123", new Propietario("Juan", "123456"));
        LocalDateTime fechaHoraIngreso = LocalDateTime.now();
        boolean resultado = parqueadero.asignarVehiculoAPuesto(vehiculo, 0, 0, fechaHoraIngreso);
        assertTrue(resultado);
        assertEquals(vehiculo, parqueadero.getMatrizPuestos()[0][0].getVehiculo());

        LOG.info("Fin prueba testAsignarVehiculoAPuesto");
    }




    @Test
    public void testVerificarPuestoOcupado() {
        LOG.info("Inicio prueba testVerificarPuestoOcupado");

        Parqueadero parqueadero = new Parqueadero(10);
        Vehiculo vehiculo = new Vehiculo("ABC123", new Propietario("Juan", "123456"));
        parqueadero.asignarVehiculoAPuesto(vehiculo, 0, 0, LocalDateTime.now());
        assertTrue(parqueadero.verificarPuestoOcupado(0, 0));
        assertFalse(parqueadero.verificarPuestoOcupado(0, 1));

        LOG.info("Fin prueba testVerificarPuestoOcupado");
    }




    @Test
    public void testObtenerPropietarioPorPuesto() {
        LOG.info("Inicio prueba testObtenerPropietarioPorPuesto");

        Parqueadero parqueadero = new Parqueadero(10);
        Propietario propietario = new Propietario("Juan", "123456");
        Vehiculo vehiculo = new Vehiculo("ABC123", propietario);
        parqueadero.asignarVehiculoAPuesto(vehiculo, 0, 0, LocalDateTime.now());
        assertEquals(propietario, parqueadero.obtenerPropietarioPorPuesto(0, 0));
        assertNull(parqueadero.obtenerPropietarioPorPuesto(0, 1));

        LOG.info("Fin prueba testObtenerPropietarioPorPuesto");
    }


    @Test
    public void testCalcularCostoTotalEstacionamiento() {
        LOG.info("Inicio prueba testCalcularCostoTotalEstacionamiento");

        Parqueadero parqueadero = new Parqueadero(10);
        parqueadero.setTarifaPorHora(Vehiculo.class, 1000.0);
        Vehiculo vehiculo = new Vehiculo("ABC123", new Propietario("Juan", "123456"));
        LocalDateTime fechaHoraIngreso = LocalDateTime.now().minusHours(5);
        parqueadero.asignarVehiculoAPuesto(vehiculo, 0, 0, fechaHoraIngreso);
        LocalDateTime fechaHoraSalida = LocalDateTime.now();
        String costo = parqueadero.calcularCostoTotalEstacionamiento("ABC123", fechaHoraSalida);
        assertEquals("El costo total del estacionamiento para el vehículo con placa ABC123 es: 5000.0", costo);

        LOG.info("Fin prueba testCalcularCostoTotalEstacionamiento");
    }



    @Test
    public void testGenerarReporteDiario() {
        LOG.info("Inicio prueba testGenerarReporteDiario");

        Parqueadero parqueadero = new Parqueadero(10);
        parqueadero.setTarifaPorHora(Vehiculo.class, 1000.0);
        Vehiculo vehiculo = new Vehiculo("ABC123", new Propietario("Juan", "123456"));
        LocalDateTime fechaHoraIngreso = LocalDateTime.now().minusHours(5);
        parqueadero.asignarVehiculoAPuesto(vehiculo, 0, 0, fechaHoraIngreso);
        Registro registro = parqueadero.getRegistros().get(0);
        registro.setMomentoSalida(LocalDateTime.now());
        String reporte = parqueadero.generarReporteDiario(LocalDate.now());
        assertTrue(reporte.contains("Total Recaudado: 5000.0"));

        LOG.info("Fin prueba testGenerarReporteDiario");
    }

    @Test
    public void testVerificarEstadoPuesto() {
        LOG.info("Inicio prueba testVerificarEstadoPuesto");

        Parqueadero parqueadero = new Parqueadero(10);
        Vehiculo vehiculo = new Vehiculo("ABC123", new Propietario("Juan", "123456"));
        parqueadero.asignarVehiculoAPuesto(vehiculo, 0, 0, LocalDateTime.now());
        assertEquals("El puesto está ocupado", parqueadero.verificarEstadoPuesto(0, 0));
        assertEquals("El puesto está disponible", parqueadero.verificarEstadoPuesto(0, 1));

        LOG.info("Fin prueba testVerificarEstadoPuesto");
    }
}
