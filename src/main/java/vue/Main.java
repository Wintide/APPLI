package vue;



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    public void start(Stage stage) {
        Appli root = new Appli();
        Scene scene = new Scene(root, 1275, 450);
        File fichier = new File("css"+File.separator+"appli.css");

        scene.getStylesheets().add(fichier.toURI().toString());

        stage.setScene(scene);
        stage.setTitle("Appli");
        stage.show();


    }
    public static void main (String [] args) {
        Application.launch(args);
    }
}
