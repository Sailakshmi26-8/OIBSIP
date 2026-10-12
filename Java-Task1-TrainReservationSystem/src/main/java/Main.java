import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/login.fxml")
        );

        Parent root = loader.load();

        Scene scene = new Scene(root);

        stage.setTitle("Online Reservation System");
        stage.setScene(scene);
        stage.setWidth(1365);
        stage.setHeight(768);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

