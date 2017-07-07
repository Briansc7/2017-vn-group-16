package exceptions;

import org.uqbar.commons.model.UserException;

@SuppressWarnings("serial")
public class MetodologiaSinNombreException extends UserException {

	public MetodologiaSinNombreException(String mensaje) {
		super(mensaje);
	}
}