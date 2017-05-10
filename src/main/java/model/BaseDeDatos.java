package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.uqbar.commons.model.UserException;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

public class BaseDeDatos {

	private List<Empresa> empresas;
	private String path;

	public BaseDeDatos(String path) {
		this.path = path;
	}

	public List<Empresa> buscarEmpresas(String nombre) {
		this.leerEmpresas();
		return this.empresas.stream()
				.filter(empresa -> empresa.getNombre().toUpperCase().contains(nombre.toUpperCase()))
				.collect(Collectors.toList());
	}

	public Empresa empresaLlamada(String nombre) {
		this.leerEmpresas();
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
		return this.empresas.stream().filter(empresa -> empresa.getNombre().equals(nombre)).findFirst();
	}

	public void leerEmpresas() {
		
		List<String> inputList = new ArrayList<String>();
		
		/*
		 * Se lee desde un archivo csv que debe tener las siguientes columnas:
		 * Empresa	Cuenta	Valor	Fecha
		 * 
		 * La primer fila va a tener dichos titulos por lo que se transforma en objetos a partir de la segunda fila
		 */

		try {/*
			String content;
			Scanner scanner = new Scanner(new File(this.path));
			content = scanner.useDelimiter("\\Z").next();
			this.empresas = new Gson().fromJson(content, BaseDeDatos.class).getEmpresas();
			scanner.close();*/
		      File inputF = new File(this.path);
		      InputStream inputFS = new FileInputStream(inputF);
		      BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
		      // se salta la primera linea que tiene el encabezado
		      // el map se debe usar para separar la primer fila en muchos elementos según la coma
		      inputList = br.lines().skip(1).map(a->a).collect(Collectors.toList());
		      br.close();
		      System.out.println(inputList.get(0));//muestra por consola la primer fila
		      System.out.println(inputList.size());//muestra la cantidad de filas transformadas a objetos
			
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			throw new UserException("No se encontro el archivo");
		} catch (JsonSyntaxException e) {
//			e.printStackTrace();
			throw new JsonSyntaxException("El archivo le�do no tiene un formato adecuado");
		} catch (JsonParseException e) {
//			e.printStackTrace();
			throw new JsonParseException("El archivo le�do no tiene un formato adecuado");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

}
