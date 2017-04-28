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
	
	private Empresa empresaPrueba;
	private Empresa empresaACoincidir;
	
	@Before
	public void initialize(){
		
		// Creo las cuentas para hacer las pruebas
		Cuenta cuenta1 = new Cuenta();
		cuenta1.setNombre("CuentaFranco");
		cuenta1.setValor(123);
		cuenta1.setFecha("2016-01-05");
		Cuenta cuenta2 = new Cuenta();
		cuenta2.setNombre("CuentaBrians");
		cuenta2.setValor(333);
		cuenta2.setFecha("2015-11-20");
		Cuenta cuenta3 = new Cuenta();
		cuenta3.setNombre("CuentaFede");
		cuenta3.setValor(222);
		cuenta3.setFecha("2016-05-06");
		
		// Lleno las cuentas a probar con todas las cuentas
		List<Cuenta> cuentasPrueba = Arrays.asList(cuenta1,cuenta2,cuenta3);
		empresaPrueba = new Empresa();
		empresaPrueba.setCuentas(cuentasPrueba);
		
		// Lleno las cuentas a coincidir con las cuentas del 2016
		List<Cuenta> cuentasACoincidir = Arrays.asList(cuenta1,cuenta3);
		empresaACoincidir = new Empresa();
		empresaACoincidir.setCuentas(cuentasACoincidir);
	}
	
	@Test 
	public void empresaDeberiaDevolvermeSoloDosCuentasConsultandoPorElPeriodo2016(){
		
		// Creo un set con las cuentas que deberían coincidir
		HashSet<Cuenta> setDeCuentasACoincidir = new HashSet<Cuenta>();
		setDeCuentasACoincidir.addAll(empresaACoincidir.getCuentas());
		
		// Creo un set con las cuentas que devuelve la prueba al hacer cuentasDelPeriodo(2016)
		List<Cuenta> cuentasEncontradas = empresaPrueba.cuentasDelPeriodo(2016);
		HashSet<Cuenta> setDeCuentasEncontradas = new HashSet<Cuenta>();
		setDeCuentasEncontradas.addAll(cuentasEncontradas);

		// Comparo ambos sets
		Assert.assertEquals(setDeCuentasACoincidir, setDeCuentasEncontradas);
	}
	
	@Test 
	public void empresaDeberiaDevolvermeDosAniosAlConsultarLosPeriodos(){
		
		// Creo un set con los periodos que deberían aparecer al consultar a la prueba (2015,2016)
		List<Integer> periodosACoincidir = Arrays.asList(2015,2016);
		HashSet<Integer> setDePeriodosACoincidir = new HashSet<Integer>();
		setDePeriodosACoincidir.addAll(periodosACoincidir);
		
		// Creo un set con los periodos que devuelve la prueba al hacer getPeriodos()
		List<Integer> periodosEncontrados = empresaPrueba.getPeriodos();
		HashSet<Integer> setDePeriodosEncontrados = new HashSet<Integer>();
		setDePeriodosEncontrados.addAll(periodosEncontrados);
		
		// Comparo ambos sets
		Assert.assertEquals(setDePeriodosACoincidir, setDePeriodosEncontrados);
	}
}
