package view;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.WindowOwner;

import viewModel.AgregarIndicadorViewModel;

public class AgregarIndicadorView extends Dialog<AgregarIndicadorViewModel>{

	public AgregarIndicadorView(WindowOwner owner) {
		super(owner, new AgregarIndicadorViewModel());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Cargar Indicador");
		
		new Label(mainPanel).setText("Ingrese el indicador");
		new TextBox(mainPanel).setWidth(250)
							.bindValueToProperty("indicador");
		
		Button agregar = new Button(mainPanel).setCaption("Agregar");
		agregar.onClick(()->{this.getModelObject().verificarIndicador();
							this.showMensaje("Indicador agregado");
		});
	}
	
	protected void showMensaje(String message) {
		MessageBox messageBox = new MessageBox(this, MessageBox.Type.Information);
		messageBox.setMessage(message);
		messageBox.open();

	}
}
