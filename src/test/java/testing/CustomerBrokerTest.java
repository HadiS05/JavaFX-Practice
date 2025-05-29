package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.bn.Customer;
import com.bn.CustomerBroker;

public class CustomerBrokerTest {
    @Test
    public void customerBroker_AccountNumberTest(){
        Customer customer = new Customer();
        customer.setAccount("1234");
        customer.setName("Freddy");
        CustomerBroker broker = new CustomerBroker();

        assertEquals("1234", broker.customerToRecord(customer).get("account"), "Account found.");
    }

    @Test
    public void customerBroker_NameTest(){
        Customer customer = new Customer();
        customer.setAccount("1234");
        customer.setName("Freddy");
        CustomerBroker broker = new CustomerBroker();

        assertEquals("Freddy", broker.customerToRecord(customer).get("name"), "name found");
    }
}
