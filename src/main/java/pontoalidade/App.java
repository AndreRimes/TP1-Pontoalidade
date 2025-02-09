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
        
        Funcionario andre = new Funcionario("Andre", "andre.2004.rimes@gmail.com", "05311988126", "senha10", 20, organizacao);
        Dia dia4 = new Dia("01/03/2025", "08:30", "17:30", null, andre);
        Dia dia5 = new Dia("02/03/2025", "09:00", "18:00", null, andre);
        dia4.setStatus(Status.ENDED);
        dia5.setStatus(Status.ENDED);
        andre.addDiaTrabalhado(dia4);
        andre.addDiaTrabalhado(dia5);

        Funcionario bernardo = new Funcionario("Bernardo", "be@gmail.com", "12345678900", "senha1", 12, organizacao);
        organizacao.addUsuario(bernardo);
        Dia dia6 = new Dia("01/03/2025", "07:00", "15:00", null, bernardo);
        dia6.setStatus(Status.ENDED);
        bernardo.addDiaTrabalhado(dia6);

        // Dias para Carlos Silva
        Funcionario carlos = (Funcionario) organizacao.getUsuarios().get(0);
        Dia dia7 = new Dia("28/02/2025", "08:00", "16:00", null, carlos);
        dia7.setStatus(Status.ENDED);
        carlos.addDiaTrabalhado(dia7);

        // Dias para Ana Pereira
        Funcionario ana = (Funcionario) organizacao.getUsuarios().get(1);
        Dia dia8 = new Dia("28/02/2025", "09:30", "17:30", null, ana);
        dia8.setStatus(Status.ENDED);
        ana.addDiaTrabalhado(dia8);


        
        Administrador adm = new Administrador("adm", "andre.24.rimes@gmail.com", "1231231321", "senha10", 20, organizacao); 
        Dia dia1 = new Dia("25/01/2025", "08:00", "16:00", null, andre );
        dia1.setStatus(Status.ENDED);
        Dia dia2 = new Dia("26/02/2025", "09:00", "17:00", null, andre);
        dia2.setStatus(Status.ENDED);
       
        
        Dia dia3 = new Dia("29/02/2025", "06:00", "10:00", null, andre);
        dia3.setStatus(Status.ENDED);
        
        Falta falta = new Falta("29/02/2025");
        dia3.setFalta(falta);
        
       Justificativa just = new Justificativa("Tive uma emergencia familiar e nao pude terminar todas as minha horas.");
       just.setStatus(StatusJustificativa.Enviada);
       falta.setJustificativa(just);
        
        andre.addDiaTrabalhado(dia1);
        andre.addDiaTrabalhado(dia2);
        andre.addDiaTrabalhado(dia3);
        
        bernardo.addDiaTrabalhado(dia1);
        bernardo.addDiaTrabalhado(dia2);
        
        organizacao.addUsuario(andre);
        organizacao.addUsuario(adm);
        
        
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
