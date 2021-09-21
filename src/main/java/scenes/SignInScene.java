package scenes;

import constants.HelpContactSceneConstants;
import constants.ProjectConstants;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utils.AlertUtils;

import java.io.IOException;
import java.net.URL;





public class SignInScene {

    private static Stage signInStage;

    @FXML
    private Label versionLabel;


    public SignInScene(){}



    public void initialize()
    {
        versionLabel.setText( ProjectConstants.VERSION_INFO );
    }

    @FXML
    public void hideSignInScene()
    {
        boolean wasHelpContactSceneCreated = showHelpContactScene();
        if( wasHelpContactSceneCreated )
        {
            signInStage.hide();
        }
    }

    private Stage createHelpContactScene()
    {
        Stage helpContactStage = new Stage();
        Parent root = null;
        URL urlToFXML = null;
        URL urlToCSS = null;

        try
        {
            urlToFXML = getClass().getResource( HelpContactSceneConstants.HELP_CONTACT_SCENE_RESOURCES_FXML );
            urlToCSS = getClass().getResource( HelpContactSceneConstants.HELP_CONTACT_SCENE_RESOURCES_CSS );
            root = FXMLLoader.load( urlToFXML );
        }
        catch ( IOException | NullPointerException e )
        {
            AlertUtils.popUpErrorAlert( e );
            return null;
        }

        helpContactStage.setTitle( "Contact" );
        helpContactStage.setResizable( false );
        Scene helpContactScene = new Scene( root, HelpContactSceneConstants.HELP_CONTACT_SCENE_HEIGHT,
                                            HelpContactSceneConstants.HELP_CONTACT_SCENE_WIDTH );

        helpContactScene.getStylesheets().clear();
        helpContactScene.getStylesheets().add( urlToCSS.toExternalForm() );

        helpContactStage.setScene( helpContactScene );
        return helpContactStage;
    }

    private boolean showHelpContactScene()
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

    public static void setSignInStage( Stage stage )
    {
        signInStage = stage;
    }

    private void listenerForClosingHelpContactScene( Stage helpContactStage ){
        helpContactStage.setOnCloseRequest( wE -> signInStage.show() );
    }
}
