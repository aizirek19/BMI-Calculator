package com.example.demo2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

    public class HelloApplication extends Application {

        private TextField weightTextField;
        private TextField heightTextField;
        private RadioButton englishUnitsRadioButton;
        private RadioButton metricUnitsRadioButton;
        private Label bmiResultLabel;

        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("BMI Calculator");

            GridPane gridPane = new GridPane();
            gridPane.setAlignment(Pos.CENTER);
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            gridPane.setPadding(new Insets(25, 25, 25, 25));

            Label weightLabel = new Label("Weight:");
            gridPane.add(weightLabel, 0, 0);

            weightTextField = new TextField();
            gridPane.add(weightTextField, 1, 0);

            Label heightLabel = new Label("Height:");
            gridPane.add(heightLabel, 0, 1);

            heightTextField = new TextField();
            gridPane.add(heightTextField, 1, 1);

            ToggleGroup unitsToggleGroup = new ToggleGroup();

            englishUnitsRadioButton = new RadioButton("English Units");
            englishUnitsRadioButton.setSelected(true);
            englishUnitsRadioButton.setToggleGroup(unitsToggleGroup);

            metricUnitsRadioButton = new RadioButton("Metric Units");
            metricUnitsRadioButton.setToggleGroup(unitsToggleGroup);

            HBox unitsRadioButtonsBox = new HBox(10);
            unitsRadioButtonsBox.getChildren().addAll(englishUnitsRadioButton,
                    metricUnitsRadioButton);
            gridPane.add(unitsRadioButtonsBox, 0, 2, 2, 1);

            Button calculateButton = new Button("Calculate BMI");
            calculateButton.setOnAction(e -> calculateBMI());
            gridPane.add(calculateButton, 0, 3, 2, 1);

            Label bmiStatusLabel = new Label("BMI Status:");
            gridPane.add(bmiStatusLabel, 0, 4);

            bmiResultLabel = new Label();
            gridPane.add(bmiResultLabel, 1, 4);

            Scene scene = new Scene(gridPane);
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        private void calculateBMI() {
            double weight;
            double height;

            try {
                weight = Double.parseDouble(weightTextField.getText());
                height = Double.parseDouble(heightTextField.getText());

                if (englishUnitsRadioButton.isSelected()) {
                    // Calculate BMI using English units formula
                    double bmi = (weight * 703) / (height * height);
                    displayBMIStatus(bmi);
                } else if (metricUnitsRadioButton.isSelected()) {
                    // Calculate BMI using metric units formula
                    double bmi = weight / (height * height);
                    displayBMIStatus(bmi);
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter valid weight and height values.");
                alert.showAndWait();
            }
        }

        private void displayBMIStatus(double bmi) {
            String status;

            if (bmi < 18.5) {
                status = "Underweight";
            } else if (bmi >= 18.5 && bmi <= 24.9) {
                status = "Normal";
            } else if (bmi >= 25 && bmi <=29.9) {
                status = "Overweight";
            } else {
                status = "Obese";
            }

            bmiResultLabel.setText(status);
        }
    }


