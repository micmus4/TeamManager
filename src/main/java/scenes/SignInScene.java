package scenes;

import constants.HelpContactSceneConstants;
import constants.HelpInformationSceneConstants;
import constants.ProjectConstants;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utils.AlertUtils;
import utils.SceneUtils;

import java.io.IOException;
import java.net.URL;


public class SignInScene
{

    private static Stage signInStage;

    @FXML
    private Label versionLabel;


    public SignInScene(){}


    public void initialize()
    {
        SceneUtils.setProjectVersionLabel( versionLabel );
    }


    public static void setSignInStage( Stage stage )
    {
        signInStage = stage;
    }


    private Stage createHelpContactScene()
    {
        Stage helpContactStage = new Stage();
        Parent root;
        URL urlToFXML;
        URL urlToCSS;
        URL icon;

        try
        {
            urlToFXML = getClass().getResource( HelpContactSceneConstants.HELP_CONTACT_SCENE_RESOURCES_FXML );
            urlToCSS = getClass().getResource( HelpContactSceneConstants.HELP_CONTACT_SCENE_RESOURCES_CSS );
            icon = getClass().getResource( ProjectConstants.ICON_RESOURCES_FXML );
            root = FXMLLoader.load( urlToFXML );
        }
        catch ( IOException | NullPointerException e )
        {
            AlertUtils.popUpErrorAlert( e );
            return null;
        }

        helpContactStage.getIcons().add( new Image( icon.toString() ) );
        helpContactStage.setTitle( "Contact" );
        helpContactStage.setResizable( false );
        Scene helpContactScene = new Scene( root, HelpContactSceneConstants.HELP_CONTACT_SCENE_HEIGHT,
                HelpContactSceneConstants.HELP_CONTACT_SCENE_WIDTH );

        helpContactScene.getStylesheets().clear();
        helpContactScene.getStylesheets().add( urlToCSS.toExternalForm() );

        helpContactStage.setScene( helpContactScene );
        return helpContactStage;
    }


    private Stage createHelpInformationScene()
    {
        Stage helpInformationStage = new Stage();
        Parent root;
        URL urlToFXML;
        URL urlToCSS;
        URL icon;

        try
        {
            urlToFXML = getClass().getResource( HelpInformationSceneConstants.HELP_INFORMATION_SCENE_RESOURCES_FXML );
            urlToCSS = getClass().getResource(  HelpInformationSceneConstants.HELP_INFORMATION_SCENE_RESOURCES_CSS );
            icon = getClass().getResource( ProjectConstants.ICON_RESOURCES_FXML );
            root = FXMLLoader.load( urlToFXML );
        }
        catch ( IOException | NullPointerException e )
        {
            AlertUtils.popUpErrorAlert( e );
            return null;
        }

        helpInformationStage.getIcons().add( new Image( icon.toString() ) );
        helpInformationStage.setTitle( "Information" );
        helpInformationStage.setResizable( false );
        Scene helpInformationScene = new Scene( root, HelpInformationSceneConstants.HELP_INFORMATION_SCENE_HEIGHT,
                HelpInformationSceneConstants.HELP_INFORMATION_SCENE_WIDTH );

        helpInformationScene.getStylesheets().clear();
        helpInformationScene.getStylesheets().add( urlToCSS.toExternalForm() );

        helpInformationStage.setScene( helpInformationScene );
        return helpInformationStage;
    }


    private boolean wasHelpContactSceneCreated()
    {
        Stage helpContactStage = createHelpContactScene();

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
        listenerForClosingHelpContactScene( helpContactStage );

        return true;
    }


    private boolean wasHelpInformationSceneCreated()
    {
        Stage helpInformationStage = createHelpInformationScene();

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
        listenerForClosingHelpInformationScene( helpInformationStage );

        return true;
    }


    @FXML
    private void popUpHelpContactSceneOnScreen()
    {
        if( wasHelpContactSceneCreated() )
        {
            signInStage.hide();
        }
    }


    @FXML
    private void popUpHelpInformationSceneOnScreen()
    {
        if( wasHelpInformationSceneCreated() )
        {
            signInStage.hide();
        }
    }


    private void listenerForClosingHelpContactScene( Stage helpContactStage )
    {
        helpContactStage.setOnCloseRequest( wE -> signInStage.show() );
    }

    private void listenerForClosingHelpInformationScene( Stage helpInformationStage )
    {
        helpInformationStage.setOnCloseRequest( wE -> signInStage.show() );
    }

}
