package view;

import java.io.IOException;

import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.WindowOwner;

import model.Empresa;
import model.metodologia.Metodologia;

import org.uqbar.arena.windows.Dialog;
import viewModel.ConsultarMetodologiasViewModel;










public class ConsultarMetodologiasView extends Dialog<ConsultarMetodologiasViewModel>{
	
	public ConsultarMetodologiasView(WindowOwner owner, String path) throws IOException{
		//super();
		super(owner, new ConsultarMetodologiasViewModel(path));
	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Consultar Metodologias");
		
		new Label(mainPanel).setText("Ingrese la Metodología");
		
		new TextBox(mainPanel).bindValueToProperty("nombreMetodologiaElegida");
		
		List<Metodologia> listaMetodologias = new List<Metodologia>(mainPanel);
		listaMetodologias.setHeight(70);
		listaMetodologias.bindItemsToProperty("metodologias");
		listaMetodologias.bindValueToProperty("metodologiaElegida");
	}

}
