package validation;

import constants.other.ImageConstants;
import constants.account.RegisterAccountConstants;
import constants.validation.ValidationConstants;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.StageUtils;
import utils.ValidationUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class RegisterUserAccountValidation implements ValidatableIf
{
    private final Logger LOGGER;

    private final StringProperty firstNameTextProperty;

    private final StringProperty lastNameTextProperty;

    private final StringProperty loginTextProperty;

    private final StringProperty emailTextProperty;

    private final StringProperty phoneNumberTextProperty;

    private final StringProperty passwordTextProperty;

    private final StringProperty confirmPasswordTextProperty;

    private final ObjectProperty< String > countryValueProperty;

    private final ImageView firstNameStatus;

    private final ImageView lastNameStatus;

    private final ImageView loginStatus;

    private final ImageView passwordStatus;

    private final ImageView confirmPasswordStatus;

    private final ImageView countryStatus;

    private final ImageView emailStatus;

    private final ImageView phoneNumberStatus;

    private final HashMap< Observable, Boolean > isRegistrationDataValidMap;


    public RegisterUserAccountValidation( LinkedHashMap< Observable, ImageView > propertyToImageMap,
                                         ArrayList< Observable > properties )
    {
        LOGGER = LogManager.getLogger( RegisterUserAccountValidation.class );

        firstNameTextProperty = (StringProperty) properties.get( 0 );
        firstNameStatus = propertyToImageMap.get( firstNameTextProperty );

        lastNameTextProperty = (StringProperty) properties.get( 1 );
        lastNameStatus = propertyToImageMap.get( lastNameTextProperty );

        loginTextProperty = (StringProperty) properties.get( 2 );
        loginStatus = propertyToImageMap.get( loginTextProperty );

        passwordTextProperty = (StringProperty) properties.get( 3 );
        passwordStatus = propertyToImageMap.get( passwordTextProperty );

        confirmPasswordTextProperty = (StringProperty) properties.get( 4 );
        confirmPasswordStatus = propertyToImageMap.get( confirmPasswordTextProperty );

        countryValueProperty = (ObjectProperty< String >) properties.get( 5 );
        countryStatus = propertyToImageMap.get( countryValueProperty );

        emailTextProperty = (StringProperty) properties.get( 6 );
        emailStatus = propertyToImageMap.get( emailTextProperty );

        phoneNumberTextProperty = (StringProperty) properties.get( 7 );
        phoneNumberStatus = propertyToImageMap.get( phoneNumberTextProperty );

        isRegistrationDataValidMap = createIsRegistrationDataValidMap();
        LOGGER.info( "RegisterUserAccountValidation properties initialized." );
    }


    @Override
    public void activateValidation()
    {
        ValidationUtils.createValidationForNameTypeProperty( firstNameTextProperty, firstNameStatus,
                isRegistrationDataValidMap, RegisterAccountConstants.FIRST_NAME_TYPE );
        ValidationUtils.createValidationForNameTypeProperty( lastNameTextProperty, lastNameStatus,
                isRegistrationDataValidMap, RegisterAccountConstants.LAST_NAME_TYPE );
        ValidationUtils.createValidationForComboBoxTypeProperty( countryValueProperty, countryStatus,
                isRegistrationDataValidMap, ValidationConstants.EMPTY_COUNTRY_MESSAGE,
                RegisterAccountConstants.COUNTRY_PROPERTY_NAME );

        createValidationForPasswordProperties( passwordTextProperty, passwordStatus );
        createValidationForPasswordProperties( confirmPasswordTextProperty, confirmPasswordStatus );
        createValidationForPhoneNumberProperty( phoneNumberTextProperty, phoneNumberStatus );
        createValidationForLoginProperty( loginTextProperty, loginStatus );
        createValidationForEmailProperty( emailTextProperty, emailStatus );
        LOGGER.info( "Activated validations for RegisterUserAccountStage properties." );
    }


    @Override
    public HashMap< Observable, Boolean > createIsRegistrationDataValidMap()
    {
        HashMap< Observable, Boolean > map = new HashMap<>();

        map.put( firstNameTextProperty, false );
        map.put( lastNameTextProperty, false );
        map.put( loginTextProperty, false );
        map.put( passwordTextProperty, false );
        map.put( confirmPasswordTextProperty, false );
        map.put( countryValueProperty, false );
        map.put( phoneNumberTextProperty, false );
        map.put( emailTextProperty, false );
        LOGGER.info( "Created map with results of RegisterUserAccountStage fields' validation." );

        return map;
    }

    private void createValidationForPasswordProperties( StringProperty aProperty, ImageView aStatus )
    {
        StageUtils.setImageTooltip( RegisterAccountConstants.EMPTY_PASSWORD_MESSAGE, aStatus );
        aProperty.addListener( ( observableValue, s, t1 ) ->
        {
            final String propertyValue = aProperty.getValue();
            if( propertyValue.isEmpty() )
            {
                StageUtils.setImage( aStatus, ImageConstants.WRONG );
                StageUtils.setImageTooltip( RegisterAccountConstants.EMPTY_PASSWORD_MESSAGE, aStatus );
                isRegistrationDataValidMap.put( aProperty, false );
            }
            else if ( propertyValue.length() < 8 || propertyValue.length() > 20 )
            {
                StageUtils.setImage( aStatus, ImageConstants.WRONG );
                StageUtils.setImageTooltip( RegisterAccountConstants.WRONG_LENGTH_PASSWORD_MESSAGE, aStatus );
                isRegistrationDataValidMap.put( aProperty, false );
            }
            else if ( !checkWhetherPasswordMeetsCharacterRequirements( propertyValue ) )
            {
                StageUtils.setImage( aStatus, ImageConstants.WRONG );
                StageUtils.setImageTooltip( RegisterAccountConstants.PASSWORD_NOT_MET_CHARACTERS_REQUIREMENTS_MESSAGE, aStatus );
                isRegistrationDataValidMap.put( aProperty, false );
            }
            else
            {
                StageUtils.setImage( aStatus, ImageConstants.CORRECT );
                StageUtils.setTooltipForCorrectInput( aStatus, "Password" );
                isRegistrationDataValidMap.put( aProperty, true );
            }
        } );
        LOGGER.info( "Created listener for validation of password property." );
    }


    public void createValidationForPhoneNumberProperty( StringProperty aProperty, ImageView aStatus )
    {
        StageUtils.setImageTooltip( RegisterAccountConstants.EMPTY_PHONE_NUMBER_MESSAGE, aStatus );
        aProperty.addListener( ( observableValue, s, t1 ) ->
        {
            final String propertyValue = aProperty.getValue();
            if( propertyValue.isEmpty() )
            {
                StageUtils.setImage( aStatus, ImageConstants.WRONG );
                StageUtils.setImageTooltip( RegisterAccountConstants.EMPTY_PHONE_NUMBER_MESSAGE, aStatus );
                isRegistrationDataValidMap.put( aProperty, false );
            }
            else if ( Pattern.compile( RegisterAccountConstants.EVERYTHING_BUT_DIGITS_SPACES_AND_DASHES_PATTERN ).
                    matcher( propertyValue ).find() )
            {
                StageUtils.setImage( aStatus, ImageConstants.WRONG );
                StageUtils.setImageTooltip( RegisterAccountConstants.NON_DIGIT_CHARACTERS_IN_PHONE_NUMBER_MESSAGE, aStatus );
                isRegistrationDataValidMap.put( aProperty, false );
            }
            else if ( !checkWhetherPhoneNumberMatchesAcceptablePatterns( propertyValue ) )
            {
                StageUtils.setImage( aStatus, ImageConstants.WRONG );
                StageUtils.setImageTooltip( RegisterAccountConstants.WRONG_PHONE_NUMBER_PATTERN, aStatus );
                isRegistrationDataValidMap.put( aProperty, false );
            }
            else
            {
                StageUtils.setImage( aStatus, ImageConstants.CORRECT );
                StageUtils.setTooltipForCorrectInput( aStatus, "Phone Number" );
                isRegistrationDataValidMap.put( aProperty, true );
            }

        } );
        LOGGER.info( "Created listener for validation of phone number property." );
    }


    public void createValidationForLoginProperty( StringProperty aProperty, ImageView aStatus )
    {
        StageUtils.setImageTooltip( RegisterAccountConstants.EMPTY_LOGIN_MESSAGE, aStatus );
        aProperty.addListener( ( observableValue, s, t1 ) ->
        {
            final String propertyValue = aProperty.getValue();
            if( propertyValue.isEmpty() )
            {
                StageUtils.setImage( aStatus, ImageConstants.WRONG );
                StageUtils.setImageTooltip( RegisterAccountConstants.EMPTY_LOGIN_MESSAGE, aStatus );
                isRegistrationDataValidMap.put( aProperty, false );
            }
            else if ( propertyValue.length() < 8 || propertyValue.length() > 20 )
            {
                StageUtils.setImage( aStatus, ImageConstants.WRONG );
                StageUtils.setImageTooltip( RegisterAccountConstants.WRONG_LENGTH_LOGIN_MESSAGE, aStatus );
                isRegistrationDataValidMap.put( aProperty, false );
            }
            else if ( Pattern.compile( RegisterAccountConstants.SPECIAL_CHARACTERS_PATTERN ).matcher( propertyValue ).find() )
            {
                StageUtils.setImage( aStatus, ImageConstants.WRONG );
                StageUtils.setImageTooltip( RegisterAccountConstants.SPECIAL_CHARACTERS_FOUND_IN_LOGIN_MESSAGE, aStatus );
                isRegistrationDataValidMap.put( aProperty, false );
            }
            else
            {
                StageUtils.setImage( aStatus, ImageConstants.CORRECT );
                StageUtils.setTooltipForCorrectInput( aStatus, "Login" );
                isRegistrationDataValidMap.put( aProperty, true );
            }
        } );
        LOGGER.info( "Created listener for validation of login property." );
    }


    private void createValidationForEmailProperty( StringProperty aProperty, ImageView aStatus )
    {
        StageUtils.setImageTooltip( RegisterAccountConstants.EMPTY_EMAIL_MESSAGE, aStatus );
        aProperty.addListener( ( observableValue, s, t1 ) ->
        {
            final String propertyValue = aProperty.getValue();
            if( propertyValue.isEmpty() )
            {
                StageUtils.setImage( aStatus, ImageConstants.WRONG );
                StageUtils.setImageTooltip( RegisterAccountConstants.EMPTY_EMAIL_MESSAGE, aStatus );
                isRegistrationDataValidMap.put( aProperty, false );
            }
            else if ( !( propertyValue.contains( "@" ) && propertyValue.contains( "." ) && propertyValue.length() >= 3) )
            {
                StageUtils.setImage( aStatus, ImageConstants.WRONG );
                StageUtils.setImageTooltip( RegisterAccountConstants.NECESSARY_CHARACTERS_IN_EMAIL_NOT_FOUND, aStatus );
                isRegistrationDataValidMap.put( aProperty, false );
            }
            else
            {
                StageUtils.setImage( aStatus, ImageConstants.CORRECT );
                StageUtils.setTooltipForCorrectInput( aStatus, "Email" );
                isRegistrationDataValidMap.put( aProperty, true );
            }

        } );
        LOGGER.info( "Created listener for validation of email property." );
    }


    private boolean checkWhetherPasswordMeetsCharacterRequirements( String aPassword )
    {   // looking for special character.
        if ( Pattern.compile( RegisterAccountConstants.SPECIAL_CHARACTERS_PATTERN ).matcher( aPassword ).find() )
        {   // looking for uppercase letter.
            if ( Pattern.compile( RegisterAccountConstants.UPPERCASE_LETTERS_PATTERN ).matcher( aPassword ).find() )
            {    // looking for lowercase letter.
                if ( Pattern.compile( RegisterAccountConstants.LOWERCASE_LETTERS_PATTERN ).matcher( aPassword ).find() )
                {   // looking for digit.
                    if ( Pattern.compile( RegisterAccountConstants.DIGITS_PATTERN ).matcher( aPassword ).find() )
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    private boolean checkWhetherPhoneNumberMatchesAcceptablePatterns( String aPhoneNumber )
    {
        // looking for pattern: 123456789
        if ( !Pattern.compile( RegisterAccountConstants.PHONE_NUMBER_PATTERN ).matcher( aPhoneNumber ).matches() )
        {   // looking for pattern: 123-456-789
            if ( !Pattern.compile( RegisterAccountConstants.PHONE_NUMBER_PATTERN_WITH_DASHES ).matcher( aPhoneNumber ).matches() )
            {   // looking for pattern: 123 456 789
                if ( !Pattern.compile( RegisterAccountConstants.PHONE_NUMBER_PATTERN_WITH_SPACES ).matcher( aPhoneNumber ).matches() )
                {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public HashMap< Observable, Boolean > getIsRegistrationDataValidMap()
    {
        return isRegistrationDataValidMap;
    }
}
