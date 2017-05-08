package model;

import java.io.File;
import java.io.FileNotFoundException;
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

		try {
			String content;
			Scanner scanner = new Scanner(new File(this.path));
			content = scanner.useDelimiter("\\Z").next();
			this.empresas = new Gson().fromJson(content, BaseDeDatos.class).getEmpresas();
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new UserException("No se encontro el archivo");
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw new JsonSyntaxException("El archivo leído no tiene un formato adecuado");
		} catch (JsonParseException e) {
			e.printStackTrace();
			throw new JsonParseException("El archivo leído no tiene un formato adecuado");
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
