package unq.tpi.desapp.exceptions;

public class InsufficientCreditException extends RuntimeException {
    public InsufficientCreditException(String msg){
        super(msg);
    }
}
