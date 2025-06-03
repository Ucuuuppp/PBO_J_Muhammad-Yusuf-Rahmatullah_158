package com.yusuf.gui;

import com.yusuf.data.DataStore;
import com.yusuf.data.Item;
import com.yusuf.main.LoginSystem;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MahasiswaDashboard {
    private TableView<Item> itemTableView;

    public Scene showDashboard(Stage stage) {
        stage.setTitle("Lost & Found Kampus");

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15));

        VBox topSection = new VBox(15);
        topSection.setPadding(new Insets(0, 0, 15, 0));

        Text welcomeText = new Text("Selamat datang, " + LoginSystem.currentUser.getName());
        welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        Text reportTitleText = new Text("Laporkan Barang Hilang/Temuan");
        reportTitleText.setFont(Font.font("Arial", FontWeight.NORMAL, 12));

        HBox reportForm = new HBox(10);
        reportForm.setAlignment(Pos.CENTER_LEFT);

        TextField itemNameField = new TextField();
        itemNameField.setPromptText("Nama Barang");
        itemNameField.setPrefWidth(200);

        TextField itemDescriptionField = new TextField();
        itemDescriptionField.setPromptText("Deskripsi Barang");
        itemDescriptionField.setPrefWidth(200);

        TextField locationField = new TextField();
        locationField.setPromptText("Lokasi Penemuan/Kehilangan");
        locationField.setPrefWidth(150);

        Button reportButton = new Button("Laporkan");

        reportForm.getChildren().addAll(itemNameField, itemDescriptionField, locationField, reportButton);
        topSection.getChildren().addAll(welcomeText, reportTitleText, reportForm);
        root.setTop(topSection);

        VBox centerSection = new VBox(10);
        Text listTitleText = new Text("Daftar Laporan Anda");
        listTitleText.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        itemTableView = new TableView<>();

        TableColumn<Item, String> nameColumn = new TableColumn<>("Nama");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        nameColumn.setPrefWidth(150);

        TableColumn<Item, String> descriptionColumn = new TableColumn<>("Deskripsi");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        descriptionColumn.setPrefWidth(150);

        TableColumn<Item, String> locationColumn = new TableColumn<>("Lokasi");
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        locationColumn.setPrefWidth(350);

        itemTableView.getColumns().addAll(nameColumn, descriptionColumn, locationColumn);
        itemTableView.setItems(DataStore.reportedItemsData);
        itemTableView.setPlaceholder(new Label("Belum ada laporan"));

        centerSection.getChildren().addAll(listTitleText, itemTableView);
        VBox.setVgrow(itemTableView, Priority.ALWAYS);
        root.setCenter(centerSection);

        HBox bottomSection = new HBox();
        bottomSection.setAlignment(Pos.CENTER_LEFT);
        bottomSection.setPadding(new Insets(15, 0, 0, 0));
        Button logoutButton = new Button("Logout");
        bottomSection.getChildren().add(logoutButton);
        root.setBottom(bottomSection);

        reportButton.setOnAction(e -> {
            String itemName = itemNameField.getText();
            String itemDescription = itemDescriptionField.getText();
            String itemLocation = locationField.getText();

            if (!itemName.isEmpty() && !itemDescription.isEmpty() && !itemLocation.isEmpty()) {
                DataStore.reportedItemsData.add(new Item(itemName, itemDescription, itemLocation, "Reported"));

                itemNameField.clear();
                itemDescriptionField.clear();
                locationField.clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Input Tidak Lengkap");
                alert.setHeaderText(null);
                alert.setContentText("Mohon lengkapi semua kolom untuk melaporkan barang.");
                alert.showAndWait();
            }
        });

        logoutButton.setOnAction(e -> {
            System.out.println("Logout clicked");
            LoginSystem.currentUser = null;
            stage.setScene(LoginPane.showLoginScene(stage));
        });

        Scene scene = new Scene(root, 650, 500);
        return scene;
    }

}
