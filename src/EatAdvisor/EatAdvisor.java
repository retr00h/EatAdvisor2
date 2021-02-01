package EatAdvisor;

import EatAdvisor.clienti.Cliente;
import EatAdvisor.clienti.ControllerUserView;
import EatAdvisor.ristoratori.Ristoratore;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

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
            ArrayList<Object> clienti = leggiDaFile(PATH_UTENTI, 1);
            if (clienti != null) {
                for (Object c : clienti) {
                    Cliente cliente = (Cliente) c;
                    if (cliente.getNickname().equals(arg)) return true;
                }
            }
            return false;
        } else if (op == 2) {
            ArrayList<Object> ristoratori = leggiDaFile(PATH_RISTORANTI, 2);
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
        /* TABELLA AGGIORNATA per ogni caso di input possibile controlla che sia valido e ritorna il conseguente valore booleano
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

    public static void changeToLoggedView(URL fxml, AnchorPane anchorPane, Cliente c) {
        try {
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(fxml);
            Parent newRoot = loader.load();
            ControllerUserView controllerUserView = loader.getController();
            controllerUserView.setUser(c);

            Scene newScene = new Scene(newRoot);
            stage.setScene(newScene);
            stage.setMinWidth(1118);
            stage.setMinHeight(600);
            stage.setResizable(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Object> leggi (int op) {
        // 1: cliente
        // 2: ristoratore
        if (op == 1) {
            return leggiDaFile(PATH_UTENTI, op);
        } else if (op == 2) {
            return leggiDaFile(PATH_RISTORANTI, op);
        } else return null;
    }

    private static ArrayList<Object> leggiDaFile(String path, int op) {
        // 1: clienti
        // 2: ristoratori
        File f = new File(path);
        ArrayList<Object> oggetti = null;
        if (f.exists() && !f.isDirectory()) {
            try {
                FileInputStream fileInput = new FileInputStream(path);
                ObjectInputStream in = new ObjectInputStream(fileInput);
                oggetti = (ArrayList<Object>) in.readObject();
                in.close();
                fileInput.close();
                return oggetti;
            } catch (IOException | ClassNotFoundException e) {
                System.out.print("Qualcosa e' andato storto e non e' stato possibile leggere i dati ");
                if (op == 1) System.out.println(" degli utenti.");
                else System.out.println(" dei ristoratori.");
            }
        }

        return oggetti;
    }

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
                ristoratori.add(0, (Ristoratore) ob);
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
        ArrayList<Object> clienti = leggiDaFile(PATH_UTENTI, 1);
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
                    ArrayList<Object> clienti = leggiDaFile(PATH_UTENTI, 1);

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
                    ArrayList<Object> ristoratori = leggiDaFile(PATH_RISTORANTI, 2);

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
                        ristoratori.add(0, r);
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

    public static void aggiorna(Ristoratore r) {
        ArrayList<Object> ristoratori = leggi(2);

        for (int i = 0; i < ristoratori.size(); i++) {
            Ristoratore oldRistoratore = (Ristoratore) ristoratori.get(i);
            if (oldRistoratore.equals(r)) {
                ristoratori.set(i, r);
            }
        }
        scrivi(ristoratori,2);
    }
}