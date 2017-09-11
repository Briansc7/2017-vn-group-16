package db;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import model.Cuenta;

public class ObjetosSimplesTest implements WithGlobalEntityManager{
	
	@Test
	public void guardarUnaCuenta() {
		EntityTransaction transaction = entityManager().getTransaction();
		
		transaction.begin();
		
		Cuenta cuentaCreadaPorMi = new Cuenta("Cuenta de Prueba", BigDecimal.valueOf(10D), LocalDate.now());
		
		entityManager().persist(cuentaCreadaPorMi);
		
		transaction.commit();
		
		Cuenta cuentaDeLaBase = entityManager().find(Cuenta.class, 1L);
		
		System.out.println(cuentaDeLaBase.getNombre());
		
	}
	
}
