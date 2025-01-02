

package pontoalidade;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;

public class JustificarFaltaController {
    @FXML
    private Label dateLabel;

    @FXML
    private TextArea descriptionField;
    

    @FXML
    private void handleJustificar() {
        String date = dateLabel.getText();
        String description = descriptionField.getText();

        System.out.println("Justification Submitted:");
        System.out.println("Date: " + date);
        System.out.println("Description: " + description);
        // Add logic to save the justification here
    }
    
    public void setDate(String date) {
        dateLabel.setText("Justificacao de falta do dia " + date);
    }
    
}
