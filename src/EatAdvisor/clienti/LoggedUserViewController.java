package EatAdvisor.clienti;

import EatAdvisor.EatAdvisor;
import EatAdvisor.ristoratori.Ristoratore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class LoggedUserViewController {

    @FXML
    private TableView<Object> tabellaRistoratori;

    @FXML
    private TableColumn<Object, String> colonnaNome;

    @FXML
    private TableColumn<Object, String> colonnaIndirizzo;

    @FXML
    private TableColumn<Object, String> colonnaComune;

    @FXML
    private TableColumn<Object, String> colonnaProvincia;

    @FXML
    private TableColumn<Object, String> colonnaCap;

    @FXML
    private TableColumn<Object, String> colonnaTipologia;

    private Cliente cliente;
    private ObservableList<Object> ristoratori = null;

    public LoggedUserViewController () {

    }

    public void initialize() {
        colonnaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colonnaIndirizzo.setCellValueFactory(new PropertyValueFactory<>("indirizzo"));
        colonnaComune.setCellValueFactory(new PropertyValueFactory<>("comune"));
        colonnaProvincia.setCellValueFactory(new PropertyValueFactory<>("provincia"));
        colonnaCap.setCellValueFactory(new PropertyValueFactory<>("cap"));
        colonnaTipologia.setCellValueFactory(new PropertyValueFactory<>("tipologia"));

        tabellaRistoratori.setRowFactory( x -> {
            TableRow<Object> row = new TableRow<Object>();
            row.setOnMouseClicked(event -> {
                // TODO: mostra i dati del ristorante (overhaul di EatAdvisor.selezionaRistorante() )
            });
            return row ;
        });

        ArrayList<Object> ristoratoriTemp = EatAdvisor.leggiRistoratori();
        if (ristoratoriTemp != null) ristoratori = FXCollections.observableArrayList(ristoratoriTemp);

        tabellaRistoratori.setItems(ristoratori);
    }

    public void setUser(Cliente c) {
        cliente = c;
    }
}
