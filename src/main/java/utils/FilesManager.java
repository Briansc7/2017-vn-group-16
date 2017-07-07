package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import exceptions.NoSePuedeLeerException;
import exceptions.RutaDeArchivoInvalidaException;

public class FilesManager {
	String path;
	
	public FilesManager(String _path){
		path = _path;
	}

	public String leerArchivo() {
		String contenidoDelArchivo = "";
		int caracter;
		FileReader f = null;

		try {
			f = new FileReader(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RutaDeArchivoInvalidaException("Archivo no encontrado");
		}

		BufferedReader b = new BufferedReader(f);

		try {
			while ((caracter = b.read()) != -1)
				contenidoDelArchivo += (char) caracter;
		} catch (IOException e) {
			e.printStackTrace();
			throw new NoSePuedeLeerException("Error en la lectura del archivo");
		}

		try {
			b.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Error al cerrar el archivo");
		}

		return contenidoDelArchivo;
	}

	// Recibe un String y sobreescribe (o crea) todo el archivo
	public void sobreescribirArchivo(String stringAGuardar) {
		FileWriter file = null;

		try {
			file = new FileWriter(path);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RutaDeArchivoInvalidaException("La ruta \"" + path + "\" es invalida");
		}

		PrintWriter pw = new PrintWriter(file);
		char caracteres[] = stringAGuardar.toCharArray();

		for (char caracter : caracteres)
			pw.print(caracter);

		try {
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Error al guardar el archivo");
		}
	}

	// Borrar un archivo para los test y demás funcionalidades
	public void borrarArchivo() {
		File file = new File(path);
		file.delete();
	}
}
