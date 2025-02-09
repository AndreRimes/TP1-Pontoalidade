/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package pontoalidade;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;


/**
 * FXML Controller class
 *
 * @author andre
 */
public class LoginController implements Initializable {
    
    private final Organizacao organizacao;
    
    @FXML
    private TextField emailField;
        
    @FXML 
    private PasswordField passwordField;
    
    @FXML
    private Label errorLabel;
    
    public LoginController(Organizacao org){
        this.organizacao = org;
    }
   
    
    
    @FXML
    private void handleClick(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        try {
            Usuario user = organizacao.validateCredentials(email, password);
    
            Router router = new Router();
                  
            if(user instanceof Funcionario){
                router.userDashboard(event, (Funcionario) user, organizacao, user.findToday());
            }else {
                router.orgDashboard(event, organizacao, (Administrador) user);
            }
        
        }catch (IllegalArgumentException e) {
            errorLabel.setText("Senha errada, tente novamente!");
        }
    }
    
     
     @FXML
    private void handleGoToSignUp(ActionEvent event) {
       Router router = new Router();
       
       router.signup(event, organizacao);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errorLabel.setText(""); // Limpa qualquer mensagem ao inicializar
       
    }    
    
}
