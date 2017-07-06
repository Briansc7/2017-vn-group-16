package exceptions;

import org.uqbar.commons.model.UserException;

@SuppressWarnings("serial")
public class NoExisteAtributoException extends UserException {

	public NoExisteAtributoException(String mensaje){
		super(mensaje);
	}
}
