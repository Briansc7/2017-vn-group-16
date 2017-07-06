package utilsTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.uqbar.commons.model.UserException;

import exceptions.ErrorSintacticoEnElJSONExeption;
import mockObjects.JsonObject;
import utils.JsonReader;

public class JsonReaderTest {
	
	@Test
	public void jsonDeUnObjectoSeParseoBien() {
		String jsonString = "{\"artibutoUno\":\"StringUno\",\"artibutoDos\":27,\"artibutoTres\":3.14,\"lista\":[1,2,3,4,5,6,7]}";

		List<JsonObject> jsonObjects = new JsonReader().obtenerObjetos(jsonString);

		System.out.println(jsonObjects);
		
		System.out.println(jsonObjects.get(0).getArtibutoTres());

		//assertEquals(jsonObjects.get(0).getArtibutoUno(),"StringUno");
	}
	
	@Test
	public void jsonDeUnaListaSeParseoBien() {
		String jsonString = "[{\"artibutoUno\":\"StringUno\",\"artibutoDos\":27,\"artibutoTres\":3.14,\"lista\":[1,2,3,4,5,6,7]},"
				+ "{\"artibutoUno\":\"StringUno\",\"artibutoDos\":27,\"artibutoTres\":3.14,\"lista\":[1,2,3,4,5,6,7]}]";

		List<JsonObject> jsonObjects = new JsonReader().obtenerObjetos(jsonString, JsonObject.class);

		System.out.println(jsonObjects);
		
		System.out.println(jsonObjects.get(0).getArtibutoTres());

		//assertEquals(jsonObjects.get(0).getArtibutoUno(),"StringUno");
	}

	@Test(expected = ErrorSintacticoEnElJSONExeption.class)
	public void elJsonMalHechoTiraError() {
		String jsonStringMalo = "[{\"id\":1,\"tipo\":\"EBITDA\",\"empresa\":\"Facebook\",\"periodo\":\"2017\",:3,\""
				+ "tipo\":\"EBITDA\",\"empresa\":\"Twitter,\"valor\":20}]";

	}
}