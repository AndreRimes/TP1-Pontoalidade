package pontoalidade;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Timeline;
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
import javafx.animation.KeyFrame;
import javafx.util.Duration;


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
    private TableColumn<RowData, String> justificationColumn;
    
    @FXML
    private Label name;
    
    @FXML
    private Label horasMes;
    
    @FXML
    private Label salarioEstimado;
    
    @FXML
    private Label hour;

    @FXML
    private Button countBtn;

    @FXML
    private Button pauseBtn;

    @FXML
    private Button terminarBtn;
    
    private Timeline timer;
    private int seconds = 0; 
    private boolean isRunning = false;
    
    private Funcionario usuarioLogado;
    
    private Organizacao org;
    
    private Dia currentDay;

    public UserDashboardController(Usuario usuarioLogado, Organizacao org, Dia cd) {
        if(usuarioLogado instanceof Funcionario){
           this.usuarioLogado = (Funcionario) usuarioLogado; 
        }
        this.currentDay = cd;
        this.org = org;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        hoursColumn.setCellValueFactory(new PropertyValueFactory<>("hours"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("actionButton"));
        justificationColumn.setCellValueFactory(new PropertyValueFactory<>("justificationStatus"));
        
        dateColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.30));
        hoursColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.30));
        actionColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
        justificationColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
        
        this.updateRowData();
        
        this.updateUserInfo();
       
       if (currentDay != null && currentDay.getStatus() == Status.RUNNING) {
            pauseBtn.setText("Pause");
        } else if (currentDay != null && currentDay.getStatus() == Status.PAUSED) {
            pauseBtn.setText("Continue");
        }
       
        
        initializeTimer();
       
        if (currentDay != null) {
            if (null != currentDay.getStatus()) switch (currentDay.getStatus()) {
                case RUNNING:{
                    java.time.LocalTime startTime = java.time.LocalTime.parse(
                            currentDay.getHorarioInicio(),
                            java.time.format.DateTimeFormatter.ofPattern("HH:mm")
                    );      java.time.LocalTime now = java.time.LocalTime.now();
                    int elapsedSeconds = (int) java.time.Duration.between(startTime, now).getSeconds();
                    setTimerValue(elapsedSeconds); 
                    isRunning = true;
                    this.startTimer();
                    break;
                    }
                case ENDED:
                    countBtn.setDisable(true);
                    pauseBtn.setDisable(true);
                    terminarBtn.setDisable(true);
                    break;
                case PAUSED:{
                    java.time.LocalTime startTime = java.time.LocalTime.parse(
                            currentDay.getHorarioInicio(),
                            java.time.format.DateTimeFormatter.ofPattern("HH:mm")
                    );      
                    java.time.LocalTime pauseTime = java.time.LocalTime.parse(
                            currentDay.getPausa().getHorarioInicio(),
                            java.time.format.DateTimeFormatter.ofPattern("HH:mm")
                    );      
                    int elapsedSeconds = (int) java.time.Duration.between(startTime, pauseTime).getSeconds();
                    setTimerValue(elapsedSeconds); 
                        break;
                    }
                default:
                    break;
            }
        }
        
       this.disableBtns();
             
       countBtn.setOnAction(e -> startTimer());

       pauseBtn.setOnAction(e -> togglePauseResume());

       terminarBtn.setOnAction(e -> endDay());
    }
    
    private void updateUserInfo(){
       this.name.setText(this.usuarioLogado.getNome());
       this.salarioEstimado.setText("Salario estimado: " + String.valueOf(this.usuarioLogado.calculaSalario())); 
       this.horasMes.setText("Horas trabalhadas no mes: " + String.valueOf(this.usuarioLogado.horasMes())); 
    }
    
    private void disableBtns(){ 
        if (currentDay != null && currentDay.getStatus() == Status.ENDED) {
           countBtn.setDisable(true);
           pauseBtn.setDisable(true);
           terminarBtn.setDisable(true);
       }
    }
    
public void updateRowData() {
    ObservableList<RowData> dias = FXCollections.observableArrayList();
    for (Dia dia : this.usuarioLogado.getDiasTrabalhados()) {
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



    
     private void initializeTimer() {
        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTimer()));
        timer.setCycleCount(Timeline.INDEFINITE); 
    }

    private void startTimer() {
        if (currentDay == null) {
            String currentDate = java.time.LocalDate.now()
                                    .format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            String currentTime = java.time.LocalTime.now()
                                    .format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"));

            Dia cd = new Dia(currentDate, currentTime, null, null);
            
            this.usuarioLogado.addDiaTrabalhado(cd);
            
            this.currentDay = cd;
            countBtn.setDisable(true);
            isRunning = true;
            timer.play();
        }
        
       isRunning = true;
       timer.play();   
    }

    private void togglePauseResume() {
    if (isRunning) {
        pauseTimer();
        pauseBtn.setText("Continue");
    } else {
        resumeTimer();
        pauseBtn.setText("Pause");
    }
}

    

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

    private void resumeTimer() {
        if (!isRunning && this.currentDay != null) {
            this.currentDay.setStatus(Status.RUNNING);

            String currentTime = java.time.LocalTime.now()
                                    .format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"));


            this.currentDay.getPausa().setHorarioFinal(currentTime);
            this.currentDay.getPausa().setHorarioTotal();

            System.out.println(this.currentDay.getPausa().getHorarioTotal());
            isRunning = true;
            timer.play();
        }
}


    private void endDay() {        
        if(this.currentDay == null){
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
    
    public void setTimerValue(int initialSeconds) {
        this.seconds = initialSeconds;
        updateTimerLabel();
    }
    
    private void updateTimerLabel() {
        int hrs = seconds / 3600;
        int mins = (seconds % 3600) / 60;
        int secs = seconds % 60;

        hour.setText(String.format("%02d:%02d:%02d", hrs, mins, secs));
    }

    private void updateTimer() {
        seconds++;

        int hrs = seconds / 3600;
        int mins = (seconds % 3600) / 60;
        int secs = seconds % 60;

        hour.setText(String.format("%02d:%02d:%02d", hrs, mins, secs));
    }

public static class RowData {
    private final String date;
    private final double hours;
    private final Button actionButton;
    private final StatusJustificativa justificationStatus;

    public RowData(String date, double hours, double meta, Falta falta, UserDashboardController parentController) {
        this.date = date;
        this.hours = hours;

        if (hours < meta && falta != null) {
            this.actionButton = new Button("Justificar Falta");
            this.actionButton.setOnAction(e -> openModal(falta, parentController));
        } else {
            this.actionButton = new Button("N/A");
            this.actionButton.setDisable(true);
        }

        if (falta != null && falta.getJustificativa() != null) {
            this.justificationStatus = falta.getJustificativa().getStatus();
        } else {
            this.justificationStatus = StatusJustificativa.Pendente;
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

    public StatusJustificativa getJustificationStatus() { 
        return justificationStatus; 
    }
}

    
    
}
