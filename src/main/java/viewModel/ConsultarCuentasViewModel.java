package viewModel;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.utils.Dependencies;
import org.uqbar.commons.utils.Observable;

import model.BaseDeDatos;
import model.Cuenta;
import model.Empresa;

@Observable
public class ConsultarCuentasViewModel{
	
	private BaseDeDatos model;
	private String path;
	
	private Empresa empresaElegida;
	private Integer periodoElegido;
	
	private List<Empresa> empresas;
	private List<Integer> periodos;// = Arrays.asList();
	
	public ConsultarCuentasViewModel(String path){
		this.path = path;
	}
	
	public void cargarEmpresas() {
		this.model = new BaseDeDatos();
		this.model.setPath(path);
		try {
			this.empresas = this.model.leerEmpresas();
		} catch (IOException  e) {
			e.getStackTrace();
		}
		
	}
	
	public List<Empresa> getEmpresas() {
		return empresas;
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

	
}
