import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button eyeButton;

    @FXML
    private CheckBox rememberCheckBox;


    // LOGIN

    @FXML
    private void login(ActionEvent event) {

        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        //It Check empty fields
        if (username.isEmpty() || password.isEmpty()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Login Failed",
                    "Please enter username and password."
            );

            return;
        }

        //It Check login credentials
        if (username.equals("admin") && password.equals("1234")) {

            openDashboard(event);

        } else {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Access Denied",
                    "Invalid username or password."
            );
        }
    }


    // OPEN DASHBOARD 

    private void openDashboard(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("dashboard.fxml")
            );

            Parent root = loader.load();

            Stage stage = (Stage)
                    ((Button) event.getSource())
                            .getScene()
                            .getWindow();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("Online Reservation System");

        } catch (Exception e) {

            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Dashboard Error",
                    e.getClass().getSimpleName()
                            + "\n\n"
                            + e.getMessage()
            );
        }
    }


    //  PASSWORD BUTTON 

    @FXML
    private void togglePassword() {

        showAlert(
                Alert.AlertType.INFORMATION,
                "Password",
                "Password visibility feature will be added later."
        );
    }


    // ALERT 

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

