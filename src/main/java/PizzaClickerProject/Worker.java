package PizzaClickerProject;


public class Worker {
    private String workertype;
    private int amount;
    private double pizzaPerSec;
    private double efficiency = 1;
    
    public Worker(String workertype, double pizzaPerSec) {
        this.workertype = workertype;
        this.pizzaPerSec = pizzaPerSec;
        this.amount = 0;
    }

    public void increaseEfficiency(double efficiencyIncrease) {
        this.efficiency *= efficiencyIncrease;
    }

    public void increaseAmount(int amount) {
        this.amount += amount;
    }

    public int getAmount() {
        return this.amount;
    }


    public double getTotalPizzaPerSec() {
        return this.amount * this.pizzaPerSec * this.efficiency;
    }

    
    

    @Override
    public String toString() {
        return "Worker [amount=" + amount + ", efficiency=" + efficiency + ", pizzaPerSec=" + pizzaPerSec
                + ", workertype=" + workertype + "]";
    }
    

    public static void main(String[] args) {
        
    }

    

    




    // Prestige?

}
