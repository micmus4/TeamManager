package stages.account;

import account.AccountFactory;
import account.UserAccount;
import constants.account.RegisterAccountConstants;
import data.AccountManager;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import properties.StageProperties;
import utils.AlertUtils;
import utils.StageUtils;
import validation.RegisterUserAccountValidation;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class RegisterUserAccountStage extends AbstractRegisterAccountStage
{

    private static final Logger LOGGER = LogManager.getLogger( RegisterUserAccountStage.class );

    private static final AccountFactory ACCOUNT_FACTORY = AccountFactory.getAccountFactoryInstance();

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
    private ComboBox< String > countryComboBox;

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

    @FXML
    private ImageView firstNameStatus;

    @FXML
    private ImageView lastNameStatus;

    @FXML
    private ImageView loginStatus;

    @FXML
    private ImageView passwordStatus;

    @FXML
    private ImageView confirmPasswordStatus;

    @FXML
    private ImageView countryStatus;

    @FXML
    private ImageView emailStatus;

    @FXML
    private ImageView phoneNumberStatus;

    @FXML
    private ImageView loginTooltipImage;

    @FXML
    private ImageView passwordTooltipImage;

    @FXML
    private ImageView emailTooltipImage;

    private RegisterUserAccountValidation registerUserAccountValidation;


    public void initialize()
    {
        LOGGER.info( "Initializing RegisterAccountStage properties" );
        StageUtils.setProjectVersionLabel( versionLabel );
        setPropertiesForControls();
        StageUtils.loadCountriesToComboBox( countryComboBox );
        setTooltips();

        LinkedHashMap< Observable, ImageView > propertyToImageMap = createMapForValidation();
        ArrayList< Observable > properties = new ArrayList<>( propertyToImageMap.keySet() );

        LOGGER.info( "Creating RegisterAccountValidation instance to validate signing up." );
        registerUserAccountValidation = new RegisterUserAccountValidation( propertyToImageMap, properties );
        registerUserAccountValidation.activateValidation();
    }

    @Override
    public void setPropertiesForControls()
    {
        firstNameTextProperty = firstNameField.textProperty();
        lastNameTextProperty = lastNameField.textProperty();
        loginTextProperty = loginField.textProperty();
        emailTextProperty = emailField.textProperty();
        phoneNumberTextProperty = phoneNumberField.textProperty();
        passwordTextProperty = passwordField.textProperty();
        confirmPasswordTextProperty = confirmPasswordField.textProperty();
        countryValueProperty = countryComboBox.valueProperty();
    }


    @Override
    public LinkedHashMap< Observable, ImageView > createMapForValidation()
    {
        LinkedHashMap< Observable, ImageView > map = new LinkedHashMap<>();

        map.put( firstNameTextProperty, firstNameStatus );
        map.put( lastNameTextProperty, lastNameStatus );
        map.put( loginTextProperty, loginStatus );
        map.put( passwordTextProperty, passwordStatus );
        map.put( confirmPasswordTextProperty, confirmPasswordStatus );
        map.put( countryValueProperty, countryStatus );
        map.put( emailTextProperty, emailStatus );
        map.put( phoneNumberTextProperty, phoneNumberStatus );

        return map;
    }


    private void setTooltips()
    {
        StageUtils.setImageTooltip( RegisterAccountConstants.REGISTER_USER_ACCOUNT_STAGE_LOGIN_TOOLTIP_MESSAGE, loginTooltipImage );
        StageUtils.setImageTooltip( RegisterAccountConstants.REGISTER_USER_ACCOUNT_STAGE_PASSWORD_TOOLTIP_MESSAGE, passwordTooltipImage );
        StageUtils.setImageTooltip( RegisterAccountConstants.REGISTER_USER_ACCOUNT_STAGE_EMAIL_TOOLTIP_MESSAGE, emailTooltipImage );
        LOGGER.info( "Setting tooltips" );
    }

    @Override
    @FXML
    public void abandonRegistrationProcess()
    {
        Stage registerUserAccountStage = (Stage) firstNameField.getScene().getWindow();
        SimpleBooleanProperty isRegisterAccountStageOnScreenProperty = StageProperties.getIsRegisterAccountStageOnScreenProperty();
        StageUtils.closeStage( registerUserAccountStage, isRegisterAccountStageOnScreenProperty );
    }

    @FXML
    private void continueRegistrationProcess()
    {
        HashMap< Observable, Boolean > isRegistrationDataValidMap = registerUserAccountValidation.getIsRegistrationDataValidMap();
        boolean allDataIsValid = !isRegistrationDataValidMap.containsValue( false );
        if( !allDataIsValid )
        {
            LOGGER.warn( "Registration of User Account stopped because of invalid data passed." );
            AlertUtils.popUpInfoAlert( "Information", "Registration failed!", "Couldn't create User Account" +
                    " because of invalid data. Make sure all data you've provided is mark as valid and try again." );
        }
        else
        {
            registerFootballClubAccount();
        }
    }


    private Stage createRegisterFootballClubAccountStage()
    {
        Parent root;
        URL urlToFXML;
        URL urlToCSS;

        try
        {
            urlToFXML = getClass().getResource( RegisterAccountConstants.REGISTER_FOOTBALL_CLUB_ACCOUNT_STAGE_RESOURCES_FXML );
            urlToCSS = getClass().getResource( RegisterAccountConstants.REGISTER_FOOTBALL_CLUB_ACCOUNT_STAGE_RESOURCES_CSS );
            root = FXMLLoader.load( urlToFXML );
            LOGGER.info( "RegisterFootballClubAccountStage loaded." );
        }
        catch ( IOException | NullPointerException e )
        {
            LOGGER.error( e.getClass().getSimpleName() + " thrown while initializing RegisterFootballClubAccountStage." );
            AlertUtils.popUpErrorAlert( e );
            return null;
        }

        LOGGER.info( "Creating RegisterFootballClubAccountStage." );
        return StageUtils.createStage( "Register football club account", false, root, urlToCSS,
                RegisterAccountConstants.REGISTER_FOOTBALL_CLUB_ACCOUNT_STAGE_WIDTH,
                RegisterAccountConstants.REGISTER_FOOTBALL_CLUB_ACCOUNT_STAGE_HEIGHT );
    }


    private Stage getRegisterUserAccountStage()
    {
        return (Stage) firstNameField.getScene().getWindow();
    }


    private void registerFootballClubAccount()
    {
        if( !verifyRegistrationData() ) return;

        String loginOfNewlyCreatedUserAccount = loginTextProperty.getValue();
        UserAccount registeredUserAccount = ACCOUNT_FACTORY.createUserAccount( firstNameTextProperty, lastNameTextProperty,
                loginTextProperty, passwordTextProperty, emailTextProperty, phoneNumberTextProperty, countryValueProperty );
        LOGGER.info( "User Account with login " + loginOfNewlyCreatedUserAccount + " created successfully." );

        Stage registerFootballClubAccountStage = createRegisterFootballClubAccountStage();
        registerFootballClubAccountStage.setUserData( registeredUserAccount );

        StageUtils.showStageAndCheckIfItsShown( registerFootballClubAccountStage, getRegisterUserAccountStage(),
                true, false, Modality.APPLICATION_MODAL, StageProperties.getIsRegisterAccountStageOnScreenProperty() );
    }


    private boolean verifyRegistrationData()
    {
        if( !passwordTextProperty.getValue().equals( confirmPasswordTextProperty.getValue() ) )
        {
            AlertUtils.popUpInfoAlert( "Error while registration",
                    "Can't proceed further",  "Password confirmation failed, please try again" );
            return false;
        }

        return !accountManager.lookForTheSameLoginAndEmailInDatabase( loginTextProperty.getValue(), emailTextProperty.getValue() );

    }

}
