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

public class MainViewClienteController {

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
    private Button loginButton;

    @FXML
    private Text registerText;

    public MainViewClienteController() {

    }

    public void initialize() {
//        loginButton.setOnMouseClicked(event -> login(true));
        continueButton.setOnMouseClicked(event -> login(false));

        nicknameField.setOnKeyReleased(event -> {
            if (!nicknameField.getText().equals("")) {
                // se il nicknameField non è vuoto, l'utente vuole loggarsi o registrarsi
                if (EatAdvisor.validate(nicknameField.getText(), 10)) {
                    // se il nickname è valido, controllo che l'utente sia registrato
                    continueButton.setVisible(false);
                    continueLabel.setVisible(false);
                    if (EatAdvisor.isRegistrato(nicknameField.getText(), 1)) {
                        // se l'utente è registrato, attendo l'inserimento della password
                        registerText.setVisible(false);
                        confirmPasswordField.setVisible(false);
                        confirmPasswordLabel.setVisible(false);
//                        loginButton.setDisable(false);
                        login(true);
                    } else {
                        // questo ramo viene eseguito se l'utente NON è registrato
                        loginButton.setDisable(true);
                        registerText.setVisible(true);
                        confirmPasswordField.setVisible(true);
                        confirmPasswordLabel.setVisible(true);
                        continueButton.setText("Continua la registrazione");
                        anchorPane.setLeftAnchor(continueButton, 201.00);
                    }
                }
            } else {
                // questo ramo esegue se nicknameField è vuoto
                continueButton.setText("Continua senza loggarti");
                registerText.setVisible(false);
                confirmPasswordField.setVisible(false);
                confirmPasswordLabel.setVisible(false);
                continueLabel.setVisible(true);
                continueButton.setVisible(true);
                continueButton.setDisable(false);
                loginButton.setDisable(true);
                anchorPane.setLeftAnchor(continueButton, 260.0);
            }
        });

        passwordField.setOnKeyReleased(event -> {
            if (checkNicknameAndPassword(nicknameField.getText(), passwordField.getText())) {
                continueButton.setDisable(false);
                login(true);
            }
        });

        confirmPasswordField.setOnKeyReleased(event -> {
            if (passwordField.getText().equals(confirmPasswordField.getText())) {
                EatAdvisor.alert(confirmPasswordLabel, true);
                continueButton.setVisible(true);
                continueButton.setOnMouseClicked(event1 -> {
                    Cliente c = new Cliente(null,null,null,null,null,
                            nicknameField.getText(), passwordField.getText());
                    registraCliente(c);
                });
            } else {
                continueButton.setVisible(false);
                EatAdvisor.alert(confirmPasswordLabel, false);
            }
        });
    }

    private void login(boolean haveToLogin) {
        if (haveToLogin) {
            if (checkNicknameAndPassword(nicknameField.getText(), passwordField.getText())) {
                loginButton.setDisable(false);
                loginButton.setOnMouseClicked(event1 -> {
                    try {
                        Stage stage = (Stage) anchorPane.getScene().getWindow();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoggedUserView.fxml"));
                        Parent newRoot = loader.load();
                        LoggedUserViewController loggedUserViewController = loader.getController();
                        loggedUserViewController.setUser(EatAdvisor.cercaCliente(
                                nicknameField.getText(), passwordField.getText()));

                        Scene newScene = new Scene(newRoot);
                        stage.setScene(newScene);
                        stage.setMinWidth(600);
                        stage.setMinHeight(400);
//                        stage.setResizable(false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            } else {
                continueButton.setDisable(true);
                continueLabel.setVisible(false);

            }
        } else {
            try {
                Stage stage = (Stage) anchorPane.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("LoggedUserView.fxml"));
                Parent newRoot = loader.load();
                LoggedUserViewController loggedUserViewController = loader.getController();
                loggedUserViewController.setUser(null);
                Scene newScene = new Scene(newRoot);
                stage.setScene(newScene);
                stage.setMinWidth(600);
                stage.setMinHeight(400);
//                stage.setResizable(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkNicknameAndPassword(String nickname, String password) {
        return EatAdvisor.cercaCliente(nickname, password) != null;
    }

    private void registraCliente(Cliente c) {
        try {
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewRegistrazione.fxml"));
            Parent newRoot = loader.load();
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