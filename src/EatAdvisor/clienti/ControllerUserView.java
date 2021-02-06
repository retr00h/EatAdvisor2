package EatAdvisor.clienti;

import EatAdvisor.EatAdvisor;
import EatAdvisor.ristoratori.Ristoratore;
import EatAdvisor.Giudizio;
import EatAdvisor.ThemeManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerUserView {

    @FXML
    private TableView<Object> tabellaRistoratori;

    @FXML
    private TableColumn<Object, String> colonnaNome;

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

    @FXML
    private TableView<Giudizio> tabellaGiudizi;

    @FXML
    private TableColumn<Giudizio, String> colonnaAutore;

    @FXML
    private TableColumn<Giudizio, String> colonnaVoto;

    @FXML
    private TableColumn<Giudizio, String> colonnaCommento;

    @FXML
    private GridPane externalGridPane;

    @FXML
    private ImageView imageView;

    private ThemeManager themeManager;

    private Cliente cliente;
    private ObservableList<Object> ristoratoriFull;

    private ControllerUserView controllerUserView;
    private Ristoratore ristoratoreSelezionato = null;
    private Giudizio nuovoGiudizio = null;

    public ControllerUserView() {

    }

    public void initialize() {
        themeManager = ThemeManager.getThemeManager();

        themeManager.changeTheme(true, externalGridPane, imageView);
        imageView.setOnMouseClicked(event -> themeManager.changeTheme(false, externalGridPane, imageView));

        controllerUserView = this;

        bottoneCerca.setOnMouseClicked(new HandlerBottoneRicerca());
        bottoneAggiungiGiudizio.setOnMouseClicked(new HandlerBottoneGiudizio());

        comboBoxRicerca.setOnAction(event -> bottoneCerca.setDisable(false));

        colonnaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
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
        ristoratoreSelezionato = r;
        nomeRistorante.setText(r.getNome());
        indirizzoRistorante.setText(r.getTipoIndirizzo() + " " + r.getNomeIndirizzo() + " " + r.getCivico() + ", " +
                r.getComune() + ", " + r.getProvincia() + ", " + r.getCap());
        tipologiaRistorante.setText(r.getTipologia());

        colonnaAutore.setCellValueFactory(new PropertyValueFactory<>("autore"));
        colonnaVoto.setCellValueFactory(new PropertyValueFactory<>("voto"));
        colonnaCommento.setCellValueFactory(new PropertyValueFactory<>("commento"));
        colonnaCommento.setCellFactory(tc -> {
            TableCell<Giudizio, String> cell = new TableCell<>();
            Text text = new Text();

            text.getStyleClass().add("text");

            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colonnaCommento.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell ;
        });

        tabellaGiudizi.setRowFactory( x -> new TableRow<Giudizio>());

        Giudizio[] giudizi = r.getGiudizi();
        ObservableList<Giudizio> listaGiudizi;

        if (giudizi != null) {
            double media = 0;
            for (Giudizio g : giudizi) {
                media += g.getVoto();
            }
            media /= giudizi.length;

            listaGiudizi = FXCollections.observableArrayList(giudizi);
            mediaGiudiziRistorante.setText("" + media);
            tabellaGiudizi.setItems(listaGiudizi);
        } else {
            mediaGiudiziRistorante.setText("Nessun giudizio");
            tabellaGiudizi.setItems(null);
        }

        mostraDettagliRistorante(true);
    }

    private class HandlerBottoneGiudizio implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DialogAggiungiGiudizio.fxml"));
                Parent parent = loader.load();
                ControllerDialogAggiungiGiudizio dialogController = loader.getController();
                dialogController.setControllerUserView(controllerUserView);
                dialogController.setAutore(cliente.getNickname());

                boolean dark = themeManager.isDark();

                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.setTitle("EatAdvisor - Aggiungi un giudizio");

                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

                if (dark != themeManager.isDark()) themeManager.changeTheme(true, externalGridPane, imageView);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (nuovoGiudizio != null) {
                ristoratoreSelezionato.addGiudizio(nuovoGiudizio);
                nuovoGiudizio = null;
                EatAdvisor.aggiorna(ristoratoreSelezionato);
                selezionaRistoratore(ristoratoreSelezionato);
            }
        }
    }

    private class HandlerBottoneRicerca implements EventHandler<MouseEvent> {
        /*  Sintassi di ricerca:
            Il carattere , separa i parametri di ricerca (se in una ricerca per comune inserisco "milano,varese"
            verranno trovati tutti i ristoranti il cui comune è Milano O Varese.
            Questo è valido per tutti i tipi di ricerca.

            Nella ricerca per comune E tipologia, il carattere ; separa i comuni dalle tipologie.
            È necessario che vengano scritti prima i comuni, e poi le tipologie.
            È necessario che venga usato un ;
            Esempio: la query "milano,varese;italiano,etnico" troverà tutti i ristoranti italiani O etnici, il cui comune
            è Milano O Varese.

            Le ricerche NON sono case sensitive.
         */
        @Override
        public void handle(MouseEvent event) {
            if (textFieldRicerca.getText().equals("")) {
                tabellaRistoratori.setItems(ristoratoriFull);
            } else {
                ObservableList<Object> lista = null;
                switch (comboBoxRicerca.getValue()) {
                    case "Nome ristorante":
                        lista = cercaRistorantePerNome(textFieldRicerca.getText());
                        break;
                    case "Tipologia ristorante":
                        lista = cercaRistorantePerTipologia(textFieldRicerca.getText());
                        break;
                    case "Comune ristorante":
                        lista = cercaRistorantePerComune(textFieldRicerca.getText());
                        break;
                    case "Comune E tipologia ristorante":
                    lista = cercaRistorantePerComuneETipologia(textFieldRicerca.getText());
                        break;
                    default: break;
                }

                if (lista == null) {
                    tabellaRistoratori.setItems(ristoratoriFull);
                    mostraDialogNessunRistoranteTrovato();
                } else {
                    tabellaRistoratori.setItems(FXCollections.observableArrayList(lista));
                }
            }
        }
    }

    private void mostraDettagliRistorante(boolean mostra) {
        bottomGridPane.setGridLinesVisible(mostra);
        dettagliRistorante.setVisible(mostra);
        textNome.setVisible(mostra);
        textIndirizzo.setVisible(mostra);
        textTipologia.setVisible(mostra);
        textGiudizi.setVisible(mostra);

        nomeRistorante.setVisible(mostra);
        indirizzoRistorante.setVisible(mostra);
        tipologiaRistorante.setVisible(mostra);
        mediaGiudiziRistorante.setVisible(mostra);

        dettagliGiudizi.setVisible(mostra);
        bottoneAggiungiGiudizio.setVisible(mostra);

        tabellaGiudizi.setVisible(mostra);

        if (cliente == null) {
            bottoneAggiungiGiudizio.setDisable(true);
            textRegistrati.setVisible(mostra);
        }
    }

    private ObservableList<Object> cercaRistorantePerNome (String arg) {
        if (arg.matches("^[A-Za-z0-9,]+$")) {
            ArrayList<Object> ristoranti = null;

            String[] nomi = arg.split(",");

            for (Object o : ristoratoriFull) {
                Ristoratore r = (Ristoratore) o;
                for (String nome : nomi) {
                    if (r.getNome().toLowerCase().contains(nome.toLowerCase())) {
                        if (ristoranti == null) ristoranti = new ArrayList<Object>();
                        ristoranti.add(o);
                    }
                }
            }
            mostraDettagliRistorante(false);
            if (ristoranti == null) {
                return null;
            } else {
                rimuoviDuplicati(ristoranti);
                return FXCollections.observableArrayList(ristoranti);
            }
        } else return null;
    }

    private ObservableList<Object> cercaRistorantePerTipologia (String arg) {
        if (arg.matches("^[A-Za-z0-9,]+$")) {
            ArrayList<Object> ristoranti = null;

            String[] tipologie = arg.split(",");

            for (Object o : ristoratoriFull) {
                Ristoratore r = (Ristoratore) o;
                for (String tipologia : tipologie) {
                    if (r.getTipologia().toLowerCase().contains(tipologia.toLowerCase())) {
                        if (ristoranti == null) ristoranti = new ArrayList<Object>();
                        ristoranti.add(o);
                    }
                }
            }
            mostraDettagliRistorante(false);
            if (ristoranti == null) {
                return null;
            } else {
                rimuoviDuplicati(ristoranti);
                return FXCollections.observableArrayList(ristoranti);
            }
        } else return null;
    }

    private ObservableList<Object> cercaRistorantePerComune(String arg) {
        if (arg.matches("^[A-Za-z0-9,]+$")) {
            ArrayList<Object> ristoranti = null;

            String[] comuni = arg.split(",");

            for (Object o : ristoratoriFull) {
                Ristoratore r = (Ristoratore) o;
                for (String comune : comuni) {
                    if (r.getComune().toLowerCase().contains(comune.toLowerCase())) {
                        if (ristoranti == null) ristoranti = new ArrayList<Object>();
                        ristoranti.add(o);
                    }
                }
            }
            mostraDettagliRistorante(false);
            if (ristoranti == null) {
                return null;
            } else {
                rimuoviDuplicati(ristoranti);
                return FXCollections.observableArrayList(ristoranti);
            }
        } else return null;
    }

    private ObservableList<Object> cercaRistorantePerComuneETipologia(String args) {
        if (args.matches("^([A-Za-z0-9,]+;[A-Za-z0-9,]+)$")) {
            int pos = args.indexOf(";");
            String comuni = args.substring(0, pos);
            String tipologie = args.substring(pos + 1);

            ObservableList<Object> ristorantiComune = cercaRistorantePerComune(comuni);
            ObservableList<Object> ristorantiTipologia = cercaRistorantePerTipologia(tipologie);


            if (ristorantiComune == null && ristorantiTipologia == null) return null;
            else if (ristorantiComune == null) return ristorantiTipologia;
            else if (ristorantiTipologia == null) return ristorantiComune;
            else {
                ArrayList<Object> lista = new ArrayList<Object>();
                for (int i = 0; i < ristorantiComune.size(); i++) {
                    Ristoratore r1 = (Ristoratore) ristorantiComune.get(i);
                    for (int j = 0; j < ristorantiTipologia.size(); j++) {
                        Ristoratore r2 = (Ristoratore) ristorantiTipologia.get(j);
                        if (r1.equals(r2)) lista.add(r1);
                    }
                }
                return FXCollections.observableArrayList(lista);
            }
        } else return null;
    }

    private void rimuoviDuplicati(ArrayList<Object> ristoranti) {
        for (int i = 0; i < ristoranti.size(); i++) {
            for (int j = 0; j < ristoranti.size(); j++) {
                if (i != j) {
                    Ristoratore r1 = (Ristoratore) ristoranti.get(i);
                    Ristoratore r2 = (Ristoratore) ristoranti.get(j);
                    if (r1.equals(r2)) ristoranti.remove(j);
                }
            }
        }
    }

    public void mostraDialogNessunRistoranteTrovato() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("EatAdvisor - nessun ristorante trovato");
        alert.setHeaderText("Nessun ristorante trovato");
        alert.setContentText("Perfavore, controlla i parametri di ricerca e riprova");

        alert.getDialogPane().getStylesheets().clear();
        if (themeManager.isDark()) {
            alert.getDialogPane().getStylesheets().add("dark.css");
        } else {
            alert.getDialogPane().getStylesheets().add("light.css");
        }

        alert.showAndWait();
    }

    public void setGiudizio(Giudizio g) {
        nuovoGiudizio = g;
    }
}
