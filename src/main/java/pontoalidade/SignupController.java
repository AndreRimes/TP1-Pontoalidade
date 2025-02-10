package pontoalidade;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;

public class SignupController implements Initializable {
    @FXML
    private Label passwordLabel;
    
    @FXML
    private PasswordField passworddoisField;
    
    @FXML
    private TextField emailField;
    
    @FXML
    private Label emailError;

    @FXML
    private TextField nameField;
    
    @FXML
    private Label nameError;

    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Label errorLabel;
    
    @FXML
    private TextField cpfField;
    
    @FXML
    private Label cpfError;

    private Organizacao organizacao;

    public SignupController(Organizacao organizacao) {
        this.organizacao = organizacao;
    }
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    private boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
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
        String rewritedPassword = passworddoisField.getText();
        String cpf = cpfField.getText();

        if (email.isEmpty() || name.isEmpty() || password.isEmpty() || cpf.isEmpty()) {
            errorLabel.setText("Por favor,preencha todos os campos");
            passwordLabel.setText("");
            return;
        }

        for (int i = 0; i < organizacao.getUsuarios().size(); i++) {
            if (organizacao.getUsuarios().get(i).getNome().equals(name)){
                nameError.setText("Esse nome já foi cadastrado.");
                return;
            }
                
            if (organizacao.getUsuarios().get(i).getEmail().equals(email)){
                emailError.setText("Esse nome já foi cadastrado.");
                return;
            }
                
            if (organizacao.getUsuarios().get(i).getCpf().equals(cpf)) {
                cpfError.setText("Esse cpf já foi cadastrado.");;
                return;
            }
        }
        if(!password.equals(rewritedPassword)){
            passwordLabel.setText("as senhas não correspondem.");
            return;
        }
        if (!isValidEmail(email)) {
        emailError.setText("Formato de email inválido.");
        return;
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
        errorLabel.setText("");
        emailError.setText("");
        cpfError.setText("");
        nameError.setText("");
        passwordLabel.setText("");
        cpfField.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            cpfField.setText(newValue.replaceAll("[^\\d]", ""));
        }
        if (cpfField.getText().length() > 11) {
            cpfField.setText(cpfField.getText().substring(0, 11));
        }
    });
        
        
    }
}

