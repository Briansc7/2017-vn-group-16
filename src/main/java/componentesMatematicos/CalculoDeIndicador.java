package componentesMatematicos;

import java.math.BigDecimal;

import exceptions.NoExisteAtributoException;
import model.Empresa;
import model.repositories.RepositorioDeIndicadores;

public class CalculoDeIndicador{
	RepositorioDeIndicadores repositorioIndicadores = RepositorioDeIndicadores.getInstance();

	public BigDecimal valorDe(String nombre, Integer unPeriodo, Empresa unaEmpresa){
		if(repositorioIndicadores.existe(nombre))
			return repositorioIndicadores.buscarIndicador(nombre).getValor(unPeriodo, unaEmpresa);
		if(unaEmpresa.existeCuentaDel(nombre, unPeriodo))
			return unaEmpresa.buscarCuenta(nombre, unPeriodo).getValor();
		
		throw new NoExisteAtributoException("No existe: " + nombre);
	}
}