package pontoalidade;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality; 
import javafx.stage.Stage;


public class UserDashboardController implements Initializable {

    @FXML
    private TableView<RowData> table;

    @FXML
    private TableColumn<RowData, String> dateColumn;

    @FXML
    private TableColumn<RowData, Integer> hoursColumn;

    @FXML
    private TableColumn<RowData, Button> actionColumn;

    private final ObservableList<RowData> mockData = FXCollections.observableArrayList(
            new RowData("2024-12-25", 8),
            new RowData("2024-12-26", 5),
            new RowData("2024-12-27", 6)
    );

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        hoursColumn.setCellValueFactory(new PropertyValueFactory<>("hours"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("actionButton"));
        
        dateColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.33));
        hoursColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.33));
        actionColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.33));

        table.setItems(mockData);
    }

    public static class RowData {
        private final String date;
        private final Integer hours;
        private final Button actionButton;

        public RowData(String date, Integer hours) {
            this.date = date;
            this.hours = hours;
            this.actionButton = new Button("Justificar Falta");
            this.actionButton.setOnAction(e -> openModal());
        }

        private void openModal() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("JustificarFalta.fxml"));
            Parent root = loader.load();
            
            
            JustificarFaltaController controller = loader.getController();  
            controller.setDate(date);
            
            Stage stage = new Stage();
            stage.setTitle("Justificar Falta");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception ex) {
            ex.printStackTrace();
            }
        }
        
        public String getDate() {
            return date;
        }

        public Integer getHours() {
            return hours;
        }

        public Button getActionButton() {
            return actionButton;
        }
    }
}
