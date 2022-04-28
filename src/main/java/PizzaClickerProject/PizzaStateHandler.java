package PizzaClickerProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class PizzaStateHandler implements IPizzaStateHandler {

    @Override
    public void writePizzaState(String filename, Factory factory) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(getFile(filename))) {
            writer.println(factory.getFactoryName());
            writer.println(factory.getCurrentBalance());
            writer.println(factory.getCurrentPizza());
            int i = 0;
            Set<String> HashMapSet = factory.getHashMap().keySet();
            ArrayList<String> listOfKeys = new ArrayList<String>(HashMapSet);
            System.out.println(factory.getHashMap());
            System.out.println(factory.getHashMap().values());
            for (ArrayList<Double> list: factory.getHashMap().values()) {
                writer.println(listOfKeys.get(i));
                System.out.println(list);
                writer.println(list);
                i++;
            }
        }
    }

    @Override
    public Factory readPizzaState(String filename) throws FileNotFoundException {
        Factory x = new Factory("XXXXXX");
        try (Scanner reader = new Scanner(getFile(filename))) {
            int i = 0;
            x.setFactoryName(reader.nextLine());
            x.setCurrentBalance(Double.parseDouble(reader.nextLine()));
            x.setCurrentPizza(Integer.parseInt(reader.nextLine()));
            while(reader.hasNextLine()){
                String keyValue = reader.nextLine();
                String currentLine = reader.nextLine();
                ArrayList<Double> values = new ArrayList<>(); 
                String[] workerValues = currentLine.substring(1, currentLine.length() - 1).split(",");
                for (String string : workerValues) {     
                    values.add(Double.parseDouble(string));
                }
                x.updateWorkers(keyValue, values.get(0), values.get(1), values.get(2), values.get(3), values.get(4));
                i++;
            }

            
        }
        x.setWorkers(x.getHashMap());
        System.out.println(x.getHashMap());
        return x;
    }

    private static File getFile(String filename) {
        return new File(PizzaStateHandler.class.getResource("PizzaSaves/").getFile() +  filename + ".txt");
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        PizzaStateHandler x = new PizzaStateHandler();
        Factory y = new Factory("Lucaspai");
        y.buyWorkers("Deliverer", 5);
        x.writePizzaState("sss", y); 
    
        System.out.println(y.getHashMap());
         
        System.out.println(x.readPizzaState("sss").getHashMap());
        
    }
}
