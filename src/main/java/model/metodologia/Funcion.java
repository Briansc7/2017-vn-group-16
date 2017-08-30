package model.metodologia;

import model.BaseDeDatos;
import model.Empresa;
import model.metodologia.condiciones.ValorAComparar;

public interface Funcion {

	public ValorAComparar calcularValor(Empresa empresa, Integer periodo, BaseDeDatos baseDeDatos);
}
