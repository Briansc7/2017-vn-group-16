package viewModel;

import org.uqbar.commons.utils.Observable;

@Observable
public class PrincipalViewModel {

	private String path;

	/*public void verificarArchivo() {
		try {
			Scanner scanner = new Scanner(new File(this.path));
			scanner.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
			throw new UserException("No se encontro el archivo");
		} 
			
	}*/
	
	public String getPath() {
		return path;//.replace("\\", "\\\\");
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
