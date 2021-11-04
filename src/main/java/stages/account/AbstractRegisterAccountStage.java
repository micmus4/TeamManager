package stages.account;

import data.AccountManager;
import javafx.beans.Observable;
import javafx.scene.image.ImageView;

import java.util.LinkedHashMap;

public abstract class AbstractRegisterAccountStage implements PropertizableIf
{
    protected AccountManager accountManager = AccountManager.getAccountManagerInstance();

    public LinkedHashMap< Observable, ImageView > createMapForValidation()
    {
        return new LinkedHashMap<>();
    }

    public void abandonRegistrationProcess()
    {
        // empty method body
    }

    @Override
    public void setPropertiesForControls()
    {
        // empty method body
    }
}
