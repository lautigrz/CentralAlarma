package unlam.dominio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Central {

	private Set<Alarma> alarma;
	private Set<Usuario> usuairos;
	private String nombre;

	public Central(String nombre) {
		super();
		this.alarma = new HashSet<>();
		this.nombre = nombre;
		this.usuairos = new HashSet<>();
	}
	
	public void activarDesactivarAlarma(Integer idAlarma, String codigoActivacionAlarma, Integer idActivador) throws Exception {
		Alarma alarma = this.buscarAlarmaPorId(idAlarma);
		alarma.activarAlarma(alarma,codigoActivacionAlarma,idActivador);
	}


	public Boolean activarSensorDeAlarma(Integer idSensor, Integer idAlarma, String codigoActivacionAlarma, Integer idActivable) throws Exception {
		Alarma alarma = this.buscarAlarmaPorId(idAlarma);
		Sensor sensor = alarma.buscarSensorPorId(idSensor);
		alarma.activarSensor(alarma,sensor,codigoActivacionAlarma,idActivable);
		
		return false;
	}

	public Boolean agregarSensorAAlarma(Integer idAlarma, String codigoConfiguracionAlarma, Sensor sensor,
			Integer idUsuarioConfigurador) throws Exception, SensorDuplicadoException {
		Alarma alarma = this.buscarAlarmaPorId(idAlarma);
		return alarma.agregarSensor(alarma, sensor, codigoConfiguracionAlarma, idUsuarioConfigurador);
	}

	public Boolean agregarUsuarioAUnaAlarma(Integer dniUsuarioAAgregar, Integer idAlarma,
			String codigoConfiguracionAlarma) throws Exception {

		Alarma alarma = this.buscarAlarmaPorId(idAlarma);
		Usuario usuario = this.buscarUsuarioPorDni(dniUsuarioAAgregar);

		return alarma.agregarUsuario(usuario, codigoConfiguracionAlarma);

	}

	public Alarma buscarAlarmaPorId(Integer id) {

		for (Alarma a : this.alarma) {
			if (a.getIdAlarma().equals(id)) {
				return a;
			}
		}

		return null;

	}

	public Usuario buscarUsuarioPorDni(Integer dni) {

		for (Usuario u : this.usuairos) {
			if (u.getDni().equals(dni)) {
				return u;
			}
		}

		return null;

	}

	public Boolean agregarUsuario(Usuario usuario) {
		return this.usuairos.add(usuario);
	}

	public Boolean agregarAlarma(Alarma alarma) {

		return this.alarma.add(alarma);

	}

	public Set<Alarma> getAlarma() {
		return alarma;
	}

	public void setAlarma(Set<Alarma> alarma) {
		this.alarma = alarma;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(alarma, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Central other = (Central) obj;
		return Objects.equals(alarma, other.alarma) && Objects.equals(nombre, other.nombre);
	}
	

}
