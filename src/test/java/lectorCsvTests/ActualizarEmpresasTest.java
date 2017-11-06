package lectorCsvTests;

import model.Cuenta;
import model.Empresa;
import model.repositories.RepositorioDeEmpresas;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

public class ActualizarEmpresasTest {

    private RepositorioDeEmpresas repositorio = RepositorioDeEmpresas.getInstance();
    private static boolean setUpIsDone = false;

    private Empresa empresa = new Empresa("Empresa test".toLowerCase(),
            Arrays.asList(new Cuenta("cuenta test", BigDecimal.ONE, LocalDate.of(2017, 8, 15)))
    );

    private Empresa empresaActualizada = new Empresa("empresa test".toLowerCase(),
            Arrays.asList(new Cuenta("Cuenta test", BigDecimal.TEN, LocalDate.of(2017, 8, 15)))
    );

    @Before
    public void setUp(){
        if(setUpIsDone)
            return;
        repositorio.guardar(empresa);
        setUpIsDone=true;
    }

    @Test
    public void actualizarEmpresa(){
        repositorio.guardar(empresaActualizada);

        Assert.assertEquals(1, repositorio.buscarTodos().size());
    }

    @Test
    public void valorCuentaActualizado(){
        repositorio.guardar(empresaActualizada);
        Cuenta cuentaRepositorio = repositorio.buscarEmpresa("empresa test").getCuentas().get(0);
        Assert.assertEquals(BigDecimal.TEN, cuentaRepositorio.getValor());
    }
}
