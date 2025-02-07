package FileAccess;

import TicketBookingDomain.Customer;
import TicketBookingDomain.Ticket;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


/**
 * The FileIO class provides methods to read customer data from a file.
 * It reads the file line by line, tokenizes each line, and creates Customer objects.
 * The customers are stored in an array and returned as a copy.
 * @author 300201044 Ekin Ece Bayrak
 * @author 300201051 Ayşenur Sivaslıgil
 * @author 300201052 Mustafa Erkoca
 * @author 310201060 Büşra Şeyma Küyner
 */

public class FileIO {
    StringTokenizer st;
    Customer[] customers = new Customer[20];
    //This method takes the customer data from the customers.csv file by tokens and creates Customer objects.
    //It returns a copy of the array of Customer objects.
    public Customer[] readFile(String relativePath) {
        File file = new File(relativePath);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine();
            int tokenIndex = 0;
            while ((line = br.readLine()) != null) {
                st = new StringTokenizer(line, ",");
                while (st.hasMoreTokens()) {
                    String token = st.nextToken();
                    Customer customer = new Customer(token, Integer.parseInt(st.nextToken()), new Ticket[25]);// create a new customer
                    customers[tokenIndex/2] = customer;
                    tokenIndex = tokenIndex + 2; 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //deep copy of the customers array
        Customer[] tempCustomers = new Customer[20];
        for (int i = 0; i < 20; i++) {
            tempCustomers[i] = new Customer(customers[i]);
        }
        return tempCustomers;
    }
}
