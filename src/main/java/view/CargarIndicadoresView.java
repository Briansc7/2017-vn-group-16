package view;

import org.uqbar.arena.widgets.FileSelector;
import org.uqbar.arena.widgets.Label;
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
		
		new Label(mainPanel).setText("Seleccione el archivo de indicadores").setWidth(250);
		new FileSelector(mainPanel).setCaption("Buscar archivo");//.bindValueToProperty("pathIndicadores");
		
	}

}
