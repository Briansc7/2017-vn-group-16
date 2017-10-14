package db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import model.Cuenta;
import model.Empresa;
import model.Indicador;
import parser.ParseException;
import parser.TokenMgrError;

//usar eso
public class ObjetosSimplesTest extends AbstractPersistenceTest implements WithGlobalEntityManager{
	
	Cuenta cuentaCreadaPorMi = new Cuenta("Cuenta de Prueba", BigDecimal.valueOf(10), LocalDate.of(2017, 8, 15));
	
	List <Cuenta> _cuentas = Arrays.asList(
			new Cuenta("Cuenta de Prueba 1", BigDecimal.valueOf(10), LocalDate.of(2017, 8, 15)),
			new Cuenta("Cuenta de Prueba 2", BigDecimal.valueOf(20), LocalDate.of(2017, 8, 15)),
			new Cuenta("Cuenta de Prueba 3", BigDecimal.valueOf(30), LocalDate.of(2017, 8, 15))
			);
	Empresa empresa = new Empresa("Empresa de Prueba", _cuentas);

	@Before
	public void persistirCuenta() {
		
		/*se persiste fuera de transacción ya que no es necesario abrir transacciones en los test
		 * al final del test se va a borrar automaticamente lo persistido
		 */
		//entityManager().persist(cuentaCreadaPorMi);

		entityManager().persist(cuentaCreadaPorMi);//guardarCuenta();
		
		//se compara contra el último registro insertado
		//assertEquals(entityManager().createQuery("from Cuenta order by id desc").getResultList().get(0),cuentaCreadaPorMi);
	}
	
	@Test
	public void guardarUnaCuenta() {
		//guardarCuenta();
		//entityManager().persist(cuentaCreadaPorMi);
//		Cuenta cuentaDeLaBase = entityManager().createQuery("from Cuenta order by id desc", Cuenta.class)
//				.getResultList().get(0);
		Cuenta cuentaDeLaBase = entityManager()
				.createQuery("from Cuenta as cuenta where cuenta.nombre = ?1", Cuenta.class)
				.setParameter(1, "Cuenta de Prueba")
				.getResultList().get(0);

		cuentaDeLaBase.getFecha();
		cuentaDeLaBase.getNombre();
	}

	@Test
	public void buscarUnaCuenta() {
		//guardarCuenta();
		//entityManager().persist(cuentaCreadaPorMi);
		Cuenta cuentaDeLaBase = entityManager().createQuery("from Cuenta order by id desc", Cuenta.class)
				.getResultList().get(0);

		cuentaDeLaBase.getFecha();
		cuentaDeLaBase.getNombre();
	}
	
	@Test
	public void editarUnaCuenta() {
		//guardarCuenta();
		//entityManager().persist(cuentaCreadaPorMi);
		Cuenta cuentaDeLaBase = entityManager()
				.createQuery("from Cuenta as cuenta where cuenta.nombre = ?1", Cuenta.class)
				.setParameter(1, "Cuenta de Prueba")
				.getResultList().get(0);
		
		cuentaDeLaBase.setNombre("OtroNombre");
		
		assertEquals(cuentaCreadaPorMi.getFecha(), cuentaDeLaBase.getFecha());
		assertEquals("OtroNombre", cuentaDeLaBase.getNombre());
	}
	
	@Test
	public void guardarUnaEmpresa() {

		entityManager().persist(empresa);
		
		Empresa empresaDeLaBase = entityManager()
				.createQuery("from Empresa order by id desc", Empresa.class)
				.getResultList().get(0);

		assertEquals(3, empresaDeLaBase.getCuentas().size());
	}
	
	@Test
	public void guardarUnIndicador() throws ParseException, TokenMgrError {
		Indicador indicador = new Indicador("Prueba", "2");

		entityManager().persist(indicador);

		List<Indicador> indicadoresDeLaBase = entityManager()
				.createQuery("from Indicador as indicador where indicador.nombre = ?1", Indicador.class)
				.setParameter(1, "Prueba")
				.getResultList();
		
		assertEquals(indicador.getNombre(),indicadoresDeLaBase.get(0).getNombre());
	}
	
	@Test
	public void selectSimple() {
		
		List <Cuenta> cuentas = entityManager()
				.createQuery("from Cuenta ", Cuenta.class)
				.getResultList();
		
		assertEquals(1, cuentas.size());
		assertEquals(0, BigDecimal.valueOf(10).compareTo(cuentas.get(0).getValor()));
	}
	
	@Test
	public void selectComplejo() {
		
		List <Cuenta> cuentas = entityManager()
				.createQuery("from Cuenta as cuenta where cuenta.fecha = ?1", Cuenta.class)
				.setParameter(1, LocalDate.of(2017, 8, 15))
				.getResultList();
		
		assertEquals(cuentaCreadaPorMi.getFecha(), cuentas.get(0).getFecha());
	}
	
//	private void guardarCuenta() {
//		entityManager().persist(cuentaCreadaPorMi);
//	}
}
