package testing;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bn.CustomerDatabase;
import com.bn.DuplicateCustomerException;

public class CustomerDatabaseTest {
    CustomerDatabase database;

    @BeforeEach
    void init() {
        // For tidying up, we'll initiate a new database in this function
        // before each test
        database = new CustomerDatabase();
    }
    @Test
    void saveCustIDTest(){
        assertEquals(1, database.saveCustomer(new HashMap<>()), "Id added");
    }

    @Test
    void saveCustIDTwiceTest(){
        database.saveCustomer(new HashMap<>());
        assertEquals(2, database.saveCustomer(new HashMap<>()), "Two id's added");
    }

    @Test 
    void getCustID(){
        database.saveCustomer(new HashMap<>());
        assertEquals("1", database.getData().get(1).get("_id"), "id is found");
    }

    @Test 
    void getCustIDTwice(){
        database.saveCustomer(new HashMap<>());
        database.saveCustomer(new HashMap<>());
        assertEquals("2", database.getData().get(2).get("_id"), "id is found");
    }

    @Test
    void correctCustomer(){
        Map<String, String> customerrecord = new HashMap<>();
        customerrecord.put("name", "Freddy");
        database.saveCustomer(customerrecord);
        assertEquals("Freddy", database.getData().get(1).get("name"), "Freddy found.");
    }

    @Test
    void findAccountTest(){
        database.saveCustomer(createDean());
        database.saveCustomer(createFrank());
        assertTrue(database.isAccountOnFile("1234"), "Dean found.");
    }

    @Test
    void findAccountTestNotFound(){
        database.saveCustomer(createDean());
        database.saveCustomer(createFrank());
        assertFalse(database.isAccountOnFile("9999"), "No account here.");
    }

    @Test
    void saveWithoutNumber(){
        Map<String, String> record = new HashMap<>();
        record.put("name", "Freddy");
        assertThrows(IllegalArgumentException.class, () -> database.saveNewCustomer(record));
    }

    @Test
    void saveAccountTwice(){
        database.saveCustomer(createDean());
        assertThrows(DuplicateCustomerException.class, () -> database.saveNewCustomer(createDean()));
    }

    public Map<String, String> createDean(){
        Map<String, String> customerRecord = new HashMap<>();
        customerRecord.put("name", "Dean");
        customerRecord.put("account", "1234");
        return customerRecord;
    }

    public Map<String, String> createFrank(){
        Map<String, String> customerRecord = new HashMap<>();
        customerRecord.put("name", "Frank");
        customerRecord.put("account", "5678");
        return customerRecord;
    }
}
