package model;

import controllers.EmpresasController;
import controllers.HomeController;
import controllers.LoginController;
import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

import spark.template.handlebars.HandlebarsTemplateEngine;
import view.PrincipalView;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;

public class Ejecutable extends Application{
	
	public static void main(String[] args) {
		
		//new Ejecutable().start();
		EmpresasController empresasController = new EmpresasController();
		HomeController homeController = new HomeController();
		LoginController loginController = new LoginController();
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

		port(8080);

		staticFileLocation("/public");

		get("/", homeController::mostrar, engine);
		get("/login", loginController::mostrar, engine);
		get("/empresas", empresasController::listar, engine);
		get("/empresas/:id/periodos", empresasController::periododDe, engine);
		get("/empresas/:id/periodos/:periodo/cuentas", empresasController::cuentasDe, engine);

		post("/login", loginController::loguear, engine);
	}
	
	@Override
	protected Window<?> createMainWindow() {
		return new PrincipalView(this);
	}
}

