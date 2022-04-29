package PizzaClickerProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
        Factory tempFactory = new Factory("XXXXXX");
        int i = 0;
        try (Scanner reader = new Scanner(getFile(filename))) {
            tempFactory.setFactoryName(reader.nextLine());
            tempFactory.setCurrentBalance(Double.parseDouble(reader.nextLine()));
            tempFactory.setCurrentPizza(Integer.parseInt(reader.nextLine()));
            while(reader.hasNextLine()){
                String keyValue = reader.nextLine();
                String currentLine = reader.nextLine();
                ArrayList<Double> values = new ArrayList<>(); 
                String[] workerValues = currentLine.substring(1, currentLine.length() - 1).split(",");
                for (String string : workerValues) {     
                    values.add(Double.parseDouble(string));
                }
                tempFactory.updateWorkers(keyValue, values.get(0), values.get(1), values.get(2), values.get(3), values.get(4));
                i++;
            }

            
        }
        tempFactory.setWorkers(tempFactory.getHashMap());
        System.out.println(tempFactory.getHashMap());
        return tempFactory;
    }

    private static File getFile(String filename) {
        Path path = Paths.get("PizzaStateHandler.java");
        return new File(path.toAbsolutePath().getParent() + "/src/main/java/PizzaClickerProject/PizzaSaves/" + filename + ".txt" );
    }

}
