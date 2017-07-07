package testMetodologia;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import model.metodologia.NoTaxativaLongevidad;
import model.metodologia.TaxativaLongevidad;
import model.metodologia.condiciones.GreaterAndEqualThan;
import model.metodologia.condiciones.GreaterThan;
import model.metodologia.condiciones.LessThan;
import parser.ParseException;
import parser.TokenMgrError;

public class TestUnAnio {
	Metodologia metodologiaBuffet;
	CondicionNoTaxativa condicionRoe;
	CondicionNoTaxativa condicionDeuda;
	CondicionTaxativa condicionMargen;
	TaxativaLongevidad condicionLongevidad1;
	NoTaxativaLongevidad condicionLongevidad2;
	List<CondicionNoTaxativa> l;
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
		empresaUno = new Empresa("Facebook", Arrays.asList(new Cuenta("ingresoNeto", new BigDecimal(15), LocalDate.parse("2017-05-10")), 
														   new Cuenta("capitalTotal", new BigDecimal(30), LocalDate.parse("2017-05-10")),
														   new Cuenta("totalLiabilities", new BigDecimal(40), LocalDate.parse("2017-05-10")),
														   new Cuenta("deuda", new BigDecimal(7), LocalDate.parse("2017-05-10"))));
		empresaDos = new Empresa("twitter", Arrays.asList(new Cuenta("ingresoNeto", new BigDecimal(10), LocalDate.parse("2017-05-10")),
														  new Cuenta("capitalTotal", new BigDecimal(20), LocalDate.parse("2017-05-10")),
														  new Cuenta("totalLiabilities", new BigDecimal(30), LocalDate.parse("2017-05-10")),
														  new Cuenta("deuda", new BigDecimal(5), LocalDate.parse("2016-05-10"))));
		empresaTres = new Empresa("google", Arrays.asList(new Cuenta("ingresoNeto", new BigDecimal(100), LocalDate.parse("2017-05-10")),
														  new Cuenta("capitalTotal", new BigDecimal(200), LocalDate.parse("2017-05-10")),
														  new Cuenta("totalLiabilities", new BigDecimal(300), LocalDate.parse("2017-05-10")),
														  new Cuenta("deuda", new BigDecimal(10), LocalDate.parse("2015-05-10"))));
		
		condicionRoe = new CondicionNoTaxativa(1, "ROE", new GreaterThan(), 1);
		condicionDeuda = new CondicionNoTaxativa(1, "debtEquityRatio", new LessThan(), 2);
		condicionMargen = new CondicionTaxativa(1, "Margen", new GreaterAndEqualThan(), "margen");
		condicionLongevidad1 = new TaxativaLongevidad(0, "", new GreaterAndEqualThan(), new BigDecimal(2));
		condicionLongevidad2 = new NoTaxativaLongevidad(0, "", new GreaterThan(), 5);
		
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
		
		Assert.assertTrue(condicionLongevidad1.aplicarCondicion(empresaTres, base));
		Assert.assertTrue(condicionLongevidad2.compararEmpresas(empresaTres, empresaDos, base));
	}
	
	@Test
	public void metodologiaBuffet() throws ParseException, TokenMgrError{
		metodologiaBuffet = new Metodologia("Buffet", Arrays.asList(condicionMargen, condicionLongevidad1), Arrays.asList(condicionRoe, condicionDeuda, condicionLongevidad2));

		Assert.assertEquals("google", metodologiaBuffet.aplicarCondiciones(Arrays.asList(empresaUno, empresaDos, empresaTres), base).get(0).getNombre());
	}
}
