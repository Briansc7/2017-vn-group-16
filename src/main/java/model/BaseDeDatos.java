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

import com.google.common.base.Function;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;



public class BaseDeDatos {

	private List<Empresa> empresas;
	private String path;

	public BaseDeDatos(String path) {
		this.path = path;
	}

	public List<Empresa> buscarEmpresas(String nombre) throws IOException {
		this.leerEmpresas();
		return this.empresas.stream()
				.filter(empresa -> empresa.getNombre().toUpperCase().contains(nombre.toUpperCase()))
				.collect(Collectors.toList());
	}

	public Empresa empresaLlamada(String nombre) throws IOException {
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

	public void leerEmpresas() throws IOException  {
		
		
		List<filaTabla> inputList = new ArrayList<filaTabla>();
		
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
		      // el map se usa para separar la primer fila en muchos elementos según la coma
		      inputList = br.lines().skip(1).map(linea->mapToItem(linea)).collect(Collectors.toList());
		      br.close();
		      
		    //muestra por consola la primer fila
		      System.out.println(inputList.get(0).getEmpresa());
		      System.out.println(inputList.get(0).getCuenta());
		      System.out.println(inputList.get(0).getValor());
		      System.out.println(inputList.get(0).getFecha());
		      System.out.println(inputList.size());//muestra la cantidad de filas transformadas a objetos
		      
		      //Lista de empresas
		      System.out.println(obtenerListaDeNombresDeEmpresas(inputList));
		      //Lista de cuentas de Facebook
		      System.out.println(obtenerNombresCuentasEmpresa(inputList,"Facebook"));
		      //Valores de la cuenta Ebitda de Facebook
		      System.out.println(obtenerValoresDeEmpresaDeCuenta(inputList,"Facebook","Ebitda"));
			
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			throw new FileNotFoundException("No se encontro el archivo");
		} catch (UserException e) {
//			e.printStackTrace();
			throw new UserException("El archivo le�do no tiene un formato adecuado");
		}

	}
	
	public List<String> obtenerListaDeNombresDeEmpresas(List<filaTabla> tabla){
		return tabla.stream().map(fila->fila.getEmpresa()).distinct().collect(Collectors.toList());
	}
	
	public List<filaTabla> obtenerFilasDeEmpresa(List<filaTabla> tabla,String empresa){
		return tabla.stream().filter(fila->fila.getEmpresa().equals(empresa)).collect(Collectors.toList());
	}
	
	public List<filaTabla> obtenerFilasDeEmpresaDeCuenta(List<filaTabla> tabla,String empresa,String cuenta){
		return obtenerFilasDeEmpresa(tabla,empresa).stream().filter(fila->fila.getCuenta().equals(cuenta)).collect(Collectors.toList());
	}
	
	public List<String> obtenerNombresCuentasEmpresa(List<filaTabla> tabla,String empresa){
		return obtenerFilasDeEmpresa(tabla, empresa).stream().map(fila->fila.getCuenta()).distinct().collect(Collectors.toList());
	}
	
	public List<String> obtenerValoresDeEmpresaDeCuenta(List<filaTabla> tabla,String empresa,String cuenta){
		return obtenerFilasDeEmpresaDeCuenta(tabla,empresa,cuenta).stream().map(fila->fila.getValor()).collect(Collectors.toList());
	}
	
	
	private filaTabla mapToItem(String line){
		  String[] p = line.split(",");// Separa el string por las comas
		  filaTabla item = new filaTabla();
		  item.setEmpresa(p[0]);//p[0] es la primer columna
		  item.setCuenta(p[1]);
		  item.setValor(p[2]);
		  item.setFecha(p[3]);
		  return item;
		};

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
