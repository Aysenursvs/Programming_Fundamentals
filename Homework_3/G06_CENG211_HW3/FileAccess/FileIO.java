package FileAccess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import GardenPuzzleDomain.Classes.GardenPlant;
import GardenPuzzleDomain.Classes.LightSource;
import GardenPuzzleDomain.Enums.ColorType;
import GardenPuzzleDomain.Enums.LightType;
import GardenPuzzleDomain.Enums.PlantType;
import GardenPuzzleDomain.Enums.Position;
import GardenPuzzleDomain.Interfaces.ISearchable;

/**
 * The FileIO class provides methods to read garden objects from a file and store them in a list.
 * The garden objects can be of types: flower, tree, bush, small_lamp, large_lamp, and spotlight.
 * Each object is created based on the tokens read from the file and added to the gardenObjectsList.
 */
public class FileIO {

    StringTokenizer st;
    List<ISearchable> gardenObjectsList = new ArrayList<ISearchable>(); 

    public List<ISearchable> readFile(String relativePath){
        File file = new File(relativePath);
         try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                st = new StringTokenizer(line, ",");
                
                while(st.hasMoreTokens()){
                    String token = st.nextToken();
                    switch(token){
                        case "flower":
                            GardenPlant flower = new GardenPlant(st.nextToken(), Position.DEFAULT, PlantType.FLOWER, st.nextToken(), Integer.parseInt(st.nextToken()));
                            gardenObjectsList.add(flower);
                            break;
                        case "tree":
                            GardenPlant tree = new GardenPlant(st.nextToken(), Position.DEFAULT, PlantType.TREE, st.nextToken(), Integer.parseInt(st.nextToken()));
                            gardenObjectsList.add(tree);
                            break;
                        case "bush":
                            GardenPlant bush = new GardenPlant(st.nextToken(), Position.DEFAULT, PlantType.BUSH, st.nextToken(), Integer.parseInt(st.nextToken()));
                            gardenObjectsList.add(bush);
                            break;
                        case "small_lamp":
                            LightSource smallLamp = new LightSource(st.nextToken(), Position.DEFAULT, LightType.SMALL_LAMP, getColor(st.nextToken()), Integer.parseInt(st.nextToken()));
                            gardenObjectsList.add(smallLamp);
                            break;
                        case "large_lamp":
                            LightSource largeLamp = new LightSource(st.nextToken(), Position.DEFAULT, LightType.LARGE_LAMP, getColor(st.nextToken()), Integer.parseInt(st.nextToken()));
                            gardenObjectsList.add(largeLamp);
                            break;
                        case "spotlight":
                            LightSource spotlight = new LightSource(st.nextToken(), Position.DEFAULT, LightType.SPOTLIGHT, getColor(st.nextToken()), Integer.parseInt(st.nextToken()));
                            gardenObjectsList.add(spotlight);
                            break;
                            
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List <ISearchable> list = new ArrayList<ISearchable>();
        list = new ArrayList<ISearchable>(gardenObjectsList);
        return list;
    }

    /**
     * Converts a string representation of a color to its corresponding ColorType enum.
     *
     * @param color the string representation of the color (e.g., "red", "blue", "green")
     * @return the corresponding ColorType enum value; if the color is not recognized, returns ColorType.DEFAULT
     */
    private ColorType getColor(String color){
        ColorType colorType;
        switch(color){
            case "red":
                colorType = ColorType.RED;
                break;
            case "blue":
                colorType = ColorType.BLUE;
                break;
            case "green":
                colorType = ColorType.GREEN;
                break;
            default:
                colorType = ColorType.DEFAULT;
                break;
        }
        return colorType;
    }

}
    
