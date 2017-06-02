package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.uqbar.commons.model.UserException;

import parser.ParseException;
import parser.TokenMgrError;

public class BaseDeDatos {

	private List<Empresa> empresas = new ArrayList<Empresa>();//Arrays.asList();
	private String path;
	private String pathIndicadores = "./Archivos de prueba/indicadores.txt";

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
			return this.primero(nombre).get();
		} else {
			throw new UserException("La empresa no existe");
		}

	}

	public Boolean existeEmpresa(String nombre) {
		return this.primero(nombre).isPresent();
	}

	public Optional<Empresa> primero(String nombre) {
		return this.empresas.stream().filter(empresa -> empresa.getNombre().equalsIgnoreCase(nombre)).findFirst();
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
		
		 if(this.existeEmpresa(p[0])){
		 this.primero(p[0]).get().getCuentas().add(new Cuenta(p[1],
				 Integer.parseInt(p[2]), p[3]));
		 }
		 else{
			 this.empresas.add(new Empresa(p[0], Arrays.asList(new Cuenta(p[1], Integer.parseInt(p[2]), p[3]))));
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
					Planilla.instance.verificarSintaxisIndicador(linea);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TokenMgrError e) {
					// TODO Auto-generated catch block
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
		
	public List<Empresa> getEmpresas() {
		return empresas;
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
}
