package EatAdvisor.ristoratori;

import EatAdvisor.EatAdvisor;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class ControllerViewRegistrazione {

    @FXML
    private Label labelNomeRistorante;

    @FXML
    private Label labelNomeIndirizzo;

    @FXML
    private Label labelCivico;

    @FXML
    private Label labelComune;

    @FXML
    private Label labelProvincia;

    @FXML
    private Label labelCap;

    @FXML
    private Label labelTelefono;

    @FXML
    private Label labelUrl;


    @FXML
    private TextField textFieldNomeRistorante;

    @FXML
    private ComboBox<String> comboBoxTipoIndirizzo;

    @FXML
    private TextField textFieldNomeIndirizzo;

    @FXML
    private TextField textFieldCivico;

    @FXML
    private TextField textFieldComune;

    @FXML
    private TextField textFieldProvincia;

    @FXML
    private TextField textFieldCap;

    @FXML
    private TextField textFieldTelefono;

    @FXML
    private TextField textFieldUrl;

    @FXML
    private ComboBox<String> comboBoxTipologia;

    @FXML
    private Button registerButton;

    @FXML
    private Text errorText;

    private Boolean nomeOk = false;
    private Boolean tipoIndirizzoOk = false;
    private Boolean indirizzoOk = false;
    private Boolean civicoOk = false;
    private Boolean comuneOk = false;
    private Boolean provinciaOk = false;
    private Boolean capOk = false;
    private Boolean telefonoOk = false;
    private Boolean urlOk = true;
    private Boolean tipologiaOk = false;


    public ControllerViewRegistrazione() {

    }

    public void initialize() {
        textFieldNomeRistorante.setOnKeyReleased(event -> {
            if (EatAdvisor.isRegistrato(textFieldNomeRistorante.getText(), 2)) {
                EatAdvisor.alert(labelNomeRistorante, false);
                errorText.setVisible(true);
            } else {
                EatAdvisor.alert(labelNomeRistorante, true);
                errorText.setVisible(false);
                nomeOk = handle(textFieldNomeRistorante, labelNomeRistorante, 13);
                checkOkay();
            }
        });

        comboBoxTipoIndirizzo.setOnAction(event -> {
//            String sel = (String) comboBoxTipologia.getValue();
            tipoIndirizzoOk = true;
            checkOkay();
        });

        textFieldNomeIndirizzo.setOnKeyReleased(event -> {
            indirizzoOk = handle(textFieldNomeIndirizzo, labelNomeIndirizzo, 2);
            checkOkay();
        });

        textFieldCivico.setOnKeyReleased(event -> {
            civicoOk = handle(textFieldCivico, labelCivico, 3);
            checkOkay();
        });

        textFieldComune.setOnKeyReleased(event -> {
            comuneOk = handle(textFieldComune, labelComune, 1);
            checkOkay();
        });

        textFieldProvincia.setOnKeyReleased(event -> {
            provinciaOk = handle(textFieldProvincia, labelProvincia, 4);
            checkOkay();
        });

        textFieldCap.setOnKeyReleased(event -> {
            capOk = handle(textFieldCap, labelCap, 5);
            checkOkay();
        });

        textFieldTelefono.setOnKeyReleased(event -> {
            telefonoOk = handle(textFieldTelefono, labelTelefono, 6);
            checkOkay();
        });

        textFieldUrl.setOnKeyReleased(event -> {
            urlOk = handle(textFieldUrl, labelUrl, 7);
            checkOkay();
        });

        comboBoxTipologia.setOnAction(event -> {
//            String sel = (String) comboBoxTipologia.getValue();
            tipologiaOk = true;
            checkOkay();
        });

        registerButton.setOnMouseClicked(event -> {
            Ristoratore ristoratore = new Ristoratore(textFieldNomeRistorante.getText(),
                    comboBoxTipoIndirizzo.getValue(), textFieldNomeIndirizzo.getText(),
                    textFieldCivico.getText(), textFieldComune.getText(), textFieldProvincia.getText(),
                    textFieldCap.getText(), textFieldTelefono.getText(), textFieldUrl.getText(),
                    comboBoxTipologia.getValue());
            EatAdvisor.registra(ristoratore, 2);
            tabulaRasa();
        });
    }

    private void tabulaRasa() {
        nomeOk = false;
        tipoIndirizzoOk = false;
        indirizzoOk = false;
        civicoOk = false;
        comuneOk = false;
        provinciaOk = false;
        capOk = false;
        telefonoOk = false;
        urlOk = true;
        tipologiaOk = false;

        registerButton.setDisable(true);
        errorText.setVisible(false);

        textFieldNomeRistorante.setText("");
        comboBoxTipoIndirizzo.getSelectionModel().clearSelection();
        textFieldNomeIndirizzo.setText("");
        textFieldCivico.setText("");
        textFieldComune.setText("");
        textFieldProvincia.setText("");
        textFieldCap.setText("");
        textFieldTelefono.setText("");
        textFieldUrl.setText("");
        comboBoxTipologia.getSelectionModel().clearSelection();


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registrazione confermata!");
        alert.setHeaderText("");
        alert.setContentText("Il ristorante è stato registrato con successo!");
        alert.showAndWait();
    }

    private void checkOkay () {
        boolean ok = nomeOk && tipoIndirizzoOk && indirizzoOk && civicoOk && comuneOk && provinciaOk
                && capOk && telefonoOk && urlOk && tipologiaOk;
        registerButton.setDisable(!ok);
    }

    private boolean handle (TextField textField, Label label, int op) {
        if (EatAdvisor.validate(textField.getText(), op)) {
            EatAdvisor.alert(label, true);
            return true;
        } else {
            EatAdvisor.alert(label, false);
            return false;
        }
    }

//    private class onKeyReleased<KeyEvent> implements EventHandler<javafx.scene.input.KeyEvent> {
//        @Override
//        public void handle(javafx.scene.input.KeyEvent event) {
//
//        }
//    }

}