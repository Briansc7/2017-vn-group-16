package db;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import model.Indicador;
import model.funciones.Funcion;
import model.funciones.Longevidad;
import model.funciones.Promedio;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import parser.ParseException;
import parser.TokenMgrError;

public class FuncionesTest extends AbstractPersistenceTest implements WithGlobalEntityManager{

	Funcion longevidad;
	Funcion promedio;
	Indicador indicadorPromedio;
	
	@Before
	public void setUp() throws ParseException, TokenMgrError{
		longevidad = new Longevidad();
		indicadorPromedio= new Indicador("Indicador promedio","ingresoNeto * 2");
		promedio = new Promedio(indicadorPromedio);
		//indicadorComplejo = new Indicador("Indicador complejo","ingresoNeto/2*2 + capitalTotal/2*2 - capitalTotal*ingresoNeto");
	}
	
	@Test
	public void guardarFuncionConIndicador(){
//		EntityTransaction transaction = entityManager().getTransaction();
//
//		transaction.begin();
		
		entityManager().persist(promedio);
		
		//transaction.commit();
		
		Funcion promedioDB = (Funcion)entityManager()
				.createQuery("select funcion from Funcion as funcion where tipoDeFuncion = ?1")
				.setParameter(1, "promedio")
				.getResultList().get(0);//.find(Funcion.class, 2L);
		
		assertEquals(Promedio.class, promedioDB.getClass());
	}
	
	@Test
	public void guardarFuncionSinIndicador(){
//		EntityTransaction transaction = entityManager().getTransaction();
//
//		transaction.begin();
		
		entityManager().persist(longevidad);
		
		//transaction.commit();
		
		Funcion longevidadDB = (Funcion)entityManager()
				.createQuery("select funcion from Funcion as funcion where tipoDeFuncion = ?1")
				.setParameter(1, "longevidad")
				.getResultList().get(0);//= entityManager().find(Funcion.class, 1L);
		
		assertEquals(Longevidad.class, longevidadDB.getClass());
	}
}
