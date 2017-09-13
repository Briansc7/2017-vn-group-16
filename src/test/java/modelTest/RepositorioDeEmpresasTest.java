package modelTest;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
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
	Empresa empresa = new Empresa("Empresa del Repositorio", _cuentas);
	
	@Before
	public void guardarUnaEmpresa(){
		repositorio.guaradarEmpresa(empresa);
	}
	
	@Test
	public void obtenerEmpresas(){
		List<Empresa> empresas = repositorio.obtenerEmpresas("Empresa del Repositorio");
		
		empresas.get(0).getNombre();
		empresas.get(0).getCuentas();
		empresas.get(0).getPeriodos();
		
		assertEquals(1, empresas.size());
	}
}
