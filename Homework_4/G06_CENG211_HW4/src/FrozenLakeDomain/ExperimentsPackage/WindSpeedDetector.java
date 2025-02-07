package FrozenLakeDomain.ExperimentsPackage;
import FrozenLakeDomain.InterfacesPackage.ISquare;
import FrozenLakeDomain.ResearcherPackage.Researcher;
import java.util.Random;

/**
 * The WindSpeedDetector class represents a piece of research equipment used to measure wind speed.
 * It extends the ResearchEquipment class and provides functionality to record and manipulate wind speed data.
 * 
 */
public class WindSpeedDetector extends ResearchEquipment{
    private int recordedWindSpeed;
    private Random random = new Random();

    //Default constructor
    public WindSpeedDetector(){
        super();
        setSymbol("ws");
        recordedWindSpeed = 0;
    }

    //Parameterized constructor
    public WindSpeedDetector(int recordedWindSpeed){
        super();
        setSymbol("ws");
        this.recordedWindSpeed = recordedWindSpeed;
    }

    //Copy constructor
    public WindSpeedDetector(WindSpeedDetector other){
        super(other);
        this.recordedWindSpeed = other.recordedWindSpeed;
    }

    //Getter and setter methods
    public int getRecordedWindSpeed(){
        return recordedWindSpeed;
    }

    public void setRecordedWindSpeed(int recordedWindSpeed){
        this.recordedWindSpeed = recordedWindSpeed;
    }

    //toString method
    @Override
    public String toString(){
        return getSymbol();
    }

    public String displayEquipment(){
        return getSymbol() + " Wind Speed Detector";
    }

    //equals method that take an object as parameter    
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        WindSpeedDetector that = (WindSpeedDetector) obj;
        return recordedWindSpeed == that.recordedWindSpeed;
    }

    //equals method that take a WindSpeedDetector as parameter
    public boolean equals(WindSpeedDetector other){
        if(other == null){
            return false;
        }
        return recordedWindSpeed == other.recordedWindSpeed;
    }

    //clone method
    @Override
    public WindSpeedDetector clone(){
        return new WindSpeedDetector(this);
    }

    /**
     * Measures and records the wind speed.
     * The recorded wind speed is a random integer between 0 and 30 (inclusive).
     */
    public void measure(){
        this.recordedWindSpeed = random.nextInt(31);
    }

    @Override
    public int effect(Researcher researcher, ISquare square) {
        return 1;
    }

    @Override
    public Experiment getRelatedExperiment() {
        return new WindspeedMeasurement();
    }
}
