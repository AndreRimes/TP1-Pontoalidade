package pontoalidade;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class SignupController implements Initializable {

    @FXML
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;
    
    @FXML
    private TextField cpfField;

    private Organizacao organizacao;

    public SignupController(Organizacao organizacao) {
        this.organizacao = organizacao;
    }

    @FXML
    public void handleSignup(ActionEvent event) {
        if (organizacao == null) {
            System.out.println("Organizacao não foi definida.");
            return;
        }

        String email = emailField.getText();
        String name = nameField.getText();
        String password = passwordField.getText();
        String cpf = cpfField.getText();

        if (email.isEmpty() || name.isEmpty() || password.isEmpty() || cpf.isEmpty()) {
            System.out.println("Por favor, preencha todos os campos.");
            return;
        }

        for (int i = 0; i < organizacao.getUsuarios().size(); i++) {
            if (organizacao.getUsuarios().get(i).getNome().equals(name) ||
                organizacao.getUsuarios().get(i).getEmail().equals(email) ||
                organizacao.getUsuarios().get(i).getCpf().equals(cpf)) {
                System.out.println("Email, nome ou cpf de usuário já cadastrado.");
                return;
            }
        }
        
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pontoalidade/chooseorg.fxml"));

        ChooseOrgController cho = new ChooseOrgController(organizacao, name, password, cpf, email);
        loader.setController(cho);

        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleGoToLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pontoalidade/login.fxml"));
            
            LoginController lg = new LoginController(organizacao);
            loader.setController(lg);

            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}

