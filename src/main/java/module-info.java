module com.bn {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.bn to javafx.fxml;
    exports com.bn;
}
