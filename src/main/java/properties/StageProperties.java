package properties;

import javafx.beans.property.SimpleBooleanProperty;

public class StageProperties
{
    private static final SimpleBooleanProperty isHelpContactStageOnScreen = new SimpleBooleanProperty();

    private static final SimpleBooleanProperty isHelpInformationStageOnScreen = new SimpleBooleanProperty();

    private static final SimpleBooleanProperty isRegisterAccountStageOnScreen = new SimpleBooleanProperty();


    public static SimpleBooleanProperty getIsHelpContactStageOnScreenProperty() {
        return isHelpContactStageOnScreen;
    }


    public static SimpleBooleanProperty getIsHelpInformationStageOnScreenProperty() {
        return isHelpInformationStageOnScreen;
    }


    public static SimpleBooleanProperty getIsRegisterAccountStageOnScreenProperty() {
        return isRegisterAccountStageOnScreen;
    }
}
