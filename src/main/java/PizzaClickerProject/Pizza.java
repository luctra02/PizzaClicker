package PizzaClickerProject;
import java.util.List;
import java.util.Arrays;

public class Pizza {
    private List <String> pizzaTypes = Arrays.asList("First Price", "Rema 1000", "Coop", "Folkets","Dr.Oetker","Big One","Grandiosa");
    private List <Integer> pizzaCosts = Arrays.asList(100,1000,5000,10000,30000,50000,100000);
    private int currentPizza = 0;
    private int pizzaCost = pizzaCosts.get(currentPizza);
    private double coinsPerClick = 1;

    public String getPizza(){
        return pizzaTypes.get(currentPizza);
    }
    
    public double getNextPizzaCost(){
        return pizzaCosts.get(currentPizza +1);
    }

    public int getCurrentPizza() {
        return currentPizza;
    }

    public int getPizzaTypeLength() {
        return pizzaTypes.size();
    }

    public double getCoinsPerClick(){
        return coinsPerClick;
    }

    public void setCurrentPizza(int i) {
        this.currentPizza = i;
        pizzaCost = pizzaCosts.get(currentPizza);
        coinsPerClick = pizzaCost/100;
    }  


    public void upgradePizza() {
        currentPizza++;
        pizzaCost = pizzaCosts.get(currentPizza);
        coinsPerClick = pizzaCost/100;
    }
    
}
