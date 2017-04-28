package modelTest;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import model.Cuenta;
import model.Empresa;
import model.ConsultarCuentasModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConsultarCuentasModelTest {
	
	private ConsultarCuentasModel consultarCuentasModelPrueba;
	private ConsultarCuentasModel consultarCuentasModelACoincidir;
	
	@Before
	public void initialize(){
		
		consultarCuentasModelPrueba = new ConsultarCuentasModel();
		
		// Creo la empresa Facebook para el modelo a coincidir		
		Cuenta cuenta1F = new Cuenta();
		cuenta1F.setNombre("EBOCuenta");
		cuenta1F.setValor(123456789);
		cuenta1F.setFecha("2016-02-06");
		Cuenta cuenta2F = new Cuenta();
		cuenta2F.setNombre("CosaCuenta");
		cuenta2F.setValor(93520123);
		cuenta2F.setFecha("2015-06-04");
		Cuenta cuenta3F = new Cuenta();
		cuenta3F.setNombre("EBOcosaloca");
		cuenta3F.setValor(759834);
		cuenta3F.setFecha("2014-01-01");
		List<Cuenta> cuentasF = Arrays.asList(cuenta1F,cuenta2F,cuenta3F);
		Empresa empresaF = new Empresa();
		empresaF.setNombre("Facebook");
		empresaF.setCuentas(cuentasF);
		
		// Creo la empresa Twitter para el modelo a coincidir
		Cuenta cuenta1T = new Cuenta();
		cuenta1T.setNombre("EBOCuenta");
		cuenta1T.setValor(126789);
		cuenta1T.setFecha("2016-02-06");
		Cuenta cuenta2T = new Cuenta();
		cuenta2T.setNombre("CosaCuenta");
		cuenta2T.setValor(920123);
		cuenta2T.setFecha("2015-06-03");
		Cuenta cuenta3T = new Cuenta();
		cuenta3T.setNombre("NombreCuentaLoca");
		cuenta3T.setValor(759834);
		cuenta3T.setFecha("2017-01-01");
		Cuenta cuenta4T = new Cuenta();
		cuenta4T.setNombre("NombreCuentaLoca");
		cuenta4T.setValor(759834);
		cuenta4T.setFecha("2017-01-01");
		List<Cuenta> cuentasT = Arrays.asList(cuenta1T,cuenta2T,cuenta3T,cuenta4T);
		Empresa empresaT = new Empresa();	
		empresaT.setNombre("Twitter");
		empresaT.setCuentas(cuentasT);
		
		// Lleno al modelo a coincidir con los datos
		List<Empresa>empresasPrueba = Arrays.asList(empresaF,empresaT);
		consultarCuentasModelACoincidir = new ConsultarCuentasModel();
		consultarCuentasModelACoincidir.setEmpresas(empresasPrueba);
	}
	
	@Test 
	public void consultarCuentasDeberiaObtenerLasMismasEmpresasDelArchivo(){
		
		// Creo un set con las empresas del modelo a coincidir
		HashSet<Empresa> setDeEmpresasACoincidir = new HashSet<Empresa>();
		setDeEmpresasACoincidir.addAll(consultarCuentasModelACoincidir.getEmpresas());
		
		// Creo un set con las empresas que la prueba lee del archivo
		List<Empresa> empresasEncontradas = consultarCuentasModelPrueba.leerEmpresas();
		HashSet<Empresa> setDeEmpresasEncontradas = new HashSet<Empresa>();
		setDeEmpresasEncontradas.addAll(empresasEncontradas);
		
		// Comparo ambos set
		Assert.assertEquals(setDeEmpresasACoincidir, setDeEmpresasEncontradas);
	}
}
