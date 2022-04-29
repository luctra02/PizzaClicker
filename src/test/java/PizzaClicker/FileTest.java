package PizzaClicker;

import PizzaClickerProject.PizzaStateHandler;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import PizzaClickerProject.Factory;

public class FileTest {
    private PizzaStateHandler ReaderWriter;
    private Factory Factory;



        @BeforeEach
        public void setup() {
            Factory = new Factory("lucaspai");
            ReaderWriter = new PizzaStateHandler();
        }
    
    /* 
    Slik ser valid_save_test.txt ut:
    
    lucaspai's factory
    12.0
    3
    Deliverer
    [1.0, 50.0, 1.4, 300.0, 4.0]
    Manager
    [125.0, 50000.0, 1.0, 37500.0, 2.0]
    CEO
    [625.0, 500000.0, 1.0, 187500.0, 1.0]
    Chef
    [25.0, 5000.0, 1.0, 7500.0, 3.0]
    Cashier
    [5.0, 500.0, 1.2, 1500.0, 5.0] 
    
    Slik ser invalid_save_test.txt ut:

    lucaspai's factory
    Deliverer
    [1.0, 50.0]
    Manager
    [125.0, 50000.0]
    CEO
    [625.0, 500000.0, 1.0, 187500.0, 1.0]
    */
    
    @Test
    public void testLoadSaveFile(){
            
    }

    @Test
    public void testWriteSaveVariables() throws FileNotFoundException {
        Factory.setCurrentBalance(10000);
        ReaderWriter.writePizzaState("test", Factory);
        Factory FactoryCopy = ReaderWriter.readPizzaState("test"); 
        assertEquals(FactoryCopy.getCurrentBalance(), Factory.getCurrentBalance());
        assertEquals(FactoryCopy.getFactoryName() , Factory.getFactoryName());
        assertEquals(FactoryCopy.getCoinsPerClick() , Factory.getCoinsPerClick());
        assertEquals(FactoryCopy.getWorkerAmount(3), Factory.getWorkerAmount(3));
        assertEquals(FactoryCopy.getTotalPizzaPerSec(), Factory.getTotalPizzaPerSec());

    }

    @Test
    public void testLoadNonExistingFile() {
        assertThrows(
                IOException.class,
                () -> ReaderWriter.readPizzaState("non_existing_file"),
                "IOException should be thrown if file does not exist!");
    }

    @Test
    public void testReadInvalidSave() {
        assertThrows(
                IllegalArgumentException.class,
                () -> ReaderWriter.readPizzaState("/TestSaves/invalid_save_test"),
                "Only IllegalArgumentException should be thrown if content of file is invalid!");
    }


}
