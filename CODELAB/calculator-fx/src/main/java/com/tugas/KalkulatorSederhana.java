package com.tugas;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class KalkulatorSederhana extends Application {

    private final TextField display = new TextField();
    private double angka1 = 0;
    private String operator = "";
    private boolean start = true;

    @Override
    public void start(Stage stage) {
        // Tampilan input
        display.setPrefHeight(50);
        display.setEditable(false);
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setStyle("-fx-font-size: 18;");

        // Grid tombol
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        String[][] tombol = {
            {"7", "8", "9", "/"},
            {"4", "5", "6", "*"},
            {"1", "2", "3", "-"},
            {"0", "CE", "=", "+"}
        };

        for (int row = 0; row < tombol.length; row++) {
            for (int col = 0; col < tombol[row].length; col++) {
                String label = tombol[row][col];
                Button btn = new Button(label);
                btn.setPrefSize(60, 60);
                btn.setStyle("-fx-font-size: 16;");
                grid.add(btn, col, row);
                btn.setOnAction(e -> prosesInput(label));
            }
        }

        VBox root = new VBox(10, display, grid);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Kalkulator Sederhana");
        stage.setResizable(false);
        stage.show();
    }

    private void prosesInput(String value) {
        switch (value) {
            case "CE":
                display.clear();
                operator = "";
                angka1 = 0;
                start = true;
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                if (!display.getText().isEmpty()) {
                    angka1 = Double.parseDouble(display.getText());
                    operator = value;
                    display.clear();
                }   break;
            case "=":
                if (!operator.isEmpty() && !display.getText().isEmpty()) {
                    double angka2 = Double.parseDouble(display.getText());
                    double hasil = 0;
                    
            switch (operator) {
                case "+":
                    hasil = angka1 + angka2;
                    break;
                case "-":
                    hasil = angka1 - angka2;
                    break;
                case "*":
                    hasil = angka1 * angka2;
                    break;
                case "/":
                    if (angka2 == 0) {
                        display.setText("Tidak bisa bagi 0");
                        operator = "";
                        return;
                    }
                    hasil = angka1 / angka2;
                    break;
                default:
                    break;
            }
                    
                    display.setText(String.valueOf(hasil));
                    operator = "";
                    start = true;
                }   break;
            default:
                if (start) {
                    display.clear();
                    start = false;
                }   display.appendText(value);
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
