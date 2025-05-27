module com.example.appli {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens com.example.appli to javafx.fxml;
    exports com.example.appli;
    exports vue;
    exports modele;
}