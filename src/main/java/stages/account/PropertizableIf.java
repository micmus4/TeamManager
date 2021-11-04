package stages.account;

/**
 * Interface for classes that posses JavaFX controls that user would like to control value of.
 *
 * Example of when it should be used:
 *  - there is a class that has a stage with TextFields, PasswordFields, ComboBoxes and etc. In this case, you for sure
 *    would need to get values of those controls.
 *
 * Example of when it should NOT be used:
 *  - there is a class that has Buttons and Labels that we don't need to have any value of.
 */

public interface PropertizableIf
{
    void setPropertiesForControls();
}
