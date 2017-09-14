package viewModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.MessageBox.Type;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import model.BaseDeDatos;
import parser.ParseException;
import parser.TokenMgrError;
import view.ConsultarCuentasView;
import utils.DatosCsv;

@Observable
public class CargarCuentasViewModel {
	private String path;
	private DatosCsv datosCsv;
	
	public void cargarCuentas() throws ParseException, TokenMgrError, IOException,NullPointerException{
		this.cargarArchivo();
		System.out.println("Archivo cargado exitosamente");
	}
	
	public void cargarArchivo() throws NullPointerException, IOException {
		try {
			Scanner scanner = new Scanner(new File(this.path));
			scanner.close();
			datosCsv = new DatosCsv(this.getPath());
			datosCsv.leerEmpresas();
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
