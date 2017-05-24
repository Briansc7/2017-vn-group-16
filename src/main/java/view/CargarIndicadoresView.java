package view;

import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import viewModel.CargarIndicadoresViewModel;

public class CargarIndicadoresView extends Dialog<CargarIndicadoresViewModel>{
	
	public CargarIndicadoresView(WindowOwner owner){
		super(owner, new CargarIndicadoresViewModel());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Cargar Indicadores");
		
	}

}
