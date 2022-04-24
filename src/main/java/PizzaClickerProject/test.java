package PizzaClickerProject;

import java.util.Timer;
import java.util.TimerTask;

public class test {
    public void random(){
        Timer timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
            System.out.print("I would be called every 2 seconds");
        }
    }, 0, 1000);
    System.out.println("hallo");
    }
    public static void main(String[] args) {
        test noe = new test();
        noe.random();
    }
}
