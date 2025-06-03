package com.yusuf;

import java.io.IOException;

import com.yusuf.gui.MainApp;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        MainApp mainApp = new MainApp();
        stage.setScene(mainApp.getScene(stage));
        stage.setAlwaysOnTop(true);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }

}