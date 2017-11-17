package modelTest;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exceptions.EseYaExisteException;
import model.Cuenta;
import model.Empresa;
import model.repositories.RepositorioDeEmpresas;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

public class RepositorioDeEmpresasTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
	private RepositorioDeEmpresas repositorio = RepositorioDeEmpresas.getInstance();
	private static boolean setUpIsDone = false;
	
	private List <Cuenta> _cuentas = Arrays.asList(
			new Cuenta("Cuenta de Prueba 1", BigDecimal.valueOf(10), LocalDate.of(2017, 8, 15)),
			new Cuenta("Cuenta de Prueba 2", BigDecimal.valueOf(20), LocalDate.of(2017, 8, 15)),
			new Cuenta("Cuenta de Prueba 3", BigDecimal.valueOf(30), LocalDate.of(2017, 8, 15))
			);
	private Empresa empresa1 = new Empresa("Empresa del Repositorio 1", _cuentas);
	private Empresa empresa2 = new Empresa("Empresa del Repositorio 2", new ArrayList<Cuenta>());
	private Empresa empresa3 = new Empresa("Empresa del Repositorio 3", new ArrayList<Cuenta>());
	private List<Empresa> empresas = Arrays.asList(empresa1, empresa2, empresa3);
	
	@Before
	public void guardarEmpresas(){
		if(setUpIsDone)
			return;
		repositorio.guardarTodos(empresas);
		setUpIsDone=true;
	}
	
	@Test
	public void obtenerEmpresa(){
		Empresa empresaObtenida = repositorio.buscarEmpresa("Empresa del Repositorio 1");
		
		empresaObtenida.getNombre();
		empresaObtenida.getCuentas();
		empresaObtenida.getPeriodos();
	}
	

	


}
