/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author andre
 */
public class ChooseOrgController implements Initializable {

    private final Organizacao organizacao;
    
    private final String userName;
    
    private final String userPassword;
    
    private final String userCPF;
    
    private final String userEmail;
    
    @FXML
    private PasswordField passwordField;
    
    
    public ChooseOrgController(Organizacao organizacao, String userName, String userPassword, String userCPF, String userEmail) {
        this.organizacao = organizacao;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userCPF = userCPF;
        this.userEmail = userEmail;
    }
    
    @FXML
    private void handleBackClick(MouseEvent event){
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pontoalidade/signup.fxml"));
     
            System.out.println(organizacao.getNome());
            
            SignupController sg = new SignupController(organizacao);
            loader.setController(sg);

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
    private void handleButtonClick(ActionEvent event) {
        String enteredPassword = passwordField.getText();
        
        System.out.println(userName);
        
        if(enteredPassword.equals(this.organizacao.getPassword())){
            Usuario user = new Funcionario(userName, userEmail, userCPF, userPassword , 10.0, organizacao) ;
            this.organizacao.addUsuario(user);
            
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
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
