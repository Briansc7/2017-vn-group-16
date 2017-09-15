package model;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

import dtos.PathFileTxtJson;
import utils.AppData;
import view.PrincipalView;

public class Ejecutable extends Application{
	
	public static void main(String[] args) {
		
		new Ejecutable().start();
		
	}
	
	@Override
	protected Window<?> createMainWindow() {
		return new PrincipalView(this);
	}
}

