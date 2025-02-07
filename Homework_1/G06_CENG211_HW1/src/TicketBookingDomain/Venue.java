/**
 * The Venue class represents a venue with multiple sections for ticket booking.
 * It provides methods to manage sections, calculate revenue, and determine occupancy rates.
 * 
 * This class includes constructors for creating a venue with default sections,
 * parameterized sections, and a copy constructor. It also provides methods to get
 * the number of sections, retrieve sections, calculate total revenue, determine
 * venue occupancy rate, decide a random section, get the section with the highest
 * revenue, and find the most expensive ticket among customers.
 * 
 * Additionally, the class overrides the toString and equals methods for
 * string representation and equality comparison.
 * 
 */
package TicketBookingDomain;



import java.util.Random;

public class Venue {
    private static final int NUMBER_OF_SECTIONS = 4;
    private double totalRevenue = 0;
    private Section[] sections = new Section[NUMBER_OF_SECTIONS];
    private Random random = new Random();

    // Default Constructor
    public Venue() {
        for (int i = 0; i < NUMBER_OF_SECTIONS; i++) {
            sections[i] = new Section(i);
        }
    }
    // Parameterized Constructor
    public Venue(Section[] sections, double totalRevenue) {
        this.sections = sections;
        this.totalRevenue = totalRevenue;
    }
    // Copy Constructor
    public Venue(Venue aVenue) {
        if (aVenue == null) {
            System.out.println("Fatal Error.");
            System.exit(0);
        }
        this.totalRevenue = aVenue.totalRevenue;
        this.sections = aVenue.sections;
    }

    public int getNumberOfSections() {
        return NUMBER_OF_SECTIONS;
    }

    public Section[] getSections() {
        //deep copy of the sections array
        Section[] tempSections = new Section[NUMBER_OF_SECTIONS];
        for (int i = 0; i < NUMBER_OF_SECTIONS; i++) {
                tempSections[i] = new Section(sections[i]);
            }
        return tempSections;
    }

    public double getTotalRevenue() {
        for (int i = 0; i < NUMBER_OF_SECTIONS; i++) {
            totalRevenue += sections[i].getRevenue();
        }
        return totalRevenue;
    } 

    // Calculate the venue's occupancy rate by summing the occupancy rates of all sections and dividing by the number of sections
    public double getVenueOccupancyRate() {
        if (sections == null || NUMBER_OF_SECTIONS == 0) {
            return 0;  
        }

        double totalOccupancyRate = 0;

        for (Section section : sections) {
            totalOccupancyRate += section.getOccupancyRate();
        }

        return totalOccupancyRate / NUMBER_OF_SECTIONS;
    }

    // Decide section number randomly and return it as a new Section object from the sections array
    public Section decideRandomSection() {
        int randomIndex = random.nextInt(NUMBER_OF_SECTIONS);  
        return new Section(sections[randomIndex]);  
    }

    // Find the section with the highest revenue and return it
    public Section getSectionWithHighestRevenue() {

        Section highestRevenueSection = sections[0];  
        double highestRevenue = highestRevenueSection.getRevenue(); //Firstly, assign the first section's revenue as the highest revenue

        //Traversal of the remaining sections
        for (int i = 1; i < NUMBER_OF_SECTIONS; i++) {
            if (sections[i].getRevenue() > highestRevenue) {
                highestRevenue = sections[i].getRevenue();
                highestRevenueSection = sections[i];
            }
        }

        return new Section(highestRevenueSection); 

    }

    // Find the most expensive ticket among customer array and return it
    public Ticket getMostExpensiveTicket(Customer [] customers) {
        Ticket mostExpensiveTicket = null;
        double highestPrice = 0;
        for (Customer customer : customers) {
            if (customer != null) {
                Ticket[] tickets = customer.getTickets();
                for (Ticket ticket : tickets) {
                    if (ticket != null && ticket.getPrice() > highestPrice) {
                        highestPrice = ticket.getPrice();
                        mostExpensiveTicket = ticket;
                    }
                }
            }
        }

        return new Ticket(mostExpensiveTicket);
    }

    // Override the toString method and display the venue's total revenue, number of sections, and sections.
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Venue Details:\n");
        sb.append("Total Revenue: ").append(getTotalRevenue()).append("\n");
        sb.append("Number of Sections: ").append(NUMBER_OF_SECTIONS).append("\n");
        sb.append("Sections:\n");
        for (Section section : sections) {
            sb.append(section.toString()).append("\n");
        }
        return sb.toString();
    }

    // Override the equals method and compare the total revenue and sections.
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Venue venue = (Venue) obj;
        return totalRevenue == venue.totalRevenue && sections.equals(venue.sections);
    }

}
