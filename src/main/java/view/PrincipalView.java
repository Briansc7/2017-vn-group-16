package view;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import viewModel.PrincipalViewModel;


public class PrincipalView extends SimpleWindow<PrincipalViewModel>{
	
	public PrincipalView(WindowOwner parent) {
		super(parent, new PrincipalViewModel());
	}
	
	//NotNullObservable elementSelected = new NotNullObservable("habilitarOpciones");
	
	@Override
	protected void addActions(Panel panelActions) {
		Button obtenerAsignaciones = new Button(panelActions)
		.setCaption("Consultar cuentas")
		.onClick(() -> this.consultarCuentas());
		
		
	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		
	}
	

	public void consultarCuentas(){
		Dialog<?> dialog = new ConsultarCuentasView(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}


}
