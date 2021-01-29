package EatAdvisor.clienti;

import EatAdvisor.EatAdvisor;
import EatAdvisor.ristoratori.Ristoratore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

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

    private Cliente cliente;
    private ObservableList<Object> ristoratoriFull;

    public LoggedUserViewController () {

    }

    public void initialize() {

        bottoneCerca.setOnMouseClicked(new HandlerBottone());

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
                // TODO: mostra i dati del ristorante (overhaul di EatAdvisor.selezionaRistorante() )
                TableRow<Object> tableRow = (TableRow<Object>) event.getSource();
                Ristoratore r = (Ristoratore) tableRow.getItem();

                selezionaRistoratore(r);
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

    public static void selezionaRistoratore(Ristoratore r) {

    }

    private class HandlerBottone implements EventHandler<MouseEvent> {
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
