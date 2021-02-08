package EatAdvisor.clienti;

import EatAdvisor.EatAdvisor;
import EatAdvisor.ThemeManager;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

public class ControllerDialogRegistrazione {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldCognome;

    @FXML
    private TextField textFieldComune;

    @FXML
    private TextField textFieldProvincia;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private Label labelNome;

    @FXML
    private Label labelCognome;

    @FXML
    private Label labelComune;

    @FXML
    private Label labelProvincia;

    @FXML
    private Label labelEmail;

    @FXML
    private Button registerButton;

    @FXML
    private ImageView imageView;
    private Cliente cliente;

    private boolean nomeOkay = false;
    private boolean cognomeOkay = false;
    private boolean comuneOkay = false;
    private boolean provinciaOkay = false;
    private boolean emailOkay = false;
    private ControllerLoginView controllerLoginView;

    private boolean debug;

    private ThemeManager themeManager;

    public ControllerDialogRegistrazione() {

    }

    public void initialize() {
        themeManager = ThemeManager.getThemeManager();
        themeManager.changeTheme(true, anchorPane, imageView);

        imageView.setOnMouseClicked(event -> {
            themeManager.changeTheme(false, anchorPane, imageView);
        });

        textFieldNome.setOnKeyReleased(event -> {
            nomeOkay = themeManager.alert(labelNome, EatAdvisor.validate(textFieldNome.getText(), 1));
            checkOkayGlobal();
        });
        textFieldCognome.setOnKeyReleased(event -> {
            cognomeOkay = themeManager.alert(labelCognome, EatAdvisor.validate(textFieldCognome.getText(), 8));
            checkOkayGlobal();
        });
        textFieldComune.setOnKeyReleased(event -> {
            comuneOkay = themeManager.alert(labelComune, EatAdvisor.validate(textFieldComune.getText(), 1));
            checkOkayGlobal();
        });
        textFieldProvincia.setOnKeyReleased(event -> {
            provinciaOkay = themeManager.alert(labelProvincia, EatAdvisor.validate(textFieldProvincia.getText(), 4));
            checkOkayGlobal();
        });
        textFieldEmail.setOnKeyReleased(event -> {
            emailOkay = themeManager.alert(labelEmail, EatAdvisor.validate(textFieldEmail.getText(), 9));
            checkOkayGlobal();
        });
    }

    private void checkOkayGlobal() {
        boolean b = nomeOkay && cognomeOkay && comuneOkay && provinciaOkay && emailOkay;
        if (b) {
            registerButton.setDisable(false);
            registerButton.setOnMouseClicked(event -> {
                cliente.setNome(textFieldNome.getText().toLowerCase());
                cliente.setCognome(textFieldCognome.getText().toLowerCase());
                cliente.setComune(textFieldComune.getText().toLowerCase());
                cliente.setProvincia(textFieldProvincia.getText().toUpperCase());
                cliente.setEmail(textFieldEmail.getText().toLowerCase());
                EatAdvisor.registra(cliente, 1, debug);

                controllerLoginView.setCliente(cliente);

                Node source = (Node)  event.getSource();
                Stage stage  = (Stage) source.getScene().getWindow();
                stage.close();
            });
        } else {
            registerButton.setDisable(true);
        }
    }

    public void setCliente(Cliente c) {
        cliente = c;
    }

    public void setControllerLoginView(ControllerLoginView controllerLoginView) {
        this.controllerLoginView = controllerLoginView;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
