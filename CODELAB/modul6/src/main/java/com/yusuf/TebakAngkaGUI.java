package com.yusuf;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TebakAngkaGUI extends Application {

    private int angkaRandom;
    private int jumlahTebakan;
    private TextField inputField;
    private Label feedbackLabel;
    private Label percobaanLabel;
    private Button tebakButton;

    @Override
    public void start(Stage stage) {
        generateAngkaBaru();

        // Judul
        Label judul = new Label("🎯 Tebak Angka 1–100");
        judul.setFont(Font.font("Arial", 24));
        judul.setTextFill(Color.DARKBLUE);

        // Field input
        inputField = new TextField();
        inputField.setPromptText("Masukkan angka di sini");
        inputField.setPrefWidth(200);
        inputField.setOnKeyPressed((keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                prosesTebakan(inputField);
            }
        });

        // Tombol
        tebakButton = new Button("🔷 Coba Tebak!");
        tebakButton
                .setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET, new CornerRadii(5), Insets.EMPTY)));
        tebakButton.setTextFill(Color.WHITE);
        tebakButton.setOnAction(e -> prosesTebakan(inputField));

        // HBox input
        HBox inputBox = new HBox(10, inputField, tebakButton);
        inputBox.setAlignment(Pos.CENTER);

        // Label feedback
        feedbackLabel = new Label();
        feedbackLabel.setFont(Font.font(16));

        // Label percobaan
        percobaanLabel = new Label("Jumlah percobaan: 0");

        // Root layout
        VBox root = new VBox(15, judul, feedbackLabel, inputBox, percobaanLabel);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        root.setBackground(new Background(new BackgroundFill(Color.CYAN, new CornerRadii(0), Insets.EMPTY)));

        Scene scene = new Scene(root, 400, 250);
        stage.setTitle("Tebak Angka Advance");
        stage.getIcons().add(new Image("https://img.icons8.com/color/48/000000/target.png")); // Opsional
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.show();
    }

    private void prosesTebakan(TextField textField) {
        if (tebakButton.getText().contains("Main Lagi")) {
            generateAngkaBaru();
            inputField.setDisable(false);
            inputField.clear();
            feedbackLabel.setText("");
            percobaanLabel.setText("Jumlah percobaan: 0");
            tebakButton.setText("🔷 Coba Tebak!");
            tebakButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
            return;
        }

        try {
            int tebakan = Integer.parseInt(inputField.getText());
            jumlahTebakan++;

            if (tebakan < angkaRandom) {
                feedbackLabel.setText("🔻 Terlalu kecil!");
                feedbackLabel.setTextFill(Color.ORANGE);
                textField.setText("");
            } else if (tebakan > angkaRandom) {
                feedbackLabel.setText("🔺 Terlalu besar!");
                feedbackLabel.setTextFill(Color.ORANGE);
                textField.setText("");
            } else {
                feedbackLabel.setText("✅ Tebakan benar!");
                feedbackLabel.setTextFill(Color.GREEN);
                tebakButton.setText("🔁 Main Lagi");
                tebakButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
                inputField.setDisable(true);
            }

            percobaanLabel.setText("Jumlah percobaan: " + jumlahTebakan);
        } catch (NumberFormatException e) {
            feedbackLabel.setText("⚠️ Masukkan angka yang valid!");
            feedbackLabel.setTextFill(Color.RED);
        }
    }

    private void generateAngkaBaru() {
        angkaRandom = (int) (Math.random() * 100) + 1;
        jumlahTebakan = 0;  
    }

    public static void main(String[] args) {
        launch(args);
    }
}
