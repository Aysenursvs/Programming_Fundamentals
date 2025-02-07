
package TicketBookingDomain;
/**
 * The Customer class represents a customer in the ticket booking domain.
 * It contains information about the customer's name, the number of booked tickets,
 * and an array of Ticket objects representing the tickets booked by the customer.
 */

public class Customer {
    private String name;
    private int numberOfBookedTickets;
    private Ticket[] tickets = new Ticket[numberOfBookedTickets];
    // Default Constructor
    public Customer() {
    }
    // Parameterized Constructor
    public Customer(String name, int numberOfBookedTickets, Ticket[] tickets) {
        this.name = name;
        this.numberOfBookedTickets = numberOfBookedTickets;
        this.tickets = tickets;
    }
    // Copy Constructor
    public Customer(Customer aCustomer) {
        if (aCustomer == null) {
            System.out.println("Fatal Error.");
            System.exit(0);
        }
        this.name = aCustomer.name;
        this.numberOfBookedTickets = aCustomer.numberOfBookedTickets;
        this.tickets = aCustomer.tickets;
    }

    //getter and setter methods
    public String getname() {
            return name;
        }
    
    public void setName(String name) {
            this.name = name;
        }
    
    public int getBookedTickets() {
            return numberOfBookedTickets;
        }
    
    public void setBookedTickets(int numberOfBookedTickets) {
            this.numberOfBookedTickets = numberOfBookedTickets;
        }

    public Ticket[] getTickets() {
        //deep copy of the tickets array
        Ticket[] tempTickets = new Ticket[numberOfBookedTickets];
        for (int i = 0; i < numberOfBookedTickets; i++) {
            tempTickets[i] = new Ticket(tickets[i]);
        }
        return tempTickets;
    }

    public void setTickets(Ticket[] tickets) {
        this.tickets = tickets;
    }

    // This method adds a ticket to the customer's ticket array at the specified index.
    public void addTicket(Ticket ticket, int index) {
        if (index < numberOfBookedTickets) {
            tickets[index] = ticket; // Add the ticket to the array
        }
    }

    // This method calculates the total price of the tickets booked by the customer.
    public double getTotalTicketPrice() {
        double customersTotalPrice = 0;  
        for (Ticket ticket : tickets) {
            if (ticket != null) {  
                customersTotalPrice += ticket.getPrice();  
            }
        }

        return customersTotalPrice;
    }

    // This method returns a string representation of the customer object.
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer Name: ").append(name).append("\n");
        sb.append("Booked Tickets: ").append(numberOfBookedTickets).append("\n");
        sb.append("Tickets: \n");
        for (Ticket ticket : tickets) {
            if (ticket != null) {
            sb.append(ticket.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    // This method checks if two customer objects are equal or not.
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Customer customer = (Customer) obj;
        return name.equals(customer.name) && numberOfBookedTickets == customer.numberOfBookedTickets && tickets.equals(customer.tickets);
    }
}
