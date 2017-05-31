package modelTest;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.Indicador;
import parser.ParseException;
import parser.TokenMgrError;
import viewModel.ConsultarCuentasViewModel;
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
		principalViewModel.setPath("D:\\facu\\sistemas de informacion\\diseño de sistemas\\workspace-eclipse\\2017-vn-group-16\\Archivos de prueba\\baseDatosPrueba.csv");
		System.out.println(principalViewModel.getPath());
		principalViewModel.verificarArchivo();
		
		ConsultarCuentasViewModel consultarCuentasViewModel = new ConsultarCuentasViewModel("Facebook");
		consultarCuentasViewModel.setPeriodoElegido(2015);
		
		resultado = new Indicador("suma","c.Ebitda");//fb ebitda 2015
		Assert.assertEquals(resultado.getValor(),14870);
	}
	
}
