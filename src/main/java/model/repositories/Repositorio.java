package model.repositories;

import exceptions.EseNoExisteException;
import exceptions.EseYaExisteException;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.ParameterExpression;
import java.util.List;

public abstract class Repositorio implements WithGlobalEntityManager, TransactionalOps {

    public <T> void agregar(T t) {
        if(this.existe(this.obtenerNombreDe(t)))
            throw new EseYaExisteException("Ya existe: " + this.obtenerNombreDe(t));

        withTransaction(()->this.entityManager().persist(t));
    }

    public <T> void agregarTodos(List<T> ts) {
        ts.stream().forEach(empresa -> agregar(empresa));
    }

    public <T> T buscarUnoPorNombre(String nombre){
        if(this.existe(nombre))
            return (T) this.buscarTodosPorNombre(nombre).get(0);
        throw new EseNoExisteException("No existe: " + nombre);
    }

    public <T> List<T> buscarTodosPorNombre(String nombre/*, String mensajeDeError*/) {
//        try {
//            return (T) entityManager().find(clase, id);
//        } catch (PersistenceException e) {
//            throw new RuntimeException(mensajeDeError);
//        }
        CriteriaBuilder criteriaBuilder = this.entityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteria = criteriaBuilder.createQuery(getTipo());
        Root<T> tipo = criteria.from(getTipo());

        criteria.select(tipo);
        ParameterExpression<String> parametroNombre = criteriaBuilder.parameter(String.class);
        criteria.where(criteriaBuilder.like(tipo.get("nombre"), parametroNombre));
        return entityManager().createQuery(criteria).setParameter("param0", nombre).getResultList();//TODO ponerle alias a param0
    }

    public <T> List<T> buscarTodos() {
        CriteriaQuery<T> criteria = this.entityManager().getCriteriaBuilder().createQuery(getTipo());

        criteria.from(getTipo());

        return this.entityManager().createQuery(criteria).getResultList();
    }

    public Boolean existe(String nombre){
        return this.buscarTodosPorNombre(nombre).size() != 0;
    }

    abstract protected  <T> String obtenerNombreDe(T t);

    abstract public <T> Class<T> getTipo();

    //TODO agregar buscarPorId()
}
