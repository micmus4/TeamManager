package stages.account;

import constants.ProjectConstants;
import constants.account.RegisterAccountConstants;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import utils.AlertUtils;
import utils.StageUtils;
import validation.RegisterAccountValidation;

import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class RegisterAccountStage
{

    private static final Logger LOGGER = LogManager.getLogger( RegisterAccountStage.class );

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


    public void initialize()
    {
        LOGGER.info( "Initializing RegisterAccountStage properties" );
        StageUtils.setProjectVersionLabel( versionLabel );
        setPropertiesForControls();
        loadCountriesToChoiceBox();
        setTooltips();

        LinkedHashMap<Observable, ImageView> propertyToImageMap = createMapForValidation();
        ArrayList<Observable> properties = new ArrayList<>( propertyToImageMap.keySet() );

        LOGGER.info( "Creating RegisterAccountValidation instance to validate signing up." );
        RegisterAccountValidation registerValidation = new RegisterAccountValidation( propertyToImageMap, properties );
        registerValidation.activateValidationForRegistrationProperties();
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
        countryValueProperty = countryComboBox.valueProperty();
    }


    private void loadCountriesToChoiceBox()
    {
        LOGGER.info( "Parsing countries data from JSON." );
        JSONParser parser = new JSONParser();
        URL urlToJSONFile = getClass().getResource( ProjectConstants.COUNTRIES_RESOURCES_JSON );

        try
        {
            if ( urlToJSONFile == null )
            {
                LOGGER.warn( "No JSON file found." );
                throw new NullPointerException();
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( e.getClass().getSimpleName() + ", couldn't parse JSON" );
            AlertUtils.popUpErrorAlert( e );
            return;
        }


        ArrayList<String> countriesArrayList = new ArrayList<>();
        try( FileReader reader = new FileReader( urlToJSONFile.getPath() ) )
        {
            LOGGER.info( "Reading from JSON." );
            Object parsedJson = parser.parse( reader );
            JSONArray jsonDataArray = (JSONArray) parsedJson;

            jsonDataArray.forEach( country -> {
                String name = (String) ((JSONObject) country).get( "name" );
                countriesArrayList.add( name );
            } );
        }
        catch ( Exception e )
        {
            LOGGER.error( e.getClass().getSimpleName() + " while parsing JSON." );
            AlertUtils.popUpErrorAlert( e );
            return;
        }

        LOGGER.info( "Setting countries for ComboBox" );
        countryComboBox.setItems( FXCollections.observableList( countriesArrayList ) );
    }


    private LinkedHashMap< Observable, ImageView > createMapForValidation()
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
        StageUtils.setImageTooltip( RegisterAccountConstants.REGISTER_ACCOUNT_STAGE_LOGIN_TOOLTIP_MESSAGE, loginTooltipImage );
        StageUtils.setImageTooltip( RegisterAccountConstants.REGISTER_ACCOUNT_STAGE_PASSWORD_TOOLTIP_MESSAGE, passwordTooltipImage );
        StageUtils.setImageTooltip( RegisterAccountConstants.REGISTER_ACCOUNT_STAGE_EMAIL_TOOLTIP_MESSAGE, emailTooltipImage );
        LOGGER.info( "Setting tooltips" );
    }

}
