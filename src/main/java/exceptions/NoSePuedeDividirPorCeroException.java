package exceptions;

@SuppressWarnings("serial")
public class NoSePuedeDividirPorCeroException extends RuntimeException {

	public NoSePuedeDividirPorCeroException(String mensaje) {
		super(mensaje);
	}
}