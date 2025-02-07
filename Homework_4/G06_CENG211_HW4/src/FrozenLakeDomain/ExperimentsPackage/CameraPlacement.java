package FrozenLakeDomain.ExperimentsPackage;
import FrozenLakeDomain.ExceptionsPackage.IncompatibleResearchEquipmentLocationException;
import FrozenLakeDomain.LakePackage.FrozenLake;
import FrozenLakeDomain.LakePackage.LakeSquare;

/**
 * The CameraPlacement class represents an experiment involving the placement of a camera
 * in a frozen lake domain. It extends the Experiment class and provides methods for 
 * setting and getting the research equipment, as well as validating the placement and 
 * displaying the results of the experiment.
 * 
 * This class includes constructors for creating instances with default, parameterized, 
 * and copy configurations. It also overrides the equals, toString, and clone methods 
 * from the Object class.
 * 
 * Additionally, the class provides a method to validate the placement of the camera 
 * in the lake and a method to display the results of the experiment
 * 
 */
public class CameraPlacement extends Experiment{
    

    //Default constructor
    public CameraPlacement() {
        super();
        setResearchEquipment(new Camera());
    }

    //Parameterized constructor 
    public CameraPlacement(Camera camera) {
        super(camera);    
    }

    //copy constructor
    public CameraPlacement(CameraPlacement other) {
        super(other);
    }

    //getter method and setter method
    public Camera getResearchEquipment() {
        return (Camera) super.getResearchEquipment();
    }

    public void setResearchEquipment(Camera camera) {
        super.setResearchEquipment(camera);
    }

    //toString method
    @Override
    public String toString() {
        return "Camera Placement " ;
    }

    //equals method that take an object as parameter
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return super.equals(obj);
    }

    //equals method that take a CameraPlacement as parameter
    public boolean equals(CameraPlacement other) {
        if (other == null) {
            return false;
        }
        return getResearchEquipment().equals(other.getResearchEquipment());
    }

    //clone method
    @Override
    public CameraPlacement clone() {
        return new CameraPlacement(this);
    }

    @Override
    public void validatePlacement(FrozenLake lake, LakeSquare square) throws IncompatibleResearchEquipmentLocationException {
       if(lake.hasDirectViewToCliffSide(square)){
       }else{
           throw new IncompatibleResearchEquipmentLocationException("Camera does not have a direct view to cliff side");
       }
    }

    /**
     * Displays the results of the camera placement experiment.
     * 
     * @return A string indicating whether the camera started recording or failed to start recording.
     */
    @Override
    public String displayResults() {
        String str = "";
        if(getResearchEquipment().isWorking()){
            str = "The camera started to recording.";
        }else{
            str = "The camera failed to start recording.";
        }
        return "Camera Placement: " + str;
    }

}
