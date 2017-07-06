package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import exceptions.ErrorSintacticoEnElJSONExeption;

public class JsonReader{
	
	//La unica manera que se me ocurrio de hacerlo para que soporte de todo
	public <T> List<T> obtenerMetodologias(String jsonString) {
		List<T> objetos = new ArrayList<T>();
		Gson gson = new Gson();
		
		
		try {
			
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw new ErrorSintacticoEnElJSONExeption("Error Sintactico en el JSON: " + jsonString);
		}

		return objetos;
	}
}