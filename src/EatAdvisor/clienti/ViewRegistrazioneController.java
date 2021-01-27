package EatAdvisor.clienti;

import EatAdvisor.EatAdvisor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewRegistrazioneController {

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

    private Cliente cliente;

    private boolean nomeOkay = false;
    private boolean cognomeOkay = false;
    private boolean comuneOkay = false;
    private boolean provinciaOkay = false;
    private boolean emailOkay = false;

    public ViewRegistrazioneController() {

    }

    public void initialize() {
        textFieldNome.setOnKeyReleased(event -> {
            nomeOkay = EatAdvisor.alert(labelNome, EatAdvisor.validate(textFieldNome.getText().toLowerCase(), 2));
            checkOkay();
        });
//        textFieldNome.focusedProperty().addListener(new ChangeListener<Boolean>()
//        {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean onFocus) {
//                if (!onFocus) {
//                    nomeOkay = EatAdvisor.alert(labelNome, EatAdvisor.validate(textFieldNome.getText(), 2));
//                }
//                checkOkay();
//            }
//        });

        textFieldCognome.setOnKeyReleased(event -> {
            cognomeOkay = EatAdvisor.alert(labelCognome, EatAdvisor.validate(textFieldCognome.getText().toLowerCase(), 12));
            checkOkay();
        });

//        textFieldCognome.focusedProperty().addListener(new ChangeListener<Boolean>()
//        {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean onFocus) {
//                if (!onFocus) {
//                    cognomeOkay = EatAdvisor.alert(labelCognome, EatAdvisor.validate(textFieldCognome.getText(), 12));
//                }
//                checkOkay();
//            }
//        });

        textFieldComune.setOnKeyReleased(event -> {
            comuneOkay = EatAdvisor.alert(labelComune, EatAdvisor.validate(textFieldComune.getText().toLowerCase(), 6));
            checkOkay();
        });

//        textFieldComune.focusedProperty().addListener(new ChangeListener<Boolean>()
//        {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean onFocus) {
//                if (!onFocus) {
//                    comuneOkay = EatAdvisor.alert(labelComune, EatAdvisor.validate(textFieldComune.getText(), 6));
//                }
//                checkOkay();
//            }
//        });

        textFieldProvincia.setOnKeyReleased(event -> {
            provinciaOkay = EatAdvisor.alert(labelProvincia, EatAdvisor.validate(textFieldProvincia.getText().toUpperCase(), 7));
            checkOkay();
        });

//        textFieldProvincia.focusedProperty().addListener(new ChangeListener<Boolean>()
//        {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean onFocus) {
//                if (!onFocus) {
//                    provinciaOkay = EatAdvisor.alert(labelProvincia, EatAdvisor.validate(textFieldProvincia.getText(), 7));
//                }
//                checkOkay();
//            }
//        });

        textFieldEmail.setOnKeyReleased(event -> {
            emailOkay = EatAdvisor.alert(labelEmail, EatAdvisor.validate(textFieldEmail.getText().toLowerCase(), 13));
            checkOkay();
        });

//        textFieldEmail.focusedProperty().addListener(new ChangeListener<Boolean>()
//        {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean onFocus) {
//                if (!onFocus) {
//                    emailOkay = EatAdvisor.alert(labelEmail, EatAdvisor.validate(textFieldEmail.getText(), 13));
//                }
//                checkOkay();
//            }
//        });
    }

    private boolean checkOkay() {
        boolean b = nomeOkay && cognomeOkay && comuneOkay && provinciaOkay && emailOkay;
        if (b) {
            registerButton.setDisable(false);
            registerButton.setOnMouseClicked(event -> {
                cliente.setNome(textFieldNome.getText().toLowerCase());
                cliente.setCognome(textFieldCognome.getText().toLowerCase());
                cliente.setComune(textFieldComune.getText().toLowerCase());
                cliente.setProvincia(textFieldProvincia.getText().toUpperCase());
                cliente.setEmail(textFieldEmail.getText().toLowerCase());
                EatAdvisor.registra(cliente, 1);

                try {
                    Stage stage = (Stage) anchorPane.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("LoggedUserView.fxml"));
                    Parent newRoot = loader.load();
                    LoggedUserViewController loggedUserViewController = loader.getController();
                    loggedUserViewController.setUser(cliente);

                    Scene newScene = new Scene(newRoot);
                    stage.setScene(newScene);
                    stage.setMinWidth(600);
                    stage.setMinHeight(400);
                    stage.setResizable(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            registerButton.setDisable(true);
        }
        return b;
    }

    public void setUser(Cliente c) {
        cliente = c;
    }
}
