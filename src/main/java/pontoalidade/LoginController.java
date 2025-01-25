/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package pontoalidade;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    
    public LoginController(Organizacao org){
        this.organizacao = org;
    }
    
    
    @FXML
    private void handleClick(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        try {
            Usuario user = organizacao.validateCredentials(email, password);
            
            
            System.out.println("USER: " + user.getNome());
                
            if(user instanceof Funcionario){
                userDashBoard(event);
            }else {
                organizationDashBoard(event);
            }
 
        } catch (IllegalArgumentException e) {
            System.out.println("SEnha errada filhao");
            showAlert("Error", "An error occurred: " + e.getMessage());
        }
    }
    
    
    private void userDashBoard(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pontoalidade/userDashBoard.fxml"));

            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void organizationDashBoard(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pontoalidade/organizationDashboard.fxml"));

            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
}
