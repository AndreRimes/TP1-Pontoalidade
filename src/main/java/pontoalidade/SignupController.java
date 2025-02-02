package pontoalidade;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

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
        
        Router router = new Router();
        router.chooseOrg(event, organizacao, name, password, cpf, email);
    }
    
    @FXML
    private void handleGoToLogin(ActionEvent event) {
       Router router = new Router();
       router.login(event, organizacao);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}

