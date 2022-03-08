package PizzaClickerProject;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Pizza {
    private List <String> pizzaTypes = Arrays.asList("First Price", "Rema 1000", "Coop", "Folkets","Dr.Oetker","Big One","Grandiosa");
    private List <Integer> pizzaCosts = Arrays.asList(0,1000,5000,10000,30000,50000,100000);
    // private List <String> toppings = Arrays.asList("Ost", "Rema 1000", "Coop", "Folkets","Dr.Oetker","Big One","Grandiosa");
    // private List <Integer> toppingcost = Arrays.asList(0,1000,5000,10000,30000,50000,100000);
    private int currentPizza = 0;
    private String pizzaType = pizzaTypes.get(currentPizza);
    private int pizzaCost = pizzaCosts.get(currentPizza);
    private int coinsPerClick = 1;

    public String getPizza(){
        return pizzaType;
    }
    
    public int getNextPizzaCost(){
        return pizzaCosts.get(currentPizza +1);
    }

    public int getCurrentPizza() {
        return currentPizza;
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

    public int getCoinsPerClick(){
        return coinsPerClick;
    }



    
}
