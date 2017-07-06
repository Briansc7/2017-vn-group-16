package utils;

import java.util.List;

public class Archivo {
	String path;//Ruta del archivo
	
	public Archivo(String _path){
		path = _path;
	}
	
	//Recibe una lista de objetos y los guarda en el path indicado
	public <T> void archivarObjetos(List<T> objetos) {
		String jsonAGuardar = new JsonCreator().getJson(objetos);
		
		new FilesManager(path).sobreescribirArchivo(jsonAGuardar);
	}
}
