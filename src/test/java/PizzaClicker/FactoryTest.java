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

	

    
}
