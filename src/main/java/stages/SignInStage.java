package stages;

import constants.HelpContactStageConstants;
import constants.HelpInformationStageConstants;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utils.AlertUtils;
import utils.StageUtils;

import java.io.IOException;
import java.net.URL;


public class SignInStage
{

    private static Stage signInStage;

    @FXML
    private Label versionLabel;


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
            urlToFXML = getClass().getResource( HelpContactStageConstants.HELP_CONTACT_SCENE_RESOURCES_FXML );
            urlToCSS = getClass().getResource( HelpContactStageConstants.HELP_CONTACT_SCENE_RESOURCES_CSS );
            root = FXMLLoader.load( urlToFXML );
        }
        catch ( IOException | NullPointerException e )
        {
            AlertUtils.popUpErrorAlert( e );
            return null;
        }

        return StageUtils.createStage( "Contact", false, root, urlToCSS,
                HelpContactStageConstants.HELP_CONTACT_SCENE_HEIGHT,
                HelpContactStageConstants.HELP_CONTACT_SCENE_WIDTH );
    }


    private Stage createHelpInformationStage()
    {
        Parent root;
        URL urlToFXML;
        URL urlToCSS;

        try
        {
            urlToFXML = getClass().getResource( HelpInformationStageConstants.HELP_INFORMATION_SCENE_RESOURCES_FXML );
            urlToCSS = getClass().getResource(  HelpInformationStageConstants.HELP_INFORMATION_SCENE_RESOURCES_CSS );
            root = FXMLLoader.load( urlToFXML );
        }
        catch ( IOException | NullPointerException e )
        {
            AlertUtils.popUpErrorAlert( e );
            return null;
        }

        return StageUtils.createStage( "Information", false, root, urlToCSS,
                HelpInformationStageConstants.HELP_INFORMATION_SCENE_HEIGHT,
                HelpInformationStageConstants.HELP_INFORMATION_SCENE_WIDTH );
    }


    private boolean wasHelpContactStageCreated()
    {
        Stage helpContactStage = createHelpContactStage();

        try
        {
            if ( helpContactStage == null )
            {
                throw new NullPointerException();
            }
        }
        catch ( NullPointerException e )
        {
            AlertUtils.popUpErrorAlert( e );
            return false;
        }

        helpContactStage.show();
        listenerForClosingHelpContactStage( helpContactStage );

        return true;
    }


    private boolean wasHelpInformationStageCreated()
    {
        Stage helpInformationStage = createHelpInformationStage();

        try
        {
            if ( helpInformationStage == null )
            {
                throw new NullPointerException();
            }
        }
        catch ( NullPointerException e )
        {
            AlertUtils.popUpErrorAlert( e );
            return false;
        }

        helpInformationStage.show();
        listenerForClosingHelpInformationStage( helpInformationStage );

        return true;
    }


    @FXML
    private void popUpHelpContactStageOnScreen()
    {
        if( wasHelpContactStageCreated() )
        {
            signInStage.hide();
        }
    }


    @FXML
    private void popUpHelpInformationStageOnScreen()
    {
        if( wasHelpInformationStageCreated() )
        {
            signInStage.hide();
        }
    }


    private void listenerForClosingHelpContactStage(Stage helpContactStage )
    {
        helpContactStage.setOnCloseRequest( wE -> signInStage.show() );
    }

    private void listenerForClosingHelpInformationStage(Stage helpInformationStage )
    {
        helpInformationStage.setOnCloseRequest( wE -> signInStage.show() );
    }

}
