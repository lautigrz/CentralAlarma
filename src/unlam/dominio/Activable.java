package unlam.dominio;

public class Activable extends Usuario implements ActivableInterfaz {

	public Activable(Integer dni, String nombre) {
		super(dni, nombre);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean activarDesactivarAlarma(Alarma alarma, String codigoActivacionAlarma) {
		// TODO Auto-generated method stub
		return null;
	}

}
