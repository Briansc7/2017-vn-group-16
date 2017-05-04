package view;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.FileSelector;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import viewModel.PrincipalViewModel;


public class PrincipalView extends SimpleWindow<PrincipalViewModel> {
	
	public PrincipalView(WindowOwner parent) {
		super(parent, new PrincipalViewModel());
	}
	
	@Override
	protected void addActions(Panel panelActions) {
		new Button(panelActions)
		.setCaption("Consultar cuentas")
		.onClick(() -> this.consultarCuentas());
		
		
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Menu principal");
		
		new Label(mainPanel).setText("Seleccione el archivo").setWidth(250);
		new FileSelector(mainPanel).setCaption("Buscar archivo").bindValueToProperty("path");
//		new TextBox(mainPanel).bindValueToProperty("path");
		
	}
	

	public void consultarCuentas() {
		Dialog<?> dialog = new ConsultarCuentasView(this, this.getModelObject().getPath());
		dialog.open();
		dialog.onAccept(() -> {});
	}


}
