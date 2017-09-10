import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import model.Cuenta;



public class testPersistencia extends AbstractPersistenceTest implements WithGlobalEntityManager{

	
	
	@Test
	public void contextUp() {
		assertNotNull(entityManager());
	}

	@Test
	public void contextUpWithTransaction() throws Exception {
		withTransaction(() -> {});
	}
		
	/*
	@Test
	public void persistirCuenta() {
		

		
		Cuenta cuenta = new Cuenta("CuentaPrueba", 123, "2016-01-05");
		
		entityManager().persist(cuenta);
		
		assertEquals(entityManager().createQuery("from Cuenta").getResultList().get(0),cuenta);
	}*/
}
