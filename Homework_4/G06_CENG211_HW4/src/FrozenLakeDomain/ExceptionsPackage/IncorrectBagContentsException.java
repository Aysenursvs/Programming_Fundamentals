package FrozenLakeDomain.ExceptionsPackage;
public class IncorrectBagContentsException extends Exception {

    public IncorrectBagContentsException() {
        super("The bag contains an incorrect item.");
    }
    public IncorrectBagContentsException(String message) {
        super(message);
    }

}
