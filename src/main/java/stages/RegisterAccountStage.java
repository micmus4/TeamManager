package stages;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import utils.StageUtils;

public class RegisterAccountStage
{
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField loginField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private ChoiceBox<String> countryChoiceBox;

    @FXML
    private Label versionLabel;

    public void initialize()
    {
        StageUtils.setProjectVersionLabel( versionLabel );
    }
}
