package FrozenLakeDomain.ExceptionsPackage;
public class UnavailableEquipmentException extends Exception {

    public UnavailableEquipmentException() {
        super("The equipment is unavailable.");
    }
    public UnavailableEquipmentException(String message) {
        super(message);
    }

}
