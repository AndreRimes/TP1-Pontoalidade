package pontoalidade;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality; 
import javafx.stage.Stage;


public class UserProfileController implements Initializable {

    @FXML
    private TableView<RowData> table;

    @FXML
    private TableColumn<RowData, String> dateColumn;

    @FXML
    private TableColumn<RowData, Integer> hoursColumn;

    @FXML
    private TableColumn<RowData, Button> actionColumn;
    
    @FXML
    private TableColumn<RowData, String> statusColumn;
    
    @FXML
    private Label nameOrg;
    
    @FXML
    private Label cnpjLabel;
    
    @FXML
    private Label emailLabel;

    @FXML 
    private ComboBox<String> monthComboBox;
    
    @FXML 
    private ComboBox<String> yearComboBox;
    
    private Usuario usuarioLogado;
    
    private Usuario user;
    
    private ObservableList<RowData> allData = FXCollections.observableArrayList();

    public UserProfileController(Usuario user, Usuario usuarioLogado) {
        System.out.println(user.getEmail());
        this.user = user;
        this.usuarioLogado = usuarioLogado;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        hoursColumn.setCellValueFactory(new PropertyValueFactory<>("hours"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("actionButton"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        dateColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        hoursColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        statusColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        actionColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        
        this.nameOrg.setText(this.user.getNome());
        this.cnpjLabel.setText(this.user.getCpf());
        this.emailLabel.setText(this.user.getEmail());
        
        monthComboBox.setItems(FXCollections.observableArrayList(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        ));

        ObservableList<String> years = FXCollections.observableArrayList();
        int currentYear = LocalDate.now().getYear();
        for (int i = currentYear - 5; i <= currentYear + 1; i++) {
            years.add(String.valueOf(i));
        }
        yearComboBox.setItems(years);

        monthComboBox.setValue(LocalDate.now().getMonth().name());
        yearComboBox.setValue(String.valueOf(currentYear));
        
        monthComboBox.setOnAction(e -> updateTable());
        yearComboBox.setOnAction(e -> updateTable());
        
        this.updateTable();
    }
    
    @FXML
    private void handleBackClick(MouseEvent event){
        Router router = new Router();
        if(usuarioLogado instanceof Funcionario){
            Funcionario func = (Funcionario) usuarioLogado;
            router.userDashboard(event, func, usuarioLogado.getOrganizacao(),  func.findToday());
        }else{
            router.orgDashboard(event, user.getOrganizacao(), (Administrador) usuarioLogado);
        }
    }
    
    public void updateTable(){
        ObservableList<RowData> data = FXCollections.observableArrayList();
        if(this.user instanceof Administrador){
            return;
        }
         
            // if(dia.getFalta() != null && dia.getFalta().getJustificativa() != null){
            //     status = "Status da justificativa: " +  dia.getFalta().getJustificativa().getStatus();
            // }else if(dia.getFalta() != null && dia.getFalta().getJustificativa() == null){
            //     status = "Justificativa Pendente";
            // }else {
            //     status = "Status do dia: " + dia.getStatus();
            // }
            
        
        ObservableList<RowData> data = FXCollections.observableArrayList();

        String selectedMonth = monthComboBox.getValue();
        String selectedYear = yearComboBox.getValue();

        if (selectedMonth == null || selectedYear == null) return;

        int monthNumber = monthComboBox.getItems().indexOf(selectedMonth) + 1;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        
        if (this.user instanceof Funcionario) {
            Funcionario func = (Funcionario) this.user;
            data.addAll(func.getDiasTrabalhados().stream()
                .filter(dia -> {
                    LocalDate date = LocalDate.parse(dia.getData(), formatter);
                    return date.getMonthValue() == monthNumber && date.getYear() == Integer.parseInt(selectedYear);
                })
                .map(dia -> new RowData(dia.getData(), dia.getHorarioTotal()))
                .collect(Collectors.toList()));

            table.setItems(data);
        }
    }

    public static class RowData {
        private final String date;
        private final double hours;
        private final String status;
        private final Dia dia;
        private final Button actionButton;
        private final Usuario user;
        private final UserProfileController controller;

        public RowData(String date, double hours, String status, Dia dia, Usuario user, UserProfileController controller) {
            this.date = date;
            this.hours = hours;
            this.dia = dia;
            this.user = user;
            this.status = status;
            this.controller = controller;
            if(dia.getFalta() != null && dia.getFalta().getJustificativa() != null){
               this.actionButton = new Button("Ver justificativa");
               this.actionButton.setOnAction(e -> openModal());
            }else{
                this.actionButton = null;
            }
        }

        private void openModal() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("Justificativa.fxml"));
            Parent root = loader.load();
            
            
            JustificativaController controller = loader.getController();
            controller.setDate(date);
            controller.setDescription(this.dia.getFalta().getJustificativa().getDescricao());
            controller.setName(this.user.getNome());
            controller.setJusticativa(dia.getFalta().getJustificativa());
            controller.setParentController(this.controller);
            
            
            Stage stage = new Stage();
            stage.setTitle("Ver Justificatva");
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
        
        public String getStatus(){
            return this.status;
        }

        public double getHours() {
            return hours;
        }

        public Button getActionButton() {
            return actionButton;
        }
    }
}
