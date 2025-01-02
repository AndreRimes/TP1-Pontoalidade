package pontoalidade;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class OrganizacaoDashboardController implements Initializable {

    public TableView<Employee> tabel;
    public TableColumn<Employee, String> nameCol;
    public TableColumn<Employee, String> emailCol;
    public TableColumn<Employee, String> cpfCol;
    public TableColumn<Employee, Button> btnCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        cpfCol.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        btnCol.setCellValueFactory(new PropertyValueFactory<>("button"));
        
        nameCol.prefWidthProperty().bind(tabel.widthProperty().multiply(0.25));
        cpfCol.prefWidthProperty().bind(tabel.widthProperty().multiply(0.25));
        emailCol.prefWidthProperty().bind(tabel.widthProperty().multiply(0.25));
        btnCol.prefWidthProperty().bind(tabel.widthProperty().multiply(0.25));
        
        

        ObservableList<Employee> employees = FXCollections.observableArrayList(
            new Employee("John Doe", "johndoe@example.com", "123.456.789-00"),
            new Employee("Jane Smith", "janesmith@example.com", "987.654.321-00"),
            new Employee("Mark Johnson", "markjohnson@example.com", "192.837.465-00")
        );

        tabel.setItems(employees);
    }

    public static class Employee {
        private String name;
        private String email;
        private String cpf;
        private Button button;

        public Employee(String name, String email, String cpf) {
            this.name = name;
            this.email = email;
            this.cpf = cpf;
            this.button = new Button("Action");
            this.button.setOnAction(event -> {
                System.out.println("Action for " + name);
            });
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getCpf() {
            return cpf;
        }

        public Button getButton() {
            return button;
        }
    }
}
