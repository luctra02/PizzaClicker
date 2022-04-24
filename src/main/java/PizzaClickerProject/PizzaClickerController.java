package PizzaClickerProject;

import java.io.FileInputStream;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.InputStream;
import javafx.scene.control.Label;
import javafx.scene.control.Button;


public class PizzaClickerController {
    @FXML
    private Label balanceLabel, factoryLabel;

    @FXML
    private Label pizzaLabel;

    @FXML
    private Label worker1Label, worker2Label, worker3Label, worker4Label, worker5Label;

    @FXML
    private Label workerAmount1Label, workerAmount2Label, workerAmount3Label, workerAmount4Label, workerAmount5Label;

    @FXML
    private GridPane workerGrid;


    private Factory factory;

    private void initFactory(String arg0) {
        factory = new Factory(arg0);
    }

    @FXML 
    private void NameFactory() {
        updateDisplay();
    }
    
    @FXML
    private void ClickPizza() {
        factory.clickPizza();
        updateDisplay();
    }

    @FXML
    private void updateDisplay(){
        workerGrid.getChildren().clear();
        this.factoryLabel.setText(factory.getFactoryName());
        this.balanceLabel.setText(String.valueOf(factory.formatNumbers(factory.getCurrentBalance())));
        this.pizzaLabel.setText(factory.getPizzaType());
        workerAmount1Label.setText(factory.getWorkerAmount(0));
        workerAmount2Label.setText(factory.getWorkerAmount(1));
        workerAmount3Label.setText(factory.getWorkerAmount(2));
        workerAmount4Label.setText(factory.getWorkerAmount(3));
        workerAmount5Label.setText(factory.getWorkerAmount(4));
        for (int i = 0; i < factory.getWorkerTypes().size();i++ ){
        workerGrid.add(createCostLabel(i),2, i);
        workerGrid.add(createWorkerLabel(i),0, i);
        workerGrid.add(createWorkerButton(factory.getWorkerObject(factory.getWorkerTypes().get(i))),1,i);

    }
    }

    @FXML
    private void initialize() {
        initFactory("lucaspai");
        this.pizzaLabel.setText(factory.getPizzaType());

        for (int i = 0; i < factory.getWorkerTypes().size();i++ ){
            workerGrid.add(createWorkerLabel(i),0, i);
            workerGrid.add(createCostLabel(i),2, i);
            workerGrid.add(createWorkerButton(factory.getWorkerObject(factory.getWorkerTypes().get(i))),1,i);
        }
        updateDisplay();
    }

    @FXML
    private Label createWorkerLabel(int i) {
        Label label = new Label(factory.getWorkerTypes().get(i));
        return label;
    }

    @FXML
    private Label createCostLabel(int i) {
        Label label = new Label(String.valueOf(factory.formatNumbers(factory.getWorkerCost(i))));
        return label;
    }

    @FXML
    private Button createWorkerButton(Worker worker){
        Button button = new Button("Buy");
        button.setOnAction((event)->buyWorkersUpdate(worker));
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMaxHeight(Double.MAX_VALUE);
        return button;
    }

    private void buyWorkersUpdate(Worker worker) {
        factory.buyWorkers(worker.getWorkerType(),1);
        updateDisplay();
    }

    @FXML
    private void upgradePizza(){

    }
}
