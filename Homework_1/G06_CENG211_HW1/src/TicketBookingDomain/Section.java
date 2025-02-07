/**
 * The Section class represents a section in a ticket booking system.
 * It manages the tickets, prices, and occupancy rates for a specific section.
 * 
 * Each section has a unique ID, a maximum price, a minimum price, and a 2D array of tickets.
 * The class provides methods to set price boundaries, create tickets, calculate revenue,
 * determine occupancy rates, and decide on a random unbooked ticket.
 * 
 * It includes constructors for creating a section with default values, with specific values,
 * and by copying another section. It also provides methods to get and set various properties,
 * and to check seat occupancies.
 * 
 * 
 */
package TicketBookingDomain;
import java.util.Random;

public class Section {
    private int id;
    private static final int NUMBER_OF_ROWS = 10;
    private static final int NUMBER_OF_SEATS = 60;
    private int maxPrice;
    private int minPrice;
    private double revenue;
    private double occupancyRate;
    Ticket[][] tickets = new Ticket[NUMBER_OF_ROWS][NUMBER_OF_SEATS];
    Random random = new Random();

    // Default Constructor
    public Section() {
    }
    // Parameterized Constructor
    public Section(int id, int maxPrice, int minPrice, Ticket[][] tickets) {
        this.id = id;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.tickets = tickets;
    }
    // Copy Constructor
    public Section(Section aSection) {
        if (aSection == null) {
            System.out.println("Fatal Error.");
            System.exit(0);
        }
        this.id = aSection.id;
        this.maxPrice = aSection.maxPrice;
        this.minPrice = aSection.minPrice;
        this.tickets = aSection.tickets;
    }

    //  Constructor for creating a section with a specific ID
    public Section(int id) {
        this.id = id;
        setPriceBoundries(id);
        createTicketsOfSection();
    }

    // Method to set price boundaries for a section
    private void setPriceBoundries(int id){
        int sectionOffset = this.id * 500;  
        int maxPriceLowerBound = 4000 - sectionOffset;
        int minPriceLowerBound = 3000 - sectionOffset;

        //Use a while loop to determine the maximum price
        maxPrice = -1;  //Assign -1 temporarily
        while (maxPrice < maxPriceLowerBound || maxPrice >= maxPriceLowerBound + 1001) {
            maxPrice = random.nextInt(1001) + maxPriceLowerBound;  // Check if it is in the price range
    }

        // Use a while loop to determine the minimum price
        minPrice = -1;  // Assign -1 temporarily
        while (minPrice < minPriceLowerBound || minPrice >= minPriceLowerBound + 1000) {
            minPrice = random.nextInt(1000) + minPriceLowerBound;  // Check if it is in the price range
    }
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }
    
    public int getNumberOfRows() {
        return NUMBER_OF_ROWS;
    }

    public int getNumberOfSeats() {
        return NUMBER_OF_SEATS;
    }

    public Ticket[][] getTickets() {
        //deep copy of the tickets array
        Ticket[][] tempTickets = new Ticket[NUMBER_OF_ROWS][NUMBER_OF_SEATS];
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < NUMBER_OF_SEATS; j++) {
                    tempTickets[i][j] = new Ticket(tickets[i][j]);
            }
        }
           return tempTickets;
    }

    // Method to calculate the revenue of a section
    public double getRevenue() {
        // Calculate the revenue by traversing the tickets array
        for(int i = 0; i < Section.NUMBER_OF_ROWS; i++){
            for(int j = 0; j < Section.NUMBER_OF_SEATS; j++){
                if(tickets[i][j].getIsBooked() == true){
                    revenue += tickets[i][j].getPrice();
                }
            }
        }
        return revenue;
    }

    // Method to calculate the occupancy rate of a section
    public double getOccupancyRate() {
        // Calculate the occupancy rate by traversing the tickets array
        double totalOccupancy = 0;
        for(int i = 0; i < Section.NUMBER_OF_ROWS; i++){
            for(int j = 0; j < Section.NUMBER_OF_SEATS; j++){
                if(tickets[i][j].getIsBooked() == true){
                    totalOccupancy++;
                }
            }
        }
        occupancyRate = totalOccupancy / (Section.NUMBER_OF_ROWS * Section.NUMBER_OF_SEATS);
        return occupancyRate;
    }

    // Method to decide on a random unbooked ticket
    public Ticket decideRandomUnbookedTicket() {
        Ticket selectedTicket = null;
        while (true) {
            int row = random.nextInt(NUMBER_OF_ROWS);
            int seat = random.nextInt(NUMBER_OF_SEATS);
            selectedTicket = tickets[row][seat];
            
            // If ticket is not booked, book it and return
            if (selectedTicket != null && !selectedTicket.getIsBooked()) {
                selectedTicket.setBooked(true);
                return new Ticket(selectedTicket);
            }
        }
    }


    // Method to create tickets for a section
    public void createTicketsOfSection() {
        // Create tickets for each row and seat by using a nested loop
        for (int i = 0; i < Section.NUMBER_OF_ROWS ; i++) {
            for (int j = 0; j < Section.NUMBER_OF_SEATS; j++) {
                switch (i) {
                    case 0:
                        tickets[i][j] = new Ticket(this.id, i, j, this.maxPrice, false);
                        break;
                    case 1:
                        tickets[i][j] = new Ticket(this.id, i, j, this.maxPrice*0.80, false);
                        break;
                    default: 
                        int price = random.nextInt(this.maxPrice - this.minPrice + 1) + this.minPrice;
                        tickets[i][j] = new Ticket(this.id, i, j, price, false);
                        break;
                    }
                }
            }
            
        }
    
    // Method to display the seat occupancies in a section with X for booked seats and O for unbooked seats
    public void occupancies(int id){
        System.out.println("Seat occupancies in Section " + id + ":");
        for(int i = 0; i < Section.NUMBER_OF_ROWS; i++){
            for(int j = 0; j < Section.NUMBER_OF_SEATS; j++){
                if(tickets[i][j].getIsBooked() == true){
                    System.out.print("X" + " ");
                }else{
                    System.out.print("O" + " ");
                }
                
            }
            System.out.println();
    }
    }

    //Override the toString method and display the section's id, max price, and min price
    public String toString() {
        return "Section: id: " + id + " maxPrice: " + maxPrice + " minPrice: " + minPrice;
    }

    //Override the equals method and compare the id, max price, and min price of two sections
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Section section = (Section) obj;
        return id == section.id &&
               maxPrice == section.maxPrice &&
               minPrice == section.minPrice;
    }
}
