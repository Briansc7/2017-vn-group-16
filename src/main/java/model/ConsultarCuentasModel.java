package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

public class ConsultarCuentasModel {

	List<Empresa> empresas;
	
	
	public List<Empresa> leerEmpresas()  {
		String content;
		
		try {
			content = new Scanner(new File("src/main/java/model/pruebagson.txt")).useDelimiter("\\Z").next();
			this.empresas = new Gson().fromJson(content, Usuario.class).getEmpresas();

		} catch (FileNotFoundException fe){
		    fe.printStackTrace();
		}
		return 	this.empresas;	
	}

}
