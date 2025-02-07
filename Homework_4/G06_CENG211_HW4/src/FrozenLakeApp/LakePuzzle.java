package FrozenLakeApp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import FrozenLakeDomain.ExceptionsPackage.IncorrectBagContentsException;
import FrozenLakeDomain.ExceptionsPackage.UnavailableDirectionException;
import FrozenLakeDomain.ExceptionsPackage.UnavailableEquipmentException;
import FrozenLakeDomain.ExperimentsPackage.CameraPlacement;
import FrozenLakeDomain.ExperimentsPackage.Experiment;
import FrozenLakeDomain.ExperimentsPackage.GlacialSampling;
import FrozenLakeDomain.ExperimentsPackage.ResearchEquipment;
import FrozenLakeDomain.ExperimentsPackage.TemperatureMeasurement;
import FrozenLakeDomain.ExperimentsPackage.WindspeedMeasurement;
import FrozenLakeDomain.LakePackage.Direction;
import FrozenLakeDomain.LakePackage.FrozenLake;
import FrozenLakeDomain.LakePackage.LakeSquare;
import FrozenLakeDomain.ResearcherPackage.Equipment;
import FrozenLakeDomain.ResearcherPackage.EquipmentStorage;
import FrozenLakeDomain.ResearcherPackage.Researcher;

/**
 * The LakePuzzle class represents a puzzle game involving researchers performing experiments on a frozen lake.
 * It manages the state of the lake, researchers, experiments, and equipment storage.
 */
public class LakePuzzle {
    
    private FrozenLake lake;
    private Queue <Researcher> researchersQueue;
    private Set <Experiment> experimentsGoals;
    private Menu menu;
    private EquipmentStorage equipmentStorage;
    private List<Experiment> completedExperimentsList;
    private Random random = new Random();

        //Default Constructor
        public LakePuzzle() {
            this.lake = new FrozenLake();
            createResearchers();
            createExperimentsGoals();
            this.menu = new Menu();
            this.equipmentStorage = new EquipmentStorage();
            this.completedExperimentsList = new ArrayList<>();
        }
        
        //Parameterized Constructor
        public LakePuzzle(FrozenLake lake, Queue<Researcher> researchersQueue, Set<Experiment> experimentsGoals, Menu menu, EquipmentStorage equipmentStorage, List<Experiment> completedExperimentsList) {
            this.lake = lake;
            this.researchersQueue = researchersQueue;
            this.experimentsGoals = experimentsGoals;
            this.menu = menu;
            this.equipmentStorage = equipmentStorage;
            this.completedExperimentsList = completedExperimentsList;
        }
        
        //Copy Constructor
        public LakePuzzle(LakePuzzle other) {
            if (other == null) {
                System.out.println("Error: null LakePuzzle object");
                System.exit(0);
            }
            this.lake = other.lake;
            this.researchersQueue = other.researchersQueue;
            this.experimentsGoals = other.experimentsGoals;
            this.menu = other.menu;
            this.equipmentStorage = other.equipmentStorage;
            this.completedExperimentsList = other.completedExperimentsList;
        }
    
        // Getters and Setters
        public FrozenLake getLake() {
            return this.lake;
        }
    
        public void setLake(FrozenLake lake) {
            this.lake = lake;
        }
    
        public Queue<Researcher> getResearchersQueue() {
            return this.researchersQueue;
        }
    
        public void setResearchersQueue(Queue<Researcher> researchersQueue) {
            this.researchersQueue = researchersQueue;
        }
    
        public Set<Experiment> getExperimentsGoals() {
            return this.experimentsGoals;
        }
    
        public void setExperimentsGoals(Set<Experiment> experimentsGoals) {
            this.experimentsGoals = experimentsGoals;
        }
    
        public Menu getMenu() {
            return this.menu;
        }
    
        public void setMenu(Menu menu) {
            this.menu = menu;
        }
    
        public EquipmentStorage getEquipmentStorage() {
            return this.equipmentStorage;
        }

        public void setEquipmentStorage(EquipmentStorage equipmentStorage) {
            this.equipmentStorage = equipmentStorage;
        }

        public List<Experiment> getCompletedExperimentsList() {
            return this.completedExperimentsList;
        }

        public void setCompletedExperimentsList(List<Experiment> completedExperimentsList) {
            this.completedExperimentsList = completedExperimentsList;
        }
    
    
        /**
         * Creates a queue of researchers with a random number of researchers
         * between 2 and 4 (inclusive). Each researcher is assigned a unique ID
         * starting from 1
         */
        private  void createResearchers() {
            researchersQueue = new LinkedList<>();
            int numberOfResearchers = random.nextInt(2, 5);
            for (int i = 0; i < numberOfResearchers; i++) {
                researchersQueue.add(new Researcher(i + 1));
            }
        }

        /**
         * Creates a set of experiment goals for the researchers.
         * The number of experiments is randomly determined based on the size of the researchers queue.
         * The method initializes a list of possible experiments, shuffles them, and then selects a subset
         * of these experiments to add to the experimentsGoals set.
         */
        private void createExperimentsGoals() {
            experimentsGoals = new HashSet<Experiment>();
            int numberOfExperiments = random.nextInt(researchersQueue.size()-1, researchersQueue.size()+1);
            List<Experiment> experiments = new ArrayList<>();
            experiments.add(new CameraPlacement());
            experiments.add(new GlacialSampling());
            experiments.add(new TemperatureMeasurement());
            experiments.add(new WindspeedMeasurement());
            Collections.shuffle(experiments);
            for (int i = 0; i < numberOfExperiments; i++) {
                experimentsGoals.add(experiments.get(i));
            }
        }

        /**
         * Starts the lake puzzle application by displaying the welcome message and 
         * processing researchers in the queue. Researchers are moved to the lake 
         * and their equipment is selected and handled. The process continues until 
         * the researchers queue is empty or all experiment goals are completed.
         */
        public void start(){ 
            menu.welcomeMessage();
            while(!researchersQueue.isEmpty() && !completedExperimentsList.containsAll(experimentsGoals)){
                Researcher researcher = researchersQueue.poll();
                lake.setToLakeObjectByRowAndColumn(researcher, 1, 6);
                menu.handleEquipmentSelection(researcher);
                menu.handleMovement(researcher);
            }
        }

        /**
         * This method checks if all research goals have been accomplished by comparing
         * the list of completed experiments with the list of experiment goals. If not
         * all goals are accomplished, it prints the results of the completed experiments
         * and indicates that the research was unsuccessful. If all goals are accomplished,
         * it prints the results of the completed experiments and indicates that the research
         * was successful.
         */
        public void finish(){
            boolean allGoalsAccomplished = true;

            for (Experiment experiment : experimentsGoals) {
                if (!completedExperimentsList.contains(experiment)) {
                    allGoalsAccomplished = false;
                    break;
                }
            }
            if(!allGoalsAccomplished){
                System.out.println("-----------> Research goal(s) have not been accomplished. Here are their wrong results:");
                for (Experiment completedExperiment : completedExperimentsList) {
                    completedExperiment.getResearchEquipment().measure();
                    System.out.println("- " + completedExperiment.displayResults());
                }
                System.err.println("\u001B[31m-----------> UNSUCCESSFUL\u001B[0m");
            }else{
                System.out.println("-----------> Research goal(s) have been accomplished. Here are their results:");
                for (Experiment completedExperiment : completedExperimentsList) {
                    completedExperiment.getResearchEquipment().measure();
                    System.out.println("- " + completedExperiment.displayResults());
                }
                System.out.println("\u001B[32m-----------> SUCCESSFUL\u001B[0m");
            }
        }
        
        /**
         * Checks if all the research equipment required for the experiments
         * is available in the equipment storage.
         *
         * @return true if all the research equipment required for the experiments
         *         is present in the equipment storage, false otherwise.
         */
        private boolean isExperimentGoalsMatchedToEquipmentStorage(){
            for (Experiment experiment : experimentsGoals) {
                if(!equipmentStorage.getResearchEquipmentStorage().containsKey(experiment.getResearchEquipment().getSymbol())) return false;
            }
            return true;
        }
    
    /**
     * The Menu class provides methods to interact with the user for the Frozen Lake Puzzle App.
     * It handles displaying welcome messages, equipment selection, researcher movement, and performing experiments.
     */
    private class Menu{
        Scanner scanner = new Scanner(System.in);

        /**
         * Prints a welcome message to the console, including the number of researchers waiting at the lake entrance,
         * the list of experiments that must be completed, and the initial map of the frozen lake.
         */
        private void welcomeMessage(){
            System.out.println("Welcome to Frozen Lake Puzzle App. There are " + researchersQueue.size() + " researchers waiting at the lake entrance.");
            System.out.println("There are " + experimentsGoals.size() +  " experiment(s) that must be completed:");

            for (Experiment experiment : experimentsGoals) {
                System.out.println("- " + experiment.toString());
            }
            
            System.out.println("The initial map of the frozen lake:");
        }

        /**
         * Handles the equipment selection process for a given researcher.
         * The researcher can select up to 3 pieces of equipment of the same type from the equipment storage.
         * If the researcher tries to head to the lake with an empty bag or with different types of equipment, an exception is thrown.
         * 
         * @param researcher The researcher who is selecting equipment.
         * @throws UnavailableEquipmentException if the selected equipment is not available in the storage.
         * @throws IncorrectBagContentsException if the researcher tries to carry different types of equipment or heads to the lake with an empty bag.
         */
        private void handleEquipmentSelection(Researcher researcher){
            lake.printLake();
            if(!isExperimentGoalsMatchedToEquipmentStorage()){
                System.out.println(" There is no matching ResearchEquipments are left for one of the Experiment goals.");
                System.err.println("\u001B[31m-----------> UNSUCCESSFUL\u001B[0m");
                System.exit(0);
            }

            System.out.println("=====> Researcher " + researcher.getId() + " starts waiting at the entrance and can select up to 3 pieces of equipment of the \r\n" + //
                                "same type. Here are the shorter notations of the equipments:");

            equipmentStorage.displayInventory();
            System.out.println("[no] Stop taking equipment and head out to the lake\n"); 

            String answer = "";
            boolean isDone = false;
            while (!isDone){
                try {
                    Scanner keyboard = new Scanner(System.in);
                    System.out.println("Enter the short name of an equipment:");
                    answer = keyboard.next();

                    if(answer.equals("no") && researcher.getEquipmentBag().size() == 0 )
                        throw new IncorrectBagContentsException("*** Researchers cannot head to the lake with an empty bag.");
                    else if (answer.equals("no")) {
                        isDone = true;
                        System.out.println("- The final contents of the bag of Researcher "+ researcher.getId()+": " + researcher.getEquipmentBag().toString()+"\n");
                    }
                    else if(researcher.getEquipmentBag().size() < 3){
                        Boolean isValidEquipment = equipmentStorage.contains(answer);
                        if(isValidEquipment && researcher.getEquipmentBag().checkType(answer, equipmentStorage)) {
                            Equipment selectedEquipment = equipmentStorage.takeEquipment(answer);
                            researcher.getEquipmentBag().add(selectedEquipment);
                            System.out.println("- Contents of the bag of Researcher "+ researcher.getId()+": " + researcher.getEquipmentBag().toString());
                        }
                        else if(!isValidEquipment) throw new UnavailableEquipmentException("*** There is no such equipment in the storage.");
                        else if(!researcher.getEquipmentBag().checkType(answer, equipmentStorage)) throw new IncorrectBagContentsException("*** Researchers cannot carry different types of equipment in their bags. ");

                    }else{
                        isDone = true;
                        System.out.println("The bag is full. Researcher " + researcher.getId() + " cannot take any more equipment.");
                        System.out.println("- The final contents of the bag of Researcher "+ researcher.getId()+": " + researcher.getEquipmentBag().toString()+"\n");
                    }
                } 
                catch (UnavailableEquipmentException e) {
                    System.out.println(e.getMessage());
                }
                catch (IncorrectBagContentsException ex){
                    System.out.println(ex.getMessage());

                }
            }

        }

        /**
         * Handles the movement of a researcher on the frozen lake.
         * The researcher can slide in a specified direction, perform experiments, or stop moving.
         * 
         * @param researcher The researcher who is moving on the lake.
         */
        private void handleMovement(Researcher researcher){
            System.out.println("=====> Researcher " + researcher.getId() + " heads out to the lake. Select a direction to slide ([U] Up, [D] Down, [L] Left, [R] Right):");
            handleSliding(researcher, true);
            lake.printLake();
            boolean isContinue = true;
            while(isContinue){
                
                System.out.println("=====> Researcher " + researcher.getId() + " manages to stop safely.  \r\n" + //
                                        "[1] Continue moving on the ice. \r\n" + //
                                        "[2] Choose experiment equipment and perform an experiment. \r\n" + //
                                        "[3] Sit on the ground and let the other researchers head out to the lake. \r");
                System.out.println("Choose the action of Researcher " + researcher.getId() + ":");
                
                String action = scanner.nextLine();
                switch (action) {
                    case "1":
                        System.out.println("Select a direction to slide:");
                        handleSliding(researcher, false);
                        if(researcher.isTired()) {
                            isContinue = false;
                            equipmentStorage.insertAllEquipment(researcher.getEquipmentBag().getEquipmentBagList());
                        }
                        lake.printLake();
                        break;
                    case "2":
                        if (researcher.getEquipmentBag().containsResearcherEquipment()) {
                            handlePerformingExperimentFromBag(researcher);
                            lake.printLake();
                        }
                        else {
                            System.out.println("=====> Researcher " + researcher.getId() + " is not carrying any research equipment.");
                        }
                        break;
                    case "3":
                        System.out.println("=====> Researcher " + researcher.getId() + " manages to stop safely.");
                        isContinue = false;
                        break;
                    default:
                        System.out.println("Invalid input. Please reenter your input:");
                        break;
                }
                if(completedExperimentsList.containsAll(experimentsGoals)) isContinue = false;
            }
        }

        /**
         * Handles the sliding of a researcher on the lake based on user input.
         * The researcher can slide in one of four directions: up, down, left, or right.
         * If the direction is invalid, the user is prompted to reenter their input.
         * 
         * @param researcher The researcher who is sliding on the lake.
         * @param isFirstSlide A boolean indicating if this is the first slide attempt.
         *                     If true, an invalid direction will throw an UnavailableDirectionException.
         */
        private void handleSliding(Researcher researcher, boolean isFirstSlide){
            boolean isSlide = true;
            while(isSlide){
                String direction = scanner.nextLine().toUpperCase();
                try{
                    switch (direction) {
                        case "U":
                            if(isFirstSlide) throw new UnavailableDirectionException("Invalid input. Please reenter your input:");
                            researcher.slide(Direction.UP, lake);
                            isSlide = false;
                            break;
                        case "D":
                            researcher.slide(Direction.DOWN, lake);
                            isSlide = false;
                            break;
                        case "L":
                            researcher.slide(Direction.LEFT, lake);
                            isSlide = false;
                            break;
                        case "R":
                            researcher.slide(Direction.RIGHT, lake);
                            isSlide = false;
                            break;
                        default:
                            System.out.println("Invalid input. Please reenter your input:");
                            break;
                    }
                } catch (UnavailableDirectionException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        /**
         * Handles the process of performing an experiment using equipment from the researcher's bag.
         * 
         * @param researcher The researcher who will perform the experiment.
         * @throws UnavailableDirectionException if the researcher is not carrying any research equipment.
         * @throws UnavailableEquipmentException if the specified equipment is not found in the researcher's bag.
         */
        public void handlePerformingExperimentFromBag(Researcher researcher){
            boolean isDone = false;
            while(!isDone){
                try {
                    for (int i =0; i< researcher.getEquipmentBag().size();i++){
                        if(!(researcher.getEquipmentBag().getEquipmentBagList().get(i) instanceof ResearchEquipment)) throw new UnavailableDirectionException("*** Researcher " +researcher.getId()+" is not carrying any research equipment.");
                    }
                    Scanner keyboard = new Scanner(System.in);
                    System.out.println("=====> Enter the name of the research equipment: ");
                    String answer = keyboard.next();
                    if(!researcher.getEquipmentBag().contains(answer)) throw new UnavailableEquipmentException("*** There is no such equipment in the bag.");
                    Experiment completedExperiment = researcher.performExperiment(( (ResearchEquipment) researcher.getEquipmentBag().getEquipmentWithString(answer)).getRelatedExperiment(), lake, ((LakeSquare)lake.getSquare(researcher.getCurrentRow(), researcher.getCurrentColumn())));
                    if(completedExperiment != null){
                        completedExperimentsList.add(completedExperiment);
                        
                    }
                    isDone = true;
                      
                } catch (UnavailableDirectionException | UnavailableEquipmentException e) {
                    System.out.println(e.getMessage());

                }
            }
           
        }
    }  
}
