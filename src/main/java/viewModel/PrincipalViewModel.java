package viewModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.uqbar.commons.utils.Observable;

@Observable
public class PrincipalViewModel {

	private String path;

	public void verificarArchivo() throws FileNotFoundException {
		try {
			Scanner scanner = new Scanner(new File(this.path));
			scanner.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
			throw new FileNotFoundException ("No se encontro el archivo");
		}
			
	}
	
	public String getPath() {
		return path;//.replace("\\", "\\\\");
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
