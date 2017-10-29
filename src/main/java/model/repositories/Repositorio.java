package model.repositories;

import exceptions.EseNoExisteException;
import exceptions.EseYaExisteException;
import model.Usuario;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.ParameterExpression;
import java.util.List;

public abstract class Repositorio implements WithGlobalEntityManager, TransactionalOps {

    public <T> void guardar(T t) {
        if(this.existe(this.obtenerNombreDe(t))){
            throw new EseYaExisteException("Ya existe: " + this.obtenerNombreDe(t));
        }

        withTransaction(()->this.entityManager().persist(t));
    }

    public <T> void guardarTodos(List<T> ts) {
        ts.stream().forEach(empresa -> guardar(empresa));
    }

    public <T> List<T> buscarTodos() {
        CriteriaQuery<T> criteria = this.entityManager().getCriteriaBuilder().createQuery(getTipo());

        criteria.from(getTipo());

        return this.entityManager().createQuery(criteria).getResultList();
    }
    
    

    protected  <T> T buscarUnoPorNombre(String nombre){
        if(this.existe(nombre))
            return (T) this.buscarTodosPorNombre(nombre).get(0);
        throw new EseNoExisteException("No existe: " + nombre);
    }

    public <T> List<T> buscarTodosPorNombre(String nombre) {
        CriteriaBuilder criteriaBuilder = this.entityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteria = criteriaBuilder.createQuery(getTipo());
        Root<T> tipo = criteria.from(getTipo());

        criteria.select(tipo);
        ParameterExpression<String> parametroNombre = criteriaBuilder.parameter(String.class);
        criteria.where(criteriaBuilder.like(tipo.get("nombre"), parametroNombre));
        return entityManager().createQuery(criteria).setParameter("param0", /*"%"+*/nombre/*+"%"*/).getResultList();//TODO ponerle alias a param0
    }

    public <T> List<T> buscarTodosPorUsuario(Usuario usuario) {
    	CriteriaBuilder criteriaBuilder = this.entityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteria = criteriaBuilder.createQuery(getTipo());
        Root<T> tipo = criteria.from(getTipo());

        criteria.select(tipo);
        ParameterExpression<Usuario> parametroUsuario = criteriaBuilder.parameter(Usuario.class);
        criteria.where(criteriaBuilder.equal(tipo.get("usuario"), parametroUsuario));
        return entityManager().createQuery(criteria).setParameter("param0", usuario).getResultList();
    
    }
    
    public <T> T buscarPorId(long id) {
        CriteriaBuilder criteriaBuilder = this.entityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteria = criteriaBuilder.createQuery(getTipo());
        Root<T> tipo = criteria.from(getTipo());

        criteria.select(tipo);
        ParameterExpression<Long> parametroId = criteriaBuilder.parameter(Long.class);
        criteria.where(criteriaBuilder.equal(tipo.get("id"), parametroId));
        return entityManager().createQuery(criteria).setParameter("param0", id).getSingleResult();//TODO ponerle alias a param0

    }

    public <T> void remover(T t) {
        withTransaction(() ->  entityManager().remove(t));
    }

    public Boolean existe(String nombre){
        return this.buscarTodosPorNombre(nombre.toLowerCase()).size() != 0;
    }

    abstract protected  <T> String obtenerNombreDe(T t);

    abstract public <T> Class<T> getTipo();

    //TODO guardar buscarPorId()
}
