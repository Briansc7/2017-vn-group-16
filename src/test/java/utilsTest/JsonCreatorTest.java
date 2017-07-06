package utilsTest;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.math.BigDecimal;

import org.junit.Test;

import mockObjects.JsonObject;
import model.Cuenta;
import utils.JsonCreator;;

public class JsonCreatorTest {
	JsonObject jsonObject = new JsonObject("StringUno", 27, 3.14D);
	
	List <JsonObject> listaDeObjetos =  Arrays.asList(jsonObject,jsonObject);
	
	String jsonDeListaEsperado= "[{\"artibutoUno\":\"StringUno\",\"artibutoDos\":27,\"artibutoTres\":3.14,\"lista\":[1,2,3,4,5,6,7]},"
			+ "{\"artibutoUno\":\"StringUno\",\"artibutoDos\":27,\"artibutoTres\":3.14,\"lista\":[1,2,3,4,5,6,7]}]";
	
	String jsonDeUnObjetoEsperado = "{\"artibutoUno\":\"StringUno\",\"artibutoDos\":27,\"artibutoTres\":3.14,\"lista\":[1,2,3,4,5,6,7]}";
			
	@Test
	public void obtenerJsonDeUnaListaDeObjetos(){
		String jsonObtenido = new JsonCreator().getJson(listaDeObjetos);
		
		//System.out.println(jsonObtenido);
		
		assertTrue(jsonObtenido.equals(jsonDeListaEsperado));
	}
	
	@Test
	public void obtenerJsonDeUnObjeto(){
		String jsonObtenido = new JsonCreator().getJson(jsonObject);
		
		//System.out.println(jsonObtenido);
		
		assertTrue(jsonObtenido.equals(jsonDeUnObjetoEsperado));
	}
}
