package mockObjects;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import model.BaseDeDatos;
import model.Cuenta;
import model.Empresa;
import model.Indicador;
import parser.ParseException;
import parser.TokenMgrError;

public class BaseDeDatosMock {
	private static Empresa empresaUno = new Empresa("Facebook", Arrays.asList(new Cuenta("ingresoNeto", new BigDecimal(15), LocalDate.parse("2017-05-10")), 
			new Cuenta("capitalTotal", new BigDecimal(30), LocalDate.parse("2017-05-10")),
			new Cuenta("totalLiabilities", new BigDecimal(40), LocalDate.parse("2017-05-10")),
			new Cuenta("deuda", new BigDecimal(7), LocalDate.parse("2017-05-10"))));
	private static Empresa empresaDos = new Empresa("twitter", Arrays.asList(new Cuenta("ingresoNeto", new BigDecimal(10), LocalDate.parse("2017-05-10")),
			new Cuenta("capitalTotal", new BigDecimal(20), LocalDate.parse("2017-05-10")),
			new Cuenta("totalLiabilities", new BigDecimal(30), LocalDate.parse("2017-05-10")),
			new Cuenta("deuda", new BigDecimal(5), LocalDate.parse("2016-05-10"))));
	private static Empresa empresaTres = new Empresa("google", Arrays.asList(new Cuenta("ingresoNeto", new BigDecimal(100), LocalDate.parse("2017-05-10")),
			new Cuenta("capitalTotal", new BigDecimal(200), LocalDate.parse("2017-05-10")),
			new Cuenta("totalLiabilities", new BigDecimal(300), LocalDate.parse("2017-05-10")),
			new Cuenta("deuda", new BigDecimal(10), LocalDate.parse("2015-05-10"))));

	private static Indicador indicadorRoe;
	private static Indicador indicadorMargen;
	private static Indicador indicadorDeuda;
	private static Indicador indicadorEquity;
	
	private static BaseDeDatos setUp(){
		
		BaseDeDatos databaseInstance;
		
		try {
			indicadorEquity = new Indicador("shareholdersEquity", "capitalTotal - totalLiabilities");
			indicadorRoe = new Indicador("roe", "2 * ingresoNeto");
			indicadorDeuda = new Indicador("debtEquityRatio", "totalLiabilities / shareholdersEquity");
			indicadorMargen = new Indicador("margen", "(ingresoNeto - 50 - 20 - 15) / ingresoNeto");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TokenMgrError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		databaseInstance = new BaseDeDatos("");
		databaseInstance.setEmpresas(Arrays.asList(empresaUno, empresaDos, empresaTres));
		databaseInstance.agregarIndicador(indicadorEquity);
		databaseInstance.agregarIndicador(indicadorRoe);
		databaseInstance.agregarIndicador(indicadorDeuda);
		databaseInstance.agregarIndicador(indicadorMargen);
		
		return databaseInstance;
	}
	
	public static BaseDeDatos getDatabaseInstance(){
		return setUp();
	}
}
