package stages.account;

import account.AccountFactory;
import account.CompleteAccount;
import account.FootballClubAccount;
import account.UserAccount;
import constants.id.BeanIdConstants;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import properties.StageProperties;
import spring.ObjectFactory;
import utils.AlertUtils;
import utils.StageUtils;
import validation.RegisterFootballClubValidation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class RegisterFootballClubStage extends AbstractRegisterAccountStage
{

    private final Logger LOGGER = LogManager.getLogger( RegisterFootballClubStage.class );

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField shortNameField;

    @FXML
    private DatePicker dateOfCreationDataPicker;

    @FXML
    private ComboBox< String > leagueComboBox;

    @FXML
    private TextField stadiumNameField;

    @FXML
    private TextField stadiumCapacityField;

    @FXML
    private ComboBox< String > countryComboBox;
    
    private StringProperty fullNameTextProperty;
    
    private StringProperty shortNameTextProperty;
    
    private StringProperty stadiumNameTextProperty;
    
    private StringProperty stadiumCapacityTextProperty;
    
    private ObjectProperty< String > countryValueProperty;
    
    private ObjectProperty< String > leagueValueProperty;
    
    private ObjectProperty< LocalDate > dateOfCreationValueProperty;

    @FXML
    private ImageView fullNameStatus;

    @FXML
    private ImageView shortNameStatus;

    @FXML
    private ImageView dateOfCreationStatus;

    @FXML
    private ImageView leagueStatus;

    @FXML
    private ImageView stadiumNameStatus;

    @FXML
    private ImageView stadiumCapacityStatus;

    @FXML
    private ImageView countryStatus;

    @FXML
    private Label versionLabel;

    private RegisterFootballClubValidation registerFootballClubValidation;

    private final AccountFactory accountFactory;

    private final ObjectFactory objectFactory = ObjectFactory.getFactory();

    public RegisterFootballClubStage()
    {
        accountFactory = (AccountFactory) objectFactory.getBean( BeanIdConstants.ACCOUNT_FACTORY_SINGLETON );
    }

    public void initialize()
    {
        setPropertiesForControls();
        StageUtils.loadCountriesToComboBox( countryComboBox );
        StageUtils.loadLeaguesToComboBox( leagueComboBox );

        LinkedHashMap< Observable, ImageView > propertyToImageMap = createMapForValidation();
        ArrayList< Observable > properties = new ArrayList<>( propertyToImageMap.keySet() );

        registerFootballClubValidation
                = new RegisterFootballClubValidation( propertyToImageMap, properties, dateOfCreationDataPicker );
        registerFootballClubValidation.activateValidation();
    }

    @Override
    public void setPropertiesForControls()
    {
        fullNameTextProperty = fullNameField.textProperty();
        shortNameTextProperty = shortNameField.textProperty();
        stadiumNameTextProperty = stadiumNameField.textProperty();
        stadiumCapacityTextProperty = stadiumCapacityField.textProperty();
        countryValueProperty = countryComboBox.valueProperty();
        leagueValueProperty = leagueComboBox.valueProperty();
        dateOfCreationValueProperty = dateOfCreationDataPicker.valueProperty();
    }

    @Override
    public LinkedHashMap< Observable, ImageView > createMapForValidation()
    {
        LinkedHashMap< Observable, ImageView > map = new LinkedHashMap<>();

        map.put( fullNameTextProperty, fullNameStatus );
        map.put( shortNameTextProperty, shortNameStatus );
        map.put( stadiumNameTextProperty, stadiumNameStatus );
        map.put( stadiumCapacityTextProperty, stadiumCapacityStatus );
        map.put( countryValueProperty, countryStatus );
        map.put( leagueValueProperty, leagueStatus );
        map.put( dateOfCreationValueProperty, dateOfCreationStatus );

        return map;
    }

    @Override
    @FXML
    public void abandonRegistrationProcess()
    {
        Stage registerFootballClubAccountStage = getStage();
        SimpleBooleanProperty isRegisterAccountStageOnScreenProperty = StageProperties.getIsRegisterAccountStageOnScreenProperty();
        StageUtils.closeStage( registerFootballClubAccountStage, isRegisterAccountStageOnScreenProperty );
    }


    public Stage getStage()
    {
        return (Stage) fullNameField.getScene().getWindow();
    }


    @FXML
    private void continueRegistrationProcess()
    {
        HashMap< Observable, Boolean > isRegistrationDataValidMap = registerFootballClubValidation.getIsRegistrationDataValidMap();
        boolean allDataIsValid = !isRegistrationDataValidMap.containsValue( false );
        if( !allDataIsValid )
        {
            LOGGER.warn( "Registration of Football Club Account stopped because of invalid data passed." );
            AlertUtils.popUpInfoAlert( "Information", "Registration failed!", "Couldn't create Football Club Account" +
                    " because of invalid data. Make sure all data you've provided is mark as valid and try again." );
        }
        else
        {
            createCompleteAccount();
        }
    }


    private void createCompleteAccount()
    {
        String fullNameOfNewlyCreatedFootballClubAccount = fullNameTextProperty.getValue();
        LOGGER.info( "Football Account with login " + fullNameOfNewlyCreatedFootballClubAccount + " created successfully." );

        FootballClubAccount registeredFootballClubAccount =
                accountFactory.createFootballClubAccount( fullNameTextProperty, shortNameTextProperty,
                stadiumNameTextProperty, stadiumCapacityTextProperty, countryValueProperty, leagueValueProperty,
                dateOfCreationValueProperty );
        UserAccount registeredUserAccount = (UserAccount) getStage().getUserData();

        abandonRegistrationProcess();

        CompleteAccount registeredCompleteAccount = accountFactory.
                createCompleteAccount( registeredUserAccount, registeredFootballClubAccount );

        accountManager.addAccount( registeredCompleteAccount );
        accountManager.writeAccountsToFile();

        AlertUtils.popUpInfoAlert( "Account created", "Account successfully created",
                "You can now log in into your account." );
    }



}
