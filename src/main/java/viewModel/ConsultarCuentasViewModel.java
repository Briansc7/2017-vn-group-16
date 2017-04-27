package viewModel;

import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.utils.Observable;
import model.Empresa;

@Observable
public class ConsultarCuentasViewModel {
	
	private String empresa;
	private double periodo;
	
	private List<String> empresas = Arrays.asList("empresa 1", "empresa 2", "empresa 3", "empresa 4", "empresa 5");
	
	
	public void consultar(){
		
	}
	
	public String getEmpresa() {
		return empresa;
	}
	
	public double getPeriodo() {
		return periodo;
	}
	
	public List<String> getEmpresas() {
		return empresas;
	}
	
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	public void setPeriodo(double periodo) {
		this.periodo = periodo;
	}
	
	public void setEmpresas(List<String> empresas) {
		this.empresas = empresas;
	}
}
