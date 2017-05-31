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
		//prueba = new Indicador("prueba","10+56");
	}
	
	@Test
	public void leerUnaCuenta() throws NumberFormatException, ParseException, TokenMgrError, NullPointerException, IOException{
		PrincipalViewModel principalViewModel = new PrincipalViewModel();
		principalViewModel.setPath(".\\Archivos de prueba\\baseDatosPrueba.csv");
		//System.out.println(principalViewModel.getPath());
		principalViewModel.verificarArchivo();
		
		//ConsultarCuentasViewModel consultarCuentasViewModel = new ConsultarCuentasViewModel("Facebook");
		//consultarCuentasViewModel.setPeriodoElegido(2015);
		Empresa empresa = new Empresa("Facebook", Arrays.asList(new Cuenta("ebitda", 500, "2015-01-21"))); 
		Planilla.instance.setEmpresaElegida(empresa);
		Planilla.instance.setPeriodoElegido(2015);
		
		resultado = new Indicador("suma","c.Ebitda");//fb ebitda 2015
		Assert.assertEquals(500,resultado.getValor());
	}
	
}
