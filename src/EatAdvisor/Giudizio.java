package EatAdvisor;

/**
 * @author Fabio Cirelli
 * Classe che permette di creare Giudizi relativi ai singoli ristoranti. *
 */
public class Giudizio implements java.io.Serializable {
    private String autore;
    private int voto;
    private String commento;

    /**
     * Costruttore.
     * Costruisce l'oggetto Giudizio specificando
     * @param autore
     * @param voto
     * @param commento
     */
    public Giudizio(String autore, int voto, String commento) {
        this.autore = autore;
        this.voto = voto;
        this.commento = commento;
    }

    /**
     * @return nickname autore del giudizio
     */
    public String getAutore() {
        return autore;
    }

    /**
     * @return voto del giudizio
     */
    public int getVoto() {
        return voto;
    }

    /**
     * @return commento del giudizio
     */
    public String getCommento() {
        return commento;
    }
}