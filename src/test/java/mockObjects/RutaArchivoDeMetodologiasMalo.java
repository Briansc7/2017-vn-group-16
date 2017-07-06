package mockObjects;

import dtos.PathFile;

public class RutaArchivoDeMetodologiasMalo implements PathFile{

	String path = "./Archivos de prueba/ArchivoJsonMalo.txt";
	
	public void setPathFile(String location) {
		path = location;
	}
	
	public String getPathFile() {
		return path;
	}
	
}