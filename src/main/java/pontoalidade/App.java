package pontoalidade;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Organizacao organizacao = new Organizacao("Tech UNB", "02.959.106/0001-42", "12345");

        // Manually setting the controller and loading the FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pontoalidade/signup.fxml"));
        SignupController sg = new SignupController(organizacao);  // Create controller with organizacao
        loader.setController(sg);  // Set the controller

        // Load the FXML and set the scene
        Parent root = loader.load();
        Scene scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.setTitle("User Signup");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml, null));
    }

    private static Parent loadFXML(String fxml, Organizacao organizacao) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        
        if (organizacao != null) {
            SignupController sg = new SignupController(organizacao);
            loader.setController(sg);
        }

        return loader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
