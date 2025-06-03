package com.yusuf.gui;


import com.yusuf.main.LoginSystem;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp {

    public Scene getScene(Stage stage) {
        MahasiswaDashboard mahasiswaDashboard = new MahasiswaDashboard();
        AdminDashboard adminDashboard = new AdminDashboard();
        
        if(LoginSystem.currentUser == null) {
            return LoginPane.showLoginScene(stage);
        } else if (LoginSystem.currentUser instanceof com.yusuf.users.Admin) {
            return adminDashboard.showDashboard(stage);
        } else {
            return mahasiswaDashboard.showDashboard(stage);
        }
    }
}