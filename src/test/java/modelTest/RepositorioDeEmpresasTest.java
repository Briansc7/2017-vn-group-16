package modelTest;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Cuenta;
import model.Empresa;
import model.repositories.RepositorioDeEmpresas;

public class RepositorioDeEmpresasTest {
	RepositorioDeEmpresas repositorio = RepositorioDeEmpresas.getInstance();
	
	List <Cuenta> _cuentas = Arrays.asList(
			new Cuenta("Cuenta de Prueba 1", BigDecimal.valueOf(10), LocalDate.of(2017, 8, 15)),
			new Cuenta("Cuenta de Prueba 2", BigDecimal.valueOf(20), LocalDate.of(2017, 8, 15)),
			new Cuenta("Cuenta de Prueba 3", BigDecimal.valueOf(30), LocalDate.of(2017, 8, 15))
			);
	Empresa empresa1 = new Empresa("Empresa del Repositorio 1", _cuentas);
	Empresa empresa2 = new Empresa("Empresa del Repositorio 2", new ArrayList<Cuenta>());
	Empresa empresa3 = new Empresa("Empresa del Repositorio 3", new ArrayList<Cuenta>());
	List<Empresa> empresas = Arrays.asList(empresa1, empresa2, empresa3);
	
	//Hacer un after para vaciar el repositorio
	
	@Before
	public void guardarEmpresas(){
		repositorio.guardarEmpresas(empresas);
	}
	
	@Test
	public void obtenerEmpresa(){
		Empresa empresaObtenida = repositorio.obtenerEmpresa("Empresa del Repositorio 1");
		
		empresaObtenida.getNombre();
		empresaObtenida.getCuentas();
		empresaObtenida.getPeriodos();
	}
	
	@Test
	public void obtenerEmpresas(){
		List<Empresa> empresasObtenidas = repositorio.obtenerEmpresas();
		
		assertEquals(3, empresasObtenidas.size());
	}
}
