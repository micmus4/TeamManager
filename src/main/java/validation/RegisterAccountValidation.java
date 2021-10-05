package validation;

import constants.ImageConstants;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import stages.help.HelpInformationStage;
import utils.StageUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;

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
        createValidationForNameProperties( firstNameTextProperty, firstNameStatus );
        createValidationForNameProperties( lastNameTextProperty, lastNameStatus );
        LOGGER.info( "Activated validations for registration properties." );
    }


    private void createValidationForNameProperties( StringProperty property, ImageView status )
    {
        property.addListener( (observableValue, s, t1 ) -> {
            if( property.getValue().isEmpty() || property.getValue() == null )
            {
                StageUtils.setImage( status, ImageConstants.WRONG_RESOURCES );

            }
            else
            {
                StageUtils.setImage( status, ImageConstants.CORRECT_RESOURCES );
            }
        } );
        LOGGER.info( "Created listener for validation of " + property.getName() );
    }


}
