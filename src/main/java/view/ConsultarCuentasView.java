package view;


import java.io.IOException;

import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import model.Cuenta;
import model.Empresa;
import model.Indicador;
import viewModel.ConsultarCuentasViewModel;

public class ConsultarCuentasView extends Dialog<ConsultarCuentasViewModel>{
	
	public ConsultarCuentasView(WindowOwner owner, String path) throws IOException{
		super(owner, new ConsultarCuentasViewModel(path));
	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Consultar cuentas");
		
	
		new Label(mainPanel).setText("Ingrese la empresa");
		new TextBox(mainPanel).bindValueToProperty("nombreEmpresaElegida");
		
		List<Empresa> listaEmpresas = new List<Empresa>(mainPanel);
		listaEmpresas.bindItemsToProperty("empresas");
		listaEmpresas.bindValueToProperty("empresaElegida");
		
		new Label(mainPanel).setText("Ingrese el periodo");
		Selector<Empresa> selectorPeriodo = new Selector<Empresa>(mainPanel);
		selectorPeriodo.bindValueToProperty("periodoElegido");
		selectorPeriodo.bindItemsToProperty("periodos");


		Table<Cuenta> tablaDeCuentas = new Table<Cuenta>(mainPanel, Cuenta.class);
		tablaDeCuentas.setNumberVisibleRows(10);
		tablaDeCuentas.bindItemsToProperty("cuentas");

		Column<Cuenta> columnaCuenta = new Column<Cuenta>(tablaDeCuentas);
		columnaCuenta.setTitle("Cuenta");
		columnaCuenta.setFixedSize(200);
		columnaCuenta.bindContentsToProperty("nombre");
		
		Column<Cuenta> columnaValor = new Column<Cuenta>(tablaDeCuentas);
		columnaValor.setTitle("Valor");
		columnaValor.setFixedSize(100);
		columnaValor.bindContentsToProperty("valor");
		
		
		//tabla de indicadores
		Table<Indicador> tablaDeIndicadores = new Table<Indicador>(mainPanel, Indicador.class);
		tablaDeIndicadores.setNumberVisibleRows(10);
		tablaDeIndicadores.bindItemsToProperty("indicadores");

		Column<Indicador> columnaIndicador = new Column<Indicador>(tablaDeIndicadores);
		columnaIndicador.setTitle("Indicador");
		columnaIndicador.setFixedSize(200);
		columnaIndicador.bindContentsToProperty("nombre");
		
		Column<Indicador> columnaValorIndicador = new Column<Indicador>(tablaDeIndicadores);
		columnaValorIndicador.setTitle("Valor");
		columnaValorIndicador.setFixedSize(100);
		columnaValorIndicador.bindContentsToProperty("valor");
	}
	
	
	
}
