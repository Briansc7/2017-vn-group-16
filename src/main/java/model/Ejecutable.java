package model;

import controllers.EmpresasController;
import controllers.HomeController;
import controllers.IndicadoresController;
import controllers.LoginController;
import exceptions.FormulaIncorrectaException;
import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import spark.template.handlebars.HandlebarsTemplateEngine;
import view.PrincipalView;

import javax.security.auth.login.LoginException;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;

public class Ejecutable extends Application implements WithGlobalEntityManager{
	
	public static void main(String[] args) {
		
		//new Ejecutable().start();
		EmpresasController empresasController = new EmpresasController();
		IndicadoresController indicadoresController = new IndicadoresController();
		HomeController homeController = new HomeController();
		LoginController loginController = new LoginController();
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

		port(8080);

		staticFileLocation("/public");

		get("/", homeController::mostrar, engine);
		get("/login", loginController::mostrar, engine);
		get("/empresas", empresasController::listar, engine);
		get("/empresas/:id/periodos", empresasController::periododDe, engine);
		get("/empresas/:id/periodos/:periodo/cuentas", empresasController::atributosDe, engine);
		get("/indicadores/nuevo", indicadoresController::nuevo, engine);
		get("/logout", loginController::logout, engine);

		get("/formulaIncorrecta", (request, response) -> {
			throw new FormulaIncorrectaException("La formula no esta bien escrita");
		});

		post("/login", loginController::loguear, engine);
		post("/indicadores", indicadoresController::agregar, engine);

		exception(FormulaIncorrectaException.class, (e, request, response) -> {
			response.status(400);
			response.body(e.getMessage());
		});
	}

	@Override
	protected Window<?> createMainWindow() {
		return new PrincipalView(this);
	}
}

