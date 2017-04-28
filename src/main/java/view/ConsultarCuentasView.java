package view;

import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import model.Cuenta;
import model.Empresa;
import viewModel.ConsultarCuentasViewModel;

public class ConsultarCuentasView extends Dialog<ConsultarCuentasViewModel>{
	
	public ConsultarCuentasView(WindowOwner owner) {
		super(owner, new ConsultarCuentasViewModel());
		this.getModelObject().cargarEmpresas();
	}
	
	//NotNullObservable observable = new NotNullObservable("habilitar");
	
	@Override
	protected void createMainTemplate(Panel mainPanel) {
		//this.setTaskDescription("Ingrese los parámetros de búsqueda");

		super.createMainTemplate(mainPanel);

		this.crearSelectorPeriodos(mainPanel);
		//this.crearTablaDeCuentas(mainPanel);
	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Consultar cuentas");
		
	
		new Label(mainPanel).setText("Ingrese la empresa");
		Selector<Empresa> selectorEmpresas = new Selector<Empresa>(mainPanel);
		//selectorEmpresas.allowNull(false);
		selectorEmpresas.bindValueToProperty("empresaElegida");
		selectorEmpresas.bindItemsToProperty("empresas");
		//selectorEmpresas.onSelection(() -> this.getModelObject().cargarPeriodos());
		
		
		//selectorPeriodo.bindEnabled(observable);

		
		/*new Button(mainPanel).setCaption("Consultar")
							.onClick(() -> this.getModelObject().consultar());
		*/
		/*Table<Cuenta> tablaDeCuentas = new Table<Cuenta>(mainPanel, Cuenta.class);
		tablaDeCuentas.setNumberVisibleRows(20);
		//tablaDeCuentas.bindEnabled(observable);
		
		tablaDeCuentas.bindItemsToProperty("cuentas");

		Column<Cuenta> columnaCuenta = new Column<Cuenta>(tablaDeCuentas);
		columnaCuenta.setTitle("Cuenta");
		columnaCuenta.setFixedSize(100);
		columnaCuenta.bindContentsToProperty("nombre");
		
		Column<Cuenta> columnaValor = new Column<Cuenta>(tablaDeCuentas);
		columnaValor.setTitle("Valor");
		columnaValor.setFixedSize(100);
		columnaValor.bindContentsToProperty("valor");*/
		
	}
	
	protected void crearSelectorPeriodos(Panel mainPanel) {
		new Label(mainPanel).setText("Ingrese el periodo");
		Selector<Empresa> selectorPeriodo = new Selector<Empresa>(mainPanel);
		//selectorPeriodo.allowNull(false);
		selectorPeriodo.bindValueToProperty("periodoElegido");
		selectorPeriodo.bindItemsToProperty("periodos");
		selectorPeriodo.onSelection(() -> this.getModelObject().cargarTabla());
	}
}
