package viewModel;

import java.util.List;

import org.uqbar.commons.utils.Dependencies;
import org.uqbar.commons.utils.Observable;

import model.ConsultarCuentasModel;
import model.Cuenta;
import model.Empresa;

@Observable
public class ConsultarCuentasViewModel {
	
	private ConsultarCuentasModel model;
	private Boolean habilitar;
	
	private Empresa empresaElegida;
	private Integer periodoElegido;
	private List<Cuenta> cuentas;
	
	private List<Empresa> empresas;// = Arrays.asList("empresa 1", "empresa 2", "empresa 3", "empresa 4", "empresa 5");
	private List<Integer> periodos;
	
	public void cargarEmpresas() {
		this.model  = new ConsultarCuentasModel();
		this.empresas = this.model.leerEmpresas();
	}
	
	/*public void consultar(){
		
	}*/
	
	
	public void cargarPeriodos(){
		this.periodos = this.empresaElegida.getPeriodos();
	}
	
	/*@Dependencies("periodoElegido")
	public List<Cuenta> getCuentas(){
		return this.empresaElegida.cuentasDelPeriodo(this.periodoElegido);
	}*/
	
	public void cargarTabla(){
		this.cuentas = this.empresaElegida.cuentasDelPeriodo(this.periodoElegido);
		//System.out.println(this.cuentas);
	}
	
	public List<Empresa> getEmpresas() {
		return empresas;
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

	@Dependencies("empresaElegida")
	public List<Integer> getPeriodos() {
		return periodos;
	}
	
	
	public void setPeriodos(List<Integer> periodos) {
		this.periodos = periodos;
	}

	@Dependencies("periodoElegido")
	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public Integer getPeriodoElegido() {
		return periodoElegido;
	}

	public void setPeriodoElegido(Integer periodoElegido) {
		this.periodoElegido = periodoElegido;
	}

	public Boolean getHabilitar() {
		return habilitar;
	}
	
	
}
