package exceptions;

@SuppressWarnings("serial")
public class EseYaExisteException extends RuntimeException{
	public EseYaExisteException(String msg) {
		super(msg);
	}
}
