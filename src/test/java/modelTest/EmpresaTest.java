package modelTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.Cuenta;
import model.Empresa;

public class EmpresaTest {
	
	private Empresa empresaPrueba;
	private Empresa empresaACoincidir;
	
	@Before
	public void initialize(){
		
		// Creo la empresa de prueba
		empresaPrueba = new Empresa("empresa Prueba", Arrays.asList(
				new Cuenta("CuentaFranco", 123, "2016-01-05"),
				new Cuenta("CuentaBrians", 333, "2015-11-20"),
				new Cuenta("CuentaFede", 222, "2016-05-06")));
		
		
		// Lleno las cuentas a coincidir con las cuentas del 2016
		//List<Cuenta> cuentasACoincidir = Arrays.asList(cuenta1,cuenta3);
		List<Cuenta> cuentasACoincidir = Arrays.asList(
				new Cuenta("CuentaFranco", 123, "2016-01-05"),
				new Cuenta("CuentaFede", 222, "2016-05-06"));
		empresaACoincidir = new Empresa("empresa A Coincidir", cuentasACoincidir);
		//empresaACoincidir.setCuentas(cuentasACoincidir);
	}
	
//	@Test 
//	public void empresaDeberiaDevolvermeSoloDosCuentasConsultandoPorElPeriodo2016(){
//		
//		// Creo un set con las cuentas que deberían coincidir
//		HashSet<Cuenta> setDeCuentasACoincidir = new HashSet<Cuenta>();
//		setDeCuentasACoincidir.addAll(empresaACoincidir.getCuentas());
//		
//		// Creo un set con las cuentas que devuelve la prueba al hacer cuentasDelPeriodo(2016)
//		List<Cuenta> cuentasEncontradas = empresaPrueba.cuentasDelPeriodo(2016);
//		HashSet<Cuenta> setDeCuentasEncontradas = new HashSet<Cuenta>();
//		setDeCuentasEncontradas.addAll(cuentasEncontradas);
//
//		// Comparo ambos sets
//		Assert.assertEquals(setDeCuentasACoincidir, setDeCuentasEncontradas);
//	}
	
	@Test 
	public void busquedaPorPer�odoTest(){
		
		// Creo un set con las cuentas que deberían coincidir
		List<Cuenta> lista = empresaACoincidir.getCuentas();
		
		// Creo un set con las cuentas que devuelve la prueba al hacer cuentasDelPeriodo(2016)
		List<Cuenta> cuentasEncontradas = empresaPrueba.cuentasDelPeriodo(2016);


		// Comparo ambos sets
		Assert.assertEquals(lista.size(), cuentasEncontradas.size());
		Assert.assertEquals(lista.get(0).getNombre(), cuentasEncontradas.get(0).getNombre());
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
