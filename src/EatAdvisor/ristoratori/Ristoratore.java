package EatAdvisor.ristoratori;

import EatAdvisor.EatAdvisor;
import EatAdvisor.Giudizio;

/**
 * @author Fabio Cirelli
 * Classe che permette di creare oggetti Ristoratori, e di registrarli sul file EatAdvisor.data.
 * Permette inoltre di aggiornare tale file, e di aggiungere giudizi ai singoli ristoranti.
 */
public class Ristoratore extends EatAdvisor implements java.io.Serializable {

    private String nome;
    private String tipoIndirizzo;
    private String nomeIndirizzo;
    private String civico;
    private String comune;
    private String provincia;
    private String cap;
    private String telefono;
    private String url;
    private String tipologia;
    private Giudizio[] giudizi = null;

    /**
     * Costruttore.
     * Costruisce l'oggetto Ristoratori specificando
     * @param nome
     * @param tipoIndirizzo
     * @param nomeIndirizzo
     * @param civico
     * @param comune
     * @param provincia
     * @param cap
     * @param telefono
     * @param url
     * @param tipologia
     */
    public Ristoratore(String nome, String tipoIndirizzo, String nomeIndirizzo, String civico, String comune,
                       String provincia, String cap, String telefono, String url, String tipologia) {

        this.nome = nome;
        this.tipoIndirizzo = tipoIndirizzo;
        this.nomeIndirizzo = nomeIndirizzo;
        this.civico = civico;
        this.comune = comune;
        this.provincia = provincia;
        this.cap = cap;
        this.telefono = telefono;
        this.url = url;
        this.tipologia = tipologia;
    }

    /**
     * @return nome del ristorante
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return tipoIndirizzo del ristorante
     */
    public String getTipoIndirizzo() {
        return tipoIndirizzo;
    }

    /**
     * @return nomeIndirizzo del ristorante
     */
    public String getNomeIndirizzo() {
        return nomeIndirizzo;
    }

    /**
     * @return civico del ristorante
     */
    public String getCivico() {
        return civico;
    }

    /**
     * @return comune del ristorante
     */
    public String getComune() {
        return comune;
    }

    /**
     * @return provincia del ristorante
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * @return cap del ristorante
     */
    public String getCap() {
        return cap;
    }

    /**
     * @return telefono del ristorante
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @return url del ristorante
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return tipologia del ristorante
     */
    public String getTipologia() {
        return tipologia;
    }

    /**
     * @return array giudizi del ristorante
     */
    public Giudizio[] getGiudizi() {
        return giudizi;
    }

    /**
     * Se l'array dei giudizi del ristorante non esiste, lo crea e aggiunge il giudizio.
     * Altrimenti crea un nuovo array di giudizi, aggiunge il giudizio "in testa", e poi aggiunge
     * i giudizi precedenti.
     *
     * @param g giudizio da aggiungere al ristorante invocante
     */
    public void addGiudizio(Giudizio g) {
        if (giudizi == null) {
            giudizi = new Giudizio[1];
            giudizi[0] = g;
        } else {
            Giudizio[] newGiudizi = new Giudizio[giudizi.length + 1];
            for (int i = 0; i < newGiudizi.length; i++) {
                if (i == 0) {
                    newGiudizi[i] = g;
                } else {
                    newGiudizi[i] = giudizi[i - 1];
                }
            }
            giudizi = newGiudizi;
        }
    }

    public boolean equals(Ristoratore r) {
        return this.nome.equals(r.nome);
    }
}