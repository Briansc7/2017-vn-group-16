package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.joda.time.DateTime;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class UploadDates {

	public static String leerArchivo() throws FileNotFoundException {

		@SuppressWarnings("resource")
		String content = new Scanner(new File("src/main/java/model/prueba.txt")).useDelimiter("\\Z").next();
		return content;
	}

	public void crearEmpresaDesdeJSON(JSONObject jObj) {

		Empresa empresa = new Empresa();
		empresa.setNombre((String) jObj.get("nombre"));

		@SuppressWarnings("unchecked")
		List<JSONObject> listaCuentas = (List<JSONObject>) jObj.get("cuentas");
		for (Integer i = 0; i < listaCuentas.size(); i++) {
			cargarCuentasDeEmpresa(listaCuentas.get(i), empresa);
		}
		Usuario.getInstance().empresas.add(empresa);
		return;

	}

	private void cargarCuentasDeEmpresa(JSONObject jObj, Empresa empresa) {

		Cuenta cuenta = new Cuenta();
//		cuenta.setFecha((String) jObj.get("fecha"));
		cuenta.setValor((Integer) jObj.get("valor"));
		cuenta.setNombre((String) jObj.get("nombre"));
		empresa.cuentas.add(cuenta);
		return;

	}

	public void actualizarInfoPorArchivo() throws FileNotFoundException, ParseException {
		String contenido = this.leerArchivo();
		List<String> items = Arrays.asList(contenido.split("\\s*;\\s*"));

		for (Integer i = 0; i < items.size(); i++) {
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(items.get(i));
			crearEmpresaDesdeJSON(json);
		}

	}

	
//	public static void main(String[] args) throws FileNotFoundException {
//		String string = leerArchivo();
//		System.out.println(string);
//	}
}
