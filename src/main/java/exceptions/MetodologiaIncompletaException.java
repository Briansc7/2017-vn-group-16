package exceptions;

import org.uqbar.commons.model.UserException;

@SuppressWarnings("serial")
public class MetodologiaIncompletaException extends UserException {

	public MetodologiaIncompletaException(String mensaje) {
		super(mensaje);
	}
}