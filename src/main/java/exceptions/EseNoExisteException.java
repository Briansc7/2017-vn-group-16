package exceptions;

@SuppressWarnings("serial")
public class EseNoExisteException extends RuntimeException{
	public EseNoExisteException(String msg) {
		super(msg);
	}
}
