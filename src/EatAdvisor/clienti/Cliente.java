package EatAdvisor.clienti;

import java.io.*;

/**
 * @author Fabio Cirelli
 * Classe che permette di creare oggetti Clienti, e di registrarli sul file Utenti.data.
 * Contiene metodi per visualizzare il menu per diverse categorie di utenti (registrati, non registrati),
 * e metodi per aggiungere giudizi al Ristorante selezionato.
 */
public class Cliente implements Serializable {

    private String nome;
    private String cognome;
    private String comune;
    private String provincia;
    private String email;
    private String nickname;
    private String password;

    /**
     * Costruttore.
     * Costruisce l'oggetto Clienti specificando
     * @param nome
     * @param cognome
     * @param comune
     * @param provincia
     * @param email
     * @param nickname
     * @param password
     */
    public Cliente(String nome, String cognome, String comune, String provincia, String email, String nickname, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.comune = comune;
        this.provincia = provincia;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return nickname dell'utente
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @return password dell'utente
     */
    public String getPassword() {
        return password;
    }
}