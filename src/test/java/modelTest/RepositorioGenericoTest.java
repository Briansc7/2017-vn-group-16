package modelTest;

import model.Indicador;
import model.repositories.Repositorio;
import model.repositories.RepositorioDeIndicadores;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import parser.ParseException;
import parser.TokenMgrError;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RepositorioGenericoTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

    private RepositorioDeIndicadores repositorio = RepositorioDeIndicadores.getInstance();
    private static boolean setUpIsDone = false;

    private Indicador indicador1;
    private Indicador indicador2;
    private Indicador indicador3;
    private List<Indicador> indicadores;

    @Before
    public void setUp() throws ParseException, TokenMgrError {
        if(setUpIsDone)
            return;

        indicador1 = new Indicador("Indicador 1","ingresoNeto/2*2 + capitalTotal/2*2 - capitalTotal*ingresoNeto");
        indicador2 = new Indicador("Indicador 2","ingresoNeto/2*2 + capitalTotal/2*2 - capitalTotal*ingresoNeto");
        indicador3 = new Indicador("Indicador 3","ingresoNeto/2*2 + capitalTotal/2*2 - capitalTotal*ingresoNeto");

        indicadores = Arrays.asList(indicador1, indicador2, indicador3);
        repositorio.guardarTodos(indicadores);

        setUpIsDone=true;
    }

    @Test
    public void buscarUnIndicador(){
        Indicador indicadorObtenido = repositorio.buscarIndicador("Indicador 1");

        indicadorObtenido.getFormula();
        indicadorObtenido.getNombre();
    }

    @Test
    public void cantidadDeIndicadores(){
        List<Indicador> indicadoresObtenidos = repositorio.buscarTodos();

        assertEquals(3, indicadoresObtenidos.size());
    }
}
