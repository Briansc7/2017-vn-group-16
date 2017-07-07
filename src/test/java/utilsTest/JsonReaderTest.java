package utilsTest;

import static org.junit.Assert.*;

import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import exceptions.ErrorSintacticoEnElJSONExeption;
import mockObjects.JsonObject;
import utils.JsonReader;

public class JsonReaderTest {
	
	@Test
	public void jsonDeUnaListaSeParseaBien() {
		String jsonString = "[{\"artibutoUno\":\"StringUno\",\"artibutoDos\":27,\"artibutoTres\":3.14,\"lista\":[1,2,3,4,5,6,7]},"
				+ "{\"artibutoUno\":\"StringUno\",\"artibutoDos\":27,\"artibutoTres\":3.14,\"lista\":[1,2,3,4,5,6,7]}]";

		Type tipoLista = new TypeToken<List<JsonObject>>() {}.getType();
		
		List<JsonObject> jsonObjects = new JsonReader<JsonObject>(tipoLista).obtenerObjetos(jsonString);

		//System.out.println(jsonObjects);
		
		//System.out.println(jsonObjects.get(0).getArtibutoTres());

		assertEquals(jsonObjects.get(0).getArtibutoUno(),"StringUno");
	}
	
	@Test
	public void jsonDeUnObjetoSeParseaBien() {
		String jsonString = "{\"artibutoUno\":\"StringUno\",\"artibutoDos\":27,\"artibutoTres\":3.14,\"lista\":[1,2,3,4,5,6,7]}";

		Type tipoObjeto = new TypeToken<JsonObject>() {}.getType();
		JsonObject jsonObject = new JsonReader<JsonObject>(tipoObjeto).obtenerUnObjeto(jsonString);

		//System.out.println(jsonObject);
		
		//System.out.println(jsonObject.getArtibutoTres());

		assertEquals(jsonObject.getArtibutoUno(),"StringUno");
	}

	@Test(expected = ErrorSintacticoEnElJSONExeption.class)
	public void elJsonDeUnObjetoMalHechoTiraError() {
		String jsonStringMalo = "{\"artibutoUno\"\"StringUno\"\"artibutoDos\":27,\"artibutlista\":[1,2,3,4,5,6,7]}";
		Type tipoObjeto = new TypeToken<JsonObject>() {}.getType();
		new JsonReader<JsonObject>(tipoObjeto).obtenerUnObjeto(jsonStringMalo);
	}
	
	@Test(expected = ErrorSintacticoEnElJSONExeption.class)
	public void elJsonDeObjetosMalHechoTiraError() {
		String jsonStringMalo = "{\"artibutoUno\"\"StringUno\"\"artibutoDos\":27,\"artibutlista\":[1,2,3,4,5,6,7]}";
		Type tipoLista = new TypeToken<List<JsonObject>>() {}.getType();
		new JsonReader<JsonObject>(tipoLista).obtenerObjetos(jsonStringMalo);
	}
}