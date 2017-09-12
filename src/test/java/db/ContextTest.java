package db;

import static org.junit.Assert.*;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

public class ContextTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	
	/*Llama al método entity manager y se fija que no sea nulo, porque ese objeto (importante en hibernate) 
	 es el objeto que vamos a usar para interactuar con el ORM. Es importante tener este tipo de test porque
	 prueba que la configuración que le hicimos al ORM es correcta.
	 */
	@Test
	public void contextUp() {
		assertNotNull(entityManager());
	}
	
	@Test
	public void contextUpWithTransaction() throws Exception {
		withTransaction(() -> {});
	}
	
	

}