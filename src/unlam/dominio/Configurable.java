package unlam.dominio;

public class Configurable extends Usuario implements ConfigurableInteraz {

	public Configurable(Integer dni, String nombre) {
		super(dni, nombre);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean agregarUsuario(Integer dni, Integer idAlarma, Integer codConfiguracion)
			throws CodigoAlarmaIncorrectoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean agregarSensorAunaAlarma(Integer idAlarma, Integer cdoConfiguracion, Sensor sensor)
			throws SensorDuplicadoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean activarSensorDeAlarma(Integer idsensor, Integer idAlarma, Integer codConfiguracion) {
		// TODO Auto-generated method stub
		return null;
	}

}
