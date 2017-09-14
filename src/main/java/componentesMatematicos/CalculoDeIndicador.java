package componentesMatematicos;

import java.math.BigDecimal;

import exceptions.NoExisteAtributoException;
import model.Empresa;
import model.repositories.RepositorioDeEmpresas;
import model.repositories.RepositorioDeIndicadores;

public class CalculoDeIndicador{
	RepositorioDeIndicadores repositorioIndicadores = RepositorioDeIndicadores.getInstance();
	RepositorioDeEmpresas repositorioDeEmpresas = RepositorioDeEmpresas.getInstance();
	
	public BigDecimal valorDe(String nombre, Integer unPeriodo, Empresa unaEmpresa){
		if(repositorioIndicadores.existeIndicador(nombre))
			return repositorioIndicadores.obtenerIndicador(nombre).getValor(unPeriodo, unaEmpresa);
		if(unaEmpresa.existeCuentaDel(nombre, unPeriodo))
			return unaEmpresa.buscarCuenta(nombre, unPeriodo).getValor();
		
		throw new NoExisteAtributoException("No existe ni el Indicador ni la Empresa: " + nombre);
	}
}