package view;

import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import viewModel.AgregarCuentasViewModel;

public class AgregarCuentasView extends Dialog<AgregarCuentasViewModel>{

	public AgregarCuentasView(WindowOwner owner) {
		super(owner, new AgregarCuentasViewModel());
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		// TODO Auto-generated method stub
		
	}

	

}
