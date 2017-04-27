package viewModel;

import java.util.List;

import org.uqbar.commons.utils.Observable;
import model.Empresa;

@Observable
public class ConsultarCuentasViewModel {
	
	private String empresa;
	private double periodo;
	
	private List<Empresa> empresas;
	
	public void consultar(){
		
	}
	
	public String getEmpresa() {
		return empresa;
	}
	
	public double getPeriodo() {
		return periodo;
	}
	
	public List<Empresa> getEmpresas() {
		return empresas;
	}
	
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	public void setPeriodo(double periodo) {
		this.periodo = periodo;
	}
	
	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
}
