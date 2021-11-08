package validation;

import constants.account.RegisterAccountConstants;
import constants.other.DateConstants;
import constants.other.ImageConstants;
import constants.validation.ValidationConstants;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.StageUtils;
import utils.ValidationUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class RegisterFootballClubValidation implements ValidatableIf
{
    private final Logger LOGGER;
    
    private final StringProperty fullNameTextProperty;

    private final StringProperty shortNameTextProperty;

    private final StringProperty stadiumNameTextProperty;

    private final StringProperty stadiumCapacityTextProperty;

    private final ObjectProperty< String > countryValueProperty;

    private final ObjectProperty< String > leagueValueProperty;

    private final ObjectProperty<LocalDate> dateOfCreationValueProperty;
    
    private final ImageView fullNameStatus;
    
    private final ImageView shortNameStatus;

    private final ImageView dateOfCreationStatus;
    
    private final ImageView leagueStatus;
    
    private final ImageView stadiumNameStatus;
    
    private final ImageView stadiumCapacityStatus;

    private final ImageView countryStatus;

    private final HashMap< Observable, Boolean > isRegistrationDataValidMap;

    private final DatePicker dateOfCreationDataPicker;


    public RegisterFootballClubValidation( LinkedHashMap< Observable, ImageView > aPropertyToImageMap,
                                           ArrayList< Observable > aProperties, DatePicker aDateOfCreationDataPicker )
    {
        LOGGER = LogManager.getLogger( RegisterFootballClubValidation.class );

        fullNameTextProperty = (StringProperty) aProperties.get( 0 );
        fullNameStatus = aPropertyToImageMap.get( fullNameTextProperty );

        shortNameTextProperty = (StringProperty) aProperties.get( 1 );
        shortNameStatus = aPropertyToImageMap.get( shortNameTextProperty );

        stadiumNameTextProperty = (StringProperty) aProperties.get( 2 );
        stadiumNameStatus = aPropertyToImageMap.get( stadiumNameTextProperty );

        stadiumCapacityTextProperty = (StringProperty) aProperties.get( 3 );
        stadiumCapacityStatus = aPropertyToImageMap.get( stadiumCapacityTextProperty );

        countryValueProperty = (ObjectProperty<String>) aProperties.get( 4 );
        countryStatus = aPropertyToImageMap.get( countryValueProperty );

        leagueValueProperty = (ObjectProperty< String >) aProperties.get( 5 );
        leagueStatus = aPropertyToImageMap.get( leagueValueProperty );

        dateOfCreationValueProperty = (ObjectProperty< LocalDate >) aProperties.get( 6 );
        dateOfCreationStatus = aPropertyToImageMap.get( dateOfCreationValueProperty );

        this.dateOfCreationDataPicker = aDateOfCreationDataPicker;

        isRegistrationDataValidMap = createIsRegistrationDataValidMap();
        LOGGER.info( "RegisterFootballClubValidation properties initialized." );
    }

    @Override
    public void activateValidation() 
    {
        ValidationUtils.createValidationForNameTypeProperty( fullNameTextProperty, fullNameStatus,
                isRegistrationDataValidMap, RegisterAccountConstants.FULL_NAME_TYPE );
        ValidationUtils.createValidationForNameTypeProperty( shortNameTextProperty, shortNameStatus,
                isRegistrationDataValidMap, RegisterAccountConstants.SHORT_NAME_TYPE );
        ValidationUtils.createValidationForNameTypeProperty( stadiumNameTextProperty, stadiumNameStatus,
                isRegistrationDataValidMap, RegisterAccountConstants.STADIUM_NAME_TYPE );
        ValidationUtils.createValidationForComboBoxTypeProperty( countryValueProperty, countryStatus,
                isRegistrationDataValidMap, ValidationConstants.EMPTY_COUNTRY_MESSAGE,
                RegisterAccountConstants.COUNTRY_PROPERTY_NAME );
        ValidationUtils.createValidationForComboBoxTypeProperty( leagueValueProperty, leagueStatus,
                isRegistrationDataValidMap, ValidationConstants.EMPTY_LEAGUE_MESSAGE,
                RegisterAccountConstants.LEAGUE_PROPERTY_NAME );
        ValidationUtils.createValidationForDatePickerTypeProperty( dateOfCreationDataPicker, dateOfCreationStatus,
                isRegistrationDataValidMap, ValidationConstants.EMPTY_DATE_OF_CREATION_MESSAGE,
                RegisterAccountConstants.DATE_OF_CREATION_PROPERTY_NAME,
                DateConstants.DATE_OF_FIRST_FOOTBALL_CLUB_CREATION, DateConstants.CURRENT_DATE );

        createValidationForStadiumCapacity( stadiumCapacityTextProperty, stadiumCapacityStatus );

        LOGGER.info( "Activated validations for RegisterFootballClubStage properties." );
    }

    @Override
    public HashMap< Observable, Boolean > createIsRegistrationDataValidMap()
    {
        HashMap< Observable, Boolean > map = new HashMap<>();

        map.put( fullNameTextProperty, false );
        map.put( shortNameTextProperty, false );
        map.put( stadiumNameTextProperty, false );
        map.put( stadiumCapacityTextProperty, false );
        map.put( countryValueProperty, false );
        map.put( leagueValueProperty, false );
        map.put( dateOfCreationValueProperty, false );
        LOGGER.info( "Created map with results of RegisterFootballClubStage fields' validations." );

        return map;
    }


    private void createValidationForStadiumCapacity( StringProperty aProperty, ImageView aStatus )
    {
        StageUtils.setImageTooltip( RegisterAccountConstants.EMPTY_STADIUM_CAPACITY_MESSAGE, aStatus );
        aProperty.addListener( ( observableValue, s, t1 ) ->
        {
            final String propertyValue = aProperty.getValue();
            if( propertyValue.isEmpty() )
            {
                StageUtils.setImage( aStatus, ImageConstants.WRONG );
                StageUtils.setImageTooltip( RegisterAccountConstants.EMPTY_STADIUM_CAPACITY_MESSAGE, aStatus );
                isRegistrationDataValidMap.put( aProperty, false );
            }
            else if ( Pattern.compile( RegisterAccountConstants.NON_DIGITS_PATTERN ).matcher( propertyValue ).find() )
            {
                StageUtils.setImage( aStatus, ImageConstants.WRONG );
                StageUtils.setImageTooltip( RegisterAccountConstants.NON_DIGITAL_CHARACTERS_IN_CAPACITY_MESSAGE, aStatus );
                isRegistrationDataValidMap.put( aProperty, false );
            }
            else if ( propertyValue.startsWith( "0" ) )
            {
                StageUtils.setImage( aStatus, ImageConstants.WRONG );
                StageUtils.setImageTooltip( RegisterAccountConstants.ZERO_AT_THE_BEGINNING_OF_CAPACITY_MESSAGE, aStatus );
                isRegistrationDataValidMap.put( aProperty, false );
            }
            else
            {
                StageUtils.setImage( aStatus, ImageConstants.CORRECT );
                StageUtils.setTooltipForCorrectInput( aStatus, "Stadium Capacity" );
                isRegistrationDataValidMap.put( aProperty, true );
            }
        } );
        LOGGER.info( "Created listener for validation of Stadium Capacity property." );
    }

    @Override
    public HashMap< Observable, Boolean > getIsRegistrationDataValidMap()
    {
        return isRegistrationDataValidMap;
    }

}
