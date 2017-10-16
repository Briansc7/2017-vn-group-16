package modelTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.Cuenta;
import model.Empresa;
import model.Indicador;
import model.repositories.RepositorioDeEmpresas;
import model.repositories.RepositorioDeIndicadores;
import parser.ParseException;
import parser.TokenMgrError;

public class OperacionesParserConCuentas {
	Indicador indicadorA;
	Indicador indicadorB;
	Indicador indicadorC;
	Empresa empresa;
	RepositorioDeIndicadores repoIndicadores;
	RepositorioDeEmpresas repoEmpresas;
	
	@Before
	public void initialize() throws ParseException, TokenMgrError{
		repoIndicadores = RepositorioDeIndicadores.getInstance();
		repoEmpresas = RepositorioDeEmpresas.getInstance();
		empresa = new Empresa("Facebook", Arrays.asList(new Cuenta("ebitda", new BigDecimal(500), LocalDate.parse("2015-01-21"))));
		indicadorC = new Indicador("suma", "ebitda");//fb ebitda 2015
		//repoEmpresas.guardarEmpresa(empresa);
	}
	
	@Test
	public void leerUnaCuenta() throws NumberFormatException {
		repoEmpresas.guardarEmpresa(empresa);
		repoIndicadores.agregar(indicadorC);
		Assert.assertEquals(new BigDecimal(500), indicadorC.getValor(2015, empresa));
	}
	
	@Test
	public void leerCuenteEIndicador() throws parser.ParseException, parser.TokenMgrError, NumberFormatException, ParseException, TokenMgrError {
		indicadorA = new Indicador("indicadorA", "ebitda * 2");
		indicadorB = new Indicador("indicadorB","(ebitda + indicadorA) / 5");
		repoIndicadores.agregar(indicadorA);
		repoIndicadores.agregar(indicadorB);
		
		//Assert.assertEquals(new BigDecimal(1000), indicadorA.getValor(2015, empresa));
		Assert.assertEquals(0, new BigDecimal(300).compareTo(indicadorB.getValor(2015, empresa)));
	}
	
}
