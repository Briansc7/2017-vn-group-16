package db;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import model.Metodologia;
import model.funciones.Longevidad;
import model.condiciones.Comparador;
import model.condiciones.CondicionComparativa;
import model.condiciones.Condicion;
import model.condiciones.CondicionValorUnico;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import builders.CondicionBuilder;

public class CondicionesTest extends AbstractPersistenceTest implements WithGlobalEntityManager{
	Condicion longevidadPropia;
	Condicion longevidadComparativa;
	Metodologia metodologia;
	
	@Before
	public void setUp() {
		longevidadPropia = new CondicionBuilder()
				.periodoDeEvaluacion(1)
				.funcionParaObtenerValor(new Longevidad())
				.comparador(Comparador.MAYOR)
				.valorContraElQueSeCompara(BigDecimal.ONE)
				.build();
		
		longevidadComparativa = new CondicionBuilder()
				.periodoDeEvaluacion(1)
				.funcionParaObtenerValor(new Longevidad())
				.comparador(Comparador.MENOR_O_IGUAL)
				.build();
		
		metodologia = new Metodologia("metodologia", Arrays.asList(longevidadComparativa, longevidadPropia));
	}
	
	@Test
	public void guardarCondicionLongevidadPropia(){
//		EntityTransaction transaction = entityManager().getTransaction();
//		transaction.begin();
//
		entityManager().persist(longevidadPropia);
		
		//transaction.commit();
		
		Condicion longevidadPropiaDB = entityManager().find(CondicionValorUnico.class, 2L);
		
		assertEquals(CondicionValorUnico.class, longevidadPropiaDB.getClass());

	}
	
	@Test
	public void guardarCondicionLongevidadComparativa(){
//		EntityTransaction transaction = entityManager().getTransaction();
//		transaction.begin();
//
		entityManager().persist(longevidadComparativa);
		
	//	transaction.commit();
		
		Condicion longevidadComparativaDB = entityManager().find(CondicionComparativa.class, 1L);
		
		assertEquals(CondicionComparativa.class, longevidadComparativaDB.getClass());

	}
	

}
