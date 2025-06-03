module com.yusuf {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.yusuf to javafx.fxml;
    opens com.yusuf.data to javafx.base;
    opens com.yusuf.users to javafx.base;


    exports com.yusuf;
}
