package exceptions;

@SuppressWarnings("serial")
public class NoExisteAtributoException extends RuntimeException {

	public NoExisteAtributoException(String mensaje){
		super(mensaje);
	}
}
