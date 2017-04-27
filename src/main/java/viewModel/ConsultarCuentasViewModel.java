package viewModel;

import org.uqbar.commons.utils.Observable;

@Observable
public class ConsultarCuentasViewModel {
	
	private String empresa;
	private double periodo;
	
	public void consultar(){
		
	}
	
	public String getEmpresa() {
		return empresa;
	}
	
	public double getPeriodo() {
		return periodo;
	}
	
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	public void setPeriodo(double periodo) {
		this.periodo = periodo;
	}
}
