package view;

import java.io.IOException;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.WindowOwner;

import model.Planilla;

import org.uqbar.arena.windows.MessageBox.Type;

import parser.ParseException;
import parser.TokenMgrError;
import viewModel.AgregarIndicadorViewModel;

public class AgregarIndicadorView extends Dialog<AgregarIndicadorViewModel>{

	public AgregarIndicadorView(WindowOwner owner, Planilla unaPlanilla) {
		super(owner, new AgregarIndicadorViewModel(unaPlanilla));
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Cargar Indicador");
		
		new Label(mainPanel).setText("Ingrese el indicador");
		new TextBox(mainPanel).setWidth(250)
							.bindValueToProperty("indicador");
		
		Button agregar = new Button(mainPanel).setCaption("Agregar");
		agregar.onClick(()-> this.verificarIndicador());
	}
	
	public void verificarIndicador(){
		
		try {
			this.getModelObject().verificarIndicador();
			this.showErrorMessageBox("Indicador agregado", MessageBox.Type.Information);
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
