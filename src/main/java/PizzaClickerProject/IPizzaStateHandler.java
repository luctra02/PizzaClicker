package PizzaClickerProject;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public interface IPizzaStateHandler {
    
    void writePizzaState(String filename, Factory factory) throws FileNotFoundException;

    Factory readPizzaState(String filename) throws FileNotFoundException;

}
