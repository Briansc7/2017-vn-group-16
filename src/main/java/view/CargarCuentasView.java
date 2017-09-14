package view;

import org.uqbar.arena.windows.Dialog;
import java.io.IOException;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.FileSelector;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.MessageBox.Type;
import org.uqbar.arena.windows.WindowOwner;

import parser.ParseException;
import parser.TokenMgrError;

import viewModel.CargarCuentasViewModel;

public class CargarCuentasView extends Dialog<CargarCuentasViewModel>{
	public CargarCuentasView(WindowOwner owner) {
		super(owner, new CargarCuentasViewModel());
	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Cargar Cuentas");
		
		new Label(mainPanel).setText("Seleccione el archivo de cuentas").setWidth(250);
		new FileSelector(mainPanel).setCaption("Buscar archivo").bindValueToProperty("path");
	}
	
	@Override
	protected void addActions(Panel panelActions) {	
		new Button(panelActions)
		.setCaption("Cargar Cuentas")
		.onClick(() -> this.cargarCuentas());
	}

	public void cargarCuentas() {
		try {
			this.getModelObject().cargarCuentas();
			this.showErrorMessageBox("Archivo cargado", MessageBox.Type.Information);
		} catch (ParseException e) {
			e.printStackTrace();
			showErrorMessageBox(e.getMessage(), MessageBox.Type.Error);
		} catch (TokenMgrError e) {
			e.printStackTrace();
			showErrorMessageBox(e.getMessage(), MessageBox.Type.Error);
		} catch (IOException e) {
			e.printStackTrace();
			showErrorMessageBox("El archivo no existe", MessageBox.Type.Error);
		} catch (RuntimeException e) {
			e.printStackTrace();
			showErrorMessageBox(e.getMessage(), MessageBox.Type.Error);
		}
		
	}
	
	protected void showErrorMessageBox(String message, Type type) {
		MessageBox messageBox = new MessageBox(this, type);
		messageBox.setMessage(message);
		messageBox.open();
	}
	
}
