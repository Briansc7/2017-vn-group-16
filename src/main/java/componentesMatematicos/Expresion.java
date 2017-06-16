package componentesMatematicos;

import java.time.Year;

import model.Empresa;
import model.Planilla;

public interface Expresion {
	
	public Integer getValor(Integer unPeriodo, Empresa unaEmpresa, Planilla unaPlanilla);
} //