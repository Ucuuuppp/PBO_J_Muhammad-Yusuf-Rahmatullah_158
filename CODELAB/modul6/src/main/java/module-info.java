module com.yusuf {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.yusuf to javafx.fxml;
    exports com.yusuf;
}
