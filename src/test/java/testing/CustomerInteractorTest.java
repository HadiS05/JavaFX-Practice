package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.bn.CustomerInteractor;
import com.bn.CustomerModel;


public class CustomerInteractorTest {
    @Test
    void customerInteractor_NameTest(){
        CustomerModel model = new CustomerModel();
        model.setAccountName("Freddy");
        model.setAccountNumber("1234");
        CustomerInteractor interactor = new CustomerInteractor(model);
        
        assertEquals("Freddy", interactor.customerFromModel().getName(), "Name found.");
    }

    @Test
    void customerInteractor_AccountNumberTest(){
        CustomerModel model = new CustomerModel();
        model.setAccountName("Freddy");
        model.setAccountNumber("1234");
        CustomerInteractor interactor = new CustomerInteractor(model);
        
        assertEquals("1234", interactor.customerFromModel().getAccount(), "Account found.");
    }
}
