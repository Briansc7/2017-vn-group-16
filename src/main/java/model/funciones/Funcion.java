package model.funciones;

import java.math.BigDecimal;

import model.BaseDeDatos;
import model.Empresa;

public interface Funcion {

	public BigDecimal[] calcularValor(Empresa empresa, Integer periodo, BaseDeDatos baseDeDatos);
}
