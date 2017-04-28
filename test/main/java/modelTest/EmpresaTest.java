package modelTest;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import model.Cuenta;
import model.Empresa;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class EmpresaTest {
	
	private Cuenta cuenta1;
	private Cuenta cuenta2;
	private Cuenta cuenta3;
	private Empresa empresaPrueba;
	
	@Before
	public void initialize(){
		;
		empresaPrueba = new Empresa();
		cuenta1 = new Cuenta();
		cuenta1.setNombre("CuentaFranco");
		cuenta1.setValor(123);
		cuenta1.setFecha("2016-01-05");
		cuenta2 = new Cuenta();
		cuenta2.setNombre("CuentaBrians");
		cuenta2.setValor(333);
		cuenta2.setFecha("2015-11-20");
		cuenta3 = new Cuenta();
		cuenta3.setNombre("CuentaFede");
		cuenta3.setValor(222);
		cuenta3.setFecha("2016-05-06");
		List<Cuenta> cuentasPrueba = Arrays.asList(cuenta1,cuenta2,cuenta3);
		empresaPrueba.setCuentas(cuentasPrueba);
	}
	
	@Test 
	public void empresaDeberiaDevolvermeSoloDosCuentasConsultandoPorElPeriodo2016(){
		List<Cuenta> cuentasACoincidir = Arrays.asList(cuenta1,cuenta3);
		HashSet<Cuenta> setDeCuentasACoincidir = new HashSet<Cuenta>();
		setDeCuentasACoincidir.addAll(cuentasACoincidir);
		List<Cuenta> cuentasEncontradas = empresaPrueba.cuentasDelPeriodo(2016);
		HashSet<Cuenta> setDeCuentasEncontradas = new HashSet<Cuenta>();
		setDeCuentasEncontradas.addAll(cuentasEncontradas);

		Assert.assertEquals(setDeCuentasACoincidir, setDeCuentasEncontradas);
	}
	
	@Test 
	public void empresaDeberiaDevolvermeDosAniosAlConsultarLosPeriodos(){
		List<Integer> periodosACoincidir = Arrays.asList(2015,2016);
		HashSet<Integer> setDePeriodosACoincidir = new HashSet<Integer>();
		setDePeriodosACoincidir.addAll(periodosACoincidir);
		List<Integer> periodosEncontrados = empresaPrueba.getPeriodos();
		HashSet<Integer> setDePeriodosEncontrados = new HashSet<Integer>();
		setDePeriodosEncontrados.addAll(periodosEncontrados);

		Assert.assertEquals(setDePeriodosACoincidir, setDePeriodosEncontrados);
	}
}
