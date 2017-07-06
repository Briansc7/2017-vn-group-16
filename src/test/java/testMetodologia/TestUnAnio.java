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
import model.metodologia.condiciones.LessThan;
import parser.ParseException;
import parser.TokenMgrError;

public class TestUnAnio {
	Metodologia metodologiaBuffet;
	CondicionNoTaxativa condicionRoe;
	CondicionNoTaxativa condicionDeuda;
	CondicionTaxativa condicionMargen;
	CondicionTaxativa condicionLongevidad1;
	CondicionNoTaxativa condicionLongevidad2;
	BaseDeDatos base;
	Empresa empresaUno;
	Empresa empresaDos;
	Empresa empresaTres;
	Indicador indicadorRoe;
	Indicador indicadorMargen;
	Indicador indicadorDeuda;
	Indicador indicadorEquity;
	Indicador indicadorLongevidad;
	
	@Before
	public void initialize() throws ParseException, TokenMgrError{
		empresaUno = new Empresa("Facebook", Arrays.asList(new Cuenta("ingresoNeto", 15, LocalDate.parse("2017-05-10")), 
														   new Cuenta("capitalTotal", 30, LocalDate.parse("2017-05-10")),
														   new Cuenta("totalLiabilities", 40, LocalDate.parse("2017-05-10")),
														   new Cuenta("longevidad", 7, LocalDate.parse("2017-05-10"))));
		empresaDos = new Empresa("twitter", Arrays.asList(new Cuenta("ingresoNeto", 10, LocalDate.parse("2017-05-10")),
														  new Cuenta("capitalTotal", 20, LocalDate.parse("2017-05-10")),
														  new Cuenta("totalLiabilities", 30, LocalDate.parse("2017-05-10")),
														  new Cuenta("longevidad", 5, LocalDate.parse("2017-05-10"))));
		empresaTres = new Empresa("google", Arrays.asList(new Cuenta("ingresoNeto", 100, LocalDate.parse("2017-05-10")),
														  new Cuenta("capitalTotal", 200, LocalDate.parse("2017-05-10")),
														  new Cuenta("totalLiabilities", 300, LocalDate.parse("2017-05-10")),
														  new Cuenta("longevidad", 10, LocalDate.parse("2017-05-10"))));
		
		condicionRoe = new CondicionNoTaxativa(1, "ROE", new GreaterThan(), 1);
		condicionDeuda = new CondicionNoTaxativa(1, "debtEquityRatio", new LessThan(), 2);
		condicionMargen = new CondicionTaxativa(1, "Margen", new Consistente(), "margen");
		
		indicadorEquity = new Indicador("shareholdersEquity", "capitalTotal - totalLiabilities");
		indicadorRoe = new Indicador("roe", "2 * ingresoNeto");
		indicadorDeuda = new Indicador("debtEquityRatio", "totalLiabilities / shareholdersEquity");
		indicadorMargen = new Indicador("margen", "(ingresoNeto - 50 - 20 - 15) / ingresoNeto");
		
		base = new BaseDeDatos("");
		base.setEmpresas(Arrays.asList(empresaUno, empresaDos, empresaTres));
		base.agregarIndicador(indicadorEquity);
		base.agregarIndicador(indicadorRoe);
		base.agregarIndicador(indicadorDeuda);
		base.agregarIndicador(indicadorMargen);
	}
	
	@Test
	public void condicion1Buffet() throws ParseException, TokenMgrError{
		
		Assert.assertTrue(condicionRoe.compararEmpresas(empresaUno, empresaDos, base));
	}
	
	@Test
	public void condicion2Buffet() throws ParseException, TokenMgrError{
		
		Assert.assertTrue(condicionDeuda.compararEmpresas(empresaUno, empresaDos, base));
	}
	
	@Test
	public void condicion3Buffet() throws ParseException, TokenMgrError{
		
		Assert.assertTrue(condicionMargen.aplicarCondicion(empresaUno, base));
	}
	
	@Test
	public void condicion4Buffet() throws ParseException, TokenMgrError{
		condicionLongevidad1 = new CondicionTaxativa(3, "longevidad", new GreaterThan(), 3);
		
		//Assert.assertTrue(condicionLongevidad1.aplicarCondicion(empresaUno, base));
	}
	
	@Test
	public void metodologiaBuffet() throws ParseException, TokenMgrError{
		metodologiaBuffet = new Metodologia("Buffet", Arrays.asList(condicionMargen), Arrays.asList(condicionRoe, condicionDeuda));
		
		Assert.assertEquals("Facebook", metodologiaBuffet.aplicarCondiciones(Arrays.asList(empresaUno, empresaDos, empresaTres), base).get(0).getNombre());
	}
}
