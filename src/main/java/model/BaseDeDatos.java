package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.uqbar.commons.model.UserException;

public class BaseDeDatos {

	private List<Empresa> empresas = Arrays.asList();
	private String path;

	public BaseDeDatos(String path) throws IOException {
		this.path = path;
		this.leerEmpresas();
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
			// el map se usa para separar la primer fila en muchos elementos
			// segun la coma
			List<Empresa> listaMapeada = br.lines().skip(1).map((String linea) -> this.mapToEmpresa(linea))
					.collect(Collectors.toList());
			this.empresas = this.fusionarEmpresasDuplicadas(listaMapeada);

			br.close();

		} catch (FileNotFoundException e) {
			// e.printStackTrace();
			throw new FileNotFoundException("No se encontro el archivo");
		} catch (UserException e) {
			// e.printStackTrace();
			throw new UserException("El archivo leido no tiene un formato adecuado");
		}

	}

	private List<Empresa> fusionarEmpresasDuplicadas(List<Empresa> listaMapeada) {
//		for (int i = 0; i < listaMapeada.size(); i++) {
//			for (int j = 0; j < i; j++) {
//				if (listaMapeada.get(j).getNombre().equals(listaMapeada.get(i).getNombre())) {
//					System.out.println(listaMapeada.get(j).getCuentas().size());
//					System.out.println(listaMapeada.get(i).getCuentas().size());
//					listaMapeada.get(i).agregarCuentas(listaMapeada.get(j).getCuentas());
//					listaMapeada.remove(j);
//					i--;
//					j--;
//				}
//			}
//
//		}
		return listaMapeada;

	}

	/*
	 * en la linea 68 habiamos intentado hacer un forEach en vez de un map y que
	 * este metodo que sea void y ejecute el if comentado, pero nos tira
	 * "argument cannot be null" en add(o eso creemos)
	 */
	private Empresa mapToEmpresa(String line) {

		String[] p = line.split(",");// Separa el string por las comas

		// if(this.empresas.isEmpty()||this.existeEmpresa(p[0]))
		// {
		// empresaLlamada(p[0]).getCuentas().add(new Cuenta(p[1],
		// Integer.parseInt(p[2]), p[3]));
		// return empresaLlamada(p[0]);
		// }
		return new Empresa(p[0], Arrays.asList(new Cuenta(p[1], Integer.parseInt(p[2]), p[3])));
		/*
		 * if (this.empresas.isEmpty() || !this.existeEmpresa(p[0])) {
		 * this.empresas.add(new Empresa(p[0], new
		 * ArrayList<Cuenta>(Arrays.asList(cuenta)))); } else {
		 * this.primero(p[0]).get().getCuentas().add(cuenta); }
		 */
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
