package mockObjects;

import dtos.PathFile;

public class RutaArchivoDeMetodologiasParaGuardar implements PathFile{

	String path = "./Archivos de prueba/TestsDeGrabacionDeMetodologias.txt";
	
	public void setPathFile(String location) {
		path = location;
	}
	
	public String getPathFile() {
		return path;
	}
		
}