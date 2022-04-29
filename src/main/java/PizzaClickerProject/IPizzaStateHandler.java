package PizzaClickerProject;

import java.io.FileNotFoundException;


public interface IPizzaStateHandler {
    
    void writePizzaState(String filename, Factory factory) throws FileNotFoundException;

    Factory readPizzaState(String filename) throws FileNotFoundException;

}
