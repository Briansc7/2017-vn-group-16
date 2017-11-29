package db;

import com.mongodb.MongoClient;
import model.IndicadorPrecalculado;
import model.repositories.RepositorioIndicadoresPrecalculados;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.List;

public class IndicadoresPrecalculadosTest {

    RepositorioIndicadoresPrecalculados repositorio;
    IndicadorPrecalculado indicador1;
    IndicadorPrecalculado indicador2;
    Datastore datastore;
    boolean setUpIsDone = false;

    @Before
    public void setUp(){
        if(!setUpIsDone){
            indicador1 = new IndicadorPrecalculado(
                    "indicador test", "prueba mongo * 10", 1l, "google", 2016, "2016");
            indicador2 = new IndicadorPrecalculado(
                    "indicador test", "prueba mongo * 20", 1l, "google", 2017, "2017");
            setUpIsDone = true;
        }
        MongoClient client = new MongoClient();
        Morphia morphia = new Morphia();
        morphia.mapPackage("model");
        datastore = morphia.createDatastore(client, "indicadoresPrecalculadosTest");
        RepositorioIndicadoresPrecalculados.getInstance().setDatastore(datastore);
        repositorio = RepositorioIndicadoresPrecalculados.getInstance();
        repositorio.guardar(indicador1);
        repositorio.guardar(indicador2);
    }
/*
    @Test
    public void persistencia(){
        repositorio.guardar(indicador);
    }
*/
    @Test
    public void levantarIndicadores(){
        List<IndicadorPrecalculado> lista = repositorio.buscarTodos();

        Assert.assertEquals(2, lista.size());
    }

    @Test
    public void nombreIndicador2017(){
        IndicadorPrecalculado indicadorPrecalculado = repositorio.buscarTodos().get(0);

        Assert.assertEquals("indicador test", indicadorPrecalculado.getNombre());
    }

    @After
    public void limpiarMongo() {
        datastore.delete(datastore.createQuery(IndicadorPrecalculado.class));
    }
}
