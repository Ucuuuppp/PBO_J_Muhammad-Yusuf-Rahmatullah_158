package com.yusuf.gui;

import com.yusuf.data.DataStore;
import com.yusuf.data.Item;
import com.yusuf.main.LoginSystem;
import com.yusuf.users.Mahasiswa;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
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

public class AdminDashboard {
    private TableView<Item> itemTableView;

    private TableView<Mahasiswa> studentTableView;

    public Scene showDashboard(Stage stage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        SplitPane splitPane = new SplitPane();
        splitPane.setDividerPositions(0.5);

        VBox leftPane = new VBox(10);
        leftPane.setPadding(new Insets(10));

        Text welcomeText = new Text("Halo, " + LoginSystem.currentUser.getName());
        welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Text itemReportTitle = new Text("Laporan Barang");
        itemReportTitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        itemTableView = new TableView<>();
        TableColumn<Item, String> itemNameCol = new TableColumn<>("Nama");
        itemNameCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        itemNameCol.setPrefWidth(100);

        TableColumn<Item, String> descriptionCol = new TableColumn<>("Deskripsi");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        descriptionCol.setPrefWidth(100);

        TableColumn<Item, String> itemLocationCol = new TableColumn<>("Lokasi");
        itemLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        itemLocationCol.setPrefWidth(100);

        TableColumn<Item, String> itemStatusCol = new TableColumn<>("Status");
        itemStatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        itemStatusCol.setPrefWidth(100);

        itemTableView.getColumns().addAll(itemNameCol, descriptionCol, itemLocationCol, itemStatusCol);
        itemTableView.setItems(DataStore.reportedItemsData);
        VBox.setVgrow(itemTableView, Priority.ALWAYS);

        Button markClaimedButton = new Button("Tandai Claimed");
        Button logoutButtonLeft = new Button("Logout");
        HBox itemButtonsBox = new HBox(10, markClaimedButton, logoutButtonLeft);
        itemButtonsBox.setAlignment(Pos.CENTER_LEFT);

        leftPane.getChildren().addAll(welcomeText, itemReportTitle, itemTableView, itemButtonsBox);

        VBox rightPane = new VBox(10);
        rightPane.setPadding(new Insets(10));

        Text studentDataTitle = new Text("Data Mahasiswa");
        studentDataTitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        studentTableView = new TableView<>();
        TableColumn<Mahasiswa, String> studentNameCol = new TableColumn<>("Nama");
        studentNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        studentNameCol.setPrefWidth(150);

        TableColumn<Mahasiswa, String> studentNimCol = new TableColumn<>("NIM");
        studentNimCol.setCellValueFactory(new PropertyValueFactory<>("nim"));
        studentNimCol.setPrefWidth(150);

        studentTableView.getColumns().addAll(studentNameCol, studentNimCol);
        studentTableView.setItems(DataStore.getMahasiswaData());
        VBox.setVgrow(studentTableView, Priority.ALWAYS);

        HBox studentForm = new HBox(10);
        studentForm.setAlignment(Pos.CENTER_LEFT);

        TextField studentNameField = new TextField();
        studentNameField.setPromptText("Nama Mahasiswa");
        studentNameField.setPrefWidth(130);

        TextField studentNimField = new TextField();
        studentNimField.setPromptText("NIM");
        studentNimField.setPrefWidth(100);

        Button addStudentButton = new Button("Tambah");
        Button deleteStudentButton = new Button("Hapus");
        studentForm.getChildren().addAll(studentNameField, studentNimField, addStudentButton, deleteStudentButton);

        rightPane.getChildren().addAll(studentDataTitle, studentTableView, studentForm);

        splitPane.getItems().addAll(leftPane, rightPane);
        root.setCenter(splitPane);

        Alert alert = new Alert(Alert.AlertType.WARNING);

        markClaimedButton.setOnAction(e -> {
            Item selectedItem = itemTableView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                if (!selectedItem.getStatus().equals("Claimed")) {
                    selectedItem.setStatus("Claimed");
                    itemTableView.refresh();
                    System.out.println("Item '" + selectedItem.getItemName() + "' marked as Claimed.");
                } else {
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setContentText("Item '" + selectedItem.getItemName() + "' sudah di-claim.");
                    alert.showAndWait();
                }
            } else {
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setTitle("Peringatan");
                alert.setHeaderText("Tidak ada item yang dipilih");
                alert.showAndWait();
            }
        });

        logoutButtonLeft.setOnAction(e -> {
            System.out.println("Logout clicked");
            LoginSystem.currentUser = null;
            stage.setScene(LoginPane.showLoginScene(stage));
        });

        addStudentButton.setOnAction(e -> {
            String name = studentNameField.getText();
            String nim = studentNimField.getText();
            if (!name.isEmpty() && !nim.isEmpty()) {
                boolean nimExists = DataStore.getMahasiswaData().stream().anyMatch(s -> s.getNim().equals(nim));
                if (nimExists) {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("NIM " + nim + " sudah terdaftar.");
                    alert.showAndWait();
                } else {
                    DataStore.userData.add(new Mahasiswa(name, nim));
                    studentNameField.clear();
                    studentNimField.clear();
                    studentTableView.setItems(DataStore.getMahasiswaData());
                    System.out.println("Mahasiswa added: " + name + " - " + nim);
                }
            } else {
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setTitle("Peringatan");
                alert.setContentText("Nama dan NIM mahasiswa tidak boleh kosong.");
                alert.showAndWait();
            }
            studentTableView.refresh();
        });

        deleteStudentButton.setOnAction(e -> {
            Mahasiswa selectedStudent = studentTableView.getSelectionModel().getSelectedItem();
            if (selectedStudent != null) {
                DataStore.userData.remove(selectedStudent);
                System.out.println("Mahasiswa removed: " + selectedStudent.getName());
                studentTableView.setItems(DataStore.getMahasiswaData());
                studentTableView.refresh();

            } else {
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setTitle("Peringatan");
                alert.setContentText("Pilih mahasiswa yang akan dihapus.");
                alert.showAndWait();
            }
        });

        Scene scene = new Scene(root, 850, 500);
        return scene;
    }
}
