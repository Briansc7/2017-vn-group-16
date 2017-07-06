package testMetodologia;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.BaseDeDatos;
import model.Cuenta;
import model.Empresa;
import model.Indicador;
import model.metodologia.CondicionNoTaxativa;
import model.metodologia.CondicionTaxativa;
import model.metodologia.Metodologia;
import model.metodologia.condiciones.Consistente;
import model.metodologia.condiciones.GreaterThan;
import parser.ParseException;
import parser.TokenMgrError;

public class TestMetodologia {
	Metodologia metodologiaBuffet;
	CondicionNoTaxativa condicionRoe;
	CondicionTaxativa condicionMargen;
	BaseDeDatos base;
	Empresa empresa1;
	Empresa empresa2;
	Cuenta cuenta1;
	Cuenta cuenta2;
	Indicador indicador;
	
	@Before
	public void initialize() throws ParseException, TokenMgrError{
		base = new BaseDeDatos("");
		cuenta1 = new Cuenta("van", 15, LocalDate.parse("2017-05-10"));
		cuenta2 = new Cuenta("van", 10, LocalDate.parse("2017-05-10"));
		empresa1 = new Empresa("Facebook", Arrays.asList(cuenta1));
		empresa2 = new Empresa("twitter", Arrays.asList(cuenta2));
		indicador = new Indicador("roe", "2 * van");
		base.agregarIndicador(indicador);
		base.setEmpresas(Arrays.asList(empresa1, empresa2));
	}
	
	@Test
	public void metodologiaBuffet(){
		condicionRoe = new CondicionNoTaxativa(1, "ROE", new GreaterThan(), "ROE");
		metodologiaBuffet = new Metodologia("Buffet", Arrays.asList(), Arrays.asList(condicionRoe));

		Assert.assertTrue(condicionRoe.compararEmresas(empresa1, empresa2, base));
	}
	
	@Test
	public void metodologiaTaxativa(){
		condicionMargen = new CondicionTaxativa(1, "Margen", new Consistente());
		
		Assert.assertTrue(condicionMargen.aplicarCondicion(empresa1, base));
	}
}
