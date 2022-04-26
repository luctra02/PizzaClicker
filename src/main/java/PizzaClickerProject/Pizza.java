package PizzaClickerProject;
import java.util.List;
import java.util.Arrays;

public class Pizza {
    private List <String> pizzaTypes = Arrays.asList("First Price", "Rema 1000", "Coop", "Folkets","Dr.Oetker","Big One","Grandiosa");
    private List <Integer> pizzaCosts = Arrays.asList(0,1000,5000,10000,30000,50000,100000);
    // private List <String> toppings = Arrays.asList("Ost", "Rema 1000", "Coop", "Folkets","Dr.Oetker","Big One","Grandiosa");
    // private List <Integer> toppingcost = Arrays.asList(0,1000,5000,10000,30000,50000,100000);
    private int currentPizza = 0;
    private String pizzaType;
    private int pizzaCost = pizzaCosts.get(currentPizza);
    private int coinsPerClick = 1;

    public String getPizza(){
        return pizzaTypes.get(currentPizza);
    }
    
    public double getNextPizzaCost(){
        return pizzaCosts.get(currentPizza +1);
    }

    public int getCurrentPizza() {
        return currentPizza;
    }

    public void setCurrentPizza(int i) {
        this.currentPizza = i;
    }

    public int getPizzaTypeLength() {
        return pizzaTypes.size();
    }


    public void upgradePizza() {
        currentPizza++;
        pizzaCost = pizzaCosts.get(currentPizza);
        pizzaType = pizzaTypes.get(currentPizza);
        coinsPerClick = pizzaCost/100;
    }

    public double getCoinsPerClick(){
        return coinsPerClick;
    }

    public static void main(String[] args) {
        Pizza test = new Pizza();
        System.out.println(test.getNextPizzaCost());
    }


    
}
