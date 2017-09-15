package view;

import java.io.IOException;

import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import model.Cuenta;
import model.Empresa;
import model.Metodologia;
import viewModel.ConsultarMetodologiasViewModel;

public class ConsultarMetodologiasView extends Dialog<ConsultarMetodologiasViewModel>{
	
	public ConsultarMetodologiasView(WindowOwner owner) throws IOException{		
		super(owner, new ConsultarMetodologiasViewModel());
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
		
		Table<Empresa> tablaDeEmpresas = new Table<Empresa>(mainPanel, Empresa.class);
		//listaEmpresas.setHeight(70);
		tablaDeEmpresas.setNumberVisibleRows(10);
		tablaDeEmpresas.bindItemsToProperty("empresas");
		
		Column<Empresa> columnaNombre= new Column<Empresa>(tablaDeEmpresas);
		columnaNombre.setTitle("Nombre");
		columnaNombre.setFixedSize(200);
		columnaNombre.bindContentsToProperty("nombre");
	}

}
