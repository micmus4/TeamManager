package stages.account;

import constants.help.HelpContactConstants;
import constants.help.HelpInformationConstants;
import constants.account.RegisterAccountConstants;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.AlertUtils;
import utils.StageUtils;

import java.io.IOException;
import java.net.URL;


public class SignInStage
{

    private static final Logger LOGGER = LogManager.getLogger( SignInStage.class );

    private static Stage signInStage;

    @FXML
    private Label versionLabel;

    private final SimpleBooleanProperty isHelpContactStageOnScreen = new SimpleBooleanProperty();

    private final SimpleBooleanProperty isHelpInformationStageOnScreen = new SimpleBooleanProperty();

    private final SimpleBooleanProperty isRegisterAccountStageOnScreen = new SimpleBooleanProperty();

    public SignInStage(){}


    public void initialize()
    {
        LOGGER.info( "Initializing SignInStage properties" );
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

    private Stage createRegisterAccountStage()
    {
        Parent root;
        URL urlToFXML;
        URL urlToCSS;

        try
        {
            urlToFXML = getClass().getResource( RegisterAccountConstants.REGISTER_ACCOUNT_STAGE_RESOURCES_FXML );
            urlToCSS = getClass().getResource( RegisterAccountConstants.REGISTER_ACCOUNT_STAGE_RESOURCES_CSS );
            root = FXMLLoader.load( urlToFXML );
            LOGGER.info( "RegisterAccountStage loaded." );
        }
        catch ( IOException | NullPointerException e )
        {
            LOGGER.error( e.getClass().getSimpleName() + " thrown while initializing RegisterAccountStage." );
            AlertUtils.popUpErrorAlert( e );
            return null;
        }

        LOGGER.info( "Creating RegisterAccountStage." );
        return StageUtils.createStage( "Register account", false, root, urlToCSS,
                RegisterAccountConstants.REGISTER_ACCOUNT_STAGE_WIDTH,
                RegisterAccountConstants.REGISTER_ACCOUNT_STAGE_HEIGHT );
    }

    @FXML
    private void popUpHelpContactStageOnScreen()
    {
        if ( !isHelpContactStageOnScreen.get() )
        {
            Stage helpContactStage = createHelpContactStage();
            boolean isHelpContactStageShown = StageUtils.isStageShown( helpContactStage, signInStage,
                                                    Modality.NONE, isHelpContactStageOnScreen );
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
            boolean isHelpInformationStageShown = StageUtils.isStageShown( helpInformationStage, signInStage,
                                                        Modality.NONE, isHelpInformationStageOnScreen );
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
            Stage registerAccountStage = createRegisterAccountStage();
            boolean isRegisterAccountStageShown = StageUtils.isStageShown( registerAccountStage, signInStage,
                                                        Modality.WINDOW_MODAL, isRegisterAccountStageOnScreen );
            if ( isRegisterAccountStageShown )
            {
                LOGGER.info( "RegisterAccountStage shown on the screen." );
                isRegisterAccountStageOnScreen.set( true );
            }
        }
    }
}
