

package pontoalidade;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class JustificativaController {
    @FXML
    private Label dateLabel;

    @FXML
    private Label descriptionField;
    

    public void setDate(String date) {
        dateLabel.setText("Justificacao de falta do dia " + date);
    }
    
    
    public void setDescription(String des){
        descriptionField.setText(des);
    }
    
}
