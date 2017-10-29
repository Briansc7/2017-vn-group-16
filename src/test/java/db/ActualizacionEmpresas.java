package db;

import model.Cuenta;
import model.Empresa;
import model.repositories.RepositorioDeEmpresas;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ActualizacionEmpresas extends AbstractPersistenceTest implements WithGlobalEntityManager {

    private RepositorioDeEmpresas repositorio = RepositorioDeEmpresas.getInstance();
    private static boolean setUpIsDone = false;

    private List<Cuenta> _cuentas = Arrays.asList(
            new Cuenta("Cuenta de Prueba 1", BigDecimal.valueOf(10), LocalDate.of(2017, 8, 15)),
            new Cuenta("Cuenta de Prueba 2", BigDecimal.valueOf(20), LocalDate.of(2017, 8, 15)),
            new Cuenta("Cuenta de Prueba 3", BigDecimal.valueOf(30), LocalDate.of(2017, 8, 15))
    );
    private Empresa empresa1 = new Empresa("Empresa del Repositorio 1".toLowerCase(), _cuentas);

    @Before
    public void guardarEmpresas(){
        if(setUpIsDone)
            return;
        repositorio.guardar(empresa1);
        setUpIsDone=true;
    }

    @Test
    public void cantidadCuentasInicial(){
        Empresa empresaObtenida = repositorio.buscarEmpresa("Empresa del Repositorio 1");

        Assert.assertEquals(3, empresaObtenida.getCuentas().size());
    }

    @Test
    public void actualizarEmpresas(){
        List<Cuenta> _cuentasNuevas = Arrays.asList(
                new Cuenta("Cuenta de Prueba 4", BigDecimal.valueOf(10), LocalDate.of(2017, 8, 15)),
                new Cuenta("Cuenta de Prueba 5", BigDecimal.valueOf(20), LocalDate.of(2017, 8, 15)),
                new Cuenta("Cuenta de Prueba 6", BigDecimal.valueOf(30), LocalDate.of(2017, 8, 15))
        );
        Empresa empresaActualizada = new Empresa("Empresa del Repositorio 1".toLowerCase(), _cuentasNuevas);
        repositorio.guardar(empresaActualizada);

        Empresa empresaObtenida = repositorio.buscarEmpresa("Empresa del Repositorio 1");

        assertEquals(6, empresaObtenida.getCuentas().size());
    }
}
