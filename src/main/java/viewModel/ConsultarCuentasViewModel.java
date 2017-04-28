package viewModel;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import model.ConsultarCuentasModel;
import model.Empresa;

@Observable
public class ConsultarCuentasViewModel {
	
	private ConsultarCuentasModel model;
	
	private double periodo;
	private Empresa empresaElegida;
	private List<Empresa> empresas;// = Arrays.asList("empresa 1", "empresa 2", "empresa 3", "empresa 4", "empresa 5");
	
	public void cargarEmpresas() {
		this.model  = new ConsultarCuentasModel();
		this.empresas = this.model.leerEmpresas();
		System.out.println(this.empresas.get(0).getCuentas().get(0).getValor());
	}
	
	public void consultar(){
		
	}
	
	public double getPeriodo() {
		return periodo;
	}
	
	public List<Empresa> getEmpresas() {
		return empresas;
	}
	
	public void setPeriodo(double periodo) {
		this.periodo = periodo;
	}
	
	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public Empresa getEmpresaElegida() {
		return empresaElegida;
	}

	public void setEmpresaElegida(Empresa empresaElegida) {
		this.empresaElegida = empresaElegida;
	}
	
	
}
