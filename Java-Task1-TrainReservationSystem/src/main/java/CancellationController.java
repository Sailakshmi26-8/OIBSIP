import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CancellationController {

    @FXML
    private TextField pnrField;

    @FXML
    private Button fetchButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button backButton;

    @FXML
    private VBox bookingDetailsBox;

    @FXML
    private Label passengerLabel;

    @FXML
    private Label trainLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label sourceLabel;

    @FXML
    private Label destinationLabel;

    @FXML
    private Label classLabel;

    @FXML
    private void fetchBooking() {

        String pnr = pnrField.getText().trim();

        if (pnr.isEmpty()) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Missing PNR",
                    "Please enter your PNR number."
            );
            return;
        }

        String sql = "SELECT pnr, passenger_name, train_number, "
                + "train_name, travel_class, journey_date, "
                + "source, destination FROM bookings WHERE pnr = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, pnr);
           
            try (ResultSet result = statement.executeQuery()) {

                if (result.next()) {

                    passengerLabel.setText(
                            result.getString("passenger_name")
                    );

                    trainLabel.setText(
                            result.getString("train_number")
                                    + " - "
                                    + result.getString("train_name")
                    );

                    dateLabel.setText(
                            result.getString("journey_date")
                    );

                    sourceLabel.setText(
                            result.getString("source")
                    );

                    destinationLabel.setText(
                            result.getString("destination")
                    );

                    classLabel.setText(
                            result.getString("travel_class")
                    );

                    bookingDetailsBox.setManaged(true);
                    bookingDetailsBox.setVisible(true);
                    cancelButton.setDisable(false);

                } else {

                    clearBooking();

                    showAlert(
                            Alert.AlertType.ERROR,
                            "Booking Not Found",
                            "No booking found for PNR: " + pnr
                    );
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Database Error",
                    "Could not retrieve booking details.\n"
                            + "Please try again.\n\n"
                            + "Reason: " + e.getMessage()
            );
        }
    }

    @FXML
    private void cancelTicket() {

        String pnr = pnrField.getText().trim();

        if (pnr.isEmpty()) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Missing PNR",
                    "Please fetch a valid booking first."
            );
            return;
        }

        Alert confirmation =
                new Alert(Alert.AlertType.CONFIRMATION);

        confirmation.setTitle("Confirm Cancellation");
        confirmation.setHeaderText("Cancel Ticket");
        confirmation.setContentText(
                "Are you sure you want to cancel this ticket?"
        );

        confirmation.showAndWait().ifPresent(response -> {

            if (response == javafx.scene.control.ButtonType.OK) {

                String sql =
                        "DELETE FROM bookings WHERE pnr = ?";

                try (Connection connection =
                             DatabaseConnection.getConnection();
                     PreparedStatement statement =
                             connection.prepareStatement(sql)) {

                    statement.setString(1, pnr);

                    int rowsDeleted = statement.executeUpdate();

                    if (rowsDeleted > 0) {

                        // Keep the old in-memory store in sync.
                        BookingStore.removeBooking(pnr);

                        showAlert(
                                Alert.AlertType.INFORMATION,
                                "Cancellation Successful",
                                "Your ticket has been cancelled successfully."
                        );

                        clearBooking();

                    } else {

                        clearBooking();

                        showAlert(
                                Alert.AlertType.ERROR,
                                "Booking Not Found",
                                "This booking was not found. It may "
                                        + "already have been cancelled."
                        );
                    }

                } catch (SQLException e) {

                    e.printStackTrace();

                    showAlert(
                            Alert.AlertType.ERROR,
                            "Cancellation Failed",
                            "The ticket could not be cancelled.\n"
                                    + "Please try again.\n\n"
                                    + "Reason: " + e.getMessage()
                    );
                }
            }
        });
    }

    private void clearBooking() {

        pnrField.clear();

        passengerLabel.setText("-");
        trainLabel.setText("-");
        dateLabel.setText("-");
        sourceLabel.setText("-");
        destinationLabel.setText("-");
        classLabel.setText("-");

        bookingDetailsBox.setManaged(false);
        bookingDetailsBox.setVisible(false);

        cancelButton.setDisable(true);
    }

    @FXML
    private void goBack(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("dashboard.fxml")
            );

            Parent root = loader.load();

            Stage stage = (Stage) backButton
                    .getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Online Reservation System");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(
            Alert.AlertType type,
            String title,
            String message) {

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}