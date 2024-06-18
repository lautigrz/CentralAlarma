package unlam.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Alarma {
	private Integer idAlarma;
	private String codActivacion;
	private String codConfiguracion;
	private String nombre;
	private Boolean estadoAlarma = false;
	List<Usuario> usuario;
	Set<Acciones> acciones;
	List<Sensor> sensores;

	public Alarma(Integer idAlarma, String codActivacion, String codConfiguracion, String nombre) {
		super();
		this.idAlarma = idAlarma;
		this.codActivacion = codActivacion;
		this.codConfiguracion = codConfiguracion;
		this.nombre = nombre;
		this.usuario = new ArrayList<>();
		this.acciones = new TreeSet<>();
		this.sensores = new ArrayList<>();
	}

	public void agregarUsuariosValidosPorConfigurador(Integer dni, Usuario usuarioAgregar, Integer idAlarma,
		String codConfi) throws Exception {
		Usuario usu = buscarUsuarioConfiguradorPorId(dni);
		verificiarIdAlarma(idAlarma);
		verificarQueElCodConfSeaCorrecto(codConfi);
		realizarAccion(null, usu, TipoOperacion.CONFUGRACION);
		this.usuario.add(usuarioAgregar);

	}
	
	public void agregarSensorAlarmaPorConfigurador(Integer dniConfi,String codConfi,Sensor sensor) throws Exception {
		Usuario usu = buscarUsuarioConfiguradorPorId(dniConfi);
		verificarQueElCodConfSeaCorrecto(codConfi);
		verificarQueNoExistaElSensor(sensor);
		realizarAccion(null, usu, TipoOperacion.CONFUGRACION);
		this.sensores.add(sensor);
	}
	
	public void activarSensorDeAlarmaPorConfigurador(Integer dniConfi, Integer idSensor, Integer idAlarma) throws Exception {
		Usuario usu = buscarUsuarioConfiguradorPorId(dniConfi);
		Sensor sensor = buscarSensorPorId(idSensor);
		verificiarIdAlarma(idAlarma);
		sensor.setEstado(true);
		realizarAccion(null, usu, TipoOperacion.CONFUGRACION);
		
	}

	private void verificiarIdAlarma(Integer idAlarma2) throws Exception {
		// TODO Auto-generated method stub
		if (!this.idAlarma.equals(idAlarma2)) {
			throw new Exception("no existe alarma");
		}

	}

	public Sensor buscarSensorPorId(Integer id) throws Exception {
		for (Sensor s : this.sensores) {
			if (s.getId().equals(id)) {
				return s;
			}
		}
		throw new Exception("No existe sensor");
	}

	public Boolean realizarAccion(Alarma alarma, Usuario usuario, TipoOperacion tipo) {
		// TODO Auto-generated method stub
		Acciones accion = new Acciones(alarma, this.acciones.size() + 1, usuario, LocalDate.now(), tipo);
		return this.acciones.add(accion);

	}

	public Boolean activarAlarma(Alarma alarma, String codigoActivacionAlarma, Integer idActivador) throws Exception {
		// TODO Auto-generated method stub
		verificarQueElCodActSeaCorrecto(codigoActivacionAlarma);
		Usuario usuario = buscarUsuarioActivadorPorId(idActivador);
		verificarQueTodosLosSensoresEstenActivados();
		return realizarAccion(alarma, usuario, TipoOperacion.ACTIVACION);
	}

	private void verificarQueTodosLosSensoresEstenActivados() throws Exception {
		// TODO Auto-generated method stub
		for (Sensor s : this.sensores) {
			if (!s.getEstado()) {
				throw new Exception("hay un sensor desactivado");
			}
		}

	}

	public Boolean agregarSensor(Alarma alarma, Sensor sensor, String codigoConfiguracion, Integer id)
			throws Exception {

		verificarQueElCodConfSeaCorrecto(codigoConfiguracion);
		verificarQueNoExistaElSensor(sensor);
		Usuario confi = buscarUsuarioConfiguradorPorId(id);
		realizarAccion(alarma, confi, TipoOperacion.CONFUGRACION);
		return this.sensores.add(sensor);
	}

	public void activarSensor(Alarma alarma, Sensor sensor, String codigoActivacionAlarma, Integer idActivable)
			throws Exception {
		// TODO Auto-generated method stub
		verificarQueElCodActSeaCorrecto(codigoActivacionAlarma);
		Usuario confi = buscarUsuarioActivadorPorId(idActivable);
		verificarQueExistaElSensor(sensor);
		sensor.setEstado(true);
		realizarAccion(alarma, confi, TipoOperacion.ACTIVACION);
	}

	private Usuario buscarUsuarioActivadorPorId(Integer idActivable) throws Exception {
		// TODO Auto-generated method stub
		for (Usuario u : this.usuario) {
			if (u.getDni().equals(idActivable) && u instanceof Activable) {
				return u;
			}
		}
		throw new Exception("no existe");

	}

	private void verificarQueExistaElSensor(Sensor sensor) throws Exception {
		// TODO Auto-generated method stub
		if (!this.sensores.contains(sensor)) {
			throw new Exception("sensor no existente");
		}
	}

	private void verificarQueElCodActSeaCorrecto(String codigoActivacionAlarma) throws Exception {
		// TODO Auto-generated method stub
		if (!this.codActivacion.equals(codigoActivacionAlarma)) {
			throw new Exception("no es correcto");
		}

	}

	private void verificarQueNoExistaElSensor(Sensor sensor) throws SensorDuplicadoException {
		// TODO Auto-generated method stub
		if (this.sensores.contains(sensor)) {
			throw new SensorDuplicadoException("sensor existente");
		}
	}

	private void verificarQueElCodConfSeaCorrecto(String codigoConfiguracion) throws Exception {
		// TODO Auto-generated method stub
		if (!this.codConfiguracion.equals(codigoConfiguracion)) {
			throw new Exception("No existe codigo");
		}

	}

	public Boolean agregarUsuario(Usuario usuario, String codigoConfiguracionAlarma) throws Exception {
		verificarQueElCodConfSeaCorrecto(codigoConfiguracionAlarma);
		return this.usuario.add(usuario);

	}

	public Usuario buscarUsuarioPorId(Integer id) throws Exception {
		for (Usuario u : this.usuario) {
			if (u.getDni().equals(id)) {
				return u;
			}
		}
		throw new Exception("no existe");
	}

	public Usuario buscarUsuarioConfiguradorPorId(Integer id) throws Exception {
		for (Usuario u : this.usuario) {
			if (u.getDni().equals(id) && u instanceof Configurable) {
				return u;
			}
		}
		throw new Exception("No existe usuario");
	}
	
	public Set<Acciones> accionesTipoConfiguracion(){
		Set<Acciones> acciones = new TreeSet<>();
		for(Acciones a : this.acciones) {
			if(a.getTIPO_DE_OPERACION().equals(TipoOperacion.CONFUGRACION)) {
				acciones.add(a);
			}
		}
		
		return acciones;
	}
	
	

	public Integer getIdAlarma() {
		return idAlarma;
	}

	public void setIdAlarma(Integer idAlarma) {
		this.idAlarma = idAlarma;
	}

	public String getCodActivacion() {
		return codActivacion;
	}

	public void setCodActivacion(String codActivacion) {
		this.codActivacion = codActivacion;
	}

	public String getCodConfiguracion() {
		return codConfiguracion;
	}

	public void setCodConfiguracion(String codConfiguracion) {
		this.codConfiguracion = codConfiguracion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}

	public Set<Acciones> getAcciones() {
		return acciones;
	}

	public void setAcciones(Set<Acciones> acciones) {
		this.acciones = acciones;
	}

	public List<Sensor> getSensores() {
		return sensores;
	}

	public void setSensores(List<Sensor> sensores) {
		this.sensores = sensores;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codActivacion, codConfiguracion, idAlarma);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alarma other = (Alarma) obj;
		return Objects.equals(codActivacion, other.codActivacion)
				&& Objects.equals(codConfiguracion, other.codConfiguracion) && Objects.equals(idAlarma, other.idAlarma);
	}

	public Boolean getEstadoAlarma() {
		return estadoAlarma;
	}

	public void setEstadoAlarma(Boolean estadoAlarma) {
		this.estadoAlarma = estadoAlarma;
	}

}
