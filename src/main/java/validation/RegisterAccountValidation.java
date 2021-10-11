package validation;

import constants.ImageConstants;
import constants.account.RegisterAccountConstants;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.StageUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RegisterAccountValidation
{
    private static final Logger LOGGER = LogManager.getLogger( RegisterAccountValidation.class );


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

    private HashMap< Observable, Boolean > isRegistrationDataValidMap;


    public RegisterAccountValidation( LinkedHashMap< Observable, ImageView > propertyToImageMap,
                                      ArrayList< Observable > properties  )
    {

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

        countryValueProperty = (ObjectProperty<String>) properties.get( 5 );
        countryStatus = propertyToImageMap.get( countryValueProperty );

        emailTextProperty = (StringProperty) properties.get( 6 );
        emailStatus = propertyToImageMap.get( emailTextProperty );

        phoneNumberTextProperty = (StringProperty) properties.get( 7 );
        phoneNumberStatus = propertyToImageMap.get( phoneNumberTextProperty );

        isRegistrationDataValidMap = createIsRegistrationDataValidMap();
        LOGGER.info( "RegisterAccountValidation properties initialized." );

    }


    public void activateValidationForRegistrationProperties()
    {
        createValidationForNameProperties( firstNameTextProperty, firstNameStatus, "First" );
        createValidationForNameProperties( lastNameTextProperty, lastNameStatus, "Last" );
        createValidationForPasswordProperties( passwordTextProperty, passwordStatus );
        createValidationForPasswordProperties( confirmPasswordTextProperty, confirmPasswordStatus );
        createValidationForCountryProperty( countryValueProperty, countryStatus );
        createValidationForPhoneNumberProperty( phoneNumberTextProperty, phoneNumberStatus );
        createValidationForLoginProperty( loginTextProperty, loginStatus );
        createValidationForEmailProperty( emailTextProperty, emailStatus );
        LOGGER.info( "Activated validations for registration properties." );
    }

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
        LOGGER.info( "Created map with results of RegisterAccountStage fields' validation." );

        return map;
    }


    private void createValidationForNameProperties( StringProperty property, ImageView status, String whichName )
    {
        StageUtils.setImageTooltip( ( whichName + RegisterAccountConstants.EMPTY_NAME_MESSAGE ), status );
        
        property.addListener( (observableValue, s, t1 ) ->
        {
            final String propertyValue = property.getValue();
            if( propertyValue.isEmpty() )
            {
                StageUtils.setImage( status, ImageConstants.WRONG );
                StageUtils.setImageTooltip( ( whichName + RegisterAccountConstants.EMPTY_NAME_MESSAGE ), status );
                isRegistrationDataValidMap.put( property, false );
            }
            else
            {
                StageUtils.setImage( status, ImageConstants.CORRECT );
                StageUtils.setTooltipForCorrectInput( status, ( whichName + " Name" ) );
                isRegistrationDataValidMap.put( property, true );
            }
        });
        LOGGER.info( "Created listener for validation of " + whichName + " Name" );
    }


    private void createValidationForPasswordProperties( StringProperty property, ImageView status )
    {
        StageUtils.setImageTooltip( RegisterAccountConstants.EMPTY_PASSWORD_MESSAGE, status );
        
        property.addListener( ( observableValue, s, t1 ) ->
        {
            final String propertyValue = property.getValue();
            if( propertyValue.isEmpty() )
            {
                StageUtils.setImage( status, ImageConstants.WRONG );
                StageUtils.setImageTooltip( RegisterAccountConstants.EMPTY_PASSWORD_MESSAGE, status );
                isRegistrationDataValidMap.put( property, false );
            }
            else if ( propertyValue.length() < 8 || propertyValue.length() > 20 )
            {
                StageUtils.setImage( status, ImageConstants.WRONG );
                StageUtils.setImageTooltip( RegisterAccountConstants.WRONG_LENGTH_PASSWORD_MESSAGE, status );
                isRegistrationDataValidMap.put( property, false );
            }
            else if ( !checkWhetherPasswordMeetsCharacterRequirements( propertyValue ) )
            {
                StageUtils.setImage( status, ImageConstants.WRONG );
                StageUtils.setImageTooltip( RegisterAccountConstants.PASSWORD_NOT_MET_CHARACTERS_REQUIREMENTS_MESSAGE, status );
                isRegistrationDataValidMap.put( property, false );
            }
            else
            {
                StageUtils.setImage( status, ImageConstants.CORRECT );
                StageUtils.setTooltipForCorrectInput( status, "Password" );
                isRegistrationDataValidMap.put( property, true );
            }
        } );
        LOGGER.info( "Created listener for validation of password property." );
    }


    private void createValidationForCountryProperty( ObjectProperty< String > property, ImageView status )
    {
        StageUtils.setImageTooltip( RegisterAccountConstants.EMPTY_COUNTRY_MESSAGE, status );
        
        property.addListener( ( observableValue, s, t1 ) ->
        {
            final String propertyValue = property.getValue();
            if( propertyValue == null )
            {
                StageUtils.setImage( status, ImageConstants.WRONG );
                StageUtils.setImageTooltip( RegisterAccountConstants.EMPTY_COUNTRY_MESSAGE, status );
                isRegistrationDataValidMap.put( property, false );
            }
            else
            {
                StageUtils.setImage( status, ImageConstants.CORRECT );
                StageUtils.setTooltipForCorrectInput( status, "Country" );
                isRegistrationDataValidMap.put( property, true );
            }
        } );
        LOGGER.info( "Created listener for validation of country property." );
    }


    public void createValidationForPhoneNumberProperty( StringProperty property, ImageView status )
    {
        StageUtils.setImageTooltip( RegisterAccountConstants.EMPTY_PHONE_NUMBER_MESSAGE, status );
        
        property.addListener( ( observableValue, s, t1 ) ->
        {
            final String propertyValue = property.getValue();
            if( propertyValue.isEmpty() )
            {
                StageUtils.setImage( status, ImageConstants.WRONG );
                StageUtils.setImageTooltip( RegisterAccountConstants.EMPTY_PHONE_NUMBER_MESSAGE, status );
                isRegistrationDataValidMap.put( property, false );
            }
            else if ( Pattern.compile( RegisterAccountConstants.EVERYTHING_BUT_DIGITS_SPACES_AND_DASHES_PATTERN ).
                    matcher( propertyValue ).find() )
            {
                StageUtils.setImage( status, ImageConstants.WRONG );
                StageUtils.setImageTooltip( RegisterAccountConstants.NON_DIGIT_CHARACTERS_IN_PHONE_NUMBER_MESSAGE, status );
                isRegistrationDataValidMap.put( property, false );
            }
            else if ( !checkWhetherPhoneNumberMatchesAcceptablePatterns( propertyValue ) )
            {
                StageUtils.setImage( status, ImageConstants.WRONG );
                StageUtils.setImageTooltip( RegisterAccountConstants.WRONG_PHONE_NUMBER_PATTERN, status );
                isRegistrationDataValidMap.put( property, false );
            }
            else
            {
                StageUtils.setImage( status, ImageConstants.CORRECT );
                StageUtils.setTooltipForCorrectInput( status, "Phone Number" );
                isRegistrationDataValidMap.put( property, true );
            }

        } );
        LOGGER.info( "Created listener for validation of phone number property." );
    }


    public void createValidationForLoginProperty( StringProperty property, ImageView status )
    {
        
        StageUtils.setImageTooltip( RegisterAccountConstants.EMPTY_LOGIN_MESSAGE, status );
        property.addListener( ( observableValue, s, t1 ) ->
        {
            final String propertyValue = property.getValue();
            if( propertyValue.isEmpty() )
            {
                StageUtils.setImage( status, ImageConstants.WRONG );
                StageUtils.setImageTooltip( RegisterAccountConstants.EMPTY_LOGIN_MESSAGE, status );
                isRegistrationDataValidMap.put( property, false );
            }
            else if ( propertyValue.length() < 8 || propertyValue.length() > 20 )
            {
                StageUtils.setImage( status, ImageConstants.WRONG );
                StageUtils.setImageTooltip( RegisterAccountConstants.WRONG_LENGTH_LOGIN_MESSAGE, status );
                isRegistrationDataValidMap.put( property, false );
            }
            else if ( Pattern.compile( RegisterAccountConstants.SPECIAL_CHARACTERS_PATTERN ).matcher( propertyValue ).find() )
            {
                StageUtils.setImage( status, ImageConstants.WRONG );
                StageUtils.setImageTooltip( RegisterAccountConstants.SPECIAL_CHARACTERS_FOUND_IN_LOGIN_MESSAGE, status );
                isRegistrationDataValidMap.put( property, false );
            }
            else
            {
                StageUtils.setImage( status, ImageConstants.CORRECT );
                StageUtils.setTooltipForCorrectInput( status, "Login" );
                isRegistrationDataValidMap.put( property, true );
            }
        } );
        LOGGER.info( "Created listener for validation of login property." );
    }


    private void createValidationForEmailProperty( StringProperty property, ImageView status )
    {
        StageUtils.setImageTooltip( RegisterAccountConstants.EMPTY_EMAIL_MESSAGE, status );
        property.addListener( ( observableValue, s, t1 ) ->
        {
            final String propertyValue = property.getValue();
            if( propertyValue.isEmpty() )
            {
                StageUtils.setImage( status, ImageConstants.WRONG );
                StageUtils.setImageTooltip( RegisterAccountConstants.EMPTY_EMAIL_MESSAGE, status );
                isRegistrationDataValidMap.put( property, false );
            }
            else if ( !( propertyValue.contains( "@" ) && propertyValue.contains( "." ) ) )
            {
                StageUtils.setImage( status, ImageConstants.WRONG );
                StageUtils.setImageTooltip( RegisterAccountConstants.NECESSARY_CHARACTERS_IN_EMAIL_NOT_FOUND, status );
                isRegistrationDataValidMap.put( property, false );
            }
            else
            {
                StageUtils.setImage( status, ImageConstants.CORRECT );
                StageUtils.setTooltipForCorrectInput( status, "Email" );
                isRegistrationDataValidMap.put( property, true );
            }

        } );
        LOGGER.info( "Created listener for validation of email property." );
    }


    private boolean checkWhetherPasswordMeetsCharacterRequirements( String password )
    {   // looking for special character.
        if ( Pattern.compile( RegisterAccountConstants.SPECIAL_CHARACTERS_PATTERN ).matcher( password ).find() )
        {   // looking for uppercase letter.
            if ( Pattern.compile( RegisterAccountConstants.UPPERCASE_LETTERS_PATTERN ).matcher( password ).find() )
            {    // looking for lowercase letter.
                if ( Pattern.compile( RegisterAccountConstants.LOWERCASE_LETTERS_PATTERN ).matcher( password ).find() )
                {   // looking for digit.
                    if ( Pattern.compile( RegisterAccountConstants.DIGITS_PATTERN ).matcher( password ).find() )
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    private boolean checkWhetherPhoneNumberMatchesAcceptablePatterns( String phoneNumber )
    {
        // looking for pattern: 123456789
        if ( !Pattern.compile( RegisterAccountConstants.PHONE_NUMBER_PATTERN ).matcher( phoneNumber ).matches() )
        {   // looking for pattern: 123-456-789
            if ( !Pattern.compile( RegisterAccountConstants.PHONE_NUMBER_PATTERN_WITH_DASHES ).matcher( phoneNumber ).matches() )
            {   // looking for pattern: 123 456 789
                if ( !Pattern.compile( RegisterAccountConstants.PHONE_NUMBER_PATTERN_WITH_SPACES ).matcher( phoneNumber ).matches() )
                {
                    return false;
                }
            }
        }
        return true;
    }

    public HashMap<Observable, Boolean> getIsRegistrationDataValidMap() {
        return isRegistrationDataValidMap;
    }
}
