package stages.account;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
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
    private ChoiceBox< String > countryChoiceBox;

    @FXML
    private Label versionLabel;

    private StringProperty firstNameTextProperty;

    private StringProperty lastNameTextProperty;

    private StringProperty loginTextProperty;

    private StringProperty emailTextProperty;

    private StringProperty phoneNumberTextProperty;

    private StringProperty passwordTextProperty;

    private StringProperty confirmPasswordTextProperty;

    private ObjectProperty< String > countryValueProperty;


    public void initialize()
    {
        StageUtils.setProjectVersionLabel( versionLabel );
        setPropertiesForControls();
    }

    private void setPropertiesForControls()
    {
        firstNameTextProperty = firstNameField.textProperty();
        lastNameTextProperty = lastNameField.textProperty();
        loginTextProperty = loginField.textProperty();
        emailTextProperty = emailField.textProperty();
        phoneNumberTextProperty = phoneNumberField.textProperty();
        passwordTextProperty = passwordField.textProperty();
        confirmPasswordTextProperty = confirmPasswordField.textProperty();
        countryValueProperty = countryChoiceBox.valueProperty();
    }

}
