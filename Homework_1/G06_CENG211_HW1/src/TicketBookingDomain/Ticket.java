package TicketBookingDomain;

/**
 * The Ticket class represents a ticket in the ticket booking domain.
 * It contains information about the section number, row number, seat number, price, and whether the ticket is booked or not.
 */

public class Ticket {
    private int sectionNumber;
    private int rowNumber;
    private int seatNumber;
    private double price;
    private boolean isBooked = false;

    // Default Constructor
    public Ticket() {}

    // Parameterized Constructor
    public Ticket(int sectionNumber, int rowNumber, int seatNumber, double price, boolean isBooked) {
        this.sectionNumber = sectionNumber;
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.price = price;
        this.isBooked = isBooked;
    }
    
    // Copy Constructor
    public Ticket(Ticket aTicket) {
        if (aTicket == null) {
            System.out.println("Fatal Error.");
            System.exit(0);
        }
        this.sectionNumber = aTicket.sectionNumber;
        this.rowNumber = aTicket.rowNumber;
        this.seatNumber = aTicket.seatNumber;
        this.price = aTicket.price;
        this.isBooked = aTicket.isBooked;
    }

    //getter and setter methods
    public boolean getIsBooked() {
        return isBooked;
    }

    public void setBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    public int getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(int sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
    // This method displays the ticket information that is section number, row number, seat number, and price.
    public String displayTicket() {
        return "Section: " + sectionNumber + " Row: " + rowNumber + " Seat: " + seatNumber + " - " + price + " TL";
    }

    // Override the toString method and display the ticket's section number, row number, seat number, price, and whether it is booked or not.
    public String toString() {
        return "Ticket: Section: " + sectionNumber + " Row: " + rowNumber + " Seat: " + seatNumber + " - " + price + " TL" + " isBooked: " + isBooked;
    }

    // Override the equals method and compare the section number, row number, seat number, price, and whether the ticket is booked or not.
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Ticket ticket = (Ticket) obj;
        return sectionNumber == ticket.sectionNumber && 
                rowNumber == ticket.rowNumber &&
                seatNumber == ticket.seatNumber &&
                Double.compare(ticket.price, price) == 0 &&
                isBooked == ticket.isBooked;
    }
}
