package model.repositories;

import com.mongodb.MongoClient;
import exceptions.NoExisteAtributoException;
import model.Empresa;
import model.Indicador;
import model.IndicadorPrecalculado;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

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

    //todo solo deberia actualizar los indicadores que dependen de las cuentas que actualice
    public void precalcularIndicadores(/*List<Empresa> empresas*/){
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
        IndicadorPrecalculado indicadorPrecalculado;
        //todo hacer builder
        try {
            indicadorPrecalculado = new IndicadorPrecalculado(
                    indicador.getNombre(),
                    indicador.getFormula(),
                    indicador.getIdUsuario(),
                    empresa.getNombre(),
                    periodo,
                    indicador.getValorString(periodo, empresa)
            );
        } catch(NoExisteAtributoException e) {
            indicadorPrecalculado = new IndicadorPrecalculado(
                    indicador.getNombre(),
                    indicador.getFormula(),
                    indicador.getIdUsuario(),
                    empresa.getNombre(),
                    periodo,
                    e.getMessage()
            );
        }

        this.guardar(indicadorPrecalculado);
    }
}
