package com.yusuf.gui;

import com.yusuf.main.LoginSystem;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginPane {

    public static Scene showLoginScene(Stage stage) {
        Label titleLabel = new Label("Login Sistem Lost & Found");

        ComboBox<String> roleBox = new ComboBox<>();
        roleBox.getItems().addAll("Mahasiswa", "Admin");
        roleBox.setValue("Mahasiswa");

        TextField nameField = new TextField();
        nameField.setPromptText("Nama / Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Login");

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        loginButton.setOnAction(e -> {
            String role = roleBox.getValue();
            String username = nameField.getText();
            String password = passwordField.getText();

            LoginSystem.currentUser = LoginSystem.userLogin(role, username, password);
            if (LoginSystem.currentUser == null) {
                errorLabel.setText("Login gagal, periksa kredensial.");
            } else {
                errorLabel.setText("");
                if(role.equalsIgnoreCase("admin")) {
                    AdminDashboard adminDashboard = new AdminDashboard();
                    stage.setScene(adminDashboard.showDashboard(stage));
                } else if (role.equalsIgnoreCase("mahasiswa")) {
                    MahasiswaDashboard mahasiswaDashboard = new MahasiswaDashboard();
                    stage.setScene(mahasiswaDashboard.showDashboard(stage));
                }
            }
        });

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(
                titleLabel,
                roleBox,
                nameField,
                passwordField,
                loginButton,
                errorLabel);

        Scene scene = new Scene(root, 350, 300);
        return scene;
    }
}