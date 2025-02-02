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
        organizacao.addUsuario(new Funcionario("Carlos Silva", "carlos.silva@techunb.com", "111.222.333-44", "senha123", 20.0, organizacao));
        organizacao.addUsuario(new Funcionario("Ana Pereira", "ana.pereira@techunb.com", "555.666.777-88", "senha456", 25.0, organizacao));
        organizacao.addUsuario(new Funcionario("João Souza", "joao.souza@techunb.com", "999.000.111-22", "senha789", 18.5, organizacao));
        Administrador andre = new Administrador("Andre", "andre.2004.rimes@gmail.com", "05311988126", "senha10", 20, organizacao);
        
        
        
        Dia dia1 = new Dia("25/01/2025", "08:00", "16:00", null);
        dia1.setStatus(Status.ENDED);
        Dia dia2 = new Dia("26/01/2025", "09:00", "17:00", null);
        dia2.setStatus(Status.ENDED);
        
        // dia esta finalizado [x]
        // dia esta pausado [x]
        // dia esta contando 
        
        Dia dia3 = new Dia("29/01/2025", "06:00", null, null);
        dia3.setStatus(Status.RUNNING);
        
        andre.addDiaTrabalhado(dia1);
        andre.addDiaTrabalhado(dia2);
        andre.addDiaTrabalhado(dia3);
        
        organizacao.addUsuario(andre);
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pontoalidade/signup.fxml"));
        SignupController sg = new SignupController(organizacao);  
        loader.setController(sg);  

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
