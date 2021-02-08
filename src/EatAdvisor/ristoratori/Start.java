package EatAdvisor.ristoratori;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class Start extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewRegistrazione.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("EatAdvisor - Ristoratori");
        primaryStage.setScene(new Scene(root));

        Parameters params = getParameters();
        List<String> list = params.getRaw();
        if (list.size() == 1 && list.get(0).equals("-debug")) {
            ControllerViewRegistrazione controllerViewRegistrazione = loader.getController();
            controllerViewRegistrazione.setDebug(true);
        }

        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
