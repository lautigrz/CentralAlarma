package unlam.test;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import unlam.dominio.Acciones;
import unlam.dominio.Activable;
import unlam.dominio.Alarma;
import unlam.dominio.Central;
import unlam.dominio.Configurable;
import unlam.dominio.Sensor;
import unlam.dominio.TipoOperacion;

public class testCentral {

	@Test
	public void queSePuedaRegistrarUnaAlarmaEnLaCentral() {
		
		Central central = new Central("ss");
		Alarma alarma = new Alarma(100,"A1","S12","messi");
		
		Boolean seAgrego = central.agregarAlarma(alarma);
		
		assertTrue(seAgrego);
	}
	
	@Test
	public void queSePuedaAgregarUnUsuarioConfiguradorAUnaAlarma() throws Exception {
		
		Central central = new Central("ss");
		Alarma alarma = new Alarma(100,"A1","S12","messi");
		
		central.agregarAlarma(alarma);
		Configurable confi = new Configurable(1234,"Lautaro");
		
		central.agregarUsuario(confi);
		
		Boolean seAgrego = central.agregarUsuarioAUnaAlarma(1234, 100, "S12");
		
		assertTrue(seAgrego);
	}
	
	
	@Test (expected = Exception.class)
	public void alAgregarUnUsuarioAUnaAlarmaConCodigoDeConfiguracionDeAlarmaInvalidoSeLanceCodigoAlarmaIncorrectoException() throws Exception {
		
		Central central = new Central("ss");
		Alarma alarma = new Alarma(100,"A1","S12","messi");
		
		central.agregarAlarma(alarma);
		Configurable confi = new Configurable(1234,"Lautaro");
		
		central.agregarUsuario(confi);
		
		central.agregarUsuarioAUnaAlarma(1234, 100, "S10");
		
	}
	
	
	@Test (expected = Exception.class)
	public void alAgregarUnSensorDuplicadoEnUnaAlarmaSeLanceUnaSensorDuplicadoException() throws Exception {
		
		Central central = new Central("ss");
		Alarma alarma = new Alarma(100,"A1","S12","messi");
		
		central.agregarAlarma(alarma);
		Sensor sensor = new Sensor(12, false);
		Configurable confi = new Configurable(1234,"Lautaro");
		
		central.agregarUsuario(confi);
		
		central.agregarUsuarioAUnaAlarma(1234, 100, "S12");
		
		central.agregarSensorAAlarma(100, "S12", sensor, 1234);
		
		Sensor sensorRepetido = new Sensor(12, false);
		central.agregarSensorAAlarma(100, "S12", sensorRepetido, 1234);
	}
	
	@Test(expected = Exception.class)
	
	public void queNoSePuedaActivarUnaAlarmaSiHayAlMenosUnSensorDesactivado() throws Exception {
		Central central = new Central("ss");
		Alarma alarma = new Alarma(100,"A1","S12","messi");
		
		central.agregarAlarma(alarma);
		Sensor sensor = new Sensor(12, false);
		Configurable confi = new Configurable(1234,"Lautaro");
		Activable acti = new Activable(4321,"leo");
		central.agregarUsuario(confi);
		central.agregarUsuario(acti);
		
		central.agregarUsuarioAUnaAlarma(1234, 100, "S12");
		central.agregarUsuarioAUnaAlarma(4321, 100, "S12");
		
		central.agregarSensorAAlarma(100, "S12", sensor, 1234);
		
		Sensor sensorRepetido = new Sensor(13, false);
		central.agregarSensorAAlarma(100, "S12", sensorRepetido, 1234);
		
		central.activarSensorDeAlarma(12, 100, "A1", 4321);
		alarma.activarAlarma(alarma, "A1", 4321);
		
	}
	
	@Test
	public void queParaUnaAlarmaDeterminadaSePuedaObtenerUnaColeccionOrdenadaDeAcccionesDeTipoConfiguracionOdenadasPorIdDeAccion() throws Exception {
		
		Central central = new Central("ss");
		Alarma alarma = new Alarma(100,"A1","S12","messi");
		
		central.agregarAlarma(alarma);
		Sensor sensor = new Sensor(12, false);
		Configurable confi = new Configurable(1234,"Lautaro");
		Activable acti = new Activable(4321,"leo");
		central.agregarUsuario(confi);
		central.agregarUsuario(acti);
		
		central.agregarUsuarioAUnaAlarma(1234, 100, "S12");
		central.agregarUsuarioAUnaAlarma(4321, 100, "S12");
		
		central.agregarSensorAAlarma(100, "S12", sensor, 1234);
		
		Sensor sensorRepetido = new Sensor(13, false);
		central.agregarSensorAAlarma(100, "S12", sensorRepetido, 1234);
		
		central.activarSensorDeAlarma(12, 100, "A1", 4321);
		central.activarSensorDeAlarma(13, 100, "A1", 4321);
	
		Sensor sensorRepetido1 = new Sensor(14, false);
		central.agregarSensorAAlarma(100, "S12", sensorRepetido1, 1234);
		
		Sensor sensorRepetido2 = new Sensor(15, false);
		central.agregarSensorAAlarma(100, "S12", sensorRepetido2, 1234);
		
		Set<Acciones> acciones = new TreeSet<>(alarma.accionesTipoConfiguracion());
		
		assertEquals(4, acciones.size());
		
		Integer [] idEsperados = {1,2,5,6};
		int control = 0;
		for(Acciones a : acciones) {
			assertEquals(idEsperados[control], a.getId());
			control++;
		}
		
		for(Acciones a : acciones) {
			assertEquals(TipoOperacion.CONFUGRACION, a.getTIPO_DE_OPERACION());
		}
		
	}

}
