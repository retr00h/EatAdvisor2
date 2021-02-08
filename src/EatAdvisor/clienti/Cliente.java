package EatAdvisor.clienti;

import java.io.*;

/**
 * @author Fabio Cirelli
 * Questa classe rappresenta i clienti.
 * Contiene metodi set/get.
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
     * @param nome nome del cliente
     * @param cognome cognome del cliente
     * @param comune comune di residenza del cliente
     * @param provincia provincia di residenza del cliente
     * @param email email del cliente
     * @param nickname nickname del cliente
     * @param password password del cliente
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

    /**
     * Setter (nome)
     * @param nome nome da settare
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Setter (cognome)
     * @param cognome cognome da settare
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Setter (comune)
     * @param comune comune di residenza da settare
     */
    public void setComune(String comune) {
        this.comune = comune;
    }

    /**
     * Setter (provincia)
     * @param provincia provincia di residenza da settare
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * Setter (email)
     * @param email email da settare
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter (nickname)
     * @return nickname dell'utente
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Getter (password)
     * @return password dell'utente
     */
    public String getPassword() {
        return password;
    }
}