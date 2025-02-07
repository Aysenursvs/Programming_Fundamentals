package FrozenLakeDomain.ExceptionsPackage;
public class UnavailableDirectionException extends Exception {

    public UnavailableDirectionException() {
        super("The direction is unavailable.");
    }
    public UnavailableDirectionException(String message) {
        super(message);
    }
   

}
