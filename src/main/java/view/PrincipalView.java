package view;


import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.FileSelector;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import model.Planilla;
import viewModel.PrincipalViewModel;


public class PrincipalView extends SimpleWindow<PrincipalViewModel> {
	
	Planilla planilla = new Planilla();
	
	public PrincipalView(WindowOwner parent) {
		super(parent, new PrincipalViewModel());
	}
	
	@Override
	protected void addActions(Panel panelActions) {
		new Button(panelActions)
		.setCaption("Consultar cuentas")
		.onClick(() -> this.consultarCuentas());
		
		new Button(panelActions)
		.setCaption("Cargar Indicadores")
		.onClick(() -> this.cargarIndicadores());
		
		new Button(panelActions)
		.setCaption("Agregar Indicador")
		.onClick(() -> this.agregarIndicador());

	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Menu principal");
		
		new Label(mainPanel).setText("Seleccione el archivo de cuentas").setWidth(250);
		new FileSelector(mainPanel).setCaption("Buscar archivo").bindValueToProperty("path");
	}
	

	public void consultarCuentas() {
		try{
			this.getModelObject().verificarArchivo();
			ConsultarCuentasView consultarCuentasView = new ConsultarCuentasView(this, this.getModelObject().getPath(), planilla);
			Dialog<?> dialog = new ConsultarCuentasView(this, this.getModelObject().getPath(), planilla);
			dialog.onCancel((consultarCuentasView.getModelObject()).borrarCuentasLeidas());
			dialog.open();
			dialog.onAccept(() -> {});
		}
		catch(Exception e){
			showErrorMessageBox(e.getMessage());
		}
		

	}
	
	protected void showErrorMessageBox(String message) {
		MessageBox messageBox = new MessageBox(this, MessageBox.Type.Error);
		messageBox.setMessage(message);
		messageBox.open();
	}
	
	public void cargarIndicadores(){
		Dialog<?> dialog = new CargarIndicadoresView(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}

	public void agregarIndicador() {
		Dialog<?> dialog = new AgregarIndicadorView(this, planilla);
		dialog.open();
		dialog.onAccept(() -> {});
	}

}
