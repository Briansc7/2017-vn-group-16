package modelTest;

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
import viewModel.PrincipalViewModel;

public class OperacionesParserConCuentas {
	Indicador resultado;
	@Before
	public void initialize(){
		Empresa empresa = new Empresa("Facebook", Arrays.asList(new Cuenta("ebitda", 500, "2015-01-21")));
		Planilla.instance.setEmpresaElegida(empresa);
		Planilla.instance.setPeriodoElegido(2015);
	}
	
	@Test
	public void leerUnaCuenta() throws NumberFormatException, ParseException, TokenMgrError, NullPointerException, IOException, parser.ParseException, parser.TokenMgrError{

		resultado = new Indicador("suma","c.Ebitda");//fb ebitda 2015
		Assert.assertEquals(500,resultado.getValor());
	}
	
}
