package db;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;

import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import model.Metodologia;
import model.funciones.Longevidad;
import model.metodologia.condiciones.Comparador;
import model.metodologia.condiciones.CondicionComparativa;
import model.metodologia.condiciones.CondicionGeneral;
import model.metodologia.condiciones.CondicionValorUnico;
import testMetodologia.CondicionBuilder;

public class CondicionesTest implements WithGlobalEntityManager{
	CondicionGeneral longevidadPropia;
	CondicionGeneral longevidadComparativa;
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
				.comparador(Comparador.MENOROIGUAL)
				.build();
		
		metodologia = new Metodologia("metodologia", Arrays.asList(longevidadComparativa, longevidadPropia));
	}
	
	@Test
	public void guardarCondicionLongevidadPropia(){
		EntityTransaction transaction = entityManager().getTransaction();
		transaction.begin();
		
		entityManager().persist(longevidadPropia);
		
		transaction.commit();
		
		CondicionGeneral longevidadPropiaDB = entityManager().find(CondicionValorUnico.class, 2L);
		
		assertEquals(CondicionValorUnico.class, longevidadPropiaDB.getClass());

	}
	
	@Test
	public void guardarCondicionLongevidadComparativa(){
		EntityTransaction transaction = entityManager().getTransaction();
		transaction.begin();
		
		entityManager().persist(longevidadComparativa);
		
		transaction.commit();
		
		CondicionGeneral longevidadComparativaDB = entityManager().find(CondicionComparativa.class, 1L);
		
		assertEquals(CondicionComparativa.class, longevidadComparativaDB.getClass());

	}
	
	@Test
	public void guardarMetodologia(){
		EntityTransaction transaction = entityManager().getTransaction();
		transaction.begin();
		
		entityManager().persist(metodologia);
		
		transaction.commit();
		
		Metodologia metodologiaDB = entityManager().find(Metodologia.class, 1L);
		
		assertEquals(metodologia.getNombre(), metodologiaDB.getNombre());
	}
}
