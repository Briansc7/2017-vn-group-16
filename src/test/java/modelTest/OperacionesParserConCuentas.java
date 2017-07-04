package modelTest;

/*
import java.io.IOException;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import calculadora.ParseException;
import calculadora.TokenMgrError;
import model.Cuenta;
import model.Empresa;
import model.Indicador;
import model.Planilla;


public class OperacionesParserConCuentas {
	
	Indicador indicadorA;
	Indicador indicadorB;

	@Before
	public void initialize(){
		Empresa empresa = new Empresa("Facebook", Arrays.asList(new Cuenta("ebitda", 500, "2015-01-21")));
		Planilla.instance.setEmpresaElegida(empresa);
		Planilla.instance.setPeriodoElegido(2015);
	}
	
	@Test
	public void leerUnaCuenta() throws NumberFormatException, ParseException, TokenMgrError, NullPointerException, IOException, parser.ParseException, parser.TokenMgrError{
		indicadorA = new Indicador("suma","c.Ebitda");//fb ebitda 2015
		
		Assert.assertEquals(500,indicadorA.getValor());
	}
	
	@Test
	public void leerCuenteEIndicador() throws parser.ParseException, parser.TokenMgrError, NumberFormatException, ParseException, TokenMgrError {
		indicadorA = new Indicador("indicadorA","c.Ebitda * 2");
		Planilla.instance.agregarIndicador(indicadorA);
		indicadorB = new Indicador("indicadorB","(c.Ebitda + i.indicadorA) / 5");
		Planilla.instance.agregarIndicador(indicadorB);
		
		Assert.assertEquals(1000, indicadorA.getValor());
		Assert.assertEquals(300, indicadorB.getValor());
	}
	
}
*/