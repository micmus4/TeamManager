package utils;

import constants.account.RegisterAccountConstants;
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
     * @param property value property of type StringProperty of JavaFX control that needs to be validated.
     * @param status ImageView instance which shows user on the screen whether validation for given field passed.
     * @param isRegistrationDataValidMap map of type HashMap< Observable, Boolean > which keys are all Stage's properties
     *                                     and values are Boolean values (true - valid property, false - invalid property).
     * @param nameType specifies which name is going to be validated, eg. "First" (for First Name) or "Full" for (Full Name).
     */

    public static void createValidationForNameTypeProperty( StringProperty property, ImageView status,
                                                            HashMap< Observable, Boolean > isRegistrationDataValidMap,
                                                            String nameType )
    {
        StageUtils.setImageTooltip( ( nameType + ValidationConstants.EMPTY_NAME_MESSAGE ), status );
        property.addListener( (observableValue, s, t1 ) ->
        {
            final String propertyValue = property.getValue();
            if( propertyValue.isEmpty() )
            {
                StageUtils.setImage( status, ImageConstants.WRONG );
                StageUtils.setImageTooltip( ( nameType + ValidationConstants.EMPTY_NAME_MESSAGE ), status );
                isRegistrationDataValidMap.put( property, false );
            }
            else
            {
                StageUtils.setImage( status, ImageConstants.CORRECT );
                StageUtils.setTooltipForCorrectInput( status, ( nameType + " Name" ) );
                isRegistrationDataValidMap.put( property, true );
            }
        });
        LOGGER.info( "Created listener for validation of " + nameType + " Name" );
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
     * @param property value property of type ObjectProperty< T > of JavaFX control that needs to be validated.
     * @param status ImageView instance which shows user on the screen whether validation for given field passed.
     * @param isRegistrationDataValidMap map of type HashMap< Observable, Boolean > which keys are all Stage's properties
     *                                  and values are Boolean values (true - valid property, false - invalid property).
     * @param emptyMessage message which is displayed when validation isn't passed due to value not being chosen.
     * @param propertyName given property's name, used in Logging to console and setting message after successfull validation.
     */

    public static < T > void createValidationForComboBoxTypeProperty( ObjectProperty< T > property, ImageView status,
                                                                   HashMap< Observable, Boolean > isRegistrationDataValidMap,
                                                                   String emptyMessage, String propertyName )
    {
        StageUtils.setImageTooltip( emptyMessage, status );
        property.addListener( ( observableValue, s, t1 ) ->
        {
            final T propertyValue = property.getValue();
            if( propertyValue == null )
            {
                StageUtils.setImage( status, ImageConstants.WRONG );
                StageUtils.setImageTooltip( emptyMessage, status );
                isRegistrationDataValidMap.put( property, false );
            }
            else
            {
                StageUtils.setImage( status, ImageConstants.CORRECT );
                StageUtils.setTooltipForCorrectInput( status, propertyName );
                isRegistrationDataValidMap.put( property, true );
            }
        } );
        LOGGER.info( "Created listener for validation of " + propertyName + " property." );
    }


    public static void createValidationForDatePickerTypeProperty( DatePicker datePicker, ImageView status,
                                                                 HashMap< Observable, Boolean > isRegistrationDataValidMap,
                                                                 String emptyMessage, String propertyName,
                                                                 LocalDate disallowDatesBefore, LocalDate disallowDatesAfter )
    {
        datePicker.setDayCellFactory( arg -> new DateCell() {
            @Override
            public void updateItem( LocalDate date, boolean empty )
            {
                super.updateItem( date, empty );
                setDisable( empty || date.compareTo( disallowDatesAfter ) > 0 || date.compareTo( disallowDatesBefore ) < 0 );
            }
        });

        StageUtils.setImageTooltip( emptyMessage, status );
        ObjectProperty< LocalDate > property = datePicker.valueProperty();
        property.addListener( ( observableValue, s, t1 ) ->
        {
            final LocalDate propertyValue = property.getValue();
            if( propertyValue == null )
            {
                StageUtils.setImage( status, ImageConstants.WRONG );
                StageUtils.setImageTooltip( emptyMessage, status );
                isRegistrationDataValidMap.put( property, false );
            }
            else
            {
                StageUtils.setImage( status, ImageConstants.CORRECT );
                StageUtils.setTooltipForCorrectInput( status, propertyName );
                isRegistrationDataValidMap.put( property, true );
            }
        } );
        LOGGER.info( "Created listener for validation of " + propertyName + " property." );
    }

}
