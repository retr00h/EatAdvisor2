package EatAdvisor.clienti;

import EatAdvisor.EatAdvisor;
import EatAdvisor.ThemeManager;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

public class ControllerLoginView {

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

    @FXML
    private ImageView imageView;

    private ThemeManager themeManager;
    private Cliente cliente = null;

    public ControllerLoginView() {

    }

    public void initialize() {
        themeManager = ThemeManager.getThemeManager();
        imageView.setImage(ThemeManager.sunIcon);

        imageView.setOnMouseClicked(event -> themeManager.changeTheme(anchorPane, imageView));

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
                themeManager.alert(confirmPasswordLabel, true);
                continueButton.setVisible(true);
                continueButton.setOnMouseClicked(event1 -> {
                    Cliente c = new Cliente(null,null,null,null,null,
                            nicknameField.getText(), passwordField.getText());
                    registraCliente(c);
                });
            } else {
                continueButton.setVisible(false);
                themeManager.alert(confirmPasswordLabel, false);
            }
        });
    }

    private void login(boolean haveToLogin) {
        if (haveToLogin) {
            if (checkNicknameAndPassword(nicknameField.getText(), passwordField.getText())) {
                loginButton.setDisable(false);
                loginButton.setOnMouseClicked(event1 ->
                        EatAdvisor.changeToLoggedView(getClass().getResource("UserView.fxml"),
                                anchorPane, EatAdvisor.cercaCliente(
                                    nicknameField.getText(), passwordField.getText())
                        )
                );
            } else {
                continueButton.setDisable(true);
                continueLabel.setVisible(false);

            }
        } else {
            EatAdvisor.changeToLoggedView(getClass().getResource("UserView.fxml"), anchorPane, null);
        }
    }

    private boolean checkNicknameAndPassword(String nickname, String password) {
        return EatAdvisor.cercaCliente(nickname, password) != null;
    }

    private void registraCliente(Cliente c) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DialogRegistrazione.fxml"));
            Parent parent = loader.load();
            ControllerDialogRegistrazione dialogController = loader.getController();
            dialogController.setControllerLoginView(this);
            dialogController.setCliente(c);

            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setMinWidth(600);
            stage.setMinHeight(400);
            stage.setResizable(false);
            stage.setTitle("EatAdvisor - Clienti - Registrati");

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            if (cliente != null) EatAdvisor.changeToLoggedView(getClass().getResource("UserView.fxml"), anchorPane, cliente);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCliente (Cliente c) {
        cliente = c;
    }
}