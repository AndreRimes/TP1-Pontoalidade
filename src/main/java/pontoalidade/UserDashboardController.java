package pontoalidade;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UserDashboardController implements Initializable {

    // FXML Elements
    @FXML private TableView<RowData> table;
    @FXML private TableColumn<RowData, String> dateColumn;
    @FXML private TableColumn<RowData, Integer> hoursColumn;
    @FXML private TableColumn<RowData, Button> actionColumn;
    @FXML private TableColumn<RowData, String> justificationColumn;
    @FXML private Label name;
    @FXML private Label horasMes;
    @FXML private Label salarioEstimado;
    @FXML private Label hour;
    @FXML private Button countBtn;
    @FXML private Button pauseBtn;
    @FXML private Button terminarBtn;
    @FXML private ComboBox<String> monthComboBox;
    @FXML private ComboBox<Integer> yearComboBox;
    @FXML private Label mes;
    
    // State variables
    private Timeline timer;
    private int seconds = 0; 
    private boolean isRunning = false;
    private Funcionario usuarioLogado;
    private Organizacao org;
    private Dia currentDay;

    // Constructor
    public UserDashboardController(Usuario usuarioLogado, Organizacao org, Dia cd) {
        if (usuarioLogado instanceof Funcionario) {
            this.usuarioLogado = (Funcionario) usuarioLogado; 
        }
        this.currentDay = cd;
        this.org = org;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeTable();
        updateUserInfo();
        configureDayStatusButtons();
        initializeTimer();
        configureButtonActions();
        initializeComboBoxes();
    }

    // Initialization of table columns and row data
    private void initializeTable() {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        hoursColumn.setCellValueFactory(new PropertyValueFactory<>("hours"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("actionButton"));
        justificationColumn.setCellValueFactory(new PropertyValueFactory<>("justificationStatus"));

        dateColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.30));
        hoursColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.30));
        actionColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
        justificationColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.20));

        updateRowData();
    }
    
    private void initializeComboBoxes() {
        monthComboBox.setItems(FXCollections.observableArrayList(
            "January", "February", "March", "April", "May", "June", "July", 
            "August", "September", "October", "November", "December"
        ));
        monthComboBox.setValue("January");

        yearComboBox.setItems(FXCollections.observableArrayList(2020, 2021, 2022, 2023, 2024, 2025));
        yearComboBox.setValue(2025);
        
        monthComboBox.setOnAction(e -> updateTableForSelectedMonthYear());
        yearComboBox.setOnAction(e -> updateTableForSelectedMonthYear());
    }
    
    private void updateTableForSelectedMonthYear() {
        String selectedMonth = monthComboBox.getValue();
        int selectedYear = yearComboBox.getValue();
        updateRowDataForMonthYear(selectedMonth, selectedYear);
    }
    
    private void updateRowDataForMonthYear(String month, int year) {
        ObservableList<RowData> filteredDias = FXCollections.observableArrayList();
        for (Dia dia : this.usuarioLogado.getDiasTrabalhados()) {
            String[] dateParts = dia.getData().split("/");
            int diaDay = Integer.parseInt(dateParts[0]);
            int diaMonth = Integer.parseInt(dateParts[1]);
            int diaYear = Integer.parseInt(dateParts[2]);

            if (diaMonth == getMonthIndex(month) && diaYear == year) {
                RowData rd = new RowData(dia.getData(), dia.getHorarioTotal(), usuarioLogado.getMetaHorasDiaria(), dia.getFalta(), this);
                filteredDias.add(rd);
            }
        }

        table.setItems(filteredDias);
    }
    
    private int getMonthIndex(String monthName) {
        switch (monthName) {
            case "January": return 1;
            case "February": return 2;
            case "March": return 3;
            case "April": return 4;
            case "May": return 5;
            case "June": return 6;
            case "July": return 7;
            case "August": return 8;
            case "September": return 9;
            case "October": return 10;
            case "November": return 11;
            case "December": return 12;
            default: return 1;
        }
    }

    // Configure buttons based on the current day status
    private void configureDayStatusButtons() {
        if (currentDay != null && currentDay.getStatus() == Status.RUNNING) {
            pauseBtn.setText("Pause");
        } else if (currentDay != null && currentDay.getStatus() == Status.PAUSED) {
            pauseBtn.setText("Continue");
        }

        disableBtns();
       terminarBtn.setOnAction(e -> endDay());
    }
    
    // Update user information
    private void updateUserInfo() {
        this.name.setText(this.usuarioLogado.getNome());
        this.salarioEstimado.setText("Salario estimado: " + String.valueOf(this.usuarioLogado.calcularSalario())); 
        this.mes.setText("Mes atual: " + obterMesAtual()); 
        this.horasMes.setText("Horas trabalhadas no mês: " + String.valueOf(this.usuarioLogado.horasMes())); 
    }

    private String obterMesAtual() {
        java.time.LocalDate hoje = java.time.LocalDate.now();
        java.time.format.TextStyle estilo = java.time.format.TextStyle.FULL;
        java.util.Locale locale = new java.util.Locale("pt", "BR");

        return hoje.getMonth().getDisplayName(estilo, locale);
    }

    // Disable buttons when day is ended
    private void disableBtns() {
        if (currentDay != null && currentDay.getStatus() == Status.ENDED) {
            countBtn.setDisable(true);
            pauseBtn.setDisable(true);
            terminarBtn.setDisable(true);
        }
    }

    // Configure button actions
    private void configureButtonActions() {
        countBtn.setOnAction(e -> startTimer());
        pauseBtn.setOnAction(e -> togglePauseResume());
        terminarBtn.setOnAction(e -> endDay());
    }

    // Update row data in the table
    public void updateRowData() {
        ObservableList<RowData> dias = FXCollections.observableArrayList();
        for (Dia dia : this.usuarioLogado.getDiasTrabalhados()) {
            System.out.println(dia.getData() + " - " + dia.getHorarioTotal());
            if (dia.getHorarioTotal() < usuarioLogado.getMetaHorasDiaria()) {
                if (dia.getFalta() == null) {
                    dia.setFalta(new Falta(dia.getData())); 
                }
            }

            RowData rd = new RowData(dia.getData(), dia.getHorarioTotal(), usuarioLogado.getMetaHorasDiaria(), dia.getFalta(), this);
            dias.add(rd);
        }

        table.setItems(dias);
    }

    // Timer initialization
    private void initializeTimer() {
        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTimer()));
        timer.setCycleCount(Timeline.INDEFINITE);
    }

    // Start the timer and create a new day if necessary
    private void startTimer() {
        if (currentDay == null) {
            String currentDate = java.time.LocalDate.now()
                                    .format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String currentTime = java.time.LocalTime.now()
                                    .format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"));

            Dia cd = new Dia(currentDate, currentTime, null, null, this.usuarioLogado);
            
            this.usuarioLogado.addDiaTrabalhado(cd);
            this.currentDay = cd;
            countBtn.setDisable(true);
            isRunning = true;
            timer.play();
        } else {
            isRunning = true;
            timer.play();
        }
    }

    // Toggle pause and resume functionality
    private void togglePauseResume() {
        if (isRunning) {
            pauseTimer();
            pauseBtn.setText("Continue");
        } else {
            resumeTimer();
            pauseBtn.setText("Pause");
        }
    }

    // Pause the timer
    private void pauseTimer() {
        if (isRunning && this.currentDay != null) {
            this.currentDay.setStatus(Status.PAUSED);

            String currentTime = java.time.LocalTime.now()
                                .format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"));

            Pausa p = new Pausa(currentTime, null);
            this.currentDay.setPausa(p);

            isRunning = false;
            timer.pause();
        }
    }

    // Resume the timer
    private void resumeTimer() {
        if (!isRunning && this.currentDay != null) {
            this.currentDay.setStatus(Status.RUNNING);

            String currentTime = java.time.LocalTime.now()
                                    .format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"));

            this.currentDay.getPausa().setHorarioFinal(currentTime);
            this.currentDay.getPausa().setHorarioTotal();

            isRunning = true;
            timer.play();
        }
    }

    // End the day and reset everything
    private void endDay() {        
        if (this.currentDay == null) {
            return;
        }

        String currentTime = java.time.LocalTime.now()
                                .format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"));

        this.currentDay.endDia(currentTime);
        this.updateRowData();
        this.updateUserInfo();
        timer.stop();
        isRunning = false;
        seconds = 0;
        this.disableBtns();
        updateTimer();
        setTimerValue(0);
    }

    // Set timer value
    public void setTimerValue(int initialSeconds) {
        this.seconds = initialSeconds;
        updateTimerLabel();
    }

    // Update the timer display
    private void updateTimerLabel() {
        int hrs = seconds / 3600;
        int mins = (seconds % 3600) / 60;
        int secs = seconds % 60;

        hour.setText(String.format("%02d:%02d:%02d", hrs, mins, secs));
    }

    // Update the timer every second
    private void updateTimer() {
        seconds++;

        int hrs = seconds / 3600;
        int mins = (seconds % 3600) / 60;
        int secs = seconds % 60;

        hour.setText(String.format("%02d:%02d:%02d", hrs, mins, secs));
    }


    // RowData class for table representation
    public static class RowData {
        private final String date;
        private final double hours;
        private final Button actionButton;
        private final String justificationStatus;
    
    

        public RowData(String date, double hours, double meta, Falta falta, UserDashboardController parentController) {
            this.date = date;
            this.hours = hours;

            if (hours < meta || falta != null) {
                this.actionButton = new Button("Justificar Falta");

                if(falta.getJustificativa() != null){
                    actionButton.setDisable(true);
                }

                this.actionButton.setOnAction(e -> openModal(falta, parentController));
            } else {
                this.actionButton = new Button("N/A");
                this.actionButton.setDisable(true);
            }

            if (falta != null && falta.getJustificativa() != null) {
                this.justificationStatus = "" +  falta.getJustificativa().getStatus();
            } else if(falta != null) {
                this.justificationStatus = "" + StatusJustificativa.Pendente;
            }else{
                this.justificationStatus = null;
            }
        }
        
        private void openModal(Falta falta, UserDashboardController parentController) {
            try {
                FXMLLoader loader = new FXMLLoader(App.class.getResource("JustificarFalta.fxml"));
                Parent root = loader.load();

                JustificarFaltaController controller = loader.getController();
                controller.setFalta(falta);
                controller.setParentController(parentController); 

                Stage stage = new Stage();
                stage.setTitle("Justificar Falta");
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();

                parentController.updateRowData();
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

        public String getJustificationStatus() { 
            return justificationStatus; 
        }
    }
}
