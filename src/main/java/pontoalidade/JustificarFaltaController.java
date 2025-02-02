package pontoalidade;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class JustificarFaltaController {
    @FXML
    private Label dateLabel;

    @FXML
    private TextArea descriptionField;

    private Falta falta;
    private UserDashboardController parentController;

    public void setFalta(Falta falta) {
        this.falta = falta;
        if (falta != null) {
            dateLabel.setText("Justificativa de falta do dia " + falta.getData());
        }
    }
    
    public void setParentController(UserDashboardController parentController) {
        this.parentController = parentController;
    }

    @FXML
    private void handleJustificar() {
        String description = descriptionField.getText().trim();

        if (description.isEmpty()) {
            System.out.println("Erro: A justificativa não pode estar vazia.");
            return;
        }

        Justificativa justificativa = new Justificativa(description);
        justificativa.setStatus(StatusJustificativa.Enviada);
        falta.setJustificativa(justificativa);
    
        System.out.println("Justification Submitted:");
        System.out.println("Date: " + falta.getData());
        System.out.println("Description: " + description);

        if (parentController != null) {
            parentController.updateRowData();
        }

        closeModal();
    }
    
    private void closeModal() {
        
        Stage stage = (Stage) dateLabel.getScene().getWindow();
        stage.close();
    }
}