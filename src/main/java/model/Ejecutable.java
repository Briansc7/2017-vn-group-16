package model;

import controllers.EmpresasController;
import controllers.HomeController;
import controllers.IndicadoresController;
import controllers.LoginController;
import controllers.MetodologiasController;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.*;
import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Ejecutable implements WithGlobalEntityManager{
	
	public static void main(String[] args) {
		
		
		
		EmpresasController empresasController = new EmpresasController();
		IndicadoresController indicadoresController = new IndicadoresController();
		HomeController homeController = new HomeController();
		LoginController loginController = new LoginController();
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		MetodologiasController metodologiasController = new MetodologiasController();
		
		port(8080);

		staticFileLocation("/public");
		
		before((req, res) -> { if (PerThreadEntityManagers.getEntityManager() != null) PerThreadEntityManagers.closeEntityManager(); });

		get("/", homeController::mostrar, engine);
		get("/login", loginController::mostrar, engine);
		get("/logout", loginController::logout, engine);
		get("/empresas", empresasController::listar, engine);
		get("/empresas/:id/periodos", empresasController::periododDe, engine);
		get("/empresas/:id/periodos/:periodo/cuentas", empresasController::atributosDe, engine);
		get("indicadores", indicadoresController::listar, engine);
		get("/indicadores/nuevo", indicadoresController::nuevo, engine);
		get("/metodologias", metodologiasController::listar, engine);
		get("/metodologias/:metodologia/empresas", metodologiasController::empresasDe, engine);
		
		post("/login", loginController::loguear, engine);
		post("/indicadores", indicadoresController::agregar, engine);
		
		// Acá se queda ejecutando el batch cada 30 segundos
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			  @Override
			  public void run() {
				  try {
			            Runtime.getRuntime().exec("./unBat/activa.bat");
			            System.out.println("Batch Ejecutado");
			        } catch (IOException e) {
			            e.printStackTrace();
			        }  
			  }
			}, 1*30*1000, 1*30*1000);
		
		
		
	}

}

