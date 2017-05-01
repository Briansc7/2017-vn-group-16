package model;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

public class BaseDeDatos {

	private List<Empresa> empresas;
	private String path;
	
	/*public BaseDeDatos(String path){
		this.path = path;
	}*/
	
	public List<Empresa> leerEmpresas() throws IOException {
		
		String content;
		Scanner scanner = new Scanner(new File(this.path));
		content = scanner.useDelimiter("\\Z").next();
		this.empresas = new Gson().fromJson(content, BaseDeDatos.class).getEmpresas();
		/*try {
			content = scanner.useDelimiter("\\Z").next();
			//content = new Scanner(new File("src/main/java/model/pruebagson.txt")).useDelimiter("\\Z").next();
			this.empresas = new Gson().fromJson(content, BaseDeDatos.class).getEmpresas();

		} catch (FileNotFoundException e){
			throw new UserException("Debe llenar algun dato");
		} finally {
			scanner.close();
		}*/
		scanner.close();
		return 	this.empresas;	
	}
	
	public List<Empresa> getEmpresas() {
		return empresas;
	}
	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	/*public String getPath() {
		return path;
	}*/

	public void setPath(String path) {
		this.path = path;
	}
	
}
