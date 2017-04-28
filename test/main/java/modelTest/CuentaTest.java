package modelTest;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import model.Cuenta;

public class CuentaTest {
	private Cuenta cuentaPrueba;
	
	@Before
	public void initialize(){
		cuentaPrueba = new Cuenta();
		cuentaPrueba.setNombre("CuentaPrueba");
		cuentaPrueba.setValor(123);
		cuentaPrueba.setFecha("2016-01-05");
	}
	
	@Test 
	public void cuentaDeberiaConvertirLaFechaYPoderRetornarmeUnAnio(){
		Integer anioACoincidir = 2016;
		Integer anioDeCuenta = cuentaPrueba.getFecha().getYear();
		Assert.assertEquals(anioACoincidir, anioDeCuenta);
	}
	
}
