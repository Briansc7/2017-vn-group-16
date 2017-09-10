package viewModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import model.BaseDeDatos;

@Observable
public class PrincipalViewModel {

	private String path;

	public void verificarArchivo() throws NullPointerException, IOException {
		try {
			Scanner scanner = new Scanner(new File(this.path));
			scanner.close();
			BaseDeDatos baseDatos = new BaseDeDatos(this.getPath());
			baseDatos.leerEmpresas();
		} catch(FileNotFoundException e){
			e.printStackTrace();
			throw new FileNotFoundException("No se encontro el archivo");
		} catch(UserException e) {
			e.printStackTrace();
			throw new UserException("El archivo le�do no tiene un formato adecuado");
		} catch(NullPointerException e){
			e.printStackTrace();
			throw new NullPointerException("Por favor, seleccione un archivo v�lido");
		} catch(IOException e){
			throw new IOException("El archivo seleccionado no tiene contenido");
		}
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
