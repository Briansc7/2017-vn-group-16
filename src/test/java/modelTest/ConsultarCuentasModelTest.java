package modelTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.BaseDeDatos;
import model.Cuenta;
import model.Empresa;

/*
public class ConsultarCuentasModelTest {
	private BaseDeDatos consultarCuentasModelACoincidir;
	
	@Before
	public void initialize() throws IOException{

		
		// Creo la empresa Facebook para el modelo a coincidir
		Empresa empresaF = new Empresa("Twitter", Arrays.asList(
				new Cuenta("EBOCuenta", 123456789, "2016-02-06"),
				new Cuenta("CosaCuenta", 93520123, "2015-06-04"),
				new Cuenta("EBOcosaloca", 759834, "2014-01-01")));
		
		
		// Creo la empresa Twitter para el modelo a coincidir
		Empresa empresaT = new Empresa("Facebook", Arrays.asList(
				new Cuenta("EBOCuenta", 126789, "2016-02-06"),
				new Cuenta("CosaCuenta", 920123, "2015-06-03"),
				new Cuenta("NombreCuentaLoca", 759834, "2017-01-01"),
				new Cuenta("NombreCuentaLoca", 759834, "2017-01-01")));

		
		// Lleno al modelo a coincidir con los datos
		List<Empresa>empresasPrueba = Arrays.asList(empresaF,empresaT);
		consultarCuentasModelACoincidir = new BaseDeDatos("Archivos de prueba/baseDatosVaciaPrueba.csv");
		consultarCuentasModelACoincidir.setEmpresas(empresasPrueba);
	}
	
	
	@Test
	public void lecturaCompletaTest() throws IOException
	{
		BaseDeDatos basePrueba = new BaseDeDatos("Archivos de prueba/baseDatosUnaEmpresa.csv");
		basePrueba.leerEmpresas();
		int tamanio = basePrueba.getEmpresas().size();
		Assert.assertEquals(tamanio, 1);
		Assert.assertEquals(basePrueba.getEmpresas().get(0).getNombre(), "Facebook");
		Assert.assertEquals(basePrueba.getEmpresas().get(0).getCuentas().get(0).getNombre(), "Ebitda");
		Assert.assertTrue(basePrueba.getEmpresas().get(0).getCuentas().get(0).getValor() == 14870);
	}
	
	@Test
	public void lecturaArchivoVacioTest() throws IOException
	{
		BaseDeDatos basePrueba = new BaseDeDatos("Archivos de prueba/baseDatosVaciaPrueba.csv");
		basePrueba.leerEmpresas();
		int tamanio = basePrueba.getEmpresas().size();
		Assert.assertEquals(tamanio, 0);
	}
}
*/