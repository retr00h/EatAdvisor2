package EatAdvisor.clienti;

import EatAdvisor.EatAdvisor;
import EatAdvisor.Giudizio;
import EatAdvisor.ThemeManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ControllerDialogAggiungiGiudizio {

    @FXML
    private GridPane gridPane;

    @FXML
    private Slider sliderVoto;

    @FXML
    private TextArea textAreaCommento;

    @FXML
    private Label labelCaratteriRimanenti;

    @FXML
    private Button bottoneAggiungiGiudizio;

    @FXML
    private ImageView imageView;

    private ThemeManager themeManager;

    private int caratteriRimanenti = 256;
    private String autore;
    private ControllerUserView controllerUserView;

    public ControllerDialogAggiungiGiudizio() {

    }

    public void initialize () {
        themeManager = ThemeManager.getThemeManager();

        themeManager.changeTheme(true, gridPane, imageView);

        imageView.setOnMouseClicked(event -> themeManager.changeTheme(false, gridPane, imageView));

        bottoneAggiungiGiudizio.setOnMouseClicked(event -> {
            if (caratteriRimanenti == 256) {
                controllerUserView.setGiudizio(new Giudizio(autore, (int) sliderVoto.getValue(), ""));
            } else {
                controllerUserView.setGiudizio(new Giudizio(autore, (int) sliderVoto.getValue(), textAreaCommento.getText()));
            }
            Node source = (Node) event.getSource();
            Stage stage  = (Stage) source.getScene().getWindow();
            stage.close();
        });

        sliderVoto.setOnMouseDragged(event -> bottoneAggiungiGiudizio.setDisable(false));

        sliderVoto.valueProperty().addListener((observable, oldValue, newValue) -> {
            String style;
            if (themeManager.isDark()) {
                style = String.format("-fx-background-color: linear-gradient(to right, #00FF9F %d%%, #30475E %d%%);",
                        newValue.intValue() * 20, newValue.intValue() * 20);
            } else {
                style = String.format("-fx-background-color: linear-gradient(to right, #30475E %d%%, #D1D1D1 %d%%);",
                        newValue.intValue() * 20, newValue.intValue() * 20);
            }
            sliderVoto.lookup(".track").setStyle(style);
        });

        textAreaCommento.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 256 ? change : null));

        textAreaCommento.setOnKeyReleased(event -> {
            caratteriRimanenti = 256 - textAreaCommento.getLength();
            labelCaratteriRimanenti.setText("Caratteri rimanenti: " + caratteriRimanenti);

            themeManager.alert(labelCaratteriRimanenti, caratteriRimanenti != 0);
        });
    }

    public void setAutore (String autore) {
        this.autore = autore;
    }

    public void setControllerUserView(ControllerUserView controller) {
        controllerUserView = controller;
    }
}
