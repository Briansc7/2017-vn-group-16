package exceptions;

public class FormulaIncorrectaException extends RuntimeException{
    public FormulaIncorrectaException(String msg) {
        super(msg);
    }
}