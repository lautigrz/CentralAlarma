package unlam.dominio;

import java.time.LocalDate;

public class Acciones implements Comparable<Acciones> {
	private Alarma alarma;
	private Integer id;
	private Usuario usuario;
	private LocalDate fecha;
	private TipoOperacion TIPO_DE_OPERACION;
	public Acciones(Alarma alarma, Integer id, Usuario usuario, LocalDate fecha, TipoOperacion tIPO_DE_OPERACION) {
		super();
		this.alarma = alarma;
		this.id = id;
		this.usuario = usuario;
		this.fecha = fecha;
		TIPO_DE_OPERACION = tIPO_DE_OPERACION;
	}
	public Alarma getAlarma() {
		return alarma;
	}
	public void setAlarma(Alarma alarma) {
		this.alarma = alarma;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public TipoOperacion getTIPO_DE_OPERACION() {
		return TIPO_DE_OPERACION;
	}
	public void setTIPO_DE_OPERACION(TipoOperacion tIPO_DE_OPERACION) {
		TIPO_DE_OPERACION = tIPO_DE_OPERACION;
	}
	@Override
	public int compareTo(Acciones o) {
		  return this.id.compareTo(o.getId());
	}
	@Override
	public String toString() {
		return "Acciones [alarma=" + alarma + ", id=" + id + ", usuario=" + usuario + ", fecha=" + fecha
				+ ", TIPO_DE_OPERACION=" + TIPO_DE_OPERACION + "]";
	}
	
	
}
