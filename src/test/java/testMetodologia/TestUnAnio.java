package testMetodologia;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import builders.CondicionBuilder;
import model.repositories.RepositorioDeEmpresas;
import model.repositories.RepositorioDeIndicadores;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.Cuenta;
import model.Empresa;
import model.Indicador;
import model.Metodologia;
import model.funciones.Consistencia;
import model.funciones.Longevidad;
import model.funciones.ValoresDelPeriodo;
import model.condiciones.Comparador;
import model.condiciones.Condicion;
import parser.ParseException;
import parser.TokenMgrError;

public class TestUnAnio  {
    Metodologia metodologiaBuffet;
    Condicion condicionRoe;
    Condicion condicionDeuda;
    Condicion condicionMargen;
    Condicion condicionLongevidadPropia;
    Condicion condicionLongevidadComparativa;
    Empresa empresaUno;
    Empresa empresaDos;
    Empresa empresaTres;
    List<Empresa> empresas;
    List<Indicador> indicadores;
    Indicador indicadorRoe;
    Indicador indicadorMargen;
    Indicador indicadorDeuda;
    Indicador indicadorEquity;
    Indicador indicadorLongevidad;

    @Before
    public void initialize() throws ParseException {
        empresaUno = new Empresa("Facebook", Arrays.asList(new Cuenta("ingresoNeto", new BigDecimal(15), LocalDate.parse("2017-05-10")),
                new Cuenta("capitalTotal", new BigDecimal(30), LocalDate.parse("2017-05-10")),
                new Cuenta("totalLiabilities", new BigDecimal(40), LocalDate.parse("2017-05-10")),
                new Cuenta("deuda", new BigDecimal(7), LocalDate.parse("2017-05-10"))));
        empresaDos = new Empresa("twitter", Arrays.asList(new Cuenta("ingresoNeto", new BigDecimal(10), LocalDate.parse("2017-05-10")),
                new Cuenta("capitalTotal", new BigDecimal(20), LocalDate.parse("2017-05-10")),
                new Cuenta("totalLiabilities", new BigDecimal(30), LocalDate.parse("2017-05-10")),
                new Cuenta("deuda", new BigDecimal(5), LocalDate.parse("2016-05-10"))));
        empresaTres = new Empresa("google", Arrays.asList(new Cuenta("ingresoNeto", new BigDecimal(100), LocalDate.parse("2017-05-10")),
                new Cuenta("capitalTotal", new BigDecimal(200), LocalDate.parse("2017-05-10")),
                new Cuenta("totalLiabilities", new BigDecimal(300), LocalDate.parse("2017-05-10")),
                new Cuenta("deuda", new BigDecimal(10), LocalDate.parse("2015-05-10"))));
        //condicionRoe = new CondicionNoTaxativa(1, "ROE", new GreaterThan(), 1);
        //condicionDeuda = new CondicionNoTaxativa(1, "debtEquityRatio", new LessThan(), 2);
        //condicionMargen = new CondicionTaxativa(1, "Margen", new GreaterAndEqualThan(), "margen");
        //condicionLongevidad1 = new TaxativaLongevidad(0, "", new GreaterAndEqualThan(), new BigDecimal(2));
        //condicionLongevidad2 = new NoTaxativaLongevidad(0, "", new GreaterThan(), 5);
        indicadorEquity = new Indicador("shareholdersEquity", "capitalTotal - totalLiabilities");
        indicadorRoe = new Indicador("roe", "2 * ingresoNeto");
        indicadorDeuda = new Indicador("debtEquityRatio", "totalLiabilities / shareholdersEquity");
        indicadorMargen = new Indicador("margen", "(ingresoNeto - 50 - 20 - 15) / ingresoNeto");

        condicionRoe = new CondicionBuilder()
                .periodoDeEvaluacion(1)
                .funcionParaObtenerValor(new ValoresDelPeriodo(indicadorRoe))
                .comparador(Comparador.MAYOR)
                .build();
        condicionDeuda = new CondicionBuilder()
                .periodoDeEvaluacion(1)
                .funcionParaObtenerValor(new ValoresDelPeriodo(indicadorDeuda))
                .comparador(Comparador.MENOR)
                .build();
        condicionMargen = new CondicionBuilder()//TODO: falta implementar consistencia
                .periodoDeEvaluacion(1)
                .funcionParaObtenerValor(new Consistencia(indicadorMargen))
                .comparador(Comparador.MAYOR_O_IGUAL)
//TODO: le tengo que pasar un valor?								.valor(valor)
                .build();
        condicionLongevidadPropia = new CondicionBuilder()
                .periodoDeEvaluacion(1)
                .funcionParaObtenerValor(new Longevidad())
                .comparador(Comparador.MAYOR_O_IGUAL)
                .valorContraElQueSeCompara(new BigDecimal(2))
                .build();
        condicionLongevidadComparativa = new CondicionBuilder()
                .periodoDeEvaluacion(1)
                .funcionParaObtenerValor(new Longevidad())
                .comparador(Comparador.MAYOR)
                .build();

        RepositorioDeEmpresas repositorioDeEmpresas = RepositorioDeEmpresas.getInstance();//base = new BaseDeDatos("");
        RepositorioDeIndicadores repositorioDeIndicadores = RepositorioDeIndicadores.getInstance();

        empresas = Arrays.asList(empresaUno, empresaDos, empresaTres);
        //repositorioDeEmpresas.guardarEmpresas(empresas);//base.setEmpresas(empresas);

        indicadores = Arrays.asList(indicadorEquity, indicadorRoe, indicadorDeuda, indicadorMargen);
        //repositorioDeIndicadores.guardarIndicadores(indicadores);
//        base.agregarIndicador(indicadorEquity);
//        base.agregarIndicador(indicadorRoe);
//        base.agregarIndicador(indicadorDeuda);
//        base.agregarIndicador(indicadorMargen);

    }

    @Test
    public void condicion1Buffet() {
        Assert.assertEquals(empresaTres, condicionRoe.analizar(empresas).get(0));
    }

    @Test
    public void condicion2Buffet() {
        Assert.assertEquals(empresaUno, condicionDeuda.analizar(empresas).get(0));
    }

    @Test
    public void condicion3Buffet() throws ParseException, TokenMgrError {

        Assert.assertEquals(empresaTres, condicionMargen.analizar(empresas).get(0));
    }

    @Test
    public void condicionLongevidadPropia() {
        Assert.assertTrue(condicionLongevidadPropia.analizar(empresas).contains(empresaTres));
    }

    @Test
    public void condicionLongevidadComparativa() {
        Assert.assertEquals(empresaTres, condicionLongevidadComparativa.analizar(empresas).get(0));
    }

    @Test
    public void metodologiaBuffet() throws ParseException, TokenMgrError {
        metodologiaBuffet = new Metodologia("Buffet", Arrays.asList(condicionMargen, condicionRoe, condicionDeuda, condicionLongevidadPropia, condicionLongevidadComparativa));

        Assert.assertEquals(empresaTres, metodologiaBuffet.aplicarCondiciones(Arrays.asList(empresaUno, empresaDos, empresaTres)).get(0));
    }
}
