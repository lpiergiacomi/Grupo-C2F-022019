package unq.tpi.desapp.exceptions;

public class CreditoInsuficiente extends RuntimeException {
    public CreditoInsuficiente(String msg){
        super(msg);
    }
}
