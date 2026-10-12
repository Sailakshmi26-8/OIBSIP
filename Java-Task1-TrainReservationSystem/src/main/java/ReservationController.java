
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ReservationController {

    @FXML
    private TextField passengerNameField;

    @FXML
    private ScrollPane reservationScrollPane;

    @FXML
    private ImageView backgroundImageView;

    @FXML
    private StackPane reservationPageStackPane;

    @FXML
    private TextField trainNumberField;

    @FXML
    private TextField trainNameField;

    @FXML
    private ComboBox<String> classComboBox;

    @FXML
    private DatePicker journeyDatePicker;

    @FXML
    private TextField sourceField;

    @FXML
    private TextField destinationField;

    @FXML
    private Button bookTicketButton;

    @FXML
    private Button backButton;

    private final Map<String, String> trains = new HashMap<>();

    @FXML
    public void initialize() {

        backgroundImageView.fitWidthProperty().bind(
                reservationPageStackPane.widthProperty()
        );

        backgroundImageView.fitHeightProperty().bind(
                reservationPageStackPane.heightProperty()
        );

        // Populate the travel class options.
        classComboBox.setItems(
                FXCollections.observableArrayList(
                        "AC 1 Tier",
                        "AC 2 Tier",
                        "AC 3 Tier",
                        "Sleeper",
                        "General"
                )
        );

        // Available trains.
        trains.put("12760", "Charminar Express");
        trains.put("12711", "Pinakini Express");
        trains.put("12603", "Chennai Central - Hyderabad Express");
        trains.put("12861", "Visakhapatnam - Mumbai Express");
        trains.put("12077", "Jan Shatabdi Express");

        //It Automatically populate the train name.
        trainNumberField.textProperty().addListener(
                (observable, oldValue, newValue) -> {

                    String trainNumber = newValue.trim();

                    if (trains.containsKey(trainNumber)) {
                        trainNameField.setText(
                                trains.get(trainNumber)
                        );
                    } else {
                        trainNameField.clear();
                    }
                }
        );
    }

    @FXML
    private void bookTicket(ActionEvent event) {

        String passengerName =
                passengerNameField.getText().trim();

        String trainNumber =
                trainNumberField.getText().trim();

        String trainName =
                trainNameField.getText().trim();

        String source =
                sourceField.getText().trim();

        String destination =
                destinationField.getText().trim();

        // 1. Check the train number first.
        if (trainNumber.isEmpty()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Missing Details",
                    "Please enter a train number."
            );
            return;
        }

        // 2. Reject non-numeric train numbers.
        if (!trainNumber.matches("\\d+")) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Train Number",
                    "Please enter a numeric train number."
            );
            return;
        }

        // 3. Check whether the train number exists.
        if (!trains.containsKey(trainNumber)) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Train Not Found",
                    "No train found for train number "
                            + trainNumber + "."
            );
            return;
        }

        // 4. Check all other required fields.
        if (passengerName.isEmpty()
                || trainName.isEmpty()
                || classComboBox.getValue() == null
                || journeyDatePicker.getValue() == null
                || source.isEmpty()
                || destination.isEmpty()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Missing Details",
                    "Please fill in all the other required fields."
            );
            return;
        }

        // 5. Source and destination must be different.
        if (source.equalsIgnoreCase(destination)) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Journey",
                    "Source and destination cannot be the same."
            );
            return;
        }

        // Generate the PNR.
        String pnr = generatePNR();

        Booking booking = new Booking(
                pnr,
                passengerName,
                trainNumber,
                trainName,
                classComboBox.getValue(),
                journeyDatePicker.getValue().toString(),
                source,
                destination
        );

        // Save the booking to MySQL.
        if (saveBookingToDatabase(booking)) {

            BookingStore.addBooking(booking);

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Booking Successful",
                    "Your ticket has been booked successfully!\n\n"
                            + "PNR Number: " + pnr + "\n"
                            + "Passenger: " + passengerName + "\n"
                            + "Train: " + trainNumber
                            + " - " + trainName + "\n"
                            + "Class: " + classComboBox.getValue() + "\n"
                            + "Date: " + journeyDatePicker.getValue() + "\n"
                            + "From: " + source + "\n"
                            + "To: " + destination
            );

            clearFields();
        }
    }

    private boolean saveBookingToDatabase(Booking booking) {

        String sql = "INSERT INTO bookings "
                + "(pnr, passenger_name, train_number, train_name, "
                + "travel_class, journey_date, source, destination) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, booking.getPnr());
            statement.setString(2, booking.getPassengerName());
            statement.setString(3, booking.getTrainNumber());
            statement.setString(4, booking.getTrainName());
            statement.setString(5, booking.getTravelClass());

            statement.setDate(
                    6,
                    java.sql.Date.valueOf(booking.getJourneyDate())
            );

            statement.setString(7, booking.getSource());
            statement.setString(8, booking.getDestination());

            statement.executeUpdate();
            return true;

        } catch (SQLException e) {

            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Booking Failed",
                    "The booking could not be saved to the database.\n"
                            + "Please try again.\n\n"
                            + "Reason: " + e.getMessage()
            );

            return false;
        }
    }

    private String generatePNR() {

        Random random = new Random();

        long pnr = 1000000000L
                + (long) (random.nextDouble() * 9000000000L);

        return String.valueOf(pnr);
    }

    @FXML
    private void goBack(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/dashboard.fxml")
            );

            Parent root = loader.load();

            Stage stage = (Stage)
                    ((javafx.scene.Node) event.getSource())
                            .getScene()
                            .getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Online Reservation System");

        } catch (Exception e) {

            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Navigation Error");
            alert.setHeaderText("Could not open Dashboard");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void clearFields() {

        passengerNameField.clear();
        trainNumberField.clear();
        trainNameField.clear();

        classComboBox.getSelectionModel().clearSelection();
        journeyDatePicker.setValue(null);

        sourceField.clear();
        destinationField.clear();
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
