package exceptions;

import org.uqbar.commons.model.UserException;

public class NoSePuedeDividirPorCeroException extends UserException {
	
	public NoSePuedeDividirPorCeroException(String mensaje) {
		
		super(mensaje);
	}
}