package FrozenLakeDomain.ExperimentsPackage;

import FrozenLakeDomain.InterfacesPackage.ISquare;
import FrozenLakeDomain.ResearcherPackage.Researcher;
import java.util.Random;

/**
 * The ChiselingEquipment class represents a type of research equipment used in experiments.
 * It extends the ResearchEquipment class and includes additional functionality specific to chiseling.
 * This class includes constructors, getter and setter methods, and overrides methods from the superclass.
 * It also provides methods for measuring sample weight, displaying equipment information, and cloning the object.
 */

public class ChiselingEquipment extends ResearchEquipment{
    private int sampleWeight;
    private Random random = new Random();

    //Default constructor
    public ChiselingEquipment(){
        super();
        setSymbol("ch");
        sampleWeight = 0;
    }

    //Parameterized constructor
    public ChiselingEquipment(int sampleWeight){
        super();
        setSymbol("ch");
        this.sampleWeight = sampleWeight;
    }

    //Copy constructor
    public ChiselingEquipment(ChiselingEquipment other){
        super(other);
        this.sampleWeight = other.sampleWeight;
    }

    //Getter and setter methods
    public int getSampleWeight(){
        return sampleWeight;
    }

    public void setSampleWeight(int sampleWeight){
        this.sampleWeight = sampleWeight;
    }

    //toString method
    @Override
    public String toString(){
        return getSymbol();    
    }

    public String displayEquipment(){
        return getSymbol() + " Chiseling equipment";
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
        ChiselingEquipment that = (ChiselingEquipment) obj;
        return sampleWeight == that.sampleWeight;
    }

    //equals method that take a ChiselingEquipment as parameter
    public boolean equals(ChiselingEquipment other){
        if(other == null){
            return false;
        }
        return sampleWeight == other.sampleWeight;
    }

    //clone method
    @Override
    public ChiselingEquipment clone(){
        return new ChiselingEquipment(this);
    }

    /**
     * Measures the weight of a sample by generating a random integer between 1 and 20 (inclusive)
     * and assigns it to the sampleWeight field.
     */
    public void measure(){
        this.sampleWeight = random.nextInt(20) + 1;
    }

    @Override
    public int effect(Researcher researcher, ISquare square) {
        return 1;
    }

    @Override
    public Experiment getRelatedExperiment() {
        return new GlacialSampling();
    }
}
