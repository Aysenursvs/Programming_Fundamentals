package GardenPuzzleDomain.Classes;

import java.util.ArrayList;

import GardenPuzzleDomain.Enums.ColorType;
import GardenPuzzleDomain.Enums.PollenType;
import GardenPuzzleDomain.Interfaces.ISquare;

/**
 * The PollenCloud class represents a cloud of pollen with associated colors.
 * It implements the ISquare interface.
 * 
 * This class provides methods to manage and manipulate the pollen and color lists.
 * It includes constructors, getter and setter methods, and methods to add, remove,
 * and print pollen and color types.
 * 
 * The class also overrides the toString and equals methods, and provides a clone method.
 * 
 * The pollen types and color types are represented by the PollenType and ColorType enums respectively.
 * 
 */
public class PollenCloud implements ISquare{
    private ArrayList<PollenType> pollenList;
    private ArrayList<ColorType> colorList;

    //default constructor
    public PollenCloud(){
        pollenList = new ArrayList<PollenType>();
        colorList = new ArrayList<ColorType>();

    }

    //parameterized constructor
    public PollenCloud(ArrayList<PollenType> pollenList, ArrayList<ColorType> colorList){
        this.pollenList = pollenList;
        this.colorList = colorList;
    }
    
    //copy constructor
    public PollenCloud(PollenCloud pollenCloud){
        if(pollenCloud == null){
            System.out.println("Fatal Error: PollenCloud object is null");
            System.exit(0);
        }
        this.pollenList = new ArrayList<>(pollenCloud.pollenList);
        this.colorList = new ArrayList<>(pollenCloud.colorList);
    }

    //getter and setter methods
    public ArrayList<PollenType> getPollenList() {
        return pollenList;    
    }

    public void setPollenList(ArrayList<PollenType> pollenList) {
        this.pollenList = pollenList;
    }

    public ArrayList<ColorType> getColorList() {
        return colorList;  
    }

    public void setColorList(ArrayList<ColorType> colorList) {
        this.colorList = colorList;
    }

    //toString method
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pollen List: [");
        for (int i = 0; i < pollenList.size(); i++) {
            sb.append(pollenList.get(i));
            if (i < pollenList.size() - 1) {
            sb.append(", ");
            }
        }
        sb.append("], Color List: [");
        for (int i = 0; i < colorList.size(); i++) {
            sb.append(colorList.get(i));
            if (i < colorList.size() - 1) {
            sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    //equals method that takes an object as parameter
    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(obj.getClass() != this.getClass()){
            return false;
        }
        PollenCloud pollenCloud = (PollenCloud) obj;
        return this.pollenList.equals(pollenCloud.pollenList) && this.colorList.equals(pollenCloud.colorList);
    }

    //equals method that takes a PollenCloud object as parameter
    public boolean equals(PollenCloud pollenCloud){
        if(pollenCloud == null){
            return false;
        }
        return this.pollenList.equals(pollenCloud.pollenList) && this.colorList.equals(pollenCloud.colorList);
    }

    //clone method
    public PollenCloud clone(){
        return new PollenCloud(this);
    }

    //addPollenType method
    public void addPollenType(PollenType pollenType){
        if(!pollenList.contains(pollenType)){
            pollenList.add(pollenType);
        }
        
    }

    //addColorType method
    public void addColorType(ColorType colorType){
        if(!colorList.contains(colorType)){
            colorList.add(colorType);
        }
    }

    //removePollenType method
    public void removePollenType(PollenType pollenType){
        pollenList.remove(pollenType);
    }

    //removeColorType method
    public void removeColorType(ColorType colorType){
        colorList.remove(colorType);
    }

    /**
     * Generates a string representation of the pollen cloud.
     * The output string consists of characters representing the presence of different pollen types and colors.
     * 
     * The format of the output string is as follows:
     * - 'f' if the pollen cloud contains PollenType.FLOWER, otherwise '.'.
     * - 't' if the pollen cloud contains PollenType.TREE, otherwise '.'.
     * - 'u' if the pollen cloud contains PollenType.BUSH, otherwise '.'.
     * - 'r' if the pollen cloud contains ColorType.RED, otherwise '.'.
     * - 'g' if the pollen cloud contains ColorType.GREEN, otherwise '.'.
     * - 'b' if the pollen cloud contains ColorType.BLUE, otherwise '.'.
     * 
     * @return A string representing the presence of different pollen types and colors in the pollen cloud.
     */
    public String printPollenCloud(){
        String output= "";
        if(pollenList.contains(PollenType.FLOWER)){
            output = output + "f";
        }
        else{
            output = output + ".";
        }

        if(pollenList.contains(PollenType.TREE)){
            output = output + "t";
        }
        else{
            output = output + ".";
        }

        if(pollenList.contains(PollenType.BUSH)){
            output = output + "u";
        }
        else{
            output = output + ".";
        }
        if(colorList.contains(ColorType.RED)){
            output = output + "r";
        }
        else{
            output = output + ".";
        }

        if(colorList.contains(ColorType.GREEN)){
            output = output + "g";
        }
        else{
            output = output + ".";
        }

        if(colorList.contains(ColorType.BLUE)){
            output = output + "b";
        }
        else{
            output = output + ".";
        }
        return output;
    }


}
