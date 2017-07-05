package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.uqbar.commons.model.UserException;

import parser.ParseException;
import parser.TokenMgrError;

public class BaseDeDatos {

	private List<Empresa> empresas = new ArrayList<Empresa>();
	private List<Indicador> indicadores = new ArrayList<Indicador>();
	private String path;
	private String pathIndicadores = "./Archivos del sistema/indicadores.txt";
	
	public BaseDeDatos(String path) {
		this.path = path;
	}

	public List<Empresa> buscarEmpresas(String nombre) throws IOException {
		return this.empresas.stream()
				.filter(empresa -> empresa.getNombre().toUpperCase().contains(nombre.toUpperCase()))
				.collect(Collectors.toList());
	}

	public Empresa empresaLlamada(String nombre) throws IOException {
		if (this.existeEmpresa(nombre)) {
			return this.primerEmpresa(nombre).get();
		} else {
			throw new UserException("La empresa no existe");
		}
	}
	
	public Indicador buscarIndicador(String nombre) {
		if(!this.existeIndicador(nombre)){
			throw new RuntimeException("No existe el indicador");
		}
		return this.primerIndicador(nombre).get();
	}
	
	public Boolean existeEmpresa(String nombre) {
		return this.primerEmpresa(nombre).isPresent();
	}

	public Boolean existeIndicador(String nombre) {
		return this.primerIndicador(nombre).isPresent();
	}
	
	public Optional<Empresa> primerEmpresa(String nombre) {
		return this.empresas.stream().filter(empresa -> empresa.getNombre().equalsIgnoreCase(nombre)).findFirst();
	}

	public Optional<Indicador> primerIndicador(String nombre) {
		return this.indicadores.stream().filter(indicador -> indicador.getNombre().equalsIgnoreCase(nombre)).findFirst();
	}
	
	public void leerEmpresas() throws IOException {

		/*
		 * Se lee desde un archivo csv que debe tener las siguientes columnas:
		 * Empresa Cuenta Valor Fecha
		 * 
		 * La primer fila va a tener dichos titulos por lo que se transforma en
		 * objetos a partir de la segunda fila
		 */

		try {
			File inputF = new File(this.path);
			InputStream inputFS = new FileInputStream(inputF);
			BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
			// se salta la primera linea que tiene el encabezado
			// el forEach se usa para separar la primer fila en muchos elementos
			// segun la coma

			br.lines().skip(1).forEach(linea -> this.agregarDatosDeLinea(linea));
			
			br.close();

		} catch (FileNotFoundException e) {
			// e.printStackTrace();
			throw new FileNotFoundException("No se encontro el archivo");
		} catch (UserException e) {
			// e.printStackTrace();
			throw new UserException("El archivo leido no tiene un formato adecuado");
		}

	}
	
	
	private void agregarDatosDeLinea(String linea){
		String[] p = linea.split(",");// Separa el string por las comas
		LocalDate fecha = LocalDate.parse(p [3]);
		
		 if(this.existeEmpresa(p[0])){
		 this.primerEmpresa(p[0]).get().getCuentas().add(new Cuenta(p[1],
				 Integer.parseInt(p[2]), fecha));
		 }
		 else{
			 this.empresas.add(new Empresa(p[0], Arrays.asList(new Cuenta(p[1], Integer.parseInt(p[2]), fecha))));
		 }
	}

	//lector de indicadores
	public void leerIndicadores() throws IOException{
		try {
			File inputF = new File(this.pathIndicadores);
			InputStream inputFS = new FileInputStream(inputF);
			BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
			
			br.lines().forEach(linea -> {
				try {
					this.verificarIndicadorParaAgregar(linea);

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TokenMgrError e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			
			br.close();

		} catch (FileNotFoundException e) {
			// e.printStackTrace();
			throw new FileNotFoundException("No se encontro el archivo");
		} catch (UserException e) {
			// e.printStackTrace();
			throw new UserException("El archivo leido no tiene un formato adecuado");
		}
	}
		
	public void verificarIndicador(String indicador) throws IOException, ParseException, TokenMgrError{
		String[] partes = indicador.split("=");
		if(partes[1].toLowerCase().contains(partes[0].trim().toLowerCase())){
			 throw new RuntimeException("No se puede usar un indicador en su propia definicion");
		}
		this.agregarIndicadorAlArchivo(indicador);
	}
	
	public void verificarIndicadorParaAgregar(String indicador) throws ParseException, TokenMgrError{
		String[] partes = indicador.split("=");
		
		if(verificarSintaxisIndicador(partes[0].trim(), partes[1]) && verificarAusenteEnLista(partes[0].trim(), partes[1])){
			// REVISAR REMOVER SI HABIA OTRO INDICADOR CON EL MISMO NOMBRE
			//this.indicadores.removeIf(indicador -> indicador.tieneElMismoNombre(partes[0].trim()));
			this.indicadores.add(new Indicador(partes[0].trim(), partes[1]));
		} 
		//throw new RuntimeException("No se puede usar un indicador en su propia definicion");
	}
	
	// Verifica sintaxis = que el indicador no se llame a si mismo
		public boolean verificarSintaxisIndicador(String nombreIndicador, String contenidoFormula) throws ParseException, TokenMgrError{
			return !contenidoFormula.toLowerCase().contains(nombreIndicador.toLowerCase());
		}
		
		// Verifica que el indicador no haya sido cargado en la lista con la misma formula (Evito asi parsear N veces)
		public boolean verificarAusenteEnLista(String nombreIndicador, String contenidoFormula) throws ParseException, TokenMgrError{
			return this.indicadores.stream().filter(indicador -> indicador.esIdentico(nombreIndicador,contenidoFormula)).collect(Collectors.toList()).isEmpty();
		}
		
		public List<Empresa> getEmpresas() {
		return empresas;
	}
		
	public void agregarIndicadorAlArchivo(String indicador) throws IOException{
		File file = new File(this.pathIndicadores);
		Writer output = new BufferedWriter(new FileWriter(file, true));
		output.append(indicador + "\r\n");
		output.close();
	}
	
	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public void borrarEmpresas(){
		this.empresas.clear();
	}

	public void setPathIndicadores(String pathIndicadores) {
		this.pathIndicadores = pathIndicadores;
	}

	public List<Indicador> getIndicadores() {
		return indicadores;
	}

	public void agregarIndicador(Indicador indicador) {
		this.indicadores.add(indicador);
	}

	/*public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}*/
}
