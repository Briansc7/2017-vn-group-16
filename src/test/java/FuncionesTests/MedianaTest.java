package FuncionesTests;

import model.Cuenta;
import model.Empresa;
import model.Indicador;
import model.funciones.Mediana;
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

public class MedianaTest {

    Empresa empresa;
    Cuenta cuentaUno;
    Cuenta cuentaDos;
    Cuenta cuentaTres;
    Cuenta cuentaCuatro;
    Indicador indicador;
    Mediana funcionMediana;
    Condicion condicionConsistenciaCreciente;
    Condicion condicionConsistenciaDecreciente;

    @Before
    public void initialize() throws ParseException {
        cuentaUno = new Cuenta("cuenta", new BigDecimal(10), LocalDate.of(2014, 10, 20));
        cuentaDos = new Cuenta("cuenta", new BigDecimal(15), LocalDate.of(2015, 10, 20));
        cuentaTres = new Cuenta("cuenta", new BigDecimal(20), LocalDate.of(2016, 10, 20));
        cuentaCuatro = new Cuenta("cuenta", new BigDecimal(22), LocalDate.of(2017, 10, 20));
        empresa = new Empresa("empresa", Arrays.asList(cuentaUno, cuentaDos, cuentaTres, cuentaCuatro));

        indicador = new Indicador("indicador", "cuenta");
        funcionMediana = new Mediana(indicador);
        condicionConsistenciaCreciente = new CondicionBuilder()
                .periodoDeEvaluacion(4)
                .comparador(Comparador.MAYOR_O_IGUAL)
                .funcionParaObtenerValor(funcionMediana)
                .valorContraElQueSeCompara(new BigDecimal(15))
                .build();
    }

    @Test
    public void pruebaConCondicion(){
        Assert.assertEquals(empresa, condicionConsistenciaCreciente.analizar(Arrays.asList(empresa)).get(0));
    }

    @Test
    public void pruebaDirectamenteConMediana(){
        Assert.assertEquals(new BigDecimal(17.5), funcionMediana.calcularValor(empresa, 4).get(0));
    }
}
