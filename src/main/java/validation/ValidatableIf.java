package validation;

import javafx.beans.Observable;

import java.util.HashMap;

/**
 * Interface for *Validation classes that validate user input from given value properties (usually from controls such as
 * TextFields, PasswordFields, ComboBoxes or DatePickers).
 */

public interface ValidatableIf
{
    void activateValidation();

    HashMap<Observable, Boolean > createIsRegistrationDataValidMap();

    HashMap< Observable, Boolean > getIsRegistrationDataValidMap();
}
