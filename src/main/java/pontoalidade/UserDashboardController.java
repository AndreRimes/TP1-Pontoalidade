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
import javafx.scene.control.Label;
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
    
    @FXML
    private Label name;
    
    @FXML
    private Label horasMes;
    
    @FXML
    private Label salarioEstimado;
    
    private Funcionario usuarioLogado;
    
    private Organizacao org;

    public UserDashboardController(Usuario usuarioLogado, Organizacao org) {
        if(usuarioLogado instanceof Funcionario){
           this.usuarioLogado = (Funcionario) usuarioLogado; 
        }
        this.org = org;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        hoursColumn.setCellValueFactory(new PropertyValueFactory<>("hours"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("actionButton"));
        
        dateColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.33));
        hoursColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.33));
        actionColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.33));
        
        ObservableList<RowData> dias = FXCollections.observableArrayList();

        for (Dia dia : this.usuarioLogado.getDiasTrabalhados()) {
            RowData rd = new RowData(dia.getData(), dia.getHorarioTotal(), usuarioLogado.getMetaHorasDiaria());
            dias.add(rd);
        }

        table.setItems(dias);

       this.name.setText(this.usuarioLogado.getNome());
       this.salarioEstimado.setText("Salario estimado: " + String.valueOf(this.usuarioLogado.calculaSalario())); 
       this.horasMes.setText("Horas trabalhadas no mes: " + String.valueOf(this.usuarioLogado.calculaSalario()));        

        
    }

    public static class RowData {
        private final String date;
        private final double hours;
        private final Button actionButton;

        public RowData(String date, double hours, double meta) {
            this.date = date;
            this.hours = hours;

            if (hours < meta) {
                this.actionButton = new Button("Justificar Falta");
                this.actionButton.setOnAction(e -> openModal());
            } else {
                this.actionButton = new Button("N/A");
                this.actionButton.setDisable(true);
            }
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

        public double getHours() {
            return hours;
        }

        public Button getActionButton() {
            return actionButton;
        }
    }
    
    
}
