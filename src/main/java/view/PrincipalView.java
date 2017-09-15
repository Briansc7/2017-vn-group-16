package view;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import parser.ParseException;
import parser.TokenMgrError;
import viewModel.PrincipalViewModel;

public class PrincipalView extends SimpleWindow<PrincipalViewModel> {
	public PrincipalView(WindowOwner parent) {
		super(parent, new PrincipalViewModel());
	}
	
	@Override
	protected void addActions(Panel panelActions) {
		
		new Button(panelActions)
		.setCaption("Cargar Cuentas")
		.onClick(() -> this.cargarCuentas());
		
		new Button(panelActions)
		.setCaption("Consultar cuentas")
		.onClick(() -> this.consultarCuentas());
		
		new Button(panelActions)
		.setCaption("Agregar Indicador")
		.onClick(() -> this.agregarIndicador());
				
		new Button(panelActions)
		.setCaption("Consultar Metodologias")
		.onClick(() -> this.consultarMetodologias());
		
		new Button(panelActions)
		.setCaption("Definir Nueva Metodologia")
		.onClick(() -> this.agregarMetodologia());
			
	}
	
	

	private void consultarMetodologias() {
		try{
			Dialog<?> dialog = new ConsultarMetodologiasView(this);
			dialog.open();
			dialog.onAccept(() -> {});
		}
		catch(Exception e){
			showErrorMessageBox(e.getMessage());
		}
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Sistema de Inversiones");
	}
	
	public void consultarCuentas() {
		try{
			ConsultarCuentasView consultarCuentasView = new ConsultarCuentasView(this);
			Dialog<?> dialog = new ConsultarCuentasView(this);
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

	public void agregarIndicador() {
		Dialog<?> dialog = new AgregarIndicadorView(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	
	public void agregarMetodologia() {
		Dialog<?> dialog;
		try {
			dialog = new AgregarMetodologiaView(this);
			dialog.open();
			dialog.onAccept(() -> {});
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException("El indicador esta mal definido");
		} catch (TokenMgrError e) {
			e.printStackTrace();
			throw new RuntimeException("El indicador esta mal definido");
		}
	}
	
	public void cargarCuentas() {
		Dialog<?> dialog = new CargarCuentasView(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	
}
