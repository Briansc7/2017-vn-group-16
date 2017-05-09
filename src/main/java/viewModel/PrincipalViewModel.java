package viewModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.uqbar.commons.utils.Observable;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import model.BaseDeDatos;

@Observable
public class PrincipalViewModel {

	private String path;

	public void verificarArchivo() throws FileNotFoundException, JsonSyntaxException, JsonParseException, NullPointerException {
		try {
			Scanner scanner = new Scanner(new File(this.path));
			scanner.close();
			BaseDeDatos baseDatos = new BaseDeDatos(this.getPath());
			baseDatos.leerEmpresas();
		} catch (FileNotFoundException e){
			e.printStackTrace();
			throw new FileNotFoundException ("No se encontro el archivo");
		}catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw new JsonSyntaxException("El archivo leído no tiene un formato adecuado");
		}catch (JsonParseException e) {
			e.printStackTrace();
			throw new JsonParseException("El archivo leído no tiene un formato adecuado");
		}catch (NullPointerException e){
			e.printStackTrace();
			throw new NullPointerException("Por favor, seleccione un archivo válido");
		}
			
	}
	
	public String getPath() {
		return path;//.replace("\\", "\\\\");
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
