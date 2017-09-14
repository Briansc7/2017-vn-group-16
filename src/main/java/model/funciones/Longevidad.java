package model.funciones;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.BaseDeDatos;
import model.Empresa;

@Entity
@DiscriminatorValue("longevidad")
public class Longevidad extends Funcion{
	public Longevidad(){
		super();
	}

	public BigDecimal[] calcularValor(Empresa empresa, Integer periodo, BaseDeDatos baseDeDatos) {
		BigDecimal[] resultado = new BigDecimal[1];
		resultado[0] = empresa.longevidad();
		return resultado;
	}
}
