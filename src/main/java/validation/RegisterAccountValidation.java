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
import java.util.LinkedHashMap;
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


    private void createValidationForNameProperties( StringProperty property, ImageView status, String whichName )
    {
        StageUtils.setImageTooltip( ( whichName + RegisterAccountConstants.EMPTY_NAME_MESSAGE ), status );
        property.addListener( (observableValue, s, t1 ) ->
        {
            final String propertyValue = property.getValue();
            if( propertyValue.isEmpty() )
            {
                StageUtils.setImage( status, ImageConstants.WRONG_RESOURCES );
                StageUtils.setImageTooltip( ( whichName + RegisterAccountConstants.EMPTY_NAME_MESSAGE ), status );
            }
            else
            {
                StageUtils.setImage( status, ImageConstants.CORRECT_RESOURCES );
                StageUtils.setTooltipForCorrectInput( status, ( whichName + " Name" ) );
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
                StageUtils.setImage( status, ImageConstants.WRONG_RESOURCES );
                StageUtils.setImageTooltip( RegisterAccountConstants.EMPTY_PASSWORD_MESSAGE, status );
            }
            else if ( propertyValue.length() < 8 || propertyValue.length() > 20 )
            {
                StageUtils.setImage( status, ImageConstants.WRONG_RESOURCES );
                StageUtils.setImageTooltip( RegisterAccountConstants.WRONG_LENGTH_PASSWORD_MESSAGE, status );
            }
            else if ( !checkWhetherPasswordMeetsCharacterRequirements( propertyValue ) )
            {
                StageUtils.setImage( status, ImageConstants.WRONG_RESOURCES );
                StageUtils.setImageTooltip( RegisterAccountConstants.PASSWORD_NOT_MET_CHARACTERS_REQUIREMENTS_MESSAGE, status );
            }
            else
            {
                StageUtils.setImage( status, ImageConstants.CORRECT_RESOURCES );
                StageUtils.setTooltipForCorrectInput( status, "Password" );
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
                StageUtils.setImage( status, ImageConstants.WRONG_RESOURCES );
                StageUtils.setImageTooltip( RegisterAccountConstants.EMPTY_COUNTRY_MESSAGE, status );
            }
            else
            {
                StageUtils.setImage( status, ImageConstants.CORRECT_RESOURCES );
                StageUtils.setTooltipForCorrectInput( status, "Country" );
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
                StageUtils.setImage( status, ImageConstants.WRONG_RESOURCES );
                StageUtils.setImageTooltip( RegisterAccountConstants.EMPTY_PHONE_NUMBER_MESSAGE, status );
            }
            else if ( Pattern.compile( "[^0-9\\-\\s]" ).matcher( propertyValue ).find() )
            {
                StageUtils.setImage( status, ImageConstants.WRONG_RESOURCES );
                StageUtils.setImageTooltip( RegisterAccountConstants.NON_DIGIT_CHARACTERS_IN_PHONE_NUMBER_MESSAGE, status );
            }
            else if ( !checkWhetherPhoneNumberMatchesAcceptablePatterns( propertyValue ) )
            {
                StageUtils.setImage( status, ImageConstants.WRONG_RESOURCES );
                StageUtils.setImageTooltip( RegisterAccountConstants.WRONG_PHONE_NUMBER_PATTERN, status );
            }
            else
            {
                StageUtils.setImage( status, ImageConstants.CORRECT_RESOURCES );
                StageUtils.setTooltipForCorrectInput( status, "Phone Number" );
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
                StageUtils.setImage( status, ImageConstants.WRONG_RESOURCES );
                StageUtils.setImageTooltip( RegisterAccountConstants.EMPTY_LOGIN_MESSAGE, status );
            }
            else if ( propertyValue.length() < 8 || propertyValue.length() > 20 )
            {
                StageUtils.setImage( status, ImageConstants.WRONG_RESOURCES );
                StageUtils.setImageTooltip( RegisterAccountConstants.WRONG_LENGTH_LOGIN_MESSAGE, status );
            }
            else if ( Pattern.compile( "[^A-Za-z0-9]" ).matcher( propertyValue ).find() )
            {
                StageUtils.setImage( status, ImageConstants.WRONG_RESOURCES );
                StageUtils.setImageTooltip( RegisterAccountConstants.SPECIAL_CHARACTERS_FOUND_IN_LOGIN_MESSAGE, status );
            }
            else
            {
                StageUtils.setImage( status, ImageConstants.CORRECT_RESOURCES );
                StageUtils.setTooltipForCorrectInput( status, "Login" );
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
                StageUtils.setImage( status, ImageConstants.WRONG_RESOURCES );
                StageUtils.setImageTooltip( RegisterAccountConstants.EMPTY_EMAIL_MESSAGE, status );
            }
            else if ( !( propertyValue.contains( "@" ) && propertyValue.contains( "." ) ) )
            {
                StageUtils.setImage( status, ImageConstants.WRONG_RESOURCES );
                StageUtils.setImageTooltip( RegisterAccountConstants.NECESSARY_CHARACTERS_IN_EMAIL_NOT_FOUND, status );
            }
            else
            {
                StageUtils.setImage( status, ImageConstants.CORRECT_RESOURCES );
                StageUtils.setTooltipForCorrectInput( status, "Email" );
            }

        } );
        LOGGER.info( "Created listener for validation of email property." );
    }


    private boolean checkWhetherPasswordMeetsCharacterRequirements( String password )
    {
        if ( Pattern.compile( "[^A-Za-z0-9]" ).matcher( password ).find() ) // looking for special character.
        {
            if ( Pattern.compile( "[^A-Z]" ).matcher( password ).find() ) // looking for uppercase letter.
            {
                if ( Pattern.compile( "[^a-z]" ).matcher( password ).find() ) // looking for lowercase letter.
                {
                    if ( Pattern.compile( "[^0-9]" ).matcher( password ).find() ) // looking for digit.
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
        if ( !Pattern.compile( "^\\d{9}$" ).matcher( phoneNumber ).matches() ) // pattern: 123456789
        {
            if ( !Pattern.compile( "^\\d{3}-\\d{3}-\\d{3}$" ).matcher( phoneNumber ).matches() ) // pattern: 123-456-789
            {
                if ( !Pattern.compile( "^\\d{3} \\d{3} \\d{3}$" ).matcher( phoneNumber ).matches() ) // pattern: 123 456 789
                {
                    return false;
                }
            }
        }
        return true;
    }
}
