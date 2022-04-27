package PizzaClickerProject;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Factory {
    private String factoryName;
    private Date foundingDate;
    private Map<String,ArrayList<Double>> workers = new HashMap<>();
    private Pizza pizza;
    // private Worker worker;
    private ArrayList<Worker> workerObjects = new ArrayList<>();
    private double currentBalance = 1000000;
    private ArrayList<String> workerTypes = new ArrayList<>(Arrays.asList("Deliverer","Cashier","Chef","Manager","CEO"));

    


    public Factory(String factoryName) {
        if(!factoryName.matches("[A-z-0-9]{6,20}")){
            throw new IllegalArgumentException("Invalid username! Make sure the name is between 6-20 characters from a-z.");
        }
        if(factoryName == "lucaspai"){
            currentBalance = 1000000;
        }
        this.factoryName = factoryName;
        this.factoryName += "'s factory";
        this.pizza = new Pizza();
        this.foundingDate = new Date();
        InitializeWorkerTypes();
    }

    public ArrayList<String> getWorkerTypes() {
        return new ArrayList<>(this.workerTypes);
    }

    public String getPizzaType() {
        return pizza.getPizza();
    }

    public String getFactoryName() { 
        return this.factoryName;
    }

    public String getWorkerAmount(int workerindex) {
        return String.valueOf(workerObjects.get(workerindex).getAmount());
    }

    public void clickPizza(){
        currentBalance += pizza.getCoinsPerClick();
    }

    public double getCurrentBalance(){
        return currentBalance;
    }

    public int getPizzaTypeLength() {
        return pizza.getPizzaTypeLength();
    }

    public boolean upgradePizza(){
        
        if (pizza.getCurrentPizza() + 1 > pizza.getPizzaTypeLength()) {
            throw new IllegalArgumentException("You already have Grandiosa");
        }

        if(getCurrentBalance() < pizza.getNextPizzaCost()){
            throw new IllegalArgumentException("Cannot afford the next Pizzatype");
        }
        currentBalance -=  pizza.getNextPizzaCost();
        pizza.upgradePizza();
        return true;
        
    }

    public int getCurrentPizza() {
        return pizza.getCurrentPizza();
        
    }

    private void InitializeWorkerTypes() {
        // workertype1: pizzaPerSec, cost, efficiency, upgradecost
        double pizzaPerSec = 1;
        double cost = 50;
        double efficiency = 1;
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

    public double getWorkerUpgradeCost(int i) {
        return workers.get(workerTypes.get(i)).get(3);
    }

    public double getWorkerCost(int i) {
        return workers.get(workerTypes.get(i)).get(1);
    }

    public double getxWorkerCost(double cost, int amount) {
        double totalcosts = 0;
        
        for (var i=0; i < amount; i++) {
            totalcosts += cost;
            cost *= 1.2;
        }
        return totalcosts;
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
        updateWorkers(workertype, temp.getTotalPizzaPerSec(), cost, efficiency, upgradecost, workers.get(workertype).get(4) + amount);

    }


    public void buyUpgrades(String workertype) {
        double efficiency = workers.get(workertype).get(2);
        double upgradecost = workers.get(workertype).get(3);
        Worker temp = workerObjects.get(workerTypes.indexOf(workertype));

        if (getCurrentBalance() < upgradecost) {
            throw new IllegalArgumentException("Cannot afford to buy upgrade");

        }
        currentBalance -=  upgradecost;

        efficiency *= 1.10;
        upgradecost *= 1.5;
        temp.setEfficiency(efficiency);
        updateWorkers(workertype, temp.getTotalPizzaPerSec(), workers.get(workertype).get(1), efficiency, upgradecost, workers.get(workertype).get(4));
    }

    public void setFactoryName(String x) {
        factoryName = x;
    }

    public void setWorkers(Map<String,ArrayList<Double>> x) {
        workers = x;
        for (Worker worker : workerObjects) {
            worker.setAmount((int) Math.round(x.get(worker.getWorkerType()).get(4)));
            worker.setEfficiency((x.get(worker.getWorkerType()).get(2)));
        }
    }

    public void setCurrentBalance(double x) {
        currentBalance = x;
    }

    public void setCurrentPizza(int x) {
        pizza.setCurrentPizza(x);
    }
    
    public void setPizzaPerClick(double x){
        pizza.setCoinsPerClick(x);
    }

    public void updateWorkers(String workertype, double pizzaPerSec, double cost, double efficiency, double upgradecost, double amount) {
        ArrayList<Double> newArbeiderCosts = new ArrayList<>(Arrays.asList(pizzaPerSec, cost, efficiency, upgradecost, amount));
        workers.put(workertype, newArbeiderCosts);
    }

    

    public Worker getWorkerObject(String workerType) {
        return workerObjects.get(workerTypes.indexOf(workerType));

    }

    public Map<String,ArrayList<Double>> getHashMap(){
        return workers;
    }

    public String formatNumbers(double number){
        ArrayList<String> illions = new ArrayList<>(Arrays.asList("K","M","B","T","Q"));
        if(number < 1000){
            return new DecimalFormat("###.##").format(number);
        }
        double digitGroups = Math.floor(Math.log10(number)/Math.log10(1000));
        return new DecimalFormat("###.##").format(number/Math.pow(1000, digitGroups)) + " " + illions.get((int) digitGroups -1);
    }

    public double passiveIncome(int ms){
        double income = 0;

        for (Worker worker : workerObjects) {
            income += worker.getTotalPizzaPerSec();
        }
    
        this.currentBalance += income/(1000/ms);
        return currentBalance;
    }
    
    public double getTotalPizzaPerSec() {
        double income = 0;

        for (Worker worker : workerObjects) {
            income += worker.getTotalPizzaPerSec();
        }
        return income;
    }

    public double getNextPizzaCost(){
        return pizza.getNextPizzaCost();
    }

    public double getCoinsPerClick(){
        return pizza.getCoinsPerClick();
    }


    @Override
    public String toString() {
        return "Factory [workers=" + workers + "]";
    }

    public static void main(String[] args) {
        Factory lucas = new Factory("lucaspai");
        lucas.buyWorkers("Chef", 5);
        System.err.println(lucas.getHashMap());
        
    }
}
