package viewModel;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.utils.Dependencies;
import org.uqbar.commons.utils.Observable;
import org.uqbar.lacar.ui.model.Action;

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
		this.baseDeDatos.leerEmpresas();
		this.baseDeDatos.leerIndicadores();

	}
	
	public Action borrarCuentasLeidas(){
		this.baseDeDatos.borrarEmpresas();
		Planilla.instance.borrarIndicadores();
		return null;
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
		if (Planilla.instance.getEmpresaElegida() == null) {
			return periodos;	
		} else {
			Planilla.instance.setPeriodoElegido(null);
			periodos = Planilla.instance.getEmpresaElegida().getPeriodos();
			return periodos;
		}
	}


	@Dependencies("periodoElegido")
	public List<Cuenta> getCuentas() {
		if (Planilla.instance.getPeriodoElegido() == null) {
			return Arrays.asList();
		} else {

			return Planilla.instance.getEmpresaElegida().cuentasDelPeriodo(Planilla.instance.getPeriodoElegido());
		}
	}
	
	@Dependencies("periodoElegido")
	public List<Indicador> getIndicadores(){
		if (Planilla.instance.getPeriodoElegido() == null) {
			return Arrays.asList();
		} else {

			try {
			Planilla.instance.borrarIndicadores();
			this.baseDeDatos.leerIndicadores();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return Planilla.instance.indicadoresDelPeriodo();
			
		}
	}
	
	
	public Integer getPeriodoElegido() {
		return Planilla.instance.getPeriodoElegido();
	}

	public void setPeriodoElegido(Integer periodoElegido) {
		Planilla.instance.setPeriodoElegido(periodoElegido);
		this.periodoElegido = periodoElegido;
	}

	public Empresa getEmpresaElegida() {
		return Planilla.instance.getEmpresaElegida();
	}

	public void setEmpresaElegida(Empresa empresaElegida) {
		Planilla.instance.setEmpresaElegida(empresaElegida);
		Planilla.instance.setPeriodoElegido(null);
		this.empresaElegida = empresaElegida;
		this.periodoElegido = null;
	}

}
