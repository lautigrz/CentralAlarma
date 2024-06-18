package unlam.dominio;

public interface ConfigurableInteraz {
	Boolean agregarUsuario(Integer dni, Integer idAlarma, Integer codConfiguracion) throws CodigoAlarmaIncorrectoException;
	Boolean agregarSensorAunaAlarma(Integer idAlarma,Integer cdoConfiguracion, Sensor sensor) throws SensorDuplicadoException;
	Boolean activarSensorDeAlarma(Integer idsensor, Integer idAlarma, Integer codConfiguracion);

}	
