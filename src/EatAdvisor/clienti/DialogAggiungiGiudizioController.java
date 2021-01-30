package EatAdvisor.clienti;

import EatAdvisor.EatAdvisor;
import EatAdvisor.Giudizio;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class DialogAggiungiGiudizioController {

    @FXML
    private Slider sliderVoto;

    @FXML
    private TextArea textAreaCommento;

    @FXML
    private Label labelCaratteriRimanenti;

    @FXML
    private Button bottoneAggiungiGiudizio;

    private int caratteriRimanenti = 256;
    private String autore;
    private Giudizio nuovoGiudizio;


    public DialogAggiungiGiudizioController () {

    }

    public void initialize () {
        bottoneAggiungiGiudizio.setOnMouseClicked(event -> {

            if (caratteriRimanenti == 256) {
                nuovoGiudizio = new Giudizio(autore, (int) sliderVoto.getValue());
            } else {
                nuovoGiudizio = new Giudizio(autore, (int) sliderVoto.getValue(), textAreaCommento.getText());
            }

            Node source = (Node)  event.getSource();
            Stage stage  = (Stage) source.getScene().getWindow();
            stage.close();
        });

        sliderVoto.setOnMouseClicked(event -> bottoneAggiungiGiudizio.setDisable(false));

        textAreaCommento.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 256 ? change : null));

        textAreaCommento.setOnKeyReleased(event -> {
            caratteriRimanenti = 256 - textAreaCommento.getLength();
            labelCaratteriRimanenti.setText("Caratteri rimanenti: " + caratteriRimanenti);

            EatAdvisor.alert(labelCaratteriRimanenti, caratteriRimanenti != 0);
        });

    }

    public void setGiudizio(Giudizio g) {
        nuovoGiudizio = g;
    }

    public void setAutore (String autore) {
        this.autore = autore;
    }
}
