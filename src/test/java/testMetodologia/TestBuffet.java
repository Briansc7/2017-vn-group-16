package testMetodologia;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.BaseDeDatos;
import model.Metodologia;
import model.funciones.Consistencia;
import model.funciones.Longevidad;
import model.funciones.ValoresDelPeriodo;
import model.metodologia.condiciones.Comparador;
import model.metodologia.condiciones.CondicionGeneral;

public class TestBuffet {
	Metodologia metodologiaBuffet;
	CondicionGeneral condicionRoe;
	CondicionGeneral condicionDeuda;
	CondicionGeneral condicionMargen;
	CondicionGeneral condicionLongevidadPropia;
	CondicionGeneral condicionLongevidadComparativa;
	BaseDeDatos base;
	
	@Before
	public void initialize() throws IOException{
		base = new BaseDeDatos("./Archivos de prueba/baseDeDatosTest.csv");
		base.setPathIndicadores("./Archivos de prueba/indicadoresTest.txt");
		base.leerEmpresas();
		base.leerIndicadores();
		
		//condicionRoe = new CondicionNoTaxativa(2, "ROE", new GreaterThan(), 1);
		condicionRoe = new CondicionBuilder()
							.periodoDeEvaluacion(2)
							.funcionParaObtenerValor(new ValoresDelPeriodo(base.buscarIndicador("ROE")))
							.comparador(Comparador.MAYOR)
							.build();
		//condicionDeuda = new CondicionNoTaxativa(2, "debtEquityRatio", new LessThan(), 2);
		condicionDeuda = new CondicionBuilder()
							.periodoDeEvaluacion(2)
							.funcionParaObtenerValor(new ValoresDelPeriodo(base.buscarIndicador("debtEquityRatio")))
							.comparador(Comparador.MENOR)
							.build();
		//condicionMargen = new CondicionTaxativa(2, "Margen", new GreaterAndEqualThan(), "margen");
		condicionMargen = new CondicionBuilder()
							.periodoDeEvaluacion(2)
							.funcionParaObtenerValor(new Consistencia(base.buscarIndicador("margen")))
							.comparador(Comparador.IGUAL)
							.valorContraElQueSeCompara(new BigDecimal(-1))
							.build();
		//condicionLongevidadPropia = new TaxativaLongevidad(0, "longevidad", new GreaterAndEqualThan(), new BigDecimal(2));
		condicionLongevidadPropia = new CondicionBuilder()
							.periodoDeEvaluacion(1)
							.funcionParaObtenerValor(new Longevidad())
							.comparador(Comparador.MAYOROIGUAL)
							.valorContraElQueSeCompara(new BigDecimal(1))
							.build();
		//condicionLongevidadComparativa = new NoTaxativaLongevidad(0, "longevidad", new GreaterThan(), 5);
		condicionLongevidadComparativa = new CondicionBuilder()
							.periodoDeEvaluacion(1)
							.funcionParaObtenerValor(new Longevidad())
							.comparador(Comparador.MAYOR)
							.build();
	}
	
	@Test
	public void metodologiaBuffet() {
		metodologiaBuffet = new Metodologia("Buffet", Arrays.asList(condicionMargen, condicionLongevidadPropia, condicionRoe, condicionDeuda, condicionLongevidadComparativa));
		//System.out.println(metodologiaBuffet.aplicarCondiciones(base.getEmpresas(), base).size());
		Assert.assertEquals("google", metodologiaBuffet.aplicarCondiciones(base.getEmpresas()).get(0).getNombre());
	}
	
	@Test
	public void condicionUnoBuffet() {
		
	}
}
