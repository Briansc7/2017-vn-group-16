package db;

import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import model.Indicador;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import parser.ParseException;
import parser.TokenMgrError;

public class IndicadoresTest extends AbstractPersistenceTest implements WithGlobalEntityManager{
	
	Indicador indicadorSimple;
	Indicador indicadorIntermedio;
	Indicador indicadorComplejo;
	
	@Before
	public void setUp() throws ParseException, TokenMgrError{
		indicadorSimple = new Indicador("Indicador simple","2*3+20-20/2");
		indicadorIntermedio = new Indicador("Indicador intermedio","ingresoNeto * 2");
		indicadorComplejo = new Indicador("Indicador complejo","ingresoNeto/2*2 + capitalTotal/2*2 - capitalTotal*ingresoNeto");
	}
	
	@Test
	public void guardarIndicadorSimple(){
//		EntityTransaction transaction = entityManager().getTransaction();
//
//		transaction.begin();
		
		entityManager().persist(indicadorSimple);
		
		//transaction.commit();
	}
	
	@Test
	public void guardarIndicadorIntermedio(){
//		EntityTransaction transaction = entityManager().getTransaction();
//
//		transaction.begin();
		
		entityManager().persist(indicadorIntermedio);
		
		//transaction.commit();
	}
	
	@Test
	public void guardarIndicadorComplejo(){
//		EntityTransaction transaction = entityManager().getTransaction();
//
//		transaction.begin();
		
		entityManager().persist(indicadorComplejo);
		
		//transaction.commit();
	}
}
