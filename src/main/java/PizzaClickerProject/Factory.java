package PizzaClickerProject;
import java.util.Date;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Factory {
    private String factoryName;
    private Date foundingDate;
    private Map<String,ArrayList<Double>> workers = new HashMap<>();
    private Pizza pizza = new Pizza();
    private Worker worker;
    private ArrayList<Worker> workerObjects = new ArrayList<>();
    private double currentBalance = 0;
    private ArrayList<String> workerTypes = new ArrayList<>(Arrays.asList("worker1","worker2","worker3","worker4","worker5"));



    public Factory(String factoryName) {
        if(!factoryName.matches("[A-z-0-9]{6,20}")){
            throw new IllegalArgumentException("Invalid username! Make sure the name is between 6-20 characters from a-z.");
        }
        if(factoryName == "lucaspai"){
            currentBalance = 100000;
        }
        factoryName += "'s factory";
        foundingDate = new Date();
        InitializeWorkerTypes();
    }

    public void clickPizza(){
        currentBalance += pizza.getCoinsPerClick();
    }

    public double getCurrentBalance(){
        return currentBalance;
    }

    public void upgradePizza(){
        
        if (pizza.getCurrentPizza() + 1 > pizza.getPizzaTypeLength()) {
            throw new IllegalArgumentException("Too much pizza!!!");
        }

        if(getCurrentBalance() < pizza.getNextPizzaCost()){
            throw new IllegalArgumentException("Cannot afford the next Pizzatype");
        }
        currentBalance -=  pizza.getNextPizzaCost();
        pizza.upgradePizza();
        
    }

    private void InitializeWorkerTypes() {
        // workertype1: pizzaPerSec, cost, efficiency, upgradecost
        double pizzaPerSec = 1;
        double cost = 100;
        double efficiency = 1.1;
        double upgradecost = 300;
        double amount = 0;
        for (var i=0; i < workerTypes.size(); i++) {
            workerObjects.add(new Worker(workerTypes.get(i), pizzaPerSec));
            ArrayList<Double> ArbeiderCosts = new ArrayList<>(Arrays.asList(pizzaPerSec, cost, efficiency, upgradecost, amount));
            workers.put(workerTypes.get(i), ArbeiderCosts);
            pizzaPerSec *= 5;
            cost *= 10;
            upgradecost *= 5;

        }
       
}

    public void buyWorkers(String workertype, int amount){
        double cost = workers.get(workertype).get(1);
        double efficiency = workers.get(workertype).get(2);
        double upgradecost = workers.get(workertype).get(3);
        double totalcosts = 0;
        Worker temp = workerObjects.get(workerTypes.indexOf(workertype));
        // Calculates the cost of amount worth of workers while price increases by 20% per worker
        for (var i=0; i < amount; i++) {
            totalcosts += cost;
            cost *= 1.2;
        }

        if (getCurrentBalance() < totalcosts) { 
            throw new IllegalArgumentException("Cannot afford to buy workers");
        }
        currentBalance -=  totalcosts;
        //Increases amount of workers in the workertype-class
        temp.increaseAmount(amount);
        updateWorkers(workertype, temp.getTotalPizzaPerSec(), cost, efficiency, upgradecost,amount);

    }



    public void buyUpgrades(String workertype) {
        double efficiency = workers.get(workertype).get(2);
        double upgradecost = workers.get(workertype).get(3);
        Worker temp = workerObjects.get(workerTypes.indexOf(workertype));

        if (getCurrentBalance() < upgradecost) {
            throw new IllegalArgumentException("Cannot afford to buy upgrade");

        }
        currentBalance -=  upgradecost;

        efficiency += 0.1;
        upgradecost *= 1.5;
        temp.increaseEfficiency(efficiency);

        updateWorkers(workertype, temp.getTotalPizzaPerSec(), workers.get(workertype).get(1), efficiency, upgradecost, workers.get(workertype).get(4));
    }

    public void updateWorkers(String workertype, double pizzaPerSec, double cost, double efficiency, double upgradecost, double amount) {
        ArrayList<Double> newArbeiderCosts = new ArrayList<>(Arrays.asList(pizzaPerSec, cost, efficiency, upgradecost, amount));
        workers.put(workertype, newArbeiderCosts);
    }

    

    @Override
    public String toString() {
        return "Factory [workers=" + workers + "]";
    }

    public Worker getWorkerObject(String workerType) {
        return workerObjects.get(workerTypes.indexOf(workerType));

    }

    public Map<String,ArrayList<Double>> getHashMap(){
        return workers;
    }

    public static void main(String[] args) {

        Factory lucas = new Factory("lucaspai");
        System.out.println(lucas.getCurrentBalance());
        lucas.upgradePizza();
        lucas.upgradePizza();
        lucas.upgradePizza();

        System.out.println(lucas.getCurrentBalance());


    }

}
