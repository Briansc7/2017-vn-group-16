package db;

import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import model.Indicador;
import model.funciones.Funcion;
import model.funciones.Longevidad;
import model.funciones.Promedio;
import parser.ParseException;
import parser.TokenMgrError;

public class FuncionesTest implements WithGlobalEntityManager{

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
		EntityTransaction transaction = entityManager().getTransaction();
		
		transaction.begin();
		
		entityManager().persist(promedio);
		
		transaction.commit();
	}
	
	@Test
	public void guardarFuncionSinIndicador(){
		EntityTransaction transaction = entityManager().getTransaction();
		
		transaction.begin();
		
		entityManager().persist(longevidad);
		
		transaction.commit();
	}
}
