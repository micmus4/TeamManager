package stages.account;

import constants.id.BeanIdConstants;
import data.AccountManager;
import javafx.beans.Observable;
import javafx.scene.image.ImageView;
import spring.ObjectFactory;

import java.util.LinkedHashMap;

public abstract class AbstractRegisterAccountStage implements PropertizableIf
{
    private ObjectFactory objectFactory = ObjectFactory.getFactory();

    protected AccountManager accountManager = (AccountManager)
            objectFactory.getBean( BeanIdConstants.ACCOUNT_MANAGER_SINGLETON );

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
