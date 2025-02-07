package TicketBookingApp;
import FileAccess.FileIO;
import TicketBookingDomain.Customer;
import TicketBookingDomain.Query;
import TicketBookingDomain.Section;
import TicketBookingDomain.Ticket;
import TicketBookingDomain.Venue;



/**
 * The TicketBookingApplication class is the main entry point for the ticket booking application.
 * It reads customer data from a CSV file, assigns random tickets to customers, and prints the 
 * booked tickets, section occupancies and queries.
 * @author 300201044 Ekin Ece Bayrak
 * @author 300201051 Ayşenur Sivaslıgil
 * @author 300201052 Mustafa Erkoca
 * @author 310201060 Büşra Şeyma Küyner
 */

public class TicketBookingApplication {

    
    public static void main(String[] args) throws Exception {
        FileIO file = new FileIO(); 
        Customer[] customers = new Customer[20];
        customers = file.readFile("src/customers.csv");
        Venue venue = new Venue();
        
        // assign random tickets and add to customer ticket array
        for (Customer customer : customers){
            Section randomSection =  venue.decideRandomSection();
            System.out.println(customer.getname()+"'s Booked Tickets: ");
            for(int i = 0 ; i < customer.getBookedTickets(); i++){
                Ticket randomTicket = randomSection.decideRandomUnbookedTicket();
                customer.addTicket(randomTicket, i);
                System.out.println("Ticket: "+ i +" " +randomTicket.displayTicket()); // print the booked tickets

            }
        }

        // print the occupancies of each section
        for (int j = 0; j < 4; j++) {
            venue.getSections()[j].occupancies(j);
        }

        // print the queries
        Query query = new Query();
        query.print(customers, venue);
        
    }

        
}

    




