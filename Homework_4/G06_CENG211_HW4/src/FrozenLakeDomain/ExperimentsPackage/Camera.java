package FrozenLakeDomain.ExperimentsPackage;

import FrozenLakeDomain.InterfacesPackage.ISquare;
import FrozenLakeDomain.ResearcherPackage.Researcher;

public class Camera extends ResearchEquipment {
    private boolean isWorking;

    //default constructor
    public Camera() {
        super();
        setSymbol("cm");
    }

    //parameterized constructor
    public Camera(boolean isWorking) {
        super();
        setSymbol("cm");
        this.isWorking = isWorking;
    }

    //copy constructor
    public Camera(Camera other) {
        super(other);
        this.isWorking = other.isWorking;
    }

    //getter method
    public boolean isWorking() {
        return isWorking;
    }

    //setter method
    public void setWorking(boolean working) {
        isWorking = working;
    }

    //toString method
    @Override
    public String toString() {
        return getSymbol();
    }

    public String displayEquipment() {
        return getSymbol() + " Camera";
    }
    
    //equals method that takes an Object as a parameter
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Camera camera = (Camera) obj;
        return isWorking == camera.isWorking;
    }

    //equals method that takes a Camera as a parameter
    public boolean equals(Camera other) {
        if (other == null) {
            return false;
        }
        return isWorking == other.isWorking;
    }

    //clone method
    @Override
    public Camera clone() {
        return new Camera(this);
    }

    /**
     * Applies the effect of the camera on the given researcher and square.
     */
    @Override
    public int effect(Researcher researcher, ISquare square) {
        return 1;
    }

    /**
     * Measures the working status of the camera.
     * The camera has an 80% chance of being marked as working.
     * This is determined by generating a random number between 0 and 1,
     * and setting the isWorking flag to true if the number is greater than 0.2.
     */
    @Override
    public void measure(){
        isWorking = Math.random() > 0.2;
    }
    
    @Override
    public Experiment getRelatedExperiment() {
        return new CameraPlacement();
    }
}
