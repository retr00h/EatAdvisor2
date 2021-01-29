package EatAdvisor.clienti;

import EatAdvisor.EatAdvisor;
import EatAdvisor.ristoratori.Ristoratore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LoggedUserViewController {

    @FXML
    private TableView<Object> tabellaRistoratori;

    @FXML
    private TableColumn<Object, String> colonnaNome;

    @FXML
    private TableColumn<Object, String> colonnaIndirizzo;

    @FXML
    private TableColumn<Object, String> colonnaTipoIndirizzo;

    @FXML
    private TableColumn<Object, String> colonnaNomeIndirizzo;

    @FXML
    private TableColumn<Object, String> colonnaCivico;

    @FXML
    private TableColumn<Object, String> colonnaComune;

    @FXML
    private TableColumn<Object, String> colonnaProvincia;

    @FXML
    private TableColumn<Object, String> colonnaCap;

    @FXML
    private TableColumn<Object, String> colonnaTipologia;

    @FXML
    private TextField textFieldRicerca;

    @FXML
    private ComboBox<String> comboBoxRicerca;

    @FXML
    private Button bottoneCerca;

    @FXML
    private GridPane bottomGridPane;

    @FXML
    private Text dettagliRistorante;

    @FXML
    private Text textNome;

    @FXML
    private Text textIndirizzo;

    @FXML
    private Text textTipologia;

    @FXML
    private Text textGiudizi;

    @FXML
    private Text nomeRistorante;

    @FXML
    private Text indirizzoRistorante;

    @FXML
    private Text tipologiaRistorante;

    @FXML
    private Text mediaGiudiziRistorante;

    @FXML
    private Text dettagliGiudizi;

    @FXML
    private Button bottoneAggiungiGiudizio;

    @FXML
    private Text textRegistrati;

    private Cliente cliente;
    private ObservableList<Object> ristoratoriFull;

    public LoggedUserViewController () {

    }

    public void initialize() {

        bottoneCerca.setOnMouseClicked(new HandlerBottoneRicerca());
        bottoneAggiungiGiudizio.setOnMouseClicked(new HandlerBottoneGiudizio());

        colonnaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
//        colonnaIndirizzo.setCellValueFactory(new PropertyValueFactory<>("indirizzo"));
        colonnaTipoIndirizzo.setCellValueFactory(new PropertyValueFactory<>("tipoIndirizzo"));
        colonnaNomeIndirizzo.setCellValueFactory(new PropertyValueFactory<>("nomeIndirizzo"));
        colonnaCivico.setCellValueFactory(new PropertyValueFactory<>("civico"));
        colonnaComune.setCellValueFactory(new PropertyValueFactory<>("comune"));
        colonnaProvincia.setCellValueFactory(new PropertyValueFactory<>("provincia"));
        colonnaCap.setCellValueFactory(new PropertyValueFactory<>("cap"));
        colonnaTipologia.setCellValueFactory(new PropertyValueFactory<>("tipologia"));

        tabellaRistoratori.setRowFactory( x -> {
            TableRow<Object> row = new TableRow<Object>();
            row.setOnMouseClicked(event -> {
                TableRow<Object> tableRow = (TableRow<Object>) event.getSource();
                Ristoratore r = (Ristoratore) tableRow.getItem();
                if (r != null) {
                    selezionaRistoratore(r);
                }
            });
            return row ;
        });

        ArrayList<Object> ristoratoriTemp = EatAdvisor.leggi(2);
        if (ristoratoriTemp != null) ristoratoriFull = FXCollections.observableArrayList(ristoratoriTemp);

        tabellaRistoratori.setItems(ristoratoriFull);
    }

    public void setUser(Cliente c) {
        cliente = c;
    }

    public void selezionaRistoratore(Ristoratore r) {
        bottomGridPane.setGridLinesVisible(true);
        dettagliRistorante.setVisible(true);
        textNome.setVisible(true);
        textIndirizzo.setVisible(true);
        textTipologia.setVisible(true);
        textGiudizi.setVisible(true);

        nomeRistorante.setText(r.getNome());
        indirizzoRistorante.setText(r.getTipoIndirizzo() + " " + r.getNomeIndirizzo() + " " + r.getCivico() + ", " +
                r.getComune() + ", " + r.getProvincia() + ", " + r.getCap());
        tipologiaRistorante.setText(r.getTipologia());
        // TODO: estrarre i giudizi dal ristorante e calcolarne la media

        nomeRistorante.setVisible(true);
        indirizzoRistorante.setVisible(true);
        tipologiaRistorante.setVisible(true);
        mediaGiudiziRistorante.setVisible(true);

        dettagliGiudizi.setVisible(true);
        bottoneAggiungiGiudizio.setVisible(true);

        if (cliente == null) {
            bottoneAggiungiGiudizio.setDisable(true);
            textRegistrati.setVisible(true);
        }
    }

    private class HandlerBottoneGiudizio implements EventHandler<MouseEvent> {
        // TODO: implementa funzionalità di giudizio
        @Override
        public void handle(MouseEvent event) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DialogAggiungiGiudizio.fxml"));
                Parent parent = loader.load();
                DialogAggiungiGiudizioController dialogController = loader.getController();
//                dialogController.setAppMainObservableList(tvObservableList);

                Scene scene = new Scene(parent, 300, 200);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }




            // TODO: implementare metodo di scrittura giudizio, che userà il seguente:

            // TODO: metodo di aggiornamento della lista di ristoratori (da implementare)
        }
    }

    private class HandlerBottoneRicerca implements EventHandler<MouseEvent> {
        // TODO: implementa ricerca
        @Override
        public void handle(MouseEvent event) {
            switch (comboBoxRicerca.getValue()) {
                case "Nome ristorante": System.out.println("AAAAAAAA"); break;
                case "Tipologia ristorante": System.out.println("BBBB"); break;
                case "Comune ristorante": System.out.println("CCCC"); break;
                case "Comune E tipologia ristorante": System.out.println("DDDD"); break;
                default: break;
            }
        }
    }
}
