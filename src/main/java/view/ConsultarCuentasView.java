package view;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import model.Cuenta;
import viewModel.ConsultarCuentasViewModel;

public class ConsultarCuentasView extends Dialog<ConsultarCuentasViewModel>{
	
	public ConsultarCuentasView(WindowOwner owner){
		super(owner, new ConsultarCuentasViewModel());
	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Consultar cuentas");
		
		new Label(mainPanel).setText("Ingrese la empresa");
		new TextBox(mainPanel).bindValueToProperty("empresa");
		
		new Label(mainPanel).setText("Ingrese un periodo");
		new TextBox(mainPanel).bindValueToProperty("periodo");
		
		new Button(mainPanel).setCaption("Consultar")
							.onClick(() -> this.getModelObject().consultar());
		
		Table<Cuenta> tablaDeCuentas = new Table<Cuenta>(mainPanel, Cuenta.class);
		tablaDeCuentas.setNumberVisibleRows(20);
		
		//tablaAsignaciones.bindItemsToProperty("asignaciones");
		
		Column<Cuenta> columnaCuenta = new Column<Cuenta>(tablaDeCuentas);
		columnaCuenta.setTitle("Cuenta");
		columnaCuenta.setFixedSize(100);
		columnaCuenta.bindContentsToProperty("nombre");

		Column<Cuenta> columnaValor = new Column<Cuenta>(tablaDeCuentas);
		columnaValor.setTitle("Valor");
		columnaValor.setFixedSize(100);
		columnaValor.bindContentsToProperty("valor");

	}
}
