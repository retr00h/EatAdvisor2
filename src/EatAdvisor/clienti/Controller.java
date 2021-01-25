package EatAdvisor.clienti;

import EatAdvisor.EatAdvisor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


public class Controller {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField nicknameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label confirmPasswordLabel;

    @FXML
    private Label continueLabel;

    @FXML
    private Button continueButton;

    @FXML
    private Text registerText;

    private boolean login = false;

    public Controller () {

    }

    public void initialize() {
        nicknameField.setOnKeyReleased(event -> {
            if (!login && !nicknameField.getText().equals("")) {
                login = true;
                continueButton.setText("Login");
                anchorPane.setLeftAnchor(continueButton, 199.0);
            } else if (nicknameField.getText().equals("")) {
                login = false;
                continueButton.setText("Continua senza loggarti");
                anchorPane.setLeftAnchor(continueButton, 260.0);
            }
        });

        nicknameField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean a, Boolean onFocus)
            {
                // se nicknameField perde il focus
                if (!onFocus) {
                    // se l'utente sta cercando di loggarsi E nicknameField non è vuoto
                    if (login && !nicknameField.getText().equals("")) {
                        // se l'utente risulta non registrato (o se il file contenente i dati degli utenti
                        // non e' raggiungibile
                        if (!EatAdvisor.isRegistrato(nicknameField.getText())) {
                            registerText.setVisible(true);
                            confirmPasswordField.setVisible(true);
                            confirmPasswordLabel.setVisible(true);
                        } else {
                            registerText.setVisible(false);
                            confirmPasswordField.setVisible(false);
                            confirmPasswordLabel.setVisible(false);
                        }
                    } else {
                        registerText.setVisible(false);
                        confirmPasswordField.setVisible(false);
                        confirmPasswordLabel.setVisible(false);
                    }
                }
            }
        });

    }
}