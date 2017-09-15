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
	private Integer periodoElegido;//FIXME: Ver si se lo puede convertir a tipo Year
	
	private List<Empresa> empresas= new ArrayList<Empresa>();
	private List<Indicador> indicadores= new ArrayList<Indicador>();
	
	private List<Integer> periodos = Arrays.asList();

	public ConsultarCuentasViewModel() throws IOException{
		this.periodoElegido = 0;
		RepositorioDeEmpresas repositorioDeEmpresas = RepositorioDeEmpresas.getInstance();
		this.setEmpresas(repositorioDeEmpresas.obtenerEmpresas());
		RepositorioDeIndicadores repositorioDeIndicadores = RepositorioDeIndicadores.getInstance();
		this.setIndicadores(repositorioDeIndicadores.obtenerIndicadores());
		
		//this.baseDeDatos = new BaseDeDatos(path);
		//this.baseDeDatos.leerEmpresas();
		//this.baseDeDatos.leerIndicadores();
	}
	
	public Action borrarCuentasLeidas(){
		//this.baseDeDatos.borrarEmpresas();
		//this.baseDeDatos.borrarIndicadores();
		this.empresas.clear();
		this.indicadores.clear();
		return null;
	}
//solo se acepta null si el framework lo devuelve. No usar nulls
	
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
			periodos = this.empresaElegida.getPeriodos();//empresa elegida es solo de la vista, no del modelo
			return periodos;
		}
	}

	@Dependencies("periodoElegido")
	public List<Cuenta> getCuentas() {
		if (this.periodoElegido == 0) {
			return null;
		//	return Arrays.asList();
		} else {
			return this.empresaElegida.cuentasDelPeriodo(this.periodoElegido);
		}
	}
	
	@Dependencies("periodoElegido")//debe ser un atributo para que pueda monitorear su cambio(mirar su get)
	public List<IndicadorAuxiliar> getIndicadores(){

		if (this.periodoElegido == 0) {
		//	return Arrays.asList();
			return null;
		} else {
			/*try {
			//this.planilla.borrarIndicadores();//FIXME:estas 2 lineas se habian agregado por no poner monitorear bien el cambio de periodo elegido
			this.baseDeDatos.leerIndicadores();		// al solucionar ese problema ya no va a ser necesario esto ni el try catch
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			List<Indicador> indicadoresReales = this.indicadores;
			List<IndicadorAuxiliar> indicadoresAuxiliares = new ArrayList<IndicadorAuxiliar>();
			//indicadoresReales.forEach(indicador -> indicadoresAuxiliares.add( new IndicadorAuxiliar(indicador.getNombre(), indicador.getValor(this.periodoElegido, this.empresaElegida, this.planilla))));
			//indicadoresReales.forEach(indicador -> indicadoresAuxiliares.add( new IndicadorAuxiliar(indicador.getNombre(), 111)));
			indicadoresReales.forEach(indicador -> indicadoresAuxiliares.add(new IndicadorAuxiliar(indicador.getNombre(), indicador.getValorString(this.periodoElegido, this.empresaElegida))));
			return indicadoresAuxiliares;
		}
	}
	
	public List<Empresa> buscarEmpresas(String nombre) /*throws IOException */{
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
//mensaje de error del parser debe ser entendible por el usuario, ej que diga error de sintaxis y si es posible de mas informacion
//que el parth del archivo indicadores.txt esté en un solo lugar
//problema aun guarda los indicadores agregados en la carpeta de archivos de prueba, 
//debe usar indicadores.txt de la carpeta archivos del sistema

//en vista consultar cuentas
//cuando en el text box no hay nada, poner en la lista de abajo por ejemplo las empresas mas consultadas
//la idea es que esa lista no esté vacía y se pueda aprovechar

//en la pantalla principal al elegir el archivo con las cuentas todo se ve exactamente igual a si no lo hubiera hecho
//lo ideal es seleccionar el archivo en una pantalla y luego me abra otra ventana donde pueda usarlo

//En la lista de indicadores se eligió no mostrar aquellos indicadores cuyas cuentas no tengan valores
//Pero en lugar de ocultar la información, es mejor mostrar todos los indicadores, y en la columna de valores
//mostrar por ejemplo un guion si el valor no esta disponible. Aun mejor seria mostrar ahí por qué no está disponible
//(no esta disponible por faltar el valor de tales cuentas). De la forma anterior el usuario no sabe si no se muestran
//los indicadores por no estar disponibles o por un error del sistema
