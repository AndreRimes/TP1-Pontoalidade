

package pontoalidade;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class JustificativaController {
    @FXML
    private Label dateLabel;

    @FXML
    private Label descriptionField;
    
    @FXML
    private Label nameLabel;
    
    @FXML
    private Button acceptButton;
    
    @FXML
    private Button refuseButton;
   
    private Justificativa justificativa;
    
    private UserProfileController parentController;


    public JustificativaController() {
    }
   
    
    @FXML
    private void handleAccept() {
        if (justificativa != null) {
            justificativa.setStatus(StatusJustificativa.Aceita);
            acceptButton.setDisable(true);
            refuseButton.setDisable(true);
            System.out.println("Justificativa aceita.");
            this.parentController.updateTable();
        }
    }
    
    @FXML
    private void handleRefuse() {
        if (justificativa != null) {
            justificativa.setStatus(StatusJustificativa.Recusada);
            acceptButton.setDisable(true);
            refuseButton.setDisable(true);
            System.out.println("Justificativa recusada.");
            this.parentController.updateTable();
        }
    }
    

    public void setDate(String date) {
        dateLabel.setText("Justificacao de falta do dia " + date);
    }
    
    public void setJusticativa(Justificativa just){
        this.justificativa = just;
        
        if(justificativa.getStatus() != StatusJustificativa.Enviada){
            acceptButton.setDisable(true);
            refuseButton.setDisable(true);
        }
         
    }
   
    public void setName(String name){
        nameLabel.setText("Justificativa de: " + name);
    }
    
     
    public void setDescription(String des){
        descriptionField.setText(des);
    }
    
     public void setParentController(UserProfileController parentController) {
        this.parentController = parentController;
    }
    
}
