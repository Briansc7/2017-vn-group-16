package view;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.CheckBox;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.MessageBox.Type;
import org.uqbar.arena.windows.WindowOwner;

import exceptions.CondicionIncompletaException;
import exceptions.MetodologiaIncompletaException;
import exceptions.MetodologiaSinNombreException;
import model.Indicador;
import model.metodologia.Condicion;
import model.metodologia.condiciones.BooleanCondition;
import viewModel.AgregarMetodologiaViewModel;

public class AgregarMetodologiaView extends Dialog<AgregarMetodologiaViewModel>{

	public AgregarMetodologiaView(WindowOwner owner) {
		super(owner, new AgregarMetodologiaViewModel());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		
		this.setTitle("Agregar Metodologia");
		
		new Label(mainPanel).setText("Alta de condici�n").setHeight(50);
		
		Panel tabla = new Panel(mainPanel);
		tabla.setLayout(new ColumnLayout(5));
		
		new Label(tabla).setText("Periodo").setWidth(120);
		new Label(tabla).setText("Indicador").setWidth(120);
		new Label(tabla).setText("Criterio").setWidth(120);
		new Label(tabla).setText("Comparar \ncontra tipo").setHeight(60).setWidth(120);
		new Label(tabla).setText("Valor a \ncomparar").setHeight(60).setWidth(120);
		
	    new TextBox(tabla).setWidth(80).bindValueToProperty("periodo");
	    Selector<String> selectorIndicadores = new Selector<String>(tabla);
	    selectorIndicadores.bindItemsToProperty("indicadores");
	    selectorIndicadores.bindValueToProperty("indicador");
	    
	    
	    Selector<BooleanCondition> selectorCriterios = new Selector<BooleanCondition>(tabla);
	    selectorCriterios.bindItemsToProperty("criterios");
	    selectorCriterios.bindValueToProperty("criterio");
	    
	    Selector<String> selectorTipos = new Selector<String>(tabla);
	    selectorTipos.bindItemsToProperty("tiposParaComparar");
	    selectorTipos.bindValueToProperty("tipoAComparar");

	    
	    new TextBox(tabla).setWidth(80).bindValueToProperty("valorAComparar");
	    
	    new Button(mainPanel).setCaption("Agregar condicion").onClick(() -> this.agregarCondicion());
	    
	    new Label(mainPanel).setText("Alta de metodologia").setHeight(50);
	    
	    new Label(mainPanel).setText("Ingrese el nombre de la metodologia");
	    new TextBox(mainPanel).setWidth(120).bindValueToProperty("nombre");
	    
	    //new Table<Condicion>(mainPanel, Condicion.class).setNumberVisibleRows(1);
	    
	    new Button(mainPanel).setCaption("Agregar metodologia").onClick(() -> this.agregarMetodologia());
	    
	}
	
	public void agregarCondicion() {
		
		try{
			
			this.getModelObject().agregarCondicion();
			this.showErrorMessageBox("Condicion agregada", MessageBox.Type.Information);
			
		} catch (CondicionIncompletaException e) {
			
			e.printStackTrace();
			showErrorMessageBox("La condicion no esta completa.", MessageBox.Type.Error);
			
		}
		
	}
	
	public void agregarMetodologia() {
		
		try{
			
			this.getModelObject().agregarMetodologia();
			this.showErrorMessageBox("Metodologia agregada", MessageBox.Type.Information);
			
		} catch (MetodologiaIncompletaException e) {
			
			e.printStackTrace();
			showErrorMessageBox("La metodologia no tiene condiciones.", MessageBox.Type.Error);
			
		} catch (MetodologiaSinNombreException e) {
			
			e.printStackTrace();
			showErrorMessageBox("Asigne un nombre a la metodologia.", MessageBox.Type.Error);
			
		}
		
	}
	
	protected void showErrorMessageBox(String message, Type type) {
		MessageBox messageBox = new MessageBox(this, type);
		messageBox.setMessage(message);
		messageBox.open();
	}
}
