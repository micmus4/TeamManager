package stages;

import constants.HelpContactStageConstants;
import constants.HelpInformationStageConstants;
import constants.RegisterAccountStageConstants;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import utils.AlertUtils;
import utils.StageUtils;

import java.io.IOException;
import java.net.URL;


public class SignInStage
{

    private static Stage signInStage;

    @FXML
    private Label versionLabel;

    private Boolean isHelpContactStageOnScreen = false;

    private Boolean isHelpInformationStageOnScreen = false;

    private Boolean isRegisterAccountStageOnScreen = false;

    public SignInStage(){}


    public void initialize()
    {
        StageUtils.setProjectVersionLabel( versionLabel );
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
            urlToFXML = getClass().getResource( HelpContactStageConstants.HELP_CONTACT_STAGE_RESOURCES_FXML );
            urlToCSS = getClass().getResource( HelpContactStageConstants.HELP_CONTACT_STAGE_RESOURCES_CSS );
            root = FXMLLoader.load( urlToFXML );
        }
        catch ( IOException | NullPointerException e )
        {
            AlertUtils.popUpErrorAlert( e );
            return null;
        }

        return StageUtils.createStage( "Contact", false, root, urlToCSS,
                HelpContactStageConstants.HELP_CONTACT_STAGE_WIDTH,
                HelpContactStageConstants.HELP_CONTACT_STAGE_HEIGHT );
    }


    private Stage createHelpInformationStage()
    {
        Parent root;
        URL urlToFXML;
        URL urlToCSS;

        try
        {
            urlToFXML = getClass().getResource( HelpInformationStageConstants.HELP_INFORMATION_STAGE_RESOURCES_FXML );
            urlToCSS = getClass().getResource(  HelpInformationStageConstants.HELP_INFORMATION_STAGE_RESOURCES_CSS );
            root = FXMLLoader.load( urlToFXML );
        }
        catch ( IOException | NullPointerException e )
        {
            AlertUtils.popUpErrorAlert( e );
            return null;
        }

        return StageUtils.createStage( "Information", false, root, urlToCSS,
                HelpInformationStageConstants.HELP_INFORMATION_STAGE_WIDTH,
                HelpInformationStageConstants.HELP_INFORMATION_STAGE_HEIGHT );
    }

    private Stage createRegisterAccountStage()
    {
        Parent root;
        URL urlToFXML;
        URL urlToCSS;

        try
        {
            urlToFXML = getClass().getResource( RegisterAccountStageConstants.REGISTER_ACCOUNT_STAGE_RESOURCES_FXML );
            urlToCSS = getClass().getResource( RegisterAccountStageConstants.REGISTER_ACCOUNT_STAGE_RESOURCES_CSS );
            root = FXMLLoader.load( urlToFXML );
        }
        catch ( IOException | NullPointerException e )
        {
            AlertUtils.popUpErrorAlert( e );
            return null;
        }

        return StageUtils.createStage( "Register account", false, root, urlToCSS,
                RegisterAccountStageConstants.REGISTER_ACCOUNT_STAGE_WIDTH,
                RegisterAccountStageConstants.REGISTER_ACCOUNT_STAGE_HEIGHT );
    }

    @FXML
    private void popUpHelpContactStageOnScreen()
    {
        if ( !isHelpContactStageOnScreen )
        {
            Stage helpContactStage = createHelpContactStage();
            boolean isHelpContactStageShown = StageUtils.isStageShown( helpContactStage, signInStage, Modality.NONE );
            if ( isHelpContactStageShown )
            {
                isHelpContactStageOnScreen = true;
                helpContactStage.setOnCloseRequest( wE -> isHelpContactStageOnScreen = false );
            }
        }
    }


    @FXML
    private void popUpHelpInformationStageOnScreen()
    {
        if ( !isHelpInformationStageOnScreen )
        {
            Stage helpInformationStage = createHelpInformationStage();
            boolean isHelpInformationStageShown = StageUtils.isStageShown( helpInformationStage, signInStage, Modality.NONE );
            if ( isHelpInformationStageShown )
            {
                isHelpInformationStageOnScreen = true;
                helpInformationStage.setOnCloseRequest( wE -> isHelpInformationStageOnScreen = false );
            }
        }
    }

    @FXML
    private void popUpRegisterAccountStageOnScreen(){
        if ( !isRegisterAccountStageOnScreen )
        {
            Stage registerAccountStage = createRegisterAccountStage();
            boolean isRegisterAccountStageShown = StageUtils.isStageShown( registerAccountStage, signInStage, Modality.WINDOW_MODAL );
            if ( isRegisterAccountStageShown )
            {
                isRegisterAccountStageOnScreen = true;
                registerAccountStage.setOnCloseRequest( wE -> isRegisterAccountStageOnScreen = false );
            }
        }
    }


}
