package model.repositories;

import com.mongodb.MongoClient;
import converters.BigDecimalConverter;
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
        morphia.getMapper().getConverters().addConverter(BigDecimalConverter.class);
        this.datastore = morphia.createDatastore(client, "indicadoresPrecalculados");
    }

    public static RepositorioIndicadoresPrecalculados getInstance() {
        return instance;
    }

    public void actualizarIndicadores(){

    }

    public void guardar(IndicadorPrecalculado indicador) {
        datastore.save(indicador);
    }

    public List<IndicadorPrecalculado> buscarTodos(){
        return datastore.find(IndicadorPrecalculado.class).asList();
    }
}
