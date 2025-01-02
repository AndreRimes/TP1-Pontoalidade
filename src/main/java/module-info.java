module pontoalidade {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens pontoalidade to javafx.fxml;
    exports pontoalidade;
}
