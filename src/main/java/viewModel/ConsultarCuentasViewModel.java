package viewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Dependencies;
import org.uqbar.commons.utils.Observable;
import org.uqbar.lacar.ui.model.Action;

import model.Cuenta;
import model.Empresa;
import model.Indicador;
import model.IndicadorAuxiliar;
import model.repositories.RepositorioDeEmpresas;
import model.repositories.RepositorioDeIndicadores;

@Observable
public class ConsultarCuentasViewModel {
	
	private String nombreEmpresaElegida = "";
	private Empresa empresaElegida;
	private Integer periodoElegido;
	
	private List<Empresa> empresas= new ArrayList<Empresa>();
	private List<Indicador> indicadores= new ArrayList<Indicador>();
	
	private List<Integer> periodos = Arrays.asList();

	public ConsultarCuentasViewModel() throws IOException{
		this.periodoElegido = 0;
		RepositorioDeEmpresas repositorioDeEmpresas = RepositorioDeEmpresas.getInstance();
		this.setEmpresas(repositorioDeEmpresas.obtenerEmpresas());
		RepositorioDeIndicadores repositorioDeIndicadores = RepositorioDeIndicadores.getInstance();
		this.setIndicadores(repositorioDeIndicadores.buscarTodos());
	}
	
	public Action borrarCuentasLeidas(){

		this.empresas.clear();
		this.indicadores.clear();
		return null;
	}

	
	@Dependencies("nombreEmpresaElegida")
	public List<Empresa> getEmpresas() {
			if (nombreEmpresaElegida.equals("")) {
				return this.buscarEmpresas("");
			} else {		
				return this.buscarEmpresas(nombreEmpresaElegida);
			}
	}

	@Dependencies("empresaElegida")
	public List<Integer> getPeriodos() {
		if (this.empresaElegida == null) {
			return periodos;	
		} else {
			periodos = this.empresaElegida.getPeriodos();
			return periodos;
		}
	}

	@Dependencies("periodoElegido")
	public List<Cuenta> getCuentas() {
		if (this.periodoElegido == 0) {
			return null;
		} else {
			return this.empresaElegida.cuentasDelPeriodo(this.periodoElegido);
		}
	}
	
	@Dependencies("periodoElegido")
	public List<IndicadorAuxiliar> getIndicadores(){

		if (this.periodoElegido == 0) {
			return null;
		} else {
			List<Indicador> indicadoresReales = this.indicadores;
			List<IndicadorAuxiliar> indicadoresAuxiliares = new ArrayList<IndicadorAuxiliar>();
			indicadoresReales.forEach(indicador -> indicadoresAuxiliares.add(new IndicadorAuxiliar(indicador.getNombre(), indicador.getValorString(this.periodoElegido, this.empresaElegida))));
			return indicadoresAuxiliares;
		}
	}
	
	public List<Empresa> buscarEmpresas(String nombre) {
		return this.empresas.stream()
				.filter(empresa -> empresa.getNombre().toUpperCase().contains(nombre.toUpperCase()))
				.collect(Collectors.toList());
	}
	
	public Integer getPeriodoElegido() {
		return this.periodoElegido;
	}

	public void setPeriodoElegido(Integer periodoElegido) {
		this.periodoElegido = periodoElegido;
	}

	public String getNombreEmpresaElegida() {
		return nombreEmpresaElegida;
	}

	public void setNombreEmpresaElegida(String nombreEmpresaElegida) {
		this.nombreEmpresaElegida = nombreEmpresaElegida;
	}

	public Empresa getEmpresaElegida() {
		return this.empresaElegida;
	}

	public void setEmpresaElegida(Empresa empresaElegida) {
		this.empresaElegida = empresaElegida;
		this.periodoElegido = 0;
	}
	
	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}
}
