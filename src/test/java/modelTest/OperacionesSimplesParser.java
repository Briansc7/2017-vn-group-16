package modelTest;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.BaseDeDatos;
import model.Empresa;
import model.Indicador;
import parser.ParseException;
import parser.TokenMgrError;

public class OperacionesSimplesParser {
	Indicador resultado;
	Empresa empresa;
	BaseDeDatos baseDeDatos;
	
	@Before
	public void initialize(){
		
	}
	
	@Test
	public void sumarNumeros() throws NumberFormatException, ParseException, TokenMgrError, parser.ParseException, parser.TokenMgrError{
		resultado = new Indicador("suma","10+56");
		Assert.assertEquals(new BigDecimal(66), resultado.getValor(2017, empresa));
	}
	
	@Test
	public void restarNumeros() throws NumberFormatException, ParseException, TokenMgrError, parser.ParseException, parser.TokenMgrError{
		resultado = new Indicador("resta","56-57");
		Assert.assertEquals(new BigDecimal(-1), resultado.getValor(2017, empresa));
	}
	
	@Test
	public void multiplicarNumeros() throws NumberFormatException, ParseException, TokenMgrError, parser.ParseException, parser.TokenMgrError{
		resultado = new Indicador("multiplicacion","23*2");
		Assert.assertEquals(new BigDecimal(46), resultado.getValor(2017, empresa));
	}
	
	@Test
	public void dividirNumeros() throws NumberFormatException, ParseException, TokenMgrError, parser.ParseException, parser.TokenMgrError{
		resultado = new Indicador("division","33/3");
		Assert.assertEquals(0, new BigDecimal(11.00).compareTo(resultado.getValor(2017, empresa)));
	}
	
	@Test
	public void divisionConDecimales() throws NumberFormatException, ParseException, TokenMgrError, parser.ParseException, parser.TokenMgrError{
		resultado = new Indicador("division","55/100");
		Assert.assertEquals(BigDecimal.valueOf(0.55), resultado.getValor(2017, empresa));
	}
	
	@Test
	public void multiplicarYSumar() throws NumberFormatException, ParseException, TokenMgrError, parser.ParseException, parser.TokenMgrError{
		resultado = new Indicador("multsum","(2*3)+(7*10)");
		Assert.assertEquals(new BigDecimal(76), resultado.getValor(2017, empresa));
	}
	
	@Test
	public void dividirYSumar() throws NumberFormatException, ParseException, TokenMgrError, parser.ParseException, parser.TokenMgrError{
		resultado = new Indicador("divsum","(10/2)+(14/7)");
		Assert.assertEquals(0, new BigDecimal(7.00).compareTo(resultado.getValor(2017, empresa)));
	}
	
	@Test
	public void sumaConParentesis() throws NumberFormatException, ParseException, TokenMgrError, parser.ParseException, parser.TokenMgrError{
		resultado = new Indicador("sumPar","(1+1)");
		Assert.assertEquals(new BigDecimal(2), resultado.getValor(2017, empresa));
	}
	
	@Test
	public void romperPrecedenciaConParentesis() throws NumberFormatException, ParseException, TokenMgrError, parser.ParseException, parser.TokenMgrError{
		resultado = new Indicador("romperPrecedencia","5*(2+2)*10");
		Assert.assertEquals(new BigDecimal(200), resultado.getValor(2017, empresa));
	}
}