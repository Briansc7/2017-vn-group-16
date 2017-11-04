package indicadoresPrecalculados;

import model.IndicadorPrecalculado;
import model.repositories.RepositorioIndicadoresPrecalculados;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class TestIndicadorePrecalculados {

    RepositorioIndicadoresPrecalculados repositorio;
    IndicadorPrecalculado indicador;

    @Before
    public void setUp(){
        repositorio = RepositorioIndicadoresPrecalculados.getInstance();
        indicador = new IndicadorPrecalculado(
                "indicador 2", "prueba mongo * 10", "google", 2016, new BigDecimal(2016));
    }
/*
    @Test
    public void persistencia(){
        repositorio.guardar(indicador);
    }
*/
    @Test
    public void levantarIndicador(){
        List<IndicadorPrecalculado> lista = repositorio.buscarTodos();

        Assert.assertEquals(1, lista.size());
    }
}
