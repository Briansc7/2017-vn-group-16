package testMetodologia;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.BaseDeDatos;
import model.metodologia.CondicionNoTaxativa;
import model.metodologia.CondicionTaxativa;
import model.metodologia.Metodologia;
import model.metodologia.condiciones.Consistente;
import model.metodologia.condiciones.GreaterThan;
import model.metodologia.condiciones.LessThan;
import parser.ParseException;
import parser.TokenMgrError;

public class TestNAnios {
	Metodologia metodologiaBuffet;
	CondicionNoTaxativa condicionRoe;
	CondicionNoTaxativa condicionDeuda;
	CondicionTaxativa condicionMargen;
	BaseDeDatos base;
	
	@Before
	public void initialize() throws IOException{
		base = new BaseDeDatos("./Archivos de prueba/baseDeDatosTest.csv");
		base.setPathIndicadores("./Archivos de prueba/indicadoresTest.txt");
		base.leerEmpresas();
		base.leerIndicadores();
		
		condicionRoe = new CondicionNoTaxativa(2, "ROE", new GreaterThan(), 1);
		condicionDeuda = new CondicionNoTaxativa(2, "debtEquityRatio", new LessThan(), 2);
		condicionMargen = new CondicionTaxativa(2, "Margen", new Consistente(), "margen");
	}
	
	@Test
	public void metodologiaBuffet() throws ParseException, TokenMgrError{
		metodologiaBuffet = new Metodologia("Buffet", Arrays.asList(condicionMargen), Arrays.asList(condicionRoe, condicionDeuda));

		Assert.assertEquals("facebook", metodologiaBuffet.aplicarCondiciones(base.getEmpresas(), base).get(0).getNombre());
	}
	
	@Test
	public void condicionUnoBuffet() throws ParseException, TokenMgrError{
		//System.out.println(base.buscarIndicador("margen").getValor(2017, base.empresaLlamada("facebook"), base));
	}
}
