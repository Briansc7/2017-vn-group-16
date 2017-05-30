package modelTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.Indicador;
import parser.ParseException;
import parser.TokenMgrError;

public class OperacionesSimplesParser {
	Indicador resultado; 
	@Before
	public void initialize(){
		//prueba = new Indicador("prueba","10+56");
	}
	
	@Test
	public void sumarNumeros() throws NumberFormatException, ParseException, TokenMgrError{
		resultado = new Indicador("suma","10+56");
		Assert.assertEquals(resultado.getValor(),10+56);
	}
	
	@Test
	public void restarNumeros() throws NumberFormatException, ParseException, TokenMgrError{
		resultado = new Indicador("resta","56-57");
		Assert.assertEquals(resultado.getValor(),56-57);
	}
	
	@Test
	public void multiplicarNumeros() throws NumberFormatException, ParseException, TokenMgrError{
		resultado = new Indicador("multiplicacion","23*2");
		Assert.assertEquals(resultado.getValor(),23*2);
	}
	
	@Test
	public void dividirNumeros() throws NumberFormatException, ParseException, TokenMgrError{
		resultado = new Indicador("division","33/3");
		Assert.assertEquals(resultado.getValor(),33/3);
	}
	
	@Test
	public void divisionConDecimales() throws NumberFormatException, ParseException, TokenMgrError{
		resultado = new Indicador("division","55/100");
		Assert.assertEquals(resultado.getValor(),55/100);
	}
	
	@Test
	public void multiplicarYSumar() throws NumberFormatException, ParseException, TokenMgrError{
		resultado = new Indicador("multsum","2*3+7*10");
		Assert.assertEquals(resultado.getValor(),2*3+7*10);
	}
	
	@Test
	public void dividirYSumar() throws NumberFormatException, ParseException, TokenMgrError{
		resultado = new Indicador("divsum","10/2+14/7");
		Assert.assertEquals(resultado.getValor(),10/2+14/7);
	}
	
	@Test
	public void sumaConParentesis() throws NumberFormatException, ParseException, TokenMgrError{
		resultado = new Indicador("sumPar","(1+1)");
		Assert.assertEquals(resultado.getValor(),1+1);
	}
}
