package start;

import constants.ProjectConstants;
import constants.SignInSceneConstants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import scenes.SignInScene;
import utils.AlertUtils;

import java.io.IOException;
import java.net.URL;

public class Start extends Application {

    public static void main( String[] args )
    {
        launch( args );
    }

    @Override
    public void start( Stage signInStage ) throws Exception
    {
        signInStage = createSignInScene( signInStage );

        try
        {
            SignInScene.setSignInStage( signInStage );
            signInStage.show();
        }
        catch ( NullPointerException e )
        {
            AlertUtils.popUpErrorAlert( e );
        }
    }


    public Stage createSignInScene( Stage signInStage )  {
        Parent root;
        URL urlToFXML;
        URL urlToCSS;
        URL icon;

        try
        {
            urlToFXML = getClass().getResource( SignInSceneConstants.SIGN_IN_SCENE_RESOURCES_FXML );
            urlToCSS = getClass().getResource( SignInSceneConstants.SIGN_IN_SCENE_RESOURCES_CSS );
            icon = getClass().getResource( ProjectConstants.ICON_RESOURCES_FXML );
            root = FXMLLoader.load(  urlToFXML );
        }
        catch ( IOException | NullPointerException e )
        {
            AlertUtils.popUpErrorAlert( e );
            System.exit( 1 );
            return null;
        }

        signInStage.getIcons().add( new Image( icon.toString() ) );
        signInStage.setTitle( "Sign in" );
        signInStage.setResizable( false );
        Scene signInStageScene = new Scene( root, SignInSceneConstants.SIGN_IN_SCENE_HEIGHT, SignInSceneConstants.SIGN_IN_SCENE_WIDTH );

        signInStageScene.getStylesheets().clear();
        signInStageScene.getStylesheets().add( urlToCSS.toExternalForm() );
        signInStage.setScene( signInStageScene );

        return signInStage;
    }
}