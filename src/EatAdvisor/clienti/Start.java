package EatAdvisor.clienti;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

/**
 * @author Fabio Cirelli
 * Questa permette di lanciare l'applicazione (lato cliente).
 */
public class Start extends Application {

    /**
     * Override del metodo start di Application.
     * Istanzia la prima finestra (LoginView) e il suo controller.
     * Se viene usata l'opzione "-debug" al lancio, vengono mostrate anche indicazioni a riga di comando.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("EatAdvisor - Clienti");
        primaryStage.setScene(new Scene(root));

        Parameters params = getParameters();
        List<String> list = params.getRaw();
        if (list.size() == 1 && list.get(0).equals("-debug")) {
            ControllerLoginView controllerLoginView = loader.getController();
            controllerLoginView.setDebug(true);
        }

        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Metodo main.
     * @param args parametri
     */
    public static void main(String[] args) {
        launch(args);
    }
}
