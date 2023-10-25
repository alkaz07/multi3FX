module com.example.multi3fx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.multi3fx to javafx.fxml;
    exports com.example.multi3fx;
}