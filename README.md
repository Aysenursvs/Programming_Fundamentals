# Object Oriented Programming Homework Assignments #

This repository contains the four homework assignments given in the "Fundamentals of Programming" course during my second year in Computer Engineering. The course focused on Object-Oriented Programming (OOP) principles, and Java was used as the programming language. Each assignment was designed to reinforce different aspects of OOP, including encapsulation, inheritance, polymorphism, abstract classes, interfaces, and exception handling.

## Homework 1: Ticket Booking Application 

### Summary: 
In this homework, a "Ticket Booking Application" was implemented using Java. The project required handling customer bookings for a venue with four sections, each containing 600 seats. Key OOP concepts used included defining classes, constructors, getters & setters, and working with arrays and CSV file I/O.
Each ticket had attributes such as section number, row number, seat number, price, and booking status. Pricing was determined based on seat location and randomized within predefined boundaries. Customers booked random tickets, and the data was loaded from a CSV file.
The program implemented classes for Ticket, Section, Venue, Customer, Query, and FileIO. Queries included:

1.	Identifying the section with the highest revenue.

2.	Calculating the total revenue of the venue.

3.	Determining the venue's occupancy rate.

4.	Finding the customer who paid the highest total price for their tickets.

5.	Identifying the most expensive ticket.

Additionally, the application displayed section occupancies using 'X' for booked tickets and 'O' for available seats. No user input was required, and standard Java I/O was used for file reading.

## Homework 2: Digital Treasure Hunt Game

### Summary: 
This homework involved implementing a "Digital Treasure Hunt" game in Java. The game is played on a 20x20 grid-based Map, where each cell contains a MapItem. The main objective is for the Player to navigate the map, collecting boosters to increase points and lives while avoiding breakers that reduce them.

### Key OOP Concepts Applied:

•	**Defining Classes & Inheritance:** MapItem serves as the base class for all game elements, including Player, Booster, and Breaker. Booster further has subclasses like Coin, Diamond, and Treasure, while Breaker has Mushroom and Frog.

•	**Encapsulation & Getters/Setters:** Each class contains appropriate encapsulated fields with getter and setter methods.

•	**ArrayLists & Collections:** The Map is represented using a two-dimensional ArrayList<ArrayList<MapItem>> to store the objects dynamically.

### Game Mechanics:

•	The game randomly places objects on the map, ensuring no two objects occupy the same MapPosition.

•	The Player starts with 100 points and 2 lives.

•	With each move, the Player randomly selects a new position on the map.

•	If the Player lands on:

    o	Coin: Gains 5 points.

    o	Diamond: Gains 10 points.

    o	Treasure: Gains 1 life.

    o	Mushroom: Loses 20 points.

    o	Frog: Loses 1 life.

    o	Booster: Contains a Coin, Diamond, and Treasure, granting their respective benefits.

    o	Breaker: Contains a Mushroom and Frog, applying their respective penalties.

•	The game ends when either points or lives drop to 0.

•	After each move, the game map is printed to the console.

•	A Scoreboard keeps track of all movements and interactions, and the results are saved to a .txt file at the end.
### Additional Considerations:

•	No user input is required; the game runs automatically.

•	The project strictly follows OOP best practices, including reusability, modular design, and clear separation of concerns.

•	File Handling: Scores are written to a text file using Java’s built-in java.io package.

•	UTF-8 Encoding is recommended to ensure proper text formatting.
## Homework 3: Garden Puzzle App
### Summary: 
In this homework, a 'Garden Puzzle App' was implemented in Java. The project focused on creating a brain teaser puzzle game where a gardener strategically places garden objects to achieve specific pollen and color conditions at a target location.
### Key OOP Concepts Applied:

•	**Interfaces & Abstract Classes:** Implemented Searchable interface to allow searching for GardenPlants and LightSources.

•	**Inheritance & Polymorphism:** GardenObjects were categorized into GardenPlant, LightSource, and Statue, with further subclasses like Flower, Tree, Bush, SmallLamp, LargeLamp, and Spotlight.

•	**Collections & File Handling:** Used List and ArrayList for managing garden squares and loaded objects from a CSV file.

### Game Mechanics:

•	The game initializes an 8x6 Garden grid with a predefined target square where specific pollen types and colors must reach.

•	A StorageShed holds GardenObjects read from storage_contents.csv.

•	Statues (7 in total) are randomly placed in the garden at initialization.

•	The gardener selects 7 GardenObjects from the StorageShed, considering their effects on pollen spread and light infusion.

•	Placed objects influence pollen and light spread according to predefined rules:

    o	Flowers spread pollen diagonally.

    o	Trees spread pollen horizontally.
    
    o	Bushes spread pollen vertically.

    o	Light sources infuse pollen with colors based on their type.

•	Statues block both pollen and light spread.

•	Once all objects are placed, the game determines success or failure based on whether the target square meets the required conditions.
### Additional Considerations:
•	**Encapsulation & Modularity:** Each class has well-defined responsibilities.

•	**Randomization:** Game setup varies with different initial conditions.

•	**No User Input Required:** The game runs automatically and prints results to the console.

•	Randomization: Game setup varies with different initial conditions.
•	No User Input Required: The game runs automatically and prints results to the console.

## Homework 4: Frozen Lake Puzzle App

### Summary:

In this assignment, you are expected to develop a puzzle application simulating a group of Arctic researchers trying to perform experiments while avoiding various hazards on a frozen lake. The researchers need specific types of research equipment to complete their experiments while avoiding dangers like ice holes, ice spikes, and cliff edges. The lake is simulated as an 8x11 grid, with researchers moving and interacting with equipment and hazards.

### Key OOP Concepts Applied:

•	**Collections:** Collections like Set, Queue, and Map are used for managing the equipment, experiments, and researchers. Researchers are stored in a Queue because they move in a sequence.

•	**Inheritance:** Equipment, hazards, and experiment types will be structured using inheritance. For example, ResearchEquipment and HazardEquipment are parent classes, with specific subclasses like TemperatureDetector or ClimbingEquipment.

•	**Generics:** Generic types like EquipmentBag<T extends Equipment> are used to store specific types of equipment for researchers.

•	**Exception Handling:** Various custom exceptions such as IncompatibleResearchEquipmentLocationException and IncorrectBagContentsException will handle invalid actions like placing equipment in incompatible locations or carrying incorrect equipment.

•	**Inner Classes:** Inner classes are used to manage specific hazards and equipment within the frozen lake, such as Hazard with subclasses like IceSpikes.

### Game Mechanics:

•	**Lake Initialization:** The frozen lake is initialized as an 8x11 grid, with the middle of the top row as the entrance. Various hazards like ice holes, ice spikes, and cliff edges are randomly placed.

•	**Research Equipment Placement:** Equipment like TemperatureDetector and WindSpeedDetector is placed on the lake according to specific rules. For example, a TemperatureDetector cannot be placed next to ice blocks.

•	**Researcher Actions:** Researchers can slide in four directions across the lake. If they encounter a hazard, they can use the appropriate equipment to overcome it.

•	**Hazard Management:** Hazards like ice holes and cliff edges can be overcome using specific equipment like LargeWoodenBoard or ClimbingEquipment.

•	**End Game Conditions:** The game ends successfully when all experiments are completed. The game fails if a researcher is injured by a hazard or if there are unaccomplished experiment goals when all researchers stop.
### Additional Information:

•	Ice blocks are non-hazardous obstacles that researchers can use to stop while sliding.

•	Researchers can carry a maximum of three pieces of equipment, which they can use to overcome hazards or perform experiments.

•	Equipment is shared among researchers, and the researchers collaborate to complete their experiments.

•	The game presents a random scenario each time, based on the placement of hazards, equipment, and research goals.

