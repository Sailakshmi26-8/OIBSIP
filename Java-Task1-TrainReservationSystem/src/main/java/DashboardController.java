import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DashboardController {

    @FXML
    private Button reservationButton;

    @FXML
    private Button cancellationButton;

    @FXML
    private Button logoutButton;


    @FXML
    private void openReservation(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("reservation.fxml")
            );

            Parent root = loader.load();

            Stage stage = (Stage) reservationButton
                    .getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Online Reservation System");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    @FXML
    private void openCancellation(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("cancellation.fxml")
            );

            Parent root = loader.load();

            Stage stage = (Stage) cancellationButton
                    .getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Online Reservation System");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    @FXML
    private void logout(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("login.fxml")
            );

            Parent root = loader.load();

            Stage stage = (Stage) logoutButton
                    .getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Online Reservation System");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}

