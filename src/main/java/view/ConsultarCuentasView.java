package view;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import model.Cuenta;
import model.Empresa;
import viewModel.ConsultarCuentasViewModel;

public class ConsultarCuentasView extends Dialog<ConsultarCuentasViewModel>{
	
	public ConsultarCuentasView(WindowOwner owner, String path) {
		super(owner, new ConsultarCuentasViewModel(path));
		//this.getModelObject().cargarEmpresas();
	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Consultar cuentas");
		
	
		new Label(mainPanel).setText("Ingrese la empresa");
		new TextBox(mainPanel).bindValueToProperty("nombreEmpresaElegida");
		/*Selector<Empresa> selectorEmpresas = new Selector<Empresa>(mainPanel);
		selectorEmpresas.bindValueToProperty("empresaElegida");
		selectorEmpresas.bindItemsToProperty("empresas");*/
		
		new Button(mainPanel).setCaption("Buscar empresa").onClick(() -> this.getModelObject().buscarEmpresa());
		
		new Label(mainPanel).setText("Ingrese el periodo");
		Selector<Empresa> selectorPeriodo = new Selector<Empresa>(mainPanel);
		selectorPeriodo.bindValueToProperty("periodoElegido");
		selectorPeriodo.bindItemsToProperty("periodos");


		Table<Cuenta> tablaDeCuentas = new Table<Cuenta>(mainPanel, Cuenta.class);
		tablaDeCuentas.setNumberVisibleRows(20);
		tablaDeCuentas.bindItemsToProperty("cuentas");

		Column<Cuenta> columnaCuenta = new Column<Cuenta>(tablaDeCuentas);
		columnaCuenta.setTitle("Cuenta");
		columnaCuenta.setFixedSize(200);
		columnaCuenta.bindContentsToProperty("nombre");
		
		Column<Cuenta> columnaValor = new Column<Cuenta>(tablaDeCuentas);
		columnaValor.setTitle("Valor");
		columnaValor.setFixedSize(100);
		columnaValor.bindContentsToProperty("valor");
		
	}
	
	
}
