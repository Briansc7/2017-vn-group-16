package db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityTransaction;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import model.Cuenta;
import model.Empresa;
import model.Indicador;
import parser.ParseException;
import parser.TokenMgrError;

public class ObjetosSimplesTest implements WithGlobalEntityManager{
	
	Cuenta cuentaCreadaPorMi = new Cuenta("Cuenta de Prueba", BigDecimal.valueOf(10), LocalDate.of(2017, 8, 15));
	
	List <Cuenta> _cuentas = Arrays.asList(
			new Cuenta("Cuenta de Prueba 1", BigDecimal.valueOf(10), LocalDate.of(2017, 8, 15)),
			new Cuenta("Cuenta de Prueba 2", BigDecimal.valueOf(20), LocalDate.of(2017, 8, 15)),
			new Cuenta("Cuenta de Prueba 3", BigDecimal.valueOf(30), LocalDate.of(2017, 8, 15))
			);
	Empresa empresa = new Empresa("Empresa de Prueba", _cuentas);
	
	
	@Test
	public void guardarUnaCuenta() {
		guardarCuenta();
		
		Cuenta cuentaDeLaBase = entityManager().find(Cuenta.class, 1L);
		
		cuentaDeLaBase.getFecha();
		cuentaDeLaBase.getNombre();
	}
	
	@Test
	public void buscarUnaCuenta() {
		guardarCuenta();
		
		entityManager().clear();
		
		Cuenta cuentaDeLaBase = entityManager().find(Cuenta.class, 1L);
		
		cuentaDeLaBase.getFecha();
		cuentaDeLaBase.getNombre();
	}
	
	@Test
	public void editarUnaCuenta() {
		guardarCuenta();
		
		Cuenta cuentaDeLaBase = entityManager().find(Cuenta.class, 1L);
		
		cuentaDeLaBase.setNombre("OtroNombre");
		
		assertEquals(cuentaCreadaPorMi.getFecha(), cuentaDeLaBase.getFecha());
		assertEquals(cuentaCreadaPorMi.getNombre(), cuentaDeLaBase.getNombre());
	}
	
	@Test
	public void guardarUnaEmpresa() {
		EntityTransaction transaction = entityManager().getTransaction();

		transaction.begin();

		entityManager().persist(empresa);

		transaction.commit();

		Empresa empresaDeLaBase = entityManager().find(Empresa.class, 1L);

		assertEquals(3,	empresaDeLaBase.getCuentas().size());
	}
	
	@Test
	public void guardarUnIndicador() throws ParseException, TokenMgrError {
		Indicador indicador = new Indicador("Prueba", "2");
		
		EntityTransaction transaction = entityManager().getTransaction();

		transaction.begin();

		entityManager().persist(indicador);

		transaction.commit();
		
		List<Indicador> indicadoresDeLaBase = entityManager()
				.createQuery("select indicador from Indicador as indicador where indicador.nombre = ?1")
				.setParameter(1, "Prueba")
				.getResultList();
		
		assertEquals(indicador.getNombre(),indicadoresDeLaBase.get(0).getNombre());
	}
	
	@Test
	public void selectSimple() {
		
		List <Cuenta> cuentas = entityManager().createQuery("select cuenta from Cuenta as cuenta where cuenta.id = ?1")
		.setParameter(1, 1L)
	    .getResultList();
		
		assertEquals(cuentas.size(), 1);
		assertTrue(BigDecimal.valueOf(10).compareTo(cuentas.get(0).getValor()) == 0);
	}
	
	@Test
	public void selectComplejo() {
		
		List <Cuenta> cuentas = entityManager().createQuery("select cuenta from Cuenta as cuenta where cuenta.fecha = ?1")
		.setParameter(1, LocalDate.of(2017, 8, 15))
	    .getResultList();
		
		assertEquals(cuentaCreadaPorMi.getFecha(), cuentas.get(0).getFecha());
	}
	
	private void guardarCuenta() {
		EntityTransaction transaction = entityManager().getTransaction();
		
		transaction.begin();
		
		entityManager().persist(cuentaCreadaPorMi);
		
		transaction.commit();
	}
}
