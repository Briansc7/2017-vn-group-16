package model.repositories;

import java.util.List;

import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import exceptions.EseNoExisteException;
import exceptions.EseYaExisteException;
import model.Metodologia;

public class RepositorioDeMetodologias implements WithGlobalEntityManager{
	private static RepositorioDeMetodologias instance;
	private EntityTransaction transaction = entityManager().getTransaction();
	
	//Singleton
	private RepositorioDeMetodologias(){}
	
	public synchronized static RepositorioDeMetodologias getInstance(){
		if(instance == null)
			instance = new RepositorioDeMetodologias();
		return instance;
	}
	
	public void guardarMetodologia(Metodologia unaMetodologia){
		if(existeMetodologia(unaMetodologia.getNombre()))
			throw new EseYaExisteException("Ya existe una metodologia de nombre: " + unaMetodologia.getNombre());
		sobreescribirMetodologia(unaMetodologia);
	}
	
	public void sobreescribirMetodologia(Metodologia unaMetodologia){
		transaction.begin();
		entityManager().persist(unaMetodologia);
		transaction.commit();
	}
	
	//Este devuelve todas las metodologias de la base de datos
	public List<Metodologia> obtenerMetodologia(){
		@SuppressWarnings("unchecked")
		List<Metodologia> metodologias = entityManager()
				.createQuery("select metodologia from Metodologia as metodologia")
				.getResultList();
		return metodologias;
	}
	
	public Metodologia obtenerMetodologia(String nombre){
		if(existeMetodologia(nombre))
			return buscarMetodologia(nombre).get(0);
		throw new EseNoExisteException("No existe una metodologia de nombre: " + nombre);
	}
	
	public Boolean existeMetodologia(String nombre){
		return buscarMetodologia(nombre).size() != 0;
	}
	
	private List<Metodologia> buscarMetodologia(String nombre){
		@SuppressWarnings("unchecked")
		List<Metodologia> metodologias = entityManager()
				.createQuery("select metodologia from Metodologia as metodologia where metodologia.nombre = ?1")
				.setParameter(1, nombre)
				.getResultList();
		return metodologias;
	}

	public void guardarMetodologias(List<Metodologia> metodologias) {
		metodologias.stream().forEach(metodologia -> guardarMetodologia(metodologia));
	}
	

	public void removerMetodologia(Metodologia metodologia) {
		transaction.begin();
		entityManager().remove(metodologia);
		transaction.commit();
	}
	
	/*
	//private CondicionGeneral condicionRoe = new CondicionNoTaxativa(2, "ROE", new GreaterThan(), 1);
	private CondicionGeneral condicionRoe = new CondicionBuilder()
			.periodoDeEvaluacion(2)
			.funcionParaObtenerValor(new ValorParaNAnios(base.buscarIndicador("ROE")))
			.comparador(Comparador.MAYOR)
			.build();
	//private CondicionNoTaxativa condicionDeuda = new CondicionNoTaxativa(2, "debtEquityRatio", new LessThan(), 2);
	private CondicionGeneral condicionDeuda = new CondicionBuilder()
			.periodoDeEvaluacion(2)
			.funcionParaObtenerValor(new ValorParaNAnios(base.buscarIndicador("debtEquityRatio")))
			.comparador(Comparador.MENOR)
			.build();
	//private CondicionTaxativa condicionMargen = new CondicionTaxativa(2, "Margen", new GreaterAndEqualThan(), "margen");
	private CondicionGeneral condicionMargen = new CondicionBuilder()
			.periodoDeEvaluacion(2)
			.funcionParaObtenerValor(new Consistencia(base.buscarIndicador("margen")))
			.comparador(Comparador.MAYOROIGUAL)
			.build();
	//private TaxativaLongevidad condicionLongevidad1 = new TaxativaLongevidad(0, "longevidad", new GreaterAndEqualThan(), new BigDecimal(2));
	private CondicionGeneral condicionLongevidadPropia = new CondicionBuilder()
			.periodoDeEvaluacion(1)
			.funcionParaObtenerValor(new Longevidad())
			.comparador(Comparador.MAYOROIGUAL)
			.valor(new BigDecimal(2))
			.build();
	//private NoTaxativaLongevidad condicionLongevidad2 = new NoTaxativaLongevidad(0, "longevidad", new GreaterThan(), 5);
	private CondicionGeneral condicionLongevidadComparativa = new CondicionBuilder()
			.periodoDeEvaluacion(1)
			.funcionParaObtenerValor(new Longevidad())
			.comparador(Comparador.MAYOR)
			.valor(new BigDecimal(5))
			.build();
	private Metodologia buffet = new Metodologia("Buffet", Arrays.asList(condicionMargen, condicionLongevidadPropia, condicionRoe, condicionDeuda, condicionLongevidadComparativa));	
	 */
}