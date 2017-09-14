package testMetodologia;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.BaseDeDatos;
import model.Cuenta;
import model.Empresa;
import model.Indicador;
import model.Metodologia;
import model.funciones.Consistencia;
import model.funciones.Longevidad;
import model.funciones.ValoresDelPeriodo;
import model.metodologia.condiciones.Comparador;
import model.metodologia.condiciones.CondicionGeneral;
import parser.ParseException;
import parser.TokenMgrError;

public class TestUnAnio {
	Metodologia metodologiaBuffet;
	CondicionGeneral condicionRoe;
	CondicionGeneral condicionDeuda;
	CondicionGeneral condicionMargen;
	CondicionGeneral condicionLongevidadPropia;
	CondicionGeneral condicionLongevidadComparativa;
	BaseDeDatos base;
	Empresa empresaUno;
	Empresa empresaDos;
	Empresa empresaTres;
	List<Empresa> empresas;
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
		/*
		condicionRoe = new CondicionNoTaxativa(1, "ROE", new GreaterThan(), 1);
		condicionDeuda = new CondicionNoTaxativa(1, "debtEquityRatio", new LessThan(), 2);
		condicionMargen = new CondicionTaxativa(1, "Margen", new GreaterAndEqualThan(), "margen");
		condicionLongevidad1 = new TaxativaLongevidad(0, "", new GreaterAndEqualThan(), new BigDecimal(2));
		condicionLongevidad2 = new NoTaxativaLongevidad(0, "", new GreaterThan(), 5);
		*/
		indicadorEquity = new Indicador("shareholdersEquity", "capitalTotal - totalLiabilities");
		indicadorRoe = new Indicador("roe", "2 * ingresoNeto");
		indicadorDeuda = new Indicador("debtEquityRatio", "totalLiabilities / shareholdersEquity");
		indicadorMargen = new Indicador("margen", "(ingresoNeto - 50 - 20 - 15) / ingresoNeto");
		
		condicionRoe = new CondicionBuilder()
								.periodoDeEvaluacion(1)
								.funcionParaObtenerValor(new ValoresDelPeriodo(indicadorRoe))
								.comparador(Comparador.MAYOR)
								.build();
		condicionDeuda = new CondicionBuilder()
								.periodoDeEvaluacion(1)
								.funcionParaObtenerValor(new ValoresDelPeriodo(indicadorDeuda))
								.comparador(Comparador.MENOR)
								.build();
		condicionMargen = new CondicionBuilder()//TODO: falta implementar consistencia
								.periodoDeEvaluacion(1)
								.funcionParaObtenerValor(new Consistencia(indicadorMargen))
								.comparador(Comparador.MAYOROIGUAL)
//TODO: le tengo que pasar un valor?								.valor(valor)
								.build();
		condicionLongevidadPropia = new CondicionBuilder()
								.periodoDeEvaluacion(1)
								.funcionParaObtenerValor(new Longevidad())
								.comparador(Comparador.MAYOROIGUAL)
								.valorContraElQueSeCompara(new BigDecimal(2))
								.build();
		condicionLongevidadComparativa = new CondicionBuilder()
								.periodoDeEvaluacion(1)
								.funcionParaObtenerValor(new Longevidad())
								.comparador(Comparador.MAYOR)
								.build();
		
		base = new BaseDeDatos("");
		empresas = Arrays.asList(empresaUno, empresaDos, empresaTres);
		base.setEmpresas(empresas);
		base.agregarIndicador(indicadorEquity);
		base.agregarIndicador(indicadorRoe);
		base.agregarIndicador(indicadorDeuda);
		base.agregarIndicador(indicadorMargen);
	}
	
	@Test
	public void condicion1Buffet() {
		Assert.assertEquals(empresaUno, condicionRoe.analizar(empresas, base).get(0));
	}
	
	@Test
	public void condicion2Buffet() {
		Assert.assertEquals(empresaUno, condicionDeuda.analizar(empresas, base).get(0));
	}
	/*
	@Test
	public void condicion3Buffet() throws ParseException, TokenMgrError{
		
		Assert.assertTrue(condicionMargen.aplicarCondicion(empresaUno, base));
	}
	*/
	@Test
	public void condicionLongevidadPropia() {
		Assert.assertTrue(condicionLongevidadPropia.analizar(empresas, base).contains(empresaTres));
	}
	
	@Test
	public void condicionLongevidadComparativa() {
		Assert.assertEquals(empresaTres, condicionLongevidadComparativa.analizar(empresas, base).get(0));
	}
	/*
	@Test
	public void metodologiaBuffet() throws ParseException, TokenMgrError{
		metodologiaBuffet = new Metodologia("Buffet", Arrays.asList(condicionMargen, condicionLongevidad1), Arrays.asList(condicionRoe, condicionDeuda, condicionLongevidad2));

		Assert.assertEquals("google", metodologiaBuffet.aplicarCondiciones(Arrays.asList(empresaUno, empresaDos, empresaTres), base).get(0).getNombre());
	}*/
}
