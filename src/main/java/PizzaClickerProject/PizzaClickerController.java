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
    private Label balanceLabel, factoryLabel, pizzapersecLabel;

    @FXML
    private Label pizzaLabel;

    @FXML
    private Label worker1Label, worker2Label, worker3Label, worker4Label, worker5Label;

    @FXML
    private Label workerAmount1Label, workerAmount2Label, workerAmount3Label, workerAmount4Label, workerAmount5Label;

    @FXML
    private GridPane workerGrid, upgradeGrid, buyAmountGrid;


    private Factory factory;

    private int amount = 1;

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
        this.factoryLabel.setText(factory.getFactoryName());
        this.balanceLabel.setText(String.valueOf(factory.formatNumbers(factory.getCurrentBalance())));
        this.pizzapersecLabel.setText(String.valueOf(factory.formatNumbers(factory.getTotalPizzaPerSec()) + " coins / sec"));
        this.pizzaLabel.setText(factory.getPizzaType());

        updateWorkerDisplay();
        updateUpgradeDisplay();
        updateBuyAmount();

    }

    @FXML
    private void updateBuyAmount() {
        workerGrid.getChildren().clear();
        for (int i = 0; i < factory.getWorkerTypes().size();i++ ){
            workerGrid.add(createAmountLabel(i),1, i);
            workerGrid.add(createCostLabel(i),3, i);
            workerGrid.add(createWorkerLabel(i),0, i);
            workerGrid.add(createWorkerButton(factory.getWorkerObject(factory.getWorkerTypes().get(i))),2,i);
        }
    }

    @FXML 
    private void updateWorkerDisplay() {
        buyAmountGrid.getChildren().clear();
        buyAmountGrid.add(new Label("Buy Amount:"), 0, 1);
        buyAmountGrid.add(createBuyAmount(1), 1, 1);
        buyAmountGrid.add(createBuyAmount(5), 2, 1);
        buyAmountGrid.add(createBuyAmount(10), 3, 1);
        buyAmountGrid.add(new Label("Worker Type"), 0, 2);
        buyAmountGrid.add(new Label("Amount"), 1, 2);
        buyAmountGrid.add(new Label("Cost"), 3, 2);
    }

    @FXML
    private Button createBuyAmount(int i) {
        Button button = new Button(String.valueOf(i) + "x");
        button.setOnAction((event)->setAmount(i));
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMaxHeight(Double.MAX_VALUE);
        return button;
    }

    private void setAmount(int i) {
        this.amount = i;
        updateDisplay();
    }

    @FXML
    private void updateUpgradeDisplay() {
        upgradeGrid.getChildren().clear();
        for (int i = 0; i < factory.getWorkerTypes().size();i++) {
            upgradeGrid.add(createUpgradeLabel(i), 0, i);
            upgradeGrid.add(createUpgradeEfficiency(factory.getWorkerObject(factory.getWorkerTypes().get(i))), 1, i);
            upgradeGrid.add(createUpgradeButton(factory.getWorkerObject(factory.getWorkerTypes().get(i))), 2, i);
            upgradeGrid.add(createUpgradeCost(i), 3, i);
        }
    }

    @FXML
    private Label createUpgradeLabel(int i) {
        Label label = new Label(factory.getWorkerTypes().get(i));
        return label;
    }

    @FXML
    private Label createUpgradeEfficiency(Worker worker) {
        Label label = new Label(factory.formatNumbers(worker.getEfficiency()) + "x");
        return label;
    }

    @FXML
    private Button createUpgradeButton(Worker worker) {
        Button button = new Button("Buy");
        button.setOnAction((event)->buyUpgrades(worker));
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMaxHeight(Double.MAX_VALUE);
        return button;
    }

    @FXML
    private Label createUpgradeCost(int i) {
        Label label = new Label(String.valueOf(factory.formatNumbers(factory.getWorkerUpgradeCost(i))));
        return label;
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
        Label label = new Label(String.valueOf(factory.formatNumbers(factory.getxWorkerCost(factory.getWorkerCost(i), this.amount))));
        return label;
    }

    @FXML
    private Label createAmountLabel(int i) {
        Label label = new Label(String.valueOf((factory.getWorkerAmount(i))));
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
        factory.buyWorkers(worker.getWorkerType(), this.amount);
        updateDisplay();
    }

    
    private void buyUpgrades(Worker worker) {
        factory.buyUpgrades(worker.getWorkerType());
        updateDisplay();
    }

    @FXML
    private void upgradePizza(){

    }
}
