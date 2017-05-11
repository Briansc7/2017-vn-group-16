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
public class ConsultarCuentasViewModel {

	private BaseDeDatos baseDeDatos;
	// private String path;

	private String nombreEmpresaElegida;
	private Empresa empresaElegida;
	private Integer periodoElegido;

	// private List<Empresa> empresas = Arrays.asList();
	private List<Integer> periodos;// = Arrays.asList();

	public ConsultarCuentasViewModel(String path) {

		this.baseDeDatos = new BaseDeDatos(path);
	}

	/*
	 * public void buscarEmpresa(){ //this.baseDeDatos = new BaseDeDatos(path);
	 * this.empresaElegida = baseDeDatos.empresaLlamada(nombreEmpresaElegida);
	 * this.periodoElegido = null; this.periodos =
	 * this.empresaElegida.getPeriodos(); }
	 */

	/*
	 * public void cargarEmpresas() { this.baseDeDatos = new BaseDeDatos();
	 * this.baseDeDatos.setPath(path); try { this.empresas =
	 * this.baseDeDatos.leerEmpresas(); } catch (IOException e) {
	 * e.getStackTrace(); }
	 * 
	 * }
	 */
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
		if (empresaElegida == null) {
			return periodos;
		} else {
			periodoElegido = null;
			periodos = this.empresaElegida.getPeriodos();
			return periodos;
		}
	}

	/*
	 * public List<Integer> getPeriodos() { return periodos; }
	 */

	@Dependencies("periodoElegido")
	public List<Cuenta> getCuentas() {
		if (periodoElegido == null) {
			return Arrays.asList();
		} else {
			return this.empresaElegida.cuentasDelPeriodo(this.periodoElegido);
		}
	}

	public Integer getPeriodoElegido() {
		return periodoElegido;
	}

	public void setPeriodoElegido(Integer periodoElegido) {
		this.periodoElegido = periodoElegido;
	}

	public Empresa getEmpresaElegida() {
		return empresaElegida;
	}

	public void setEmpresaElegida(Empresa empresaElegida) {
		this.empresaElegida = empresaElegida;
	}

}
