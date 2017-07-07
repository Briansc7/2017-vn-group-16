package model;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

import dtos.PathFileTxtJson;
import utils.AppData;
import view.PrincipalView;

public class Ejecutable extends Application{
	
	public static void main(String[] args) {
		//Planilla.instance.agregarIndicador(new Indicador("a", "c.van + 100"));
		
		AppData.getInstance().setInicializacionMetodologias(new PathFileTxtJson("./Archivos del sistema/Metodologias.txt"));
		
		new Ejecutable().start();
		
	}
	
	@Override
	protected Window<?> createMainWindow() {
		return new PrincipalView(this);
	}
}

