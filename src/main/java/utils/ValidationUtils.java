package utils;

import constants.other.ImageConstants;
import constants.validation.ValidationConstants;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.HashMap;

public class ValidationUtils
{
    private static final Logger LOGGER = LogManager.getLogger( ValidationUtils.class );


    /**
     * Creates listener for given property. This listener servers as a validation for that property.
     * In this case, it passes validation when the property's value is not empty. When it's empty - the validation isn't
     * passed.
     *
     * If validation is passed, we will see a green circle next to the validated property and its tooltip
     * will say that given input is acceptable. It will also set value to 'true' for this property in
     * 'isRegistrationDataValidMap' which has keys for all validation properties of a Stage and values which are Booleans
     * that indicate whether property is valid or not (true - valid, false - invalid).
     *
     * If validation is not passed, we will see a red circle next to the validated property and its tooltip will say
     * that validation did not pass because user didn't provide an input for that property. It will also set value for this property
     * to false in 'isRegistrationDataValidMap'.
     *
     * In most cases, it should be applied to TextField's value properties that we don't want to be empty.
     *
     * @param aProperty value property of type StringProperty of JavaFX control that needs to be validated.
     * @param aStatus ImageView instance which shows user on the screen whether validation for given field passed.
     * @param aIsRegistrationDataValidMap map of type HashMap< Observable, Boolean > which keys are all Stage's properties
     *                                     and values are Boolean values (true - valid property, false - invalid property).
     * @param aNameType specifies which name is going to be validated, eg. "First" (for First Name) or "Full" for (Full Name).
     */

    public static void createValidationForNameTypeProperty( StringProperty aProperty, ImageView aStatus,
                                                            HashMap< Observable, Boolean > aIsRegistrationDataValidMap,
                                                            String aNameType )
    {
        StageUtils.setImageTooltip( ( aNameType + ValidationConstants.EMPTY_NAME_MESSAGE ), aStatus );
        aProperty.addListener( (observableValue, s, t1 ) ->
        {
            final String propertyValue = aProperty.getValue();
            if( propertyValue.isEmpty() )
            {
                StageUtils.setImage( aStatus, ImageConstants.WRONG );
                StageUtils.setImageTooltip( ( aNameType + ValidationConstants.EMPTY_NAME_MESSAGE ), aStatus );
                aIsRegistrationDataValidMap.put( aProperty, false );
            }
            else
            {
                StageUtils.setImage( aStatus, ImageConstants.CORRECT );
                StageUtils.setTooltipForCorrectInput( aStatus, ( aNameType + " Name" ) );
                aIsRegistrationDataValidMap.put( aProperty, true );
            }
        });
        LOGGER.info( "Created listener for validation of " + aNameType + " Name" );
    }


    /**
     * Creates listener for given property. This listener servers as a validation for that property.
     * In this case, it passes validation when the property's value was chosen from ComboBox.
     * If it wasn't chosen, validation isn't passed.
     *
     * If validation is passed, we will see a green circle next to the validated property and its tooltip
     * will say that given input is acceptable. It will also set value to 'true' for this property in
     * 'isRegistrationDataValidMap' which has keys for all validation properties of a Stage and values which are Booleans
     * that indicate whether property is valid or not (true - valid, false - invalid).
     *
     * If validation is not passed, we will see a red circle next to the validated property and its tooltip will say
     * that validation did not pass because user didn't choose value for that property. It will also set value for this property
     * to false in 'isRegistrationDataValidMap'.
     *
     * @param aProperty value property of type ObjectProperty< T > of JavaFX control that needs to be validated.
     * @param aStatus ImageView instance which shows user on the screen whether validation for given field passed.
     * @param aIsRegistrationDataValidMap map of type HashMap< Observable, Boolean > which keys are all Stage's properties
     *                                  and values are Boolean values (true - valid property, false - invalid property).
     * @param aEmptyMessage message which is displayed when validation isn't passed due to value not being chosen.
     * @param aPropertyName given property's name, used in Logging to console and setting message after successful validation.
     */

    public static < T > void createValidationForComboBoxTypeProperty( ObjectProperty< T > aProperty, ImageView aStatus,
                                                                   HashMap< Observable, Boolean > aIsRegistrationDataValidMap,
                                                                   String aEmptyMessage, String aPropertyName )
    {
        StageUtils.setImageTooltip( aEmptyMessage, aStatus );
        aProperty.addListener( ( observableValue, s, t1 ) ->
        {
            final T propertyValue = aProperty.getValue();
            if( propertyValue == null )
            {
                StageUtils.setImage( aStatus, ImageConstants.WRONG );
                StageUtils.setImageTooltip( aEmptyMessage, aStatus );
                aIsRegistrationDataValidMap.put( aProperty, false );
            }
            else
            {
                StageUtils.setImage( aStatus, ImageConstants.CORRECT );
                StageUtils.setTooltipForCorrectInput( aStatus, aPropertyName );
                aIsRegistrationDataValidMap.put( aProperty, true );
            }
        } );
        LOGGER.info( "Created listener for validation of " + aPropertyName + " property." );
    }


    public static void createValidationForDatePickerTypeProperty( DatePicker aDatePicker, ImageView aStatus,
                                                                 HashMap< Observable, Boolean > aIsRegistrationDataValidMap,
                                                                 String aEmptyMessage, String aPropertyName,
                                                                 LocalDate aDisallowDatesBefore, LocalDate aDisallowDatesAfter )
    {
        aDatePicker.setDayCellFactory( arg -> new DateCell() {
            @Override
            public void updateItem( LocalDate date, boolean empty )
            {
                super.updateItem( date, empty );
                setDisable( empty || date.compareTo( aDisallowDatesAfter ) > 0 || date.compareTo( aDisallowDatesBefore ) < 0 );
            }
        });

        StageUtils.setImageTooltip( aEmptyMessage, aStatus );
        ObjectProperty< LocalDate > property = aDatePicker.valueProperty();
        property.addListener( ( observableValue, s, t1 ) ->
        {
            final LocalDate propertyValue = property.getValue();
            if( propertyValue == null )
            {
                StageUtils.setImage( aStatus, ImageConstants.WRONG );
                StageUtils.setImageTooltip( aEmptyMessage, aStatus );
                aIsRegistrationDataValidMap.put( property, false );
            }
            else
            {
                StageUtils.setImage( aStatus, ImageConstants.CORRECT );
                StageUtils.setTooltipForCorrectInput( aStatus, aPropertyName );
                aIsRegistrationDataValidMap.put( property, true );
            }
        } );
        LOGGER.info( "Created listener for validation of " + aPropertyName + " property." );
    }

}
