package FrozenLakeDomain.ExperimentsPackage;

import FrozenLakeDomain.ExceptionsPackage.IncompatibleResearchEquipmentLocationException;
import FrozenLakeDomain.LakePackage.FrozenLake;
import FrozenLakeDomain.LakePackage.LakeSquare;

/**
 * The GlacialSampling class represents a specific type of experiment that involves chiseling equipment
 * to sample ice blocks in a frozen lake environment.
 * 
 */
public class GlacialSampling extends Experiment{
    
    //default constructor
    public GlacialSampling(){
        super();
        setResearchEquipment(new ChiselingEquipment());
    }

    //parameterized constructor
    public GlacialSampling(ChiselingEquipment chiselingEquipment){
        super(chiselingEquipment);
    }

    //copy constructor
    public GlacialSampling(GlacialSampling other){
        super(other);
    }

    //Getter and setter methods
    public ChiselingEquipment getResearchEquipment(){
        return (ChiselingEquipment) super.getResearchEquipment();
    }

    public void setResearcEquipment(ChiselingEquipment chiselingEquipment){
        super.setResearchEquipment(chiselingEquipment);
    }

    //toString method
    @Override
    public String toString(){
        return "Glacial Sampling " ;
    }

    //equals method that takes an object as parameter
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        return super.equals(obj);
    }

    //equals method that takes a GlacialSampling as parameter
    public boolean equals(GlacialSampling other){
        if(other == null){
            return false;
        }
        return super.equals(other);
    }

    //clone method
    @Override
    public GlacialSampling clone(){
        return new GlacialSampling(this);
    }

    @Override
    public void validatePlacement(FrozenLake frozenLake, LakeSquare square) throws IncompatibleResearchEquipmentLocationException {
        if(frozenLake.isNearToHazard(square, "IceBlock")){
        }else{
            throw new IncompatibleResearchEquipmentLocationException("Can not be measure!");
        }
    }

    @Override
    public String displayResults() {
        return "Glacial Sampling: " + getResearchEquipment().getSampleWeight() + " kg";
    }
}
