module com.example.w1_jfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.w1_jfx to javafx.fxml;
    exports com.example.w1_jfx;
}