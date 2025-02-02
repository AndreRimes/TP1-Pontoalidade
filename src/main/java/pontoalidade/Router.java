/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pontoalidade;

import java.io.IOException;
import java.lang.ModuleLayer.Controller;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author andre
 */
public class Router {
    
    
    public void login(ActionEvent event, Organizacao org){
        LoginController controller = new LoginController(org);
        
        redirect("login", controller, event);
    }
    
    public void signup(ActionEvent event, Organizacao org){
        SignupController controller = new SignupController(org);
   
      redirect("signup", controller, event);
    }
    
    public void chooseOrg(ActionEvent event, Organizacao organizacao, String userName, String userPassword, String userCPF, String userEmail){
        ChooseOrgController controller = new ChooseOrgController(organizacao, userName, userPassword, userCPF, userEmail);    
        
        redirect("chooseorg", controller, event);
    }
    
   
    public void userDashboard(Event event, Funcionario usuarioLogado, Organizacao org, Dia currentDay ){
        UserDashboardController userDashboardController = new UserDashboardController(usuarioLogado, org, currentDay);
        
        redirect("userDashBoard", userDashboardController, event);
    }
    
    public void orgDashboard(Event event, Organizacao org, Administrador user){
        OrganizacaoDashboardController organizacaoDashboardController = new OrganizacaoDashboardController(org, user);

        redirect("organizacaoDashboard", organizacaoDashboardController, event);
    }
    
    public void userProfile(ActionEvent event, Usuario user, Usuario userLogado){
        UserProfileController controller = new UserProfileController(user, userLogado);
        
        redirect("userProfile", controller , event);
    }
    
    
    
    
    private void redirect(String url,  Initializable controller, Event event ){
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pontoalidade/" + url +  ".fxml"));
            
            loader.setController(controller);

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
