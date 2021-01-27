package EatAdvisor.ristoratori;

import EatAdvisor.EatAdvisor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ViewRistoratoreController {

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


    public ViewRistoratoreController () {

    }

    public void initialize() {
        textFieldNomeRistorante.setOnKeyReleased(event -> {
            // TODO: verificare che il ristoratore non sia già registrato
            nomeOk = handle(textFieldNomeRistorante, labelNomeRistorante, 2);
            checkOkay();
        });

        comboBoxTipoIndirizzo.setOnAction(event -> {
//            String sel = (String) comboBoxTipologia.getValue();
            tipoIndirizzoOk = true;
            checkOkay();
        });

        textFieldNomeIndirizzo.setOnKeyReleased(event -> {
            indirizzoOk = handle(textFieldNomeIndirizzo, labelNomeIndirizzo, 4);
            checkOkay();
        });

        textFieldCivico.setOnKeyReleased(event -> {
            civicoOk = handle(textFieldCivico, labelCivico, 5);
            checkOkay();
        });

        textFieldComune.setOnKeyReleased(event -> {
            comuneOk = handle(textFieldComune, labelComune, 6);
            checkOkay();
        });

        textFieldProvincia.setOnKeyReleased(event -> {
            provinciaOk = handle(textFieldProvincia, labelProvincia, 7);
            checkOkay();
        });

        textFieldCap.setOnKeyReleased(event -> {
            capOk = handle(textFieldCap, labelCap, 8);
            checkOkay();
        });

        textFieldTelefono.setOnKeyReleased(event -> {
            telefonoOk = handle(textFieldTelefono, labelTelefono, 9);
            checkOkay();
        });

        textFieldUrl.setOnKeyReleased(event -> {
            urlOk = handle(textFieldUrl, labelUrl, 10);
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

            // TODO: registrare il ristoratore appena creato
        });
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