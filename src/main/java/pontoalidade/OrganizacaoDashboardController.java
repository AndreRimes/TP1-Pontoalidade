package pontoalidade;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.collections.transformation.FilteredList;

public class OrganizacaoDashboardController implements Initializable {

    public TableView<Employee> tabel;
    public TableColumn<Employee, String> nameCol;
    public TableColumn<Employee, String> emailCol;
    public TableColumn<Employee, String> cpfCol;
    public TableColumn<Employee, Void> btnCol;
    
    @FXML
    private Label nameOrg;
    
    @FXML
    private Label cnpjLabel;
    
    @FXML
    private Label senha;
    
    @FXML
    private Button senhaBtn;
    
    @FXML
    private TextField searchName;
    
    private Organizacao org;
    
    private Administrador usuarioLogado;
    
    private ObservableList<Employee> employees = FXCollections.observableArrayList();
    
    private FilteredList<Employee> filteredEmployees;

    public OrganizacaoDashboardController(Organizacao org, Administrador userlogado) {
        this.org = org;
        this.usuarioLogado = userlogado;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        cpfCol.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        
        btnCol.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Deletar");
            private final Button editButton = new Button("Ver Perfil");
            private final HBox buttonsBox = new HBox(5, editButton, deleteButton);

            {
                deleteButton.setOnAction(event -> {
                    Employee employee = getTableView().getItems().get(getIndex());
                    deleteUser(employee.getCpf(), event);
                });

                editButton.setOnAction(event -> {
                    Employee employee = getTableView().getItems().get(getIndex());
                    
                    Usuario userFound = null;
                    for(Usuario user : org.getUsuarios()){
                        if(user.getCpf().equals(employee.getCpf())){
                            userFound = user;
                        }
                    }
                    
                    Router router = new Router();
                    router.userProfile(event, userFound, usuarioLogado);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(buttonsBox);
                }
            }
        });

        nameCol.prefWidthProperty().bind(tabel.widthProperty().multiply(0.25));
        cpfCol.prefWidthProperty().bind(tabel.widthProperty().multiply(0.25));
        emailCol.prefWidthProperty().bind(tabel.widthProperty().multiply(0.25));
        btnCol.prefWidthProperty().bind(tabel.widthProperty().multiply(0.25));

        this.nameOrg.setText(this.org.getNome());
        this.cnpjLabel.setText(this.org.getCnpj());
        
        filteredEmployees = new FilteredList<>(employees, p -> true);
        tabel.setItems(filteredEmployees);

        searchName.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredEmployees.setPredicate(employee -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return employee.getName().toLowerCase().contains(lowerCaseFilter);
            });
        });
        
        this.updateTable();
    }
    
    private final void updateTable() {
        employees.clear();
        for (Usuario usuario : org.getUsuarios()) {
            employees.add(new Employee(usuario.getNome(), usuario.getEmail(), usuario.getCpf()));
        }
        
        filteredEmployees = new FilteredList<>(employees, p -> true);
        tabel.setItems(filteredEmployees);
    }

    @FXML
    private final void handleMostrarSenha() {
        if ("Esconder Senha".equals(senhaBtn.getText())) {
            senha.setText("Senha da organizacao: *******");
            senhaBtn.setText("Mostrar senha");
        } else {
            senha.setText("Senha da organizacao: " + this.org.getPassword());
            senhaBtn.setText("Esconder Senha");
        }
    }

    public final void deleteUser(String cpf, ActionEvent event) {
        Iterator<Usuario> iterator = org.getUsuarios().iterator();

        while (iterator.hasNext()) {
            Usuario user = iterator.next();
            if (cpf.equals(user.getCpf())) {
                iterator.remove();
                if (user.getId() == usuarioLogado.getId()) {
                    Router router = new Router();
                    router.login(event, org);
                }
                this.updateTable();
                break;
            }
        }
    }

    public static class Employee {
        private String name;
        private String email;
        private String cpf;

        public Employee(String name, String email, String cpf) {
            this.name = name;
            this.email = email;
            this.cpf = cpf;
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
    }
}
