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
        return this.amount * this.basePizzaPerSec * this.efficiency;
    }

    
    

    @Override
    public String toString() {
        return "Worker [amount=" + amount + ", efficiency=" + efficiency + ", pizzaPerSec=" + basePizzaPerSec
                + ", workertype=" + workertype + "]";
    }
    

    public static void main(String[] args) {
        
    }

    

    




    // Prestige?

}
