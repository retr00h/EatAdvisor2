package EatAdvisor.clienti;

import EatAdvisor.EatAdvisor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainViewController {

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

    public MainViewController() {

    }

    public void initialize() {
        nicknameField.setOnKeyReleased(event -> {
            if (!nicknameField.getText().equals("")) {
                continueButton.setText("Login");
                anchorPane.setLeftAnchor(continueButton, 199.0);
                continueLabel.setVisible(false);
                if (EatAdvisor.isRegistrato(nicknameField.getText()) ) {
                    if (checkOkay(nicknameField.getText(), passwordField.getText())) {
                        continueButton.setDisable(false);
                        continueButton.setOnMouseClicked(event1 -> {
                            try {
                                Stage stage = (Stage) anchorPane.getScene().getWindow();
                                FXMLLoader loader = new FXMLLoader();
                                Parent newRoot = loader.load(getClass().getResource("LoggedUserView.fxml"));
                                LoggedUserViewController loggedUserViewController = loader.getController();
                                loggedUserViewController.setUser(EatAdvisor.cercaCliente(
                                        nicknameField.getText(), passwordField.getText()));
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
                        continueButton.setDisable(true);
                        continueLabel.setVisible(false);
                    }
                } else {
                    registerText.setVisible(true);
                    confirmPasswordField.setVisible(true);
                    confirmPasswordLabel.setVisible(true);
                    continueButton.setText("Continua la registrazione");
//                    continueButton.setDisable(true);
//                    continueLabel.setVisible(false);
                }
            } else {
                continueButton.setText("Continua senza loggarti");
                registerText.setVisible(false);
                confirmPasswordField.setVisible(false);
                confirmPasswordLabel.setVisible(false);
                continueLabel.setVisible(true);
                continueButton.setDisable(false);
                anchorPane.setLeftAnchor(continueButton, 260.0);
            }
        });

        confirmPasswordField.setOnKeyReleased(event -> {
            if (passwordField.getText().equals(confirmPasswordField.getText())) {
                EatAdvisor.alert(confirmPasswordLabel, true);
                continueButton.setOnMouseClicked(event1 -> {
                    Cliente c = new Cliente(null,null,null,null,null,
                            nicknameField.getText(), passwordField.getText());
                    registraCliente(c);
                });
            } else {
                EatAdvisor.alert(confirmPasswordLabel, false);
            }
        });

//        nicknameField.setOnKeyReleased(event -> {
//            if (!login && !nicknameField.getText().equals("")) {
//                login = true;
//                continueButton.setText("Login");
//                anchorPane.setLeftAnchor(continueButton, 199.0);
//            } else if (nicknameField.getText().equals("")) {
//                login = false;
//                continueButton.setText("Continua senza loggarti");
//                registerText.setVisible(false);
//                confirmPasswordField.setVisible(false);
//                confirmPasswordLabel.setVisible(false);
//                anchorPane.setLeftAnchor(continueButton, 260.0);
//            }
//        });

//        nicknameField.focusedProperty().addListener(new ChangeListener<Boolean>()
//        {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean onFocus)
//            {
//                // se nicknameField perde il focus
//                if (!onFocus) {
//                    // se l'utente sta cercando di loggarsi E nicknameField non è vuoto
//                    if (login && !nicknameField.getText().equals("")) {
//                        // se l'utente risulta non registrato (o se il file contenente i dati degli utenti
//                        // non e' raggiungibile
//                        if (!EatAdvisor.isRegistrato(nicknameField.getText())) {
//                            registerText.setVisible(true);
//                            confirmPasswordField.setVisible(true);
//                            confirmPasswordLabel.setVisible(true);
//                            continueButton.setText("Continua la registrazione");
//                            continueButton.setOnMouseClicked(event -> {
//                                if (!EatAdvisor.isRegistrato(nicknameField.getText())) {
//                                    if (passwordField.getText().equals(confirmPasswordField.getText())) {
//                                        Cliente c = new Cliente(null,null,null,null,
//                                                null, nicknameField.getText(), passwordField.getText());
//                                        registraCliente(c);
//                                    }
//                                }
//                            });
//                        } else {
//                            // questo ramo esegue solo se:
//                            // - nicknameField perde il focus E
//                            // - l'utente sta cercando di loggarsi E
//                            // - l'utente risulta registrato
//                            registerText.setVisible(false);
//                            confirmPasswordField.setVisible(false);
//                            confirmPasswordLabel.setVisible(false);
//                            continueButton.setText("Login");
//                        }
//                    } else {
//                        // questo ramo esegue solo se:
//                        // - nicknameField perde il focus E
//                        // - l'utente sta cercando di loggarsi E nicknameField e' vuoto, OPPURE
//                        // - l'utente non sta cercando di loggarsi
//                        registerText.setVisible(false);
//                        confirmPasswordField.setVisible(false);
//                        confirmPasswordLabel.setVisible(false);
//                    }
//                }
//            }
//        });

    }

    private boolean checkOkay(String nickname, String password) {
        return EatAdvisor.cercaCliente(nickname, password) != null;
    }

    private void registraCliente(Cliente c) {
        try {
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            Parent newRoot = loader.load(getClass().getResource("ViewRegistrazione.fxml"));
            Scene newScene = new Scene(newRoot);
            stage.setScene(newScene);
            stage.setMinWidth(600);
            stage.setMinHeight(400);
            stage.setResizable(false);

            ViewRegistrazioneController viewRegistrazioneController = loader.getController();
            viewRegistrazioneController.setUser(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
