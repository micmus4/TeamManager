package stages.account;

import account.CompleteAccount;
import constants.help.HelpContactConstants;
import constants.help.HelpInformationConstants;
import constants.account.RegisterAccountConstants;
import constants.id.BeanIdConstants;
import data.AccountManager;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import properties.StageProperties;
import spring.ObjectFactory;
import utils.AlertUtils;
import utils.StageUtils;

import java.io.IOException;
import java.net.URL;


public class SignInStage implements PropertizableIf
{

    private static final Logger LOGGER = LogManager.getLogger( SignInStage.class );

    private static Stage signInStage;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    private StringProperty loginTextProperty;

    private StringProperty passwordTextProperty;

    @FXML
    private Label versionLabel;

    private SimpleBooleanProperty isHelpContactStageOnScreen;

    private SimpleBooleanProperty isHelpInformationStageOnScreen;

    private SimpleBooleanProperty isRegisterAccountStageOnScreen;

    private final ObjectFactory objectFactory = ObjectFactory.getFactory();

    private final AccountManager accountManager = (AccountManager)
            objectFactory.getBean( BeanIdConstants.ACCOUNT_MANAGER_SINGLETON );

    public SignInStage(){}


    public void initialize()
    {
        LOGGER.info( "Initializing SignInStage properties" );
        setPropertiesForControls();
        isHelpContactStageOnScreen = StageProperties.getIsHelpContactStageOnScreenProperty();
        isRegisterAccountStageOnScreen = StageProperties.getIsRegisterAccountStageOnScreenProperty();
        isHelpInformationStageOnScreen = StageProperties.getIsHelpInformationStageOnScreenProperty();

        StageUtils.setProjectVersionLabel( versionLabel );
        isRegisterAccountStageOnScreen.setValue( false );
        isHelpInformationStageOnScreen.setValue( false );
        isHelpContactStageOnScreen.setValue( false );
    }


    public static void setSignInStage( Stage stage )
    {
        signInStage = stage;
    }


    private Stage createHelpContactStage()
    {
        Parent root;
        URL urlToFXML;
        URL urlToCSS;

        try
        {
            urlToFXML = getClass().getResource( HelpContactConstants.HELP_CONTACT_STAGE_RESOURCES_FXML );
            urlToCSS = getClass().getResource( HelpContactConstants.HELP_CONTACT_STAGE_RESOURCES_CSS );
            root = FXMLLoader.load( urlToFXML );
            LOGGER.info( "HelpContactStage loaded" );
        }
        catch ( IOException | NullPointerException e )
        {
            LOGGER.error( "HelpContactStage failed to show on screen." );
            AlertUtils.popUpErrorAlert( e );
            return null;
        }

        LOGGER.info( "Creating HelpContactStage." );
        return StageUtils.createStage( "Contact", false, root, urlToCSS,
                HelpContactConstants.HELP_CONTACT_STAGE_WIDTH,
                HelpContactConstants.HELP_CONTACT_STAGE_HEIGHT );
    }


    private Stage createHelpInformationStage()
    {
        Parent root;
        URL urlToFXML;
        URL urlToCSS;

        try
        {
            urlToFXML = getClass().getResource( HelpInformationConstants.HELP_INFORMATION_STAGE_RESOURCES_FXML );
            urlToCSS = getClass().getResource(  HelpInformationConstants.HELP_INFORMATION_STAGE_RESOURCES_CSS );
            root = FXMLLoader.load( urlToFXML );
            LOGGER.info( "HelpInformationStage loaded." );
        }
        catch ( IOException | NullPointerException e )
        {
            LOGGER.error( e.getClass().getSimpleName() + " thrown while initializing HelpInformationStage." );
            AlertUtils.popUpErrorAlert( e );
            return null;
        }

        LOGGER.info( "Creating HelpInformationStage." );
        return StageUtils.createStage( "Information", false, root, urlToCSS,
                HelpInformationConstants.HELP_INFORMATION_STAGE_WIDTH,
                HelpInformationConstants.HELP_INFORMATION_STAGE_HEIGHT );
    }


    private Stage createRegisterUserAccountStage()
    {
        Parent root;
        URL urlToFXML;
        URL urlToCSS;

        try
        {
            urlToFXML = getClass().getResource( RegisterAccountConstants.REGISTER_USER_ACCOUNT_STAGE_RESOURCES_FXML );
            urlToCSS = getClass().getResource( RegisterAccountConstants.REGISTER_USER_ACCOUNT_STAGE_RESOURCES_CSS );
            root = FXMLLoader.load( urlToFXML );
            LOGGER.info( "RegisterUserAccountStage loaded." );
        }
        catch ( IOException | NullPointerException e )
        {
            LOGGER.error( e.getClass().getSimpleName() + " thrown while initializing RegisterUserAccountStage." );
            AlertUtils.popUpErrorAlert( e );
            return null;
        }

        LOGGER.info( "Creating RegisterUserAccountStage." );
        return StageUtils.createStage( "Register user account", false, root, urlToCSS,
                RegisterAccountConstants.REGISTER_USER_ACCOUNT_STAGE_WIDTH,
                RegisterAccountConstants.REGISTER_USER_ACCOUNT_STAGE_HEIGHT );
    }


    @FXML
    private void popUpHelpContactStageOnScreen()
    {
        if ( !isHelpContactStageOnScreen.get() )
        {
            Stage helpContactStage = createHelpContactStage();
            boolean isHelpContactStageShown = StageUtils.showStageAndCheckIfItsShown( helpContactStage, signInStage,
                                                    Modality.NONE, isHelpContactStageOnScreen, true );
            if ( isHelpContactStageShown )
            {
                LOGGER.info( "HelpContactStage shown on the screen." );
                isHelpContactStageOnScreen.set( true );
            }
        }
    }


    @FXML
    private void popUpHelpInformationStageOnScreen()
    {
        if ( !isHelpInformationStageOnScreen.get() )
        {
            Stage helpInformationStage = createHelpInformationStage();
            boolean isHelpInformationStageShown = StageUtils.showStageAndCheckIfItsShown( helpInformationStage, signInStage,
                                                        Modality.NONE, isHelpInformationStageOnScreen, true );
            if ( isHelpInformationStageShown )
            {
                LOGGER.info( "HelpInformationStage shown on the screen." );
                isHelpInformationStageOnScreen.set( true );
            }
        }
    }


    @FXML
    private void popUpRegisterAccountStageOnScreen(){
        if ( !isRegisterAccountStageOnScreen.get() )
        {
            Stage registerAccountStage = createRegisterUserAccountStage();
            registerAccountStage.setUserData( getSignInStage() );
            boolean isRegisterAccountStageShown = StageUtils.showStageAndCheckIfItsShown( registerAccountStage, signInStage,
                                                        Modality.WINDOW_MODAL, isRegisterAccountStageOnScreen, true );
            if ( isRegisterAccountStageShown )
            {
                LOGGER.info( "RegisterAccountStage shown on the screen." );
                isRegisterAccountStageOnScreen.set( true );
            }
        }
    }


    private Stage getSignInStage()
    {
        return (Stage) versionLabel.getScene().getWindow();
    }


    @Override
    public void setPropertiesForControls()
    {
        loginTextProperty = loginField.textProperty();
        passwordTextProperty = passwordField.textProperty();
    }


    @FXML
    public void logIn()
    {
        String login = loginTextProperty.getValue();
        String password = passwordTextProperty.getValue();
        CompleteAccount account = accountManager.logIn( login, password );
        if( account != null )
        {
            // open main application
        }
    }
}
