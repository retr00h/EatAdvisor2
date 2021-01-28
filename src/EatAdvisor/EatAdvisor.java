package EatAdvisor;

import EatAdvisor.clienti.Cliente;
import EatAdvisor.ristoratori.Ristoratore;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Fabio Cirelli
 * Classe "di servizio" usata dalle altre classi del package.
 * Fornisce metodi di validazione dell'input, ricerca dei ristoranti, visualizzazione di informazioni di essi,
 * ed un metodo per leggere i clienti dal file Utenti.data.
 */
public class EatAdvisor {

    protected static final String PATH_UTENTI = "data" + File.separator + "Utenti.dati";
    protected static final String PATH_RISTORANTI = "data" + File.separator + "EatAdvisor.dati";

    public static boolean isRegistrato(String arg, int op) {
        // 1: cliente
        // 2: ristoratore
        if (op == 1) {
            ArrayList<Object> clienti = leggiClienti();
            if (clienti != null) {
                for (Object c : clienti) {
                    Cliente cliente = (Cliente) c;
                    if (cliente.getNickname().equals(arg)) return true;
                }
            }
            return false;
        } else if (op == 2) {
            ArrayList<Object> ristoratori = leggiRistoratori();
            if (ristoratori != null) {
                for (Object r : ristoratori) {
                    Ristoratore ristoratore = (Ristoratore) r;
                    if (ristoratore.getNome().toLowerCase().equals(arg.toLowerCase())) return true;
                }
            }
            return false;
        }
        return false;
    }

    public static boolean alert(Label l, boolean okay) {
        if (okay) {
            l.setTextFill(Paint.valueOf("000000"));
            return true;
        } else {
            l.setTextFill(Paint.valueOf("FF0000"));
            return false;
        }
    }

//    public static void alert(Label l, Text text, boolean okay) {
//        if (okay) {
//            text.setVisible(true);
//        } else {
//            text.setVisible(false);
//        }
//        alert(l, okay);
//    }

//    public static void login (Cliente c) {
//        if (cercaCliente(c.getNickname(), c.getPassword()) != null) {
//
//        }
//    }

    /**
     * Per op 0, 1 e 16 controlla direttamente se s e' valida.
     * Per tutti gli altri op ammessi confronta s con una regex e ne ritorna il risultato boolean.
     * Per gli op non ammessi ritorna false.
     *
     * @param  s  stringa da validare
     * @param  op tipo di stringa
     * @return      valore boolean che rappresenta la validita' della stringa s
     */
    public static boolean validate(String s, int op) {

        /* TABELLA AGGIORNATA per ogni caso possibile di input controlla che sia valido e ritorna il conseguente valore booleano
           1: nome OPPURE comune
           2: nomeIndirizzo
           3: civico
           4: provincia
           5: cap
           6: telefono
           7: url
           8: cognome
           9: email
           10: nickname
           11: password
           12: commento
           13: nomeRistorante
         */



        /* per ogni caso possibile di input controlla che sia valido e ritorna il conseguente valore booleano
            selezioneRistorante 0
            selezioneCliente    1
            nome                2
            tipoIndirizzo       3
            nomeIndirizzo       4
            civico              5
            comune              6
            provincia           7
            cap                 8
            telefono            9
            url                 10
            tipologiaRistorante 11
            cognome             12
            email               13
            nickname            14
            password            15
            selezioneRicerca    16
            selezioneRistorante 17
            selezioneGiudizio   18
            voto                19
            commento            20
         */

        String regexNomeEComune = "^[a-z\\s]+$"; //"^[A-Za-z\\s]+[A-Za-z\\s]*$";
        String regexNomeIndirizzo = "^[a-z0-9\\s]+$";
        String regexCivico = "^[0-9]{1,4}[A-Z]|[0-9]{1,4}$";
        String regexProvincia = "^[A-Za-z]{2}$";
        String regexCap = "^[0-9]{5}$";
        String regexTelefono = "^(\\+[0-9]{12}|[0-9]{10})$";
        String regexUrl = "^(http://|https://)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$";

        String regexCognome = "^[A-Za-z\\s]+[A-Za-z\\s]*$";
        String regexEmail = "^[a-zA-Z0-9_.-]{1,64}@[a-zA-Z0-9.-]{1,}\\.[a-zA-Z]{2,3}$";
        String regexNickname = "^[A-Za-z0-9_.\\-]+$";
        String regexPassword = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,})";

        String regexNomeRistorante = "^[A-Za-z1-9\\s]+$";

        switch (op) {
            case 1: return s.toLowerCase().matches(regexNomeEComune);
            case 2: return s.toLowerCase().matches(regexNomeIndirizzo);
            case 3: return s.matches(regexCivico);
            case 4: return s.matches(regexProvincia);
            case 5: return s.matches(regexCap);
            case 6: return s.matches(regexTelefono);
            case 7: return s.length() == 0 || s.toLowerCase().matches(regexUrl);
            case 8: return s.matches(regexCognome);
            case 9: return s.matches(regexEmail);
            case 10: return s.matches(regexNickname);
            case 11: return s.matches(regexPassword);
            case 12: return s.length() <= 256;
            case 13: return s.matches(regexNomeRistorante);
            default: return false;
        }
    }

    /**
     * Legge s da input, fa un primo confronto utilizzando EatAdvisor.validate(String s, int op).
     * Se tale confronto da' esito positivo, ritorna s.
     * Altrimenti itera finche' EatAdvisor.validate(String s, int op) non risulta true.
     * Per alcuni op viene modificata la stringa in input tramite String.toLowerCase() o String.toUpperCase().
     *
     * @param input stringa da validare
     * @param op    tipo di stringa
     * @return stringa inserita in input
     */
//    public static String input(Scanner input, int op) {
//        // per ogni caso possibile di input (vedere il metodo validate) legge la stringa, controlla che sia valida,
//        // e se non lo e', continua a richiederla finche non ne viene inserita una valida
//        String s;
//        switch (op) {
//            case 0:
//                s = input.nextLine();
//                if (validate(s, 0)) {
//                    return s;
//                } else {
//                    while (true) {
//                        System.out.println("\nRiprovare.\n");
//                        s = input.nextLine();
//                        if (validate(s, 0)) {
//                            return s;
//                        }
//                    }
//                }
//            case 1:
//                s = input.nextLine();
//                if (validate(s, 1)) {
//                    return s;
//                } else {
//                    while (true) {
//                        System.out.println("\nRiprovare.\n");
//                        s = input.nextLine();
//                        if (validate(s, 1)) {
//                            return s;
//                        }
//                    }
//                }
//            case 2:
//                s = input.nextLine();
//                if (validate(s, 2)) {
//                    return s;
//                } else {
//                    while (true) {
//                        System.out.println("\nRiprovare.\n");
//                        s = input.nextLine();
//                        if (validate(s, 2)) {
//                            return s;
//                        }
//                    }
//                }
//            case 3:
//                s = input.nextLine().toLowerCase();
//                if (validate(s, 3)) {
//                    return s;
//                } else {
//                    while (true) {
//                        System.out.println("\nRiprovare.\nI tipi disponibili sono: via, viale, corso, piazza, " +
//                                "piazzale, largo, lungolago, lungomare, rotonda, vicolo, vicoletto.\n");
//                        s = input.nextLine().toLowerCase();
//                        if (validate(s, 3)) {
//                            return s;
//                        }
//                    }
//                }
//            case 4:
//                s = input.nextLine().toLowerCase();
//                if (validate(s, 4)) {
//                    return s;
//                } else {
//                    while (true) {
//                        System.out.println("\nRiprovare.\n");
//                        s = input.nextLine().toLowerCase();
//                        if (validate(s, 4)) {
//                            return s;
//                        }
//                    }
//                }
//            case 5:
//                s = input.nextLine().toUpperCase().replace(" ", "");
//                if (validate(s, 5)) {
//                    return s;
//                } else {
//                    while (true) {
//                        System.out.println("\nRiprovare.\n");
//                        s = input.nextLine().toUpperCase().replace(" ", "");
//                        if (validate(s, 5)) {
//                            return s;
//                        }
//                    }
//                }
//            case 6:
//                s = input.nextLine().toLowerCase();
//                if (validate(s, 6)) {
//                    return s;
//                } else {
//                    while (true) {
//                        System.out.println("\nRiprovare.\n");
//                        s = input.nextLine().toLowerCase();
//                        if (validate(s, 6)) {
//                            return s;
//                        }
//                    }
//                }
//            case 7:
//                s = input.nextLine().toUpperCase();
//                if (validate(s, 7)) {
//                    return s;
//                } else {
//                    while (true) {
//                        System.out.println("\nRiprovare.\n");
//                        s = input.nextLine().toUpperCase();
//                        if (validate(s, 7)) {
//                            return s;
//                        }
//                    }
//                }
//            case 8:
//                s = input.nextLine();
//                if (validate(s, 8)) {
//                    return s;
//                } else {
//                    while (true) {
//                        System.out.println("\nRiprovare.\n");
//                        s = input.nextLine();
//                        if (validate(s, 8)) {
//                            return s;
//                        }
//                    }
//                }
//            case 9:
//                s = input.nextLine().replace(" ", "");
//                if (validate(s, 9)) {
//                    return s;
//                } else {
//                    while (true) {
//                        System.out.println("\nRiprovare.\n");
//                        s = input.nextLine().replace(" ", "");
//                        if (validate(s, 9)) {
//                            return s;
//                        }
//                    }
//                }
//            case 10:
//                s = input.nextLine();
//                if (validate(s, 10)) {
//                    return s;
//                } else {
//                    while (true) {
//                        System.out.println("\nRiprovare. Per un elenco completo dei formati disponibili, " +
//                                "consultare il manuale a pagina XXX.\n\n");
//                        s = input.nextLine();
//                        if (validate(s, 10)) {
//                            return s;
//                        }
//                    }
//                }
//            case 11:
//                s = input.nextLine().toLowerCase();
//                if (validate(s, 11)) {
//                    return s;
//                } else {
//                    while (true) {
//                        System.out.println("\nRiprovare.\n");
//                        s = input.nextLine().toLowerCase();
//                        if (validate(s, 11)) {
//                            return s;
//                        }
//                    }
//                }
//            case 12:
//                s = input.nextLine();
//                if (validate(s, 12)) {
//                    return s;
//                } else {
//                    while (true) {
//                        System.out.println("\nRiprovare.\n");
//                        s = input.nextLine();
//                        if (validate(s, 12)) {
//                            return s;
//                        }
//                    }
//                }
//            case 13:
//                s = input.nextLine().replace(" ", "");
//                if (validate(s, 13)) {
//                    return s;
//                } else {
//                    while (true) {
//                        System.out.println("\nRiprovare.\n");
//                        s = input.nextLine().replace(" ", "");
//                        if (validate(s, 13)) {
//                            return s;
//                        }
//                    }
//                }
//            case 14:
//                s = input.nextLine();
//                if (validate(s, 14)) {
//                    return s;
//                } else {
//                    while (true) {
//                        System.out.println("\nRiprovare. Per un elenco completo dei formati disponibili, " +
//                                "consultare il manuale a pagina XXX.\n\n");
//                        s = input.nextLine();
//                        if (validate(s, 14)) {
//                            return s;
//                        }
//                    }
//                }
//            case 15:
//                s = input.nextLine();
//                if (validate(s, 15)) {
//                    return s;
//                } else {
//                    while (true) {
//                        System.out.println("\nRiprovare.\n");
//                        s = input.nextLine();
//                        if (validate(s, 15)) {
//                            return s;
//                        }
//                    }
//                }
//            case 16:
//                s = input.nextLine();
//                if (validate(s, 16)) {
//                    return s;
//                } else {
//                    while (true) {
//                        System.out.println("\nRiprovare.\n");
//                        s = input.nextLine();
//                        if (validate(s, 16)) {
//                            return s;
//                        }
//                    }
//                }
//            case 17:
//                s = input.nextLine();
//                if (validate(s, 17)) {
//                    return s;
//                } else {
//                    while (true) {
//                        System.out.println("\nRiprovare.\n");
//                        s = input.nextLine();
//                        if (validate(s, 17)) {
//                            return s;
//                        }
//                    }
//                }
//            case 18:
//                s = input.nextLine();
//                if (validate(s, 18)) {
//                    return s;
//                } else {
//                    while (true) {
//                        System.out.println("\nRiprovare.\n");
//                        s = input.nextLine();
//                        if (validate(s, 18)) {
//                            return s;
//                        }
//                    }
//                }
//            case 19:
//                s = input.nextLine();
//                if (validate(s, 19)) {
//                    return s;
//                } else {
//                    while (true) {
//                        System.out.println("\nRiprovare. Valori consentiti: 1, 2, 3, 4, 5.\n");
//                        s = input.nextLine();
//                        if (validate(s, 19)) {
//                            return s;
//                        }
//                    }
//                }
//            case 20:
//                s = input.nextLine();
//                if (validate(s, 20)) {
//                    return s;
//                } else {
//                    while (true) {
//                        System.out.println("\nMax 256 caratteri. Riprovare.\n");
//                        s = input.nextLine();
//                        if (validate(s, 20)) {
//                            return s;
//                        }
//                    }
//                }
//            default: return null;
//        }
//    }

    /**
     * Metodo statico che ordina l'arraylist ristoratori tramite bubble sort
     *
     * @param ristoratori arraylist da ordinare
     */
    public static void sortRistorantiNome(ArrayList<Ristoratore> ristoratori) {
        int n = ristoratori.size();
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j > i; j--) {
                if (ristoratori.get(j).getNome().compareToIgnoreCase(ristoratori.get(j - 1).getNome()) < 0) {
                    Ristoratore temp = ristoratori.get(j);
                    ristoratori.set(j, ristoratori.get(j - 1));
                    ristoratori.set(j - 1, temp);
                }
            }
        }
    }

    /**
     * Metodo statico che ritorna un arraylist di ristoratori del comune
     *
     * @param comune comune da cercare
     * @return arraylist Ristoratori contenente i ristoranti trovati
     */
    public static ArrayList<Ristoratore> ricercaComune(String comune) {
        String filename = "data" + File.separator + "EatAdvisor.dati";
        File f = new File(filename);
        if (f.exists() && !f.isDirectory()) {
            try {
                // lettura arraylist ristoratori da EatAdvisor.dati
                FileInputStream fileInput = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(fileInput);
                ArrayList<Ristoratore> ristoratori = (ArrayList<Ristoratore>) in.readObject();
                in.close();
                fileInput.close();
                // arraylist ristoratori deserializzato
                // per ogni ristorante nell'arraylist, controlla che il comune inserito sia uguale a quello del ristorante.
                // nel caso lo sia, il ristorante viene aggiunto ad un altro arraylist.
                // viene ritornato il secondo arraylist
                ArrayList<Ristoratore> rok = new ArrayList<>();
                for (int i = 0; i < ristoratori.size(); i++) {
                    if (ristoratori.get(i).getComune().equals(comune)) {
                        rok.add(ristoratori.get(i));
                    }
                }

                return rok;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("\nQualcosa e' andato storto e non e' stato possibile leggere i dati dei ristoranti.\n");
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Metodo statico che ritorna un arraylist di ristoratori della tipologia
     *
     * @param tipologia tipologia da cercare
     * @return arraylist Ristoratori contenente i ristoranti trovati
     */
    public static ArrayList<Ristoratore> ricercaTipologia(String tipologia) {
        String filename = "data" + File.separator + "EatAdvisor.dati";
        File f = new File(filename);
        if (f.exists() && !f.isDirectory()) {
            try {
                // lettura arraylist ristoratori da EatAdvisor.dati
                FileInputStream fileInput = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(fileInput);
                ArrayList<Ristoratore> ristoratori = (ArrayList<Ristoratore>) in.readObject();
                in.close();
                fileInput.close();
                // arraylist ristoratori deserializzato
                // per ogni ristorante nell'arraylist, controlla che la tipologia inserita sia uguale a quella del ristorante.
                // nel caso lo sia, il ristorante viene aggiunto ad un altro arraylist.
                // viene ritornato il secondo arraylist
                ArrayList<Ristoratore> rok = new ArrayList<>();
                for (int i = 0; i < ristoratori.size(); i++) {
                    if (ristoratori.get(i).getTipologia().equals(tipologia)) {
                        rok.add(ristoratori.get(i));
                    }
                }

                return rok;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("\nQualcosa e' andato storto e non e' stato possibile leggere i dati dei ristoranti.\n");
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Metodo statico che ritorna un arraylist di ristoratori il cui nome contiene nome
     *
     * @param nome nome da cercare
     * @return arraylist Ristoratori contenente i ristoranti trovati
     */
    public static ArrayList<Ristoratore> ricercaNome(String nome) {
        String filename = "data" + File.separator + "EatAdvisor.dati";
        File f = new File(filename);
        if (f.exists() && !f.isDirectory()) {
            try {
                // lettura arraylist ristoratori da EatAdvisor.dati
                FileInputStream fileInput = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(fileInput);
                ArrayList<Ristoratore> ristoratori = (ArrayList<Ristoratore>) in.readObject();
                in.close();
                fileInput.close();
                // arraylist ristoratori deserializzato
                // per ogni ristorante nell'arraylist, controlla che il nome inserito sia contenuto in quello del ristorante.
                // nel caso lo sia, il ristorante viene aggiunto ad un altro arraylist.
                // viene ritornato il secondo arraylist
                ArrayList<Ristoratore> rok = new ArrayList<>();
                for (int i = 0; i < ristoratori.size(); i++) {
                    if (ristoratori.get(i).getNome().toLowerCase().contains(nome.toLowerCase())) {
                        rok.add(ristoratori.get(i));
                    }
                }

                return rok;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("\nQualcosa e' andato storto e non e' stato possibile leggere i dati dei ristoranti.\n");
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Metodo statico che, a partire dalla stringa s inserita, ritorna la stessa stringa,
     * con il primo carattere maiuscolo
     *
     * @param s stringa della quale rendere maiuscolo il primo carattere
     * @return stringa s con il primo carattere maiuscolo
     */
    private static String capitalize(String s) {
        // se la stringa non e' nulla o vuota, concatena il primo carattere della stringa reso maiuscolo al resto
        // della stringa
        if (s == null || s.isEmpty()) {
            return s;
        }

        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * Metodo statico che stampa a video i giudizi di r
     *
     * @param r ristorante del quale visualizzare i giudizi
     */
    public static void visualizzaGiudizi(Ristoratore r) {
        // se g non e' null visualizza il numero di giudizi con voto == i incrementando di 1 la posizione corrispondente
        // nell'array n, poi visualizza i giudizi
        Giudizio[] g = r.getGiudizi();
        if (g != null) {
            int[] n = new int[5];
            for (int i = 0; i < g.length; i++) {
                int voto = g[i].getVoto();
                switch (voto) {
                    case 1:
                        n[0]++;
                        break;
                    case 2:
                        n[1]++;
                        break;
                    case 3:
                        n[2]++;
                        break;
                    case 4:
                        n[3]++;
                        break;
                    case 5:
                        n[4]++;
                        break;
                }
            }
            System.out.println();
            for (int i = 0; i < n.length; i++) {
                System.out.println("Gudizi con voto " + (i + 1) + ": " + n[i]);
            }
            System.out.println();
            System.out.print("Giudizi:\n");
            for (Giudizio giudizio : g) {
                System.out.println("Autore: " + giudizio.getAutore());
                System.out.println("Voto: " + giudizio.getVoto());
                if (giudizio.getCommento() != null) {
                    System.out.println("Commento: " + giudizio.getCommento() + "\n");
                }
                System.out.println();
            }
        } else {
            System.out.println("Giudizi: nessuno, per ora...\n");
        }
    }

    /**
     * Metodo statico che stampa a video un numero progressivo,
     * i nomi e gli indirizzi dei ristoranti nell'arraylist ristoratori specificato
     *
     * @param ristoratori arraylist di ristoranti dei quali visualizzare numero, nome e indirizzo
     */
    public static void visualizzaRistoranti(ArrayList<Ristoratore> ristoratori) {
        if (ristoratori != null) {
            int i = 1;
            for (Ristoratore r : ristoratori) {
                if (r != null) {
                    String indirizzo = capitalize(r.getTipoIndirizzo()) + " " +
                            capitalize(r.getNomeIndirizzo()) + ", #" +
                            r.getCivico() + ", " + capitalize(r.getComune()) + ", " +
                            r.getProvincia() + " " + r.getCap();
                    System.out.println("Ristorante " + (i));
                    System.out.println("Nome: " + r.getNome());
                    System.out.println("Indirizzo: " + indirizzo);
                    System.out.println();
                }
                i++;
            }
        }
    }

    /**
     * Metodo statico che riceve un arraylist di ristoranti e un intero n maggiore di 0.
     * Ritorna il ristorante n-esimo
     *
     * @param ristoratori arraylist dal quale selezionare un ristorante
     * @param n           ristorante da selezionare
     * @return ristorante selezionato
     */
    public static Ristoratore selezionaRistorante(ArrayList<Ristoratore> ristoratori, int n) {
        n--;
        if (n >= 0 && n < ristoratori.size()) {
            return ristoratori.get(n);
        } else {
            return null;
        }
    }

    /**
     * Metodo statico che visualizza le informazioni del ristorante selezionato
     *
     * @param r ristorante del quale visualizzare le informazioni complete
     */
    public static void visualizzaInfoRistorante(Ristoratore r) {
        if (r != null) {
            String indirizzo = capitalize(r.getTipoIndirizzo()) + " " + capitalize(r.getNomeIndirizzo()) + ", #" +
                    r.getCivico() + ", " + capitalize(r.getComune()) + ", " + r.getProvincia() + " " + r.getCap();
            System.out.println("Nome: " + r.getNome());
            System.out.println("Indirizzo: " + indirizzo);
            System.out.println("Telefono: " + r.getTelefono());
            System.out.println("Sito web: " + r.getUrl());
            System.out.println("Tipologia: " + capitalize(r.getTipologia()));
            visualizzaGiudizi(r);
        }
    }

    /**
     * Metodo statico che legge le informazioni dei clienti dal file Utenti.dati
     *
     * @return arraylist Clienti contenente i clienti letti
     */
    private static ArrayList<Object> leggiClienti() {
        File f = new File(PATH_UTENTI);
        ArrayList<Object> utenti = null;
        if (f.exists() && !f.isDirectory()) {
            try {
                // lettura array utenti da utenti.dati
                FileInputStream fileInput = new FileInputStream(PATH_UTENTI);
                ObjectInputStream in = new ObjectInputStream(fileInput);
                utenti = (ArrayList<Object>) in.readObject();
                in.close();
                fileInput.close();
                return utenti;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Qualcosa e' andato storto e non e' stato possibile leggere i dati degli utenti.");
            }
        } else {
            System.out.println("Non ci sono utenti registrati.");
        }
        return utenti;
    }

    public static ArrayList<Object> leggiRistoratori() {
        File f = new File(PATH_RISTORANTI);
        ArrayList<Object> ristoranti = null;
        if (f.exists() && !f.isDirectory()) {
            try {
                // lettura array ristoranti da EatAdvisor.dati
                FileInputStream fileInput = new FileInputStream(PATH_RISTORANTI);
                ObjectInputStream in = new ObjectInputStream(fileInput);
                ristoranti = (ArrayList<Object>) in.readObject();
                in.close();
                fileInput.close();
                return ristoranti;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Qualcosa e' andato storto e non e' stato possibile leggere i dati degli utenti.");
            }
        } else {
            System.out.println("Non ci sono ristoratori registrati.");
        }
        return ristoranti;
    }

//    private static ArrayList<Object> leggi(int op) {
//        if (op == 1) {
//            return leggiClienti();
//        } else if (op == 2) {
//            return leggiRistoratori();
//        }
//        return null;
//    }

    public static void scrivi(ArrayList<Object> o, int op) {
        // 1: cliente
        // 2. ristoratore
        if (op == 1) {
            ArrayList<Cliente> clienti = new ArrayList<>();
            for (Object ob : o) {
                clienti.add((Cliente) ob);
            }
            scriviClienti(clienti);
        } else if (op == 2) {
            ArrayList<Ristoratore> ristoratori = new ArrayList<>();
            for (Object ob : o) {
                ristoratori.add((Ristoratore) ob);
            }
            scriviRistoratori(ristoratori);
        }
    }

    public static void scriviClienti(ArrayList<Cliente> clienti) {
        try {
            FileOutputStream fileOutput = new FileOutputStream(PATH_UTENTI);
            ObjectOutputStream out = new ObjectOutputStream(fileOutput);

            // serializzazione oggetto nel file utenti.dati
            out.writeObject(clienti);

            out.close();
            fileOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void scriviRistoratori(ArrayList<Ristoratore> ristoratori) {
        try {
            FileOutputStream fileOutput = new FileOutputStream(PATH_RISTORANTI);
            ObjectOutputStream out = new ObjectOutputStream(fileOutput);

            // serializzazione oggetto nel file utenti.dati
            out.writeObject(ristoratori);

            out.close();
            fileOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Cliente cercaCliente(String nickname, String password) {
        ArrayList<Object> clienti = leggiClienti();
        if (clienti != null){
            for (Object o : clienti) {
                Cliente clienteRegistrato = (Cliente) o;
                if (nickname.equals(clienteRegistrato.getNickname()) && password.equals(clienteRegistrato.getPassword())) {
                    return clienteRegistrato;
                }
            }
        }
        return null;
    }

//    public static void registraCliente (Cliente c) {
//        File f = new File(PATH_UTENTI);
//
//        // crea la directory se non esiste
//        File directory = new File("data/");
//        if (!directory.exists()) {
//            directory.mkdir();
//        }
//
//        if (f.exists() && !f.isDirectory()) {
//            try {
//                // lettura arraylist utenti da utenti.dati
//                ArrayList<Object> clienti = leggiClienti();
//
//                // se il nickname dell'utente invocante e' gia inserito, stampa un errore e interrompe
//                // l'esecuzione del metodo
//                boolean ok = true;
//                for (int i = 0; i < clienti.size(); i++) {
//                    Cliente clienteRegistrato = (Cliente) clienti.get(i);
//                    if (c.getNickname().equals(clienteRegistrato.getNickname())) {
//                        System.out.println("E' gia' presente un ristorante con questo nome a questo indirizzo.\n");
//                        ok = false;
//                        break;
//                    }
//                }
//                if (ok) {
//                    clienti.add(c);
//                }
//
//                scrivi(clienti, 1);
//                System.out.println("Dati inseriti con successo!\n");
//            } catch (Exception e) {
//                System.out.println("Dati non inseriti");
//            }
//        } else {
//            try {
//                ArrayList<Cliente> clienti = new ArrayList<Cliente>();
//                clienti.add(c);
//
//                scriviClienti(clienti);
//                System.out.println("Dati inseriti con successo!\n");
//            } catch (Exception e) {
//                System.out.println("Dati non inseriti");
//            }
//        }
//    }

    public static void registra(Object o, int op) {
        // 1: cliente
        // 2: ristoratore
        File directory = new File("data/");
        if (!directory.exists()) {
            directory.mkdir();
        }

        File f;
        if (op == 1) {
            f = new File(PATH_UTENTI);

            Cliente c = (Cliente) o;
            if (f.exists() && !f.isDirectory()) {
                try {
                    // lettura arraylist utenti da utenti.dati
                    ArrayList<Object> clienti = leggiClienti();

                    // se il nickname dell'utente invocante e' gia inserito, stampa un errore e interrompe
                    // l'esecuzione del metodo
                    boolean ok = true;
                    for (Object clienteRegistrato : clienti) {
                        Cliente cliente = (Cliente) clienteRegistrato;
                        if (c.getNickname().equals(cliente.getNickname())) {
                            System.out.println("E' gia' presente un ristorante con questo nome a questo indirizzo.\n");
                            ok = false;
                            break;
                        }
                    }
                    if (ok) {
                        clienti.add(c);
                    }

                    scrivi(clienti, 1);
                    System.out.println("Dati inseriti con successo!\n");
                } catch (Exception e) {
                    System.out.println("Dati non inseriti");
                }
            } else {
                try {
                    ArrayList<Cliente> clienti = new ArrayList<Cliente>();
                    clienti.add(c);

                    scriviClienti(clienti);
                    System.out.println("Dati inseriti con successo!\n");
                } catch (Exception e) {
                    System.out.println("Dati non inseriti");
                }
            }
        } else if (op == 2) {
            f = new File(PATH_RISTORANTI);

            Ristoratore r = (Ristoratore) o;
            if (f.exists() && !f.isDirectory()) {
                try {
                    // lettura arraylist ristoratori da utenti.dati
                    ArrayList<Object> ristoratori = leggiRistoratori();

                    // se il nickname dell'utente invocante e' gia inserito, stampa un errore e interrompe
                    // l'esecuzione del metodo
                    boolean ok = true;
                    for (Object ristoratoreRegistrato : ristoratori) {
                        Ristoratore ristoratore = (Ristoratore) ristoratoreRegistrato;
                        if (r.getNome().equals(ristoratore.getNome())) {
                            System.out.println("E' gia' presente un ristorante con questo nome a questo indirizzo.\n");
                            ok = false;
                            break;
                        }
                    }
                    if (ok) {
                        ristoratori.add(r);
                    }

                    scrivi(ristoratori, 2);
                    System.out.println("Dati inseriti con successo!\n");
                } catch (Exception e) {
                    System.out.println("Dati non inseriti");
                }
            } else {
                try {
                    ArrayList<Ristoratore> ristoratori = new ArrayList<Ristoratore>();
                    ristoratori.add(r);

                    scriviRistoratori(ristoratori);
                    System.out.println("Dati inseriti con successo!\n");
                } catch (Exception e) {
                    System.out.println("Dati non inseriti");
                }
            }
        }
    }

}