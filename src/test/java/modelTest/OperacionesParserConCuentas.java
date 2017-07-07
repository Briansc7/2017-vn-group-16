package modelTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.BaseDeDatos;
import model.Cuenta;
import model.Empresa;
import model.Indicador;
import parser.ParseException;
import parser.TokenMgrError;

public class OperacionesParserConCuentas {
	Indicador indicadorA;
	Indicador indicadorB;
	Empresa empresa;
	BaseDeDatos baseDeDatos;
	
	@Before
	public void initialize(){
		empresa = new Empresa("Facebook", Arrays.asList(new Cuenta("ebitda", new BigDecimal(500), LocalDate.parse("2015-01-21"))));
		baseDeDatos = new BaseDeDatos("");
	}
	
	@Test
	public void leerUnaCuenta() throws NumberFormatException, ParseException, TokenMgrError, NullPointerException, IOException, parser.ParseException, parser.TokenMgrError{
		indicadorA = new Indicador("suma", "Ebitda");//fb ebitda 2015
		
		Assert.assertEquals(new BigDecimal(500), indicadorA.getValor(2015, empresa, baseDeDatos));
	}
	
	@Test
	public void leerCuenteEIndicador() throws parser.ParseException, parser.TokenMgrError, NumberFormatException, ParseException, TokenMgrError {
		indicadorA = new Indicador("indicadorA", "Ebitda * 2");
		indicadorB = new Indicador("indicadorB","(Ebitda + indicadorA) / 5");
		baseDeDatos.agregarIndicador(indicadorA);
		baseDeDatos.agregarIndicador(indicadorB);
		
		Assert.assertEquals(new BigDecimal(1000), indicadorA.getValor(2015, empresa, baseDeDatos));
		Assert.assertEquals(0, new BigDecimal(300).compareTo(indicadorB.getValor(2015, empresa, baseDeDatos)));
	}
	
}
