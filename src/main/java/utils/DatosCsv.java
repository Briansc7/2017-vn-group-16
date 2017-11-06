package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import exceptions.ArchivoVacioException;
import model.Cuenta;
import model.Empresa;

public class DatosCsv {
	private List<Empresa> empresas = new ArrayList<>();
	private String path;

	public DatosCsv(String path) {
		this.path = path;

	}

	public Boolean existeEmpresa(String nombre) {
		return this.buscarEmpresa(nombre).isPresent();
	}

	public Optional<Empresa> buscarEmpresa(String nombre) {
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
			File inputFile = new File(this.path);
			InputStream inputStream = new FileInputStream(inputFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			// se salta la primera linea que tiene el encabezado
			// el forEach se usa para separar la primer fila en muchos elementos
			// segun la coma

			if(!br.ready()){
				br.close();
				throw new ArchivoVacioException("El archivo no tiene contenido");
			}

			br.lines().forEach(linea -> this.agregarDatosDeLinea(linea));

			br.close();

		} catch (FileNotFoundException e) {
			// e.printStackTrace();
			throw new FileNotFoundException("No se encontro el archivo");
		}

	}

	private void agregarDatosDeLinea(String linea){
		String[] p = linea.split(",");// Separa el string por las comas
		LocalDate fecha = LocalDate.parse(p[3].trim());

		if(this.existeEmpresa(p[0].trim())){
			this.buscarEmpresa(p[0].trim()).get().getCuentas().add(new Cuenta(p[1].trim(),
					new BigDecimal(p[2].trim()), fecha));
		} else {
			this.empresas.add(new Empresa(p[0].trim().toLowerCase(),
					Arrays.asList(new Cuenta(p[1].trim().toLowerCase(), new BigDecimal(p[2].trim()), fecha))));
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
	
}
