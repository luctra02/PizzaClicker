package PizzaClicker;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import PizzaClickerProject.Factory;


public class FactoryTest {
    private Factory factory;

    @BeforeEach
    public void setup() {
        factory = new Factory("lucaspai");

    }

    @Test
	@DisplayName("Tester om validering av brukernavn")
    public void testFactoryName(){

        assertThrows(IllegalArgumentException.class, () -> {
            new Factory("test");
        }, "IllegalArgumentException skal utløses når navnet er under 6 bokstaver");
        assertThrows(IllegalArgumentException.class, () -> {
            new Factory("testover20digitslmaopoghahaha");
        }, "IllegalArgumentException skal utløses når navnet er over 20 bokstaver");
        assertThrows(IllegalArgumentException.class, () -> {
            new Factory("ÅÅØÆÅØÅ");
        }, "IllegalArgumentException skal utløses når brukernavnet har andre bokstaver enn A-Z");
        assertThrows(IllegalArgumentException.class, () -> {
            new Factory("-.,@^¨`");
        }, "IllegalArgumentException skal utløses når navnet har symboler");
    }

    @Test
    public void buyWorkers (){
        factory.setCurrentBalance(10000000);
        factory.buyWorkers("Deliverer", 5);
        assertEquals(5, factory.getHashMap().get("Deliverer").get(4));
        assertEquals(5.0, factory.getTotalPizzaPerSec());
        factory.buyWorkers("Deliverer", 5);
        assertEquals(10, factory.getHashMap().get("Deliverer").get(4));
        assertEquals(10.0, factory.getTotalPizzaPerSec());
        factory.buyWorkers("Chef", 5);
        assertEquals(5, factory.getHashMap().get("Chef").get(4));
        assertEquals(135.0, factory.getTotalPizzaPerSec());
        factory.buyWorkers("CEO", 2);
        assertEquals(2, factory.getHashMap().get("CEO").get(4));
        assertEquals(1385, factory.getTotalPizzaPerSec());
    }

    @Test
    public void testBuyUpgrades(){
        factory.setCurrentBalance(300);
        factory.buyUpgrades("Deliverer");
        assertEquals(0.0, factory.getCurrentBalance());
        assertEquals(1.1, factory.getHashMap().get("Deliverer").get(2));

    }

    @Test
    public void testClickPizza() {
        factory.clickPizza();
        assertEquals(1.0, factory.getCurrentBalance());
        factory.setCurrentBalance(1000);
        factory.upgradePizza();
        factory.clickPizza();
        assertEquals(10.0, factory.getCurrentBalance());
    }

    @Test
    public void testFormatNumbers(){
        assertEquals("1 K", factory.formatNumbers(1000));
        assertEquals("13.93 K", factory.formatNumbers(13934));
        assertEquals("3.7 M", factory.formatNumbers(3698123));

    }

    @Test
    public void testCurrentBalance() {
        assertEquals(0.0, factory.getCurrentBalance());
        assertThrows(IllegalArgumentException.class, () -> {
            factory.buyWorkers("Chef", 100);
        }, "IllegalArgumentException skal utløses ved ikke not penger");
        assertThrows(IllegalArgumentException.class, () -> {
            factory.buyUpgrades("Chef");
        }, "IllegalArgumentException skal utløses ved ikke not penger");
        assertThrows(IllegalArgumentException.class, () -> {
            factory.upgradePizza();
        }, "IllegalArgumentException skal utløses ved ikke not penger");
    }

    @Test
    public void testPizzaUpgrade() {
        factory.setCurrentBalance(1000000);
        assertEquals(0, factory.getCurrentPizza());
        factory.upgradePizza();
        assertEquals(1, factory.getCurrentPizza());
        factory.upgradePizza();
        factory.upgradePizza();
        assertEquals(3, factory.getCurrentPizza());
        factory.upgradePizza();
        factory.upgradePizza();
        assertEquals(5, factory.getCurrentPizza());
        factory.upgradePizza();
        assertThrows(IllegalArgumentException.class, () -> {
            factory.upgradePizza();
        }, "Max Pizza");
    }
    
}
