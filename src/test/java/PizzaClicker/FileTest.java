package PizzaClicker;

import PizzaClickerProject.PizzaStateHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.Reader;

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

    @Test
    public void testWriteSaveVariables() throws FileNotFoundException {
        ReaderWriter.writePizzaState("test", Factory);
        Factory FactoryCopy = ReaderWriter.readPizzaState("test"); 
        assertEquals(0, FactoryCopy.getCurrentBalance());
    }

}
