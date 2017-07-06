package mockObjects;

import dtos.PathFile;

public class RutaArchivoDeMetodologiasBueno implements PathFile{

	String path = "./Archivos de prueba/ArchivoDeMetodologiasBueno.txt";
	
	public void setPathFile(String location) {
		path = location;
	}
	
	public String getPathFile() {
		return path;
	}
	
}