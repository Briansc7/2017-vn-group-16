package view;

import java.io.IOException;

import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import model.Empresa;
import model.Metodologia;
import viewModel.ConsultarMetodologiasViewModel;

public class ConsultarMetodologiasView extends Dialog<ConsultarMetodologiasViewModel>{
	
	public ConsultarMetodologiasView(WindowOwner owner, String path) throws IOException{		
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
				
		
		new Label(mainPanel).setText("Empresas que cumplen con la metodología:");
		
		List<Empresa> listaEmpresas = new List<Empresa>(mainPanel);
		listaEmpresas.setHeight(70);
		listaEmpresas.bindItemsToProperty("empresas");
	}

}
