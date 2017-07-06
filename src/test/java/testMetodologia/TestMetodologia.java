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

public class TestMetodologia {
	Metodologia metodologiaBuffet;
	CondicionNoTaxativa condicionRoe;
	CondicionNoTaxativa condicionDeuda;
	CondicionTaxativa condicionMargen;
	CondicionTaxativa condicionLongevidad1;
	CondicionNoTaxativa condicionLongevidad2;
	BaseDeDatos base;
	Empresa empresaUno;
	Empresa empresaDos;
	Indicador indicadorRoe;
	Indicador indicadorMargen;
	Indicador indicadorDeuda;
	Indicador indicadorEquity;
	Indicador indicadorLongevidad;
	
	@Before
	public void initialize() throws ParseException, TokenMgrError{
		base = new BaseDeDatos("");
		empresaUno = new Empresa("Facebook", Arrays.asList(new Cuenta("ingresoNeto", 15, LocalDate.parse("2017-05-10")), 
														 new Cuenta("capitalTotal", 30, LocalDate.parse("2017-05-10")),
														 new Cuenta("totalLiabilities", 40, LocalDate.parse("2017-05-10")),
														 new Cuenta("longevidad", 7, LocalDate.parse("2017-05-10"))));
		empresaDos = new Empresa("twitter", Arrays.asList(new Cuenta("ingresoNeto", 10, LocalDate.parse("2017-05-10")),
														new Cuenta("capitalTotal", 20, LocalDate.parse("2017-05-10")),
														new Cuenta("totalLiabilities", 30, LocalDate.parse("2017-05-10")),
														 new Cuenta("longevidad", 5, LocalDate.parse("2017-05-10"))));
		indicadorEquity = new Indicador("shareholdersEquity", "capitalTotal - totalLiabilities");
		base.agregarIndicador(indicadorEquity);
		base.setEmpresas(Arrays.asList(empresaUno, empresaDos));
	}
	
	@Test
	public void condicion1Buffet() throws ParseException, TokenMgrError{
		condicionRoe = new CondicionNoTaxativa(1, "ROE", new GreaterThan(), 1);
		indicadorRoe = new Indicador("roe", "2 * ingresoNeto");
		base.agregarIndicador(indicadorRoe);
		
		Assert.assertTrue(condicionRoe.compararEmpresas(empresaUno, empresaDos, base));
	}
	
	@Test
	public void condicion2Buffet() throws ParseException, TokenMgrError{
		condicionDeuda = new CondicionNoTaxativa(1, "debtEquityRatio", new LessThan(), 2);
		indicadorDeuda = new Indicador("debtEquityRatio", "totalLiabilities / shareholdersEquity");
		base.agregarIndicador(indicadorDeuda);
		
		Assert.assertTrue(condicionDeuda.compararEmpresas(empresaUno, empresaDos, base));
	}
	
	@Test
	public void condicion3Buffet() throws ParseException, TokenMgrError{
		condicionMargen = new CondicionTaxativa(1, "Margen", new Consistente(), "");
		indicadorMargen = new Indicador("margen", "(ingresoNeto - 50 - 20 - 15) / ingresoNeto");
		base.agregarIndicador(indicadorMargen);
		
		Assert.assertTrue(condicionMargen.aplicarCondicion(empresaUno, base));
	}
	
	@Test
	public void condicion4Buffet() throws ParseException, TokenMgrError{
		condicionLongevidad1 = new CondicionTaxativa(3, "longevidad", new GreaterThan(), 3);
		
		Assert.assertTrue(condicionLongevidad1.aplicarCondicion(empresaUno, base));
	}
}
