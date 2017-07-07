package exceptions;

@SuppressWarnings("serial")
public class NoSeEncuentraException extends RuntimeException{
	public NoSeEncuentraException(String msg) {
		super(msg);
	}
}
