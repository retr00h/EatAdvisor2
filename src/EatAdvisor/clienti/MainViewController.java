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
    private Button loginButton;

    @FXML
    private Text registerText;

    public MainViewController() {

    }

    public void initialize() {
        loginButton.setOnMouseClicked(event -> login());
        continueButton.setOnMouseClicked(event -> {
            notLoggedUser();
        });

        nicknameField.setOnKeyReleased(event -> {
            if (!nicknameField.getText().equals("")) {
                if (EatAdvisor.validate(nicknameField.getText(), 14)) {
                    continueButton.setVisible(false);
//                    anchorPane.setLeftAnchor(continueButton, 199.0);
                    continueLabel.setVisible(false);
                    if (EatAdvisor.isRegistrato(nicknameField.getText())) {
                        registerText.setVisible(false);
                        confirmPasswordField.setVisible(false);
                        confirmPasswordLabel.setVisible(false);
//                        continueButton.setText("Login");
                        loginButton.setDisable(false);
                        login();
//                        if (checkOkay(nicknameField.getText(), passwordField.getText())) {
//                            continueButton.setDisable(false);
//                            continueButton.setOnMouseClicked(event1 -> {
//                                try {
//                                    Stage stage = (Stage) anchorPane.getScene().getWindow();
//                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("LoggedUserView.fxml"));
//                                    Parent newRoot = loader.load();
//
//                                    LoggedUserViewController loggedUserViewController = loader.getController();
//                                    loggedUserViewController.setUser(EatAdvisor.cercaCliente(
//                                            nicknameField.getText(), passwordField.getText()));
//
//                                    Scene newScene = new Scene(newRoot);
//                                    stage.setScene(newScene);
//                                    stage.setMinWidth(600);
//                                    stage.setMinHeight(400);
//                                    stage.setResizable(false);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            });
//                        } else {
//                            continueButton.setDisable(true);
//                            continueLabel.setVisible(false);
//                        }
                    } else {
                        loginButton.setDisable(true);
                        registerText.setVisible(true);
                        confirmPasswordField.setVisible(true);
                        confirmPasswordLabel.setVisible(true);
                        continueButton.setText("Continua la registrazione");
                    }
                }
            } else {
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
            if (checkOkay(nicknameField.getText(), passwordField.getText())) {
                continueButton.setDisable(false);
                login();
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
    }

    private void notLoggedUser() {
        try {
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NotLoggedUserView.fxml"));
            Parent newRoot = loader.load();

            Scene newScene = new Scene(newRoot);
            stage.setScene(newScene);
            stage.setMinWidth(600);
            stage.setMinHeight(400);
            stage.setResizable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void login() {
        if (checkOkay(nicknameField.getText(), passwordField.getText())) {
            continueButton.setDisable(false);
            continueButton.setOnMouseClicked(event1 -> {
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
                    stage.setResizable(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            continueButton.setDisable(true);
            continueLabel.setVisible(false);
        }
    }

    private boolean checkOkay(String nickname, String password) {
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