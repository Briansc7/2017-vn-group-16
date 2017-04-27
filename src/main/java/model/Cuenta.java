package model;



import org.joda.time.DateTime;
import org.uqbar.commons.utils.Observable;

@Observable
public class Cuenta {
	
	String nombre;
	Integer valor;
	DateTime fecha;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getValor() {
		return valor;
	}
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	public DateTime getFecha() {
		return fecha;
	}
	public void setFecha(DateTime fecha) {
		this.fecha = fecha;
	}

}
