package PizzaClicker;

import PizzaClickerProject.PizzaStateHandler;

import static org.junit.jupiter.api.Assertions.*;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;



import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import PizzaClickerProject.Factory;
import PizzaClickerProject.IPizzaStateHandler;

public class FileTest {
    private PizzaStateHandler ReaderWriter;
    private Factory Factory;

    private static final String test_save_file = """
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
            """.replaceAll("\\R", System.getProperty("line.separator"));

    private static final String invalid_save_file = """
            lucaspai's factory
            Deliverer
            [1.0, 50.0]
            Manager
            [125.0, 50000.0]
            CEO
            [625.0, 500000.0, 1.0, 187500.0, 1.0]
            """.replaceAll("\\R", System.getProperty("line.separator"));

    
        /* @BeforeAll
        public void setup() throws IOException {
            Files.write(ReaderWriter.getFile("test_receipt").toPath(), test_receipt_file.getBytes());
            Files.write(ReaderWriter.getFile("invalid_receipt").toPath(),
                    invalid_receipt_file.getBytes());
        }  */

        @BeforeEach
        public void setup() {
            Factory = new Factory("lucaspai");
            ReaderWriter = new PizzaStateHandler();
        }

    @Test
    public void testWriteSaveVariables() throws FileNotFoundException {
        ReaderWriter.writePizzaState("test", Factory);
        Factory FactoryCopy = ReaderWriter.readPizzaState("test"); 
        assertEquals(0, FactoryCopy.getCurrentBalance());
    }

    @Test
    public void testLoadNonExistingFile() {
        assertThrows(
                IOException.class,
                () -> ReaderWriter.readPizzaState("non_existing_file"),
                "IOException should be thrown if file does not exist!");
    }

    @Test
    public void testReadInvalidReceipt() {
        assertThrows(
                IllegalArgumentException.class,
                () -> ReaderWriter.readPizzaState("invalid_save"),
                "Only IllegalArgumentException should be thrown if content of file is invalid!");
    }
}
