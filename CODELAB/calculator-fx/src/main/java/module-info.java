module com.tugas {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.tugas to javafx.fxml;
    exports com.tugas;
}
