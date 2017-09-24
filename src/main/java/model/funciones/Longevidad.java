package model.funciones;

import java.math.BigDecimal;
import java.util.Arrays;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.Empresa;

@Entity
@DiscriminatorValue("longevidad")
public class Longevidad extends Funcion{

		
	public BigDecimal[] calcularValor(Empresa empresa, Integer periodo) {
		return Arrays.asList(empresa.longevidad());
	}
}
