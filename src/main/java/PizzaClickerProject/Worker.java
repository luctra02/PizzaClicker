package PizzaClickerProject;


public class Worker {
    private String workertype;
    private int amount;
    private double basePizzaPerSec;
    private double efficiency = 1;
    
    public Worker(String workertype, double basePizzaPerSec) {
        this.workertype = workertype;
        this.basePizzaPerSec = basePizzaPerSec;
        this.amount = 0;
    }

    public double getEfficiency() {
        return this.efficiency;
    }

    public int getAmount() {
        return this.amount;
    }

    public String getWorkerType(){
        return this.workertype; 
    }

    public double getTotalPizzaPerSec() {
        return this.amount * this.basePizzaPerSec * this.efficiency;
    }

    public void setAmount(int x) {
        this.amount = x;
    }

    public void setEfficiency(double x) {
        this.efficiency = x;
    }

    public void increaseAmount(int amount) {
        this.amount += amount;
    }
}
