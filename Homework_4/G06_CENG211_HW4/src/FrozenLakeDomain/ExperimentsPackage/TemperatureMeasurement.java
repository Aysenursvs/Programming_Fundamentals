package FrozenLakeDomain.ExperimentsPackage;

import FrozenLakeDomain.ExceptionsPackage.IncompatibleResearchEquipmentLocationException;
import FrozenLakeDomain.LakePackage.FrozenLake;
import FrozenLakeDomain.LakePackage.LakeSquare;

/**
 * The TemperatureMeasurement class represents an experiment that measures temperature.
 * It extends the Experiment class and uses a TemperatureDetector as its research equipment.
 * 
 */
public class TemperatureMeasurement extends Experiment{
    

    //Default constructor
    public TemperatureMeasurement(){
        super();
        setResearchEquipment(new TemperatureDetector());
    }

    //Parameterized constructor
    public TemperatureMeasurement(TemperatureDetector temperatureDetector){
        super(temperatureDetector);
        
    }

    //Copy constructor
    public TemperatureMeasurement(TemperatureMeasurement other){
        super(other);
        
    }

    //Getter and setter methods
    public TemperatureDetector getResearchEquipment(){
        return (TemperatureDetector) super.getResearchEquipment();
    }

    public void setTemperatureDetector(TemperatureDetector temperatureDetector){
        super.setResearchEquipment(temperatureDetector);
    }

    //toString method
    @Override
    public String toString(){
        return "Temperature Measurement " ;
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
    public boolean equals(TemperatureMeasurement other){
        if(other == null){
            return false;
        }
        return getResearchEquipment().equals(other.getResearchEquipment());
    }

    //clone method
    @Override
    public TemperatureMeasurement clone(){
        return new TemperatureMeasurement(this);
    }

    @Override
    public void validatePlacement(FrozenLake frozenLake, LakeSquare square) throws IncompatibleResearchEquipmentLocationException {
        if(!frozenLake.isNearToHazard(square, "IceBlock") && !frozenLake.isNearToEdge(square)){
        }else{
            throw new IncompatibleResearchEquipmentLocationException("TemperatureMeasurement can not be placed here.");
        }
    }

    @Override
    public String displayResults() {
        return "Temperature Measurement: " + getResearchEquipment().getRecordedTemperature() + " C";
    }
}
