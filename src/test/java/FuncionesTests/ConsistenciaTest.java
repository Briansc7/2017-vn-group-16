package FuncionesTests;

import model.Cuenta;
import model.Empresa;
import model.Indicador;
import model.funciones.Consistencia;
import model.condiciones.Comparador;
import model.condiciones.Condicion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parser.ParseException;
import builders.CondicionBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

public class ConsistenciaTest {

    Empresa empresa;
    Cuenta cuentaUno;
    Cuenta cuentaDos;
    Cuenta cuentaTres;
    Indicador indicador;
    Consistencia funcionConsistencia;
    Condicion condicionConsistenciaCreciente;
    Condicion condicionConsistenciaDecreciente;

    @Before
    public void initialize() throws ParseException {
        indicador = new Indicador("indicador", "cuenta");
        funcionConsistencia = new Consistencia(indicador);
        condicionConsistenciaCreciente = new CondicionBuilder()
                .periodoDeEvaluacion(3)
                .comparador(Comparador.IGUAL)
                .funcionParaObtenerValor(funcionConsistencia)
                .valorContraElQueSeCompara(BigDecimal.ONE)//si la consistencia es 1, quiere decir que es creciente
                .build();
        condicionConsistenciaDecreciente = new CondicionBuilder()
                .periodoDeEvaluacion(3)
                .comparador(Comparador.IGUAL)
                .funcionParaObtenerValor(funcionConsistencia)
                .valorContraElQueSeCompara(new BigDecimal(-1))//si la consistencia es -1, quiere decir que es decreciente
                .build();
    }

    @Test
    public void elIndicadorEsCreciente(){
        cuentaUno = new Cuenta("cuenta", new BigDecimal(10), LocalDate.of(2015, 10, 20));
        cuentaDos = new Cuenta("cuenta", new BigDecimal(15), LocalDate.of(2016, 10, 20));
        cuentaTres = new Cuenta("cuenta", new BigDecimal(20), LocalDate.of(2017, 10, 20));
        empresa = new Empresa("empresa", Arrays.asList(cuentaUno, cuentaDos, cuentaTres));

        Assert.assertEquals(empresa, condicionConsistenciaCreciente.analizar(Arrays.asList(empresa)).get(0));
    }

    @Test
    public void elIndicadorEsDecreciente(){
        cuentaUno = new Cuenta("cuenta", new BigDecimal(20), LocalDate.of(2015, 10, 20));
        cuentaDos = new Cuenta("cuenta", new BigDecimal(15), LocalDate.of(2016, 10, 20));
        cuentaTres = new Cuenta("cuenta", new BigDecimal(10), LocalDate.of(2017, 10, 20));
        empresa = new Empresa("empresa", Arrays.asList(cuentaUno, cuentaDos, cuentaTres));

        Assert.assertEquals(empresa, condicionConsistenciaDecreciente.analizar(Arrays.asList(empresa)).get(0));
    }

    @Test
    public void crecienteAunqueFalteCuentaDe2016(){
        cuentaUno = new Cuenta("cuenta", new BigDecimal(10), LocalDate.of(2015, 10, 20));
        cuentaDos = new Cuenta("cuenta", new BigDecimal(15), LocalDate.of(2017, 10, 20));
        empresa = new Empresa("empresa", Arrays.asList(cuentaUno, cuentaDos));

        Assert.assertEquals(empresa, condicionConsistenciaCreciente.analizar(Arrays.asList(empresa)).get(0));
    }

    @Test
    public void decrecienteAunqueFalteCuentaDe2016(){
        cuentaUno = new Cuenta("cuenta", new BigDecimal(20), LocalDate.of(2015, 10, 20));
        cuentaDos = new Cuenta("cuenta", new BigDecimal(15), LocalDate.of(2017, 10, 20));
        empresa = new Empresa("empresa", Arrays.asList(cuentaUno, cuentaDos));

        Assert.assertEquals(empresa, condicionConsistenciaDecreciente.analizar(Arrays.asList(empresa)).get(0));
    }
}
