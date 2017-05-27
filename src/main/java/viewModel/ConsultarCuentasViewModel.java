package viewModel;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.utils.Dependencies;
import org.uqbar.commons.utils.Observable;


import model.BaseDeDatos;
import model.Cuenta;
import model.Empresa;
import model.Indicador;
import model.Planilla;

@Observable
public class ConsultarCuentasViewModel {

	private BaseDeDatos baseDeDatos;
	
	private String nombreEmpresaElegida;
	private Empresa empresaElegida;
	private Integer periodoElegido;

	private List<Integer> periodos = Arrays.asList();

	public ConsultarCuentasViewModel(String path) throws IOException{

		this.baseDeDatos = new BaseDeDatos(path);
	}

	
	@Dependencies("nombreEmpresaElegida")
	public List<Empresa> getEmpresas() throws IOException {

			if (nombreEmpresaElegida == null || nombreEmpresaElegida.equals("")) {
				return Arrays.asList();
			} else {
				return baseDeDatos.buscarEmpresas(nombreEmpresaElegida);
			}

	}

	public String getNombreEmpresaElegida() {
		return nombreEmpresaElegida;
	}

	public void setNombreEmpresaElegida(String nombreEmpresaElegida) {
		this.nombreEmpresaElegida = nombreEmpresaElegida;
	}

	@Dependencies("empresaElegida")
	public List<Integer> getPeriodos() {
		if (this.empresaElegida == null) {
			return periodos;
		} else {
			this.periodoElegido = null;
			System.out.println("hola");
			periodos = this.empresaElegida.getPeriodos();
			return periodos;
		}
	}


	@Dependencies("periodoElegido")
	public List<Cuenta> getCuentas() {
		if (this.periodoElegido == null) {
			return Arrays.asList();
		} else {
			return this.empresaElegida.cuentasDelPeriodo(this.periodoElegido);
		}
	}
	
	public List<Indicador> getIndicadores(){
		return Planilla.instance.getIndicadores();
	}
	
	
	public Integer getPeriodoElegido() {
		return periodoElegido;
	}

	public void setPeriodoElegido(Integer periodoElegido) {
		this.periodoElegido = periodoElegido;
	}

	public Empresa getEmpresaElegida() {
		return this.empresaElegida;
	}

	public void setEmpresaElegida(Empresa empresaElegida) {
		this.empresaElegida = empresaElegida;
	}

}
