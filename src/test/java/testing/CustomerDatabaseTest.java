package testing;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.bn.CustomerDatabase;

public class CustomerDatabaseTest {
    @Test
    void saveCustIDTest(){
        CustomerDatabase database = new CustomerDatabase();
        assertEquals(1, database.saveCustomer(new HashMap<>()), "Id added");
    }

    @Test
    void saveCustIDTwiceTest(){
        CustomerDatabase database = new CustomerDatabase();
        database.saveCustomer(new HashMap<>());
        assertEquals(2, database.saveCustomer(new HashMap<>()), "Two id's added");
    }

    @Test 
    void getCustID(){
        CustomerDatabase database = new CustomerDatabase();
        database.saveCustomer(new HashMap<>());
        assertEquals("1", database.getData().get(1).get("_id"), "id is found");
    }

    @Test 
    void getCustIDTwice(){
        CustomerDatabase database = new CustomerDatabase();
        database.saveCustomer(new HashMap<>());
        database.saveCustomer(new HashMap<>());
        assertEquals("2", database.getData().get(2).get("_id"), "id is found");
    }

    @Test
    void correctCustomer(){
        CustomerDatabase database = new CustomerDatabase();
        Map<String, String> customerrecord = new HashMap<>();
        customerrecord.put("name", "Freddy");
        database.saveCustomer(customerrecord);
        assertEquals("Freddy", database.getData().get(1).get("name"), "Freddy found.");
    }
}
