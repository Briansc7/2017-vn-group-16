package modelTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.BaseDeDatos;
import model.Cuenta;
import model.Empresa;

public class ConsultarCuentasModelTest {
	
	private BaseDeDatos consultarCuentasModelPrueba;
	private BaseDeDatos consultarCuentasModelACoincidir;
	
	@Before
	public void initialize(){
		
		consultarCuentasModelPrueba = new BaseDeDatos();
		
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
		consultarCuentasModelACoincidir = new BaseDeDatos();
		consultarCuentasModelACoincidir.setEmpresas(empresasPrueba);
	}
	
//	@Test 
//	public void consultarCuentasDeberiaObtenerLasMismasEmpresasDelArchivo(){
//		
//		// Creo un set con las empresas del modelo a coincidir
//		HashSet<Empresa> setDeEmpresasACoincidir = new HashSet<Empresa>();
//		setDeEmpresasACoincidir.addAll(consultarCuentasModelACoincidir.getEmpresas());
//		
//		// Creo un set con las empresas que la prueba lee del archivo
//		List<Empresa> empresasEncontradas = consultarCuentasModelPrueba.leerEmpresas();
//		HashSet<Empresa> setDeEmpresasEncontradas = new HashSet<Empresa>();
//		//setDeEmpresasEncontradas = setDeEmpresasACoincidir;
//		setDeEmpresasEncontradas.addAll(empresasEncontradas);
//		
//		// Comparo ambos set
//		Assert.assertEquals(setDeEmpresasACoincidir, setDeEmpresasEncontradas);
//	}
}
