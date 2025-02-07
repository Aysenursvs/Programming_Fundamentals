package FrozenLakeDomain.ExperimentsPackage;

import FrozenLakeDomain.InterfacesPackage.ISquare;
import FrozenLakeDomain.ResearcherPackage.Researcher;
import java.util.Random;

/**
 * The TemperatureDetector class represents a piece of research equipment used to detect temperature.
 * It extends the ResearchEquipment class and includes functionality to measure temperature and interact with researchers and experiments.
 * 
 * */
public class TemperatureDetector extends ResearchEquipment {
    private int recordedTemperature;
    private Random random = new Random();

    //default constructor
    public TemperatureDetector() {
        super();
        setSymbol("td");
        recordedTemperature = 0;
    }

    //parameterized constructor
    public TemperatureDetector(int recordedTemperature) {
        super();
        setSymbol("td");
        this.recordedTemperature = recordedTemperature;
    }

    //copy constructor
    public TemperatureDetector(TemperatureDetector other) {
        super(other);
        recordedTemperature = other.recordedTemperature;
    }

    //getter and setter methods
    public int getRecordedTemperature() {
        return recordedTemperature;
    }

    public void setRecordedTemperature(int recordedTemperature) {
        this.recordedTemperature = recordedTemperature;
    }

    //toString method
    @Override
    public String toString() {
        return getSymbol();
    }

    public String displayEquipment() {
        return getSymbol() + " Temperature Detector";
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
        TemperatureDetector that = (TemperatureDetector) obj;
        return recordedTemperature == that.recordedTemperature;
    }

    //equals method that take a TemperatureDetector as parameter
    public boolean equals(TemperatureDetector other) {
        if (other == null) {
            return false;
        }
        return recordedTemperature == other.recordedTemperature;
    }

    //clone method
    @Override
    public TemperatureDetector clone() {
        return new TemperatureDetector(this);
    }

    /**
     * Measures the temperature and records it.
     * The recorded temperature is a random negative integer between -1 and -30.
     */
    @Override
    public void measure() {
        this.recordedTemperature = -1 * random.nextInt(31);
    }

    @Override
    public int effect(Researcher researcher, ISquare square) {
        return 1;
    }

    @Override
    public Experiment getRelatedExperiment() {
        return new TemperatureMeasurement();
    }

}
