package FrozenLakeDomain.ExperimentsPackage;

import FrozenLakeDomain.InterfacesPackage.ISquare;
import FrozenLakeDomain.ResearcherPackage.Equipment;
import FrozenLakeDomain.ResearcherPackage.Researcher;

/**
 * The ResearchEquipment class is an abstract class that extends the Equipment class.
 * It represents equipment used in research within the FrozenLakeDomain.
 * This class provides constructors and abstract methods that must be implemented by subclasses.
 */
public abstract class ResearchEquipment extends Equipment {

    public ResearchEquipment(){

    }

    public ResearchEquipment(ResearchEquipment re){
        if(re == null){
            System.out.println("Null!");
            System.exit(0);
        }
    }

    @Override
    public abstract int effect(Researcher researcher, ISquare square);
    public abstract String displayEquipment();

    /**
     * Abstract method to perform a measurement.
     * Subclasses must provide an implementation for this method.
     */
    public abstract void measure();
    
    /**
     * Retrieves the experiment related to this research equipment.
     *
     * @return the related Experiment instance
     */
    public abstract Experiment getRelatedExperiment();
}
