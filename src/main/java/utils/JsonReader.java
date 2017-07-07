package utils;

import java.lang.reflect.Type;
import java.util.List;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import exceptions.ErrorSintacticoEnElJSONExeption;

public class JsonReader <T>{
	Type objectsType;
	
	public JsonReader(Type _objectsType){
		objectsType = _objectsType;
	}

	//Lo mejor que pude hacer para hacerlo generico
	public List<T> obtenerObjetos(String jsonString) {
		Gson gson = new Gson();
		
		try {
			return gson.fromJson(jsonString, objectsType);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw new ErrorSintacticoEnElJSONExeption("Error Sintactico en el JSON: " + jsonString);
		}
	}
	
	public T obtenerUnObjeto(String jsonString) {
		Gson gson = new Gson();
		
		try {
			return gson.fromJson(jsonString, objectsType);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw new ErrorSintacticoEnElJSONExeption("Error Sintactico en el JSON: " + jsonString);
		}
	}
}