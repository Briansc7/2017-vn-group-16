package model.repositories;

import builders.IndicadorPrecalculadoBuilder;
import com.mongodb.MongoClient;
import model.Empresa;
import model.Indicador;
import model.IndicadorPrecalculado;
import model.Usuario;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import java.util.List;

public class RepositorioIndicadoresPrecalculados {

    private Datastore datastore;
    private static RepositorioIndicadoresPrecalculados instance = new RepositorioIndicadoresPrecalculados();

    private RepositorioIndicadoresPrecalculados() {
        MongoClient client = new MongoClient();
        Morphia morphia = new Morphia();
        morphia.mapPackage("model");
        this.datastore = morphia.createDatastore(client, "indicadoresPrecalculados");
    }

    public static RepositorioIndicadoresPrecalculados getInstance() {
        return instance;
    }

    public void guardar(IndicadorPrecalculado indicador) {
        datastore.save(indicador);
    }

    public List<IndicadorPrecalculado> buscarTodos(){
        return datastore.find(IndicadorPrecalculado.class).asList();
    }

    public List<IndicadorPrecalculado> buscarTodosFiltrados(Empresa empresa, Integer periodo, Usuario usuario){
        Query<IndicadorPrecalculado> query = datastore.createQuery(IndicadorPrecalculado.class);
        query.and(
                query.criteria("nombreEmpresa").equal(empresa.getNombre()),
                query.criteria("periodo").equal(periodo),
                query.criteria("idUsuario").equal(usuario.getId())
        );

        return query.asList();
    }

    public void precalcularIndicadores(){
        datastore.delete(datastore.createQuery(IndicadorPrecalculado.class));
        List<Empresa> empresas = RepositorioDeEmpresas.getInstance().buscarTodos();
        empresas.forEach(this::actualizarEmpresa);
    }

    private void actualizarEmpresa(Empresa empresa) {
        empresa.getPeriodos().forEach(periodo -> this.actualizarPeriodoDe(periodo, empresa));
    }

    private void actualizarPeriodoDe(Integer periodo, Empresa empresa) {
        List<Indicador> indicadores = RepositorioDeIndicadores.getInstance().buscarTodos();
        indicadores.forEach(indicador -> this.precalcularIndicador(indicador, empresa, periodo));
    }

    private void precalcularIndicador(Indicador indicador, Empresa empresa, Integer periodo) {
        IndicadorPrecalculadoBuilder builder = new IndicadorPrecalculadoBuilder()
                .nombreIndicador(indicador.getNombre())
                .formulaIndicador(indicador.getFormula())
                .idUsuario(indicador.getIdUsuario())
                .nombreEmpresa(empresa.getNombre())
                .periodoDeEvaluacion(periodo)
                .valorString(indicador.getValorString(periodo, empresa));
            
        this.guardar(builder.build());
    }

    public void setDatastore(Datastore datastore) {
        this.datastore = datastore;
    }
}
