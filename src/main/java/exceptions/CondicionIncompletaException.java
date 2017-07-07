package exceptions;

import org.uqbar.commons.model.UserException;

@SuppressWarnings("serial")
public class CondicionIncompletaException extends UserException {

	public CondicionIncompletaException(String mensaje) {
		super(mensaje);
	}
}