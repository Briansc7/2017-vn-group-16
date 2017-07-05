package exceptions;

import org.uqbar.commons.model.UserException;

public class NoExisteAtributoException extends UserException {

	public NoExisteAtributoException(String mensaje){
		super(mensaje);
	}
}
