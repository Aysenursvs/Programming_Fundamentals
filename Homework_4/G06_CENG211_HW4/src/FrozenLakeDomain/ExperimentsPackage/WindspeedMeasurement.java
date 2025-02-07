package FrozenLakeDomain.ExperimentsPackage;

import FrozenLakeDomain.ExceptionsPackage.IncompatibleResearchEquipmentLocationException;
import FrozenLakeDomain.LakePackage.FrozenLake;
import FrozenLakeDomain.LakePackage.LakeSquare;

/**
 * The WindspeedMeasurement class represents an experiment that measures wind speed.
 * It extends the Experiment class and uses a WindSpeedDetector as its research equipment.
 * 
 * */
public class WindspeedMeasurement extends Experiment {
    

    //Default constructor
    public WindspeedMeasurement(){
        super();
        setResearchEquipment(new WindSpeedDetector());
    }

    //Parameterized constructor
    public WindspeedMeasurement(WindSpeedDetector windSpeedDetector){
        super(windSpeedDetector);
    }

    //Copy constructor
    public WindspeedMeasurement(WindspeedMeasurement other){
        super(other);
    }

    //Getter and setter methods
    public WindSpeedDetector getResearchEquipment(){
        return (WindSpeedDetector) super.getResearchEquipment();
    }

    public void setWindSpeedDetector(WindSpeedDetector windSpeedDetector){
        super.setResearchEquipment(windSpeedDetector);
    }

    //toString method
    @Override
    public String toString(){
        return "Wind Speed Measurement ";
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
        return super.equals(obj);
    }

    //equals method that take a TemperatureMeasurement as parameter
    public boolean equals(WindspeedMeasurement other){
        if(other == null){
            return false;
        }
        return getResearchEquipment().equals(other.getResearchEquipment());
    }

    //clone method
    @Override
    public WindspeedMeasurement clone(){
        return new WindspeedMeasurement(this);
    }

    @Override
    public void validatePlacement(FrozenLake frozenLake, LakeSquare square) throws IncompatibleResearchEquipmentLocationException {
        if(!frozenLake.isNearToHazard(square, "CliffEdge") && !frozenLake.isNearToHazard(square, "IceSpike") && !frozenLake.isNearToHazard(square, "HoleInIce") ){
        }else{
            throw new IncompatibleResearchEquipmentLocationException("WindspeedMeasurement can not be placed here.");
        }       
    }

    @Override
    public String displayResults() {
        return "Wind Speed Measurement: " + getResearchEquipment().getRecordedWindSpeed() + " m/s";
    }
}


