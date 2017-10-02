package model;

import controllers.EmpresasController;
import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

import spark.template.handlebars.HandlebarsTemplateEngine;
import view.PrincipalView;

import static spark.Spark.get;
import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;

public class Ejecutable extends Application{
	
	public static void main(String[] args) {
		
		//new Ejecutable().start();
		EmpresasController empresas = new EmpresasController();
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

		port(8080);

		staticFileLocation("/public");

		get("/empresas", empresas::listar, engine);
		get("/empresas/:id/periodos", empresas::periododDe, engine);
	}
	
	@Override
	protected Window<?> createMainWindow() {
		return new PrincipalView(this);
	}
}

