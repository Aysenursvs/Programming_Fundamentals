/**
 * The Query class provides methods to generate various reports and statistics
 * related to ticket booking, such as highest revenue section, total revenue,
 * occupancy rate, highest paying customer, and the most expensive ticket.
 */
package TicketBookingDomain;


public class Query {


    public void print(Customer[] customer, Venue venue) {
        //The highest revenue section
        Section highestRevenueSection = sectionsHighestRevenue(venue);
        System.out.println("Highest Revenue Section: " + highestRevenueSection.getId());

        //Total revenue
        double totalRevenue = totalRevenue(venue);
        System.out.println("Total Revenue: " + totalRevenue + " TL");

        //Occupancy rate of the venue
        double occupancyRate = occupancyRateOfVenue(venue);
        System.out.println("Total Occupancy Rate: " + occupancyRate + "%");

        // The highest paying customer
        Customer highestPayingCustomer = getHighestPayingCustomer(customer);
        System.out.println("Highest Paying Customer: " + highestPayingCustomer.getname());

        // The most expensive ticket
        Ticket mostExpensiveTicket = mostExpensiveTicket(venue, customer);
        System.out.println("The Most Expensive Ticket's Occupancy and Price: Section: " + mostExpensiveTicket.getSectionNumber() + " Row: " + mostExpensiveTicket.getRowNumber() + " Seat: " + mostExpensiveTicket.getSeatNumber() + " Price: " + mostExpensiveTicket.getPrice() );

    }

    //Methods to generate reports and statistics
    public Section  sectionsHighestRevenue(Venue venue){
        return new Section(venue.getSectionWithHighestRevenue()) ;
    }

    public double totalRevenue(Venue venue){
        return venue.getTotalRevenue() ;
    }
    
    public double occupancyRateOfVenue(Venue venue){
        return venue.getVenueOccupancyRate() ;
    }
    
    public Ticket mostExpensiveTicket(Venue venue, Customer[] customers){
        return new Ticket(venue.getMostExpensiveTicket(customers)) ;
    }

    //Method to find the highest paying customer
    public Customer getHighestPayingCustomer(Customer[] customers) {
        if (customers == null || customers.length == 0) {
            return null; // If there are no customers, return null
        }

        Customer highestPayingCustomer = customers[0];  //Choose the first customer as the highest paying customer firstly
        double highestTotalPrice = highestPayingCustomer.getTotalTicketPrice();

        // Traverse the remaining customers
        for (int i = 1; i < customers.length; i++) {
            if (customers[i] != null && (customers[i].getTotalTicketPrice() > highestTotalPrice)) { //
                highestPayingCustomer = customers[i]; 
                highestTotalPrice = customers[i].getTotalTicketPrice();
            }
        }

        return new Customer(highestPayingCustomer); 
    }
}
