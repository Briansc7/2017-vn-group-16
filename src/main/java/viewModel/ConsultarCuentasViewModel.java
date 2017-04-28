package viewModel;

import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.model.ObservableObject;
import org.uqbar.commons.utils.Dependencies;
import org.uqbar.commons.utils.Observable;

import model.ConsultarCuentasModel;
import model.Cuenta;
import model.Empresa;

@Observable
public class ConsultarCuentasViewModel{// extends ObservableObject{
	
	private ConsultarCuentasModel model;
	private Boolean habilitar;
	
	private Empresa empresaElegida;
	private Integer periodoElegido;
	
	private List<Empresa> empresas;// = Arrays.asList();// = Arrays.asList("empresa 1", "empresa 2", "empresa 3", "empresa 4", "empresa 5");
	private List<Integer> periodos = Arrays.asList();
	
	public void cargarEmpresas() {
		this.model  = new ConsultarCuentasModel();
		this.empresas = this.model.leerEmpresas();
	}
	
	public void limpiarPeriodos(){
		periodos = Arrays.asList();
		//this.firePropertyChange("periodos", this.getPeriodos(), Arrays.asList());
		//this.setFieldValue("periodos", Arrays.asList());
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
		if(empresaElegida == null) {
			return periodos;
		} else{
			periodoElegido = null;
			periodos = this.empresaElegida.getPeriodos();
			return periodos;
		}
	}

	@Dependencies("periodoElegido")
	public List<Cuenta> getCuentas() {
		if(periodoElegido == null) {
			return Arrays.asList();
		} else{
			return this.empresaElegida.cuentasDelPeriodo(this.periodoElegido);
		}
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
