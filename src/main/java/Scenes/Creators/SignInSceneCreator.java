package Scenes.Creators;

import Constants.ProjectConstants;
import Constants.SignInSceneConstants;
import Utils.AlertUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;


public class SignInSceneCreator {

    private static final SignInSceneCreator signInScene = new SignInSceneCreator();
    private URL SIGN_IN_SCENE_FXML_URL;


    public SignInSceneCreator() {
    }

    public static SignInSceneCreator getSignInScene(){
        return signInScene;
    }

    public Stage createSignInScene(Stage signInStage)  {
        Parent root;

        try{

            SIGN_IN_SCENE_FXML_URL = new URL( ProjectConstants.PROTOCOL, ProjectConstants.HOST, SignInSceneConstants.SIGN_IN_SCENE_ABSOLUTE_STRING_PATH_TO_FXML );
            root = FXMLLoader.load( Objects.requireNonNull( SIGN_IN_SCENE_FXML_URL ) );

        } catch ( IOException | NullPointerException e ) {

            String headerText = e.getClass().getCanonicalName();
            String contentText = e.getMessage();
            AlertUtils.popUpErrorAlert( headerText, contentText );

            return null;

        }

        signInStage.setTitle( "Sign in" );
        signInStage.setResizable( false );
        Scene signInStageScene = new Scene( root, SignInSceneConstants.SIGN_IN_SCENE_HEIGHT, SignInSceneConstants.SIGN_IN_SCENE_WIDTH );

        signInStageScene.getStylesheets().clear();
        signInStageScene.getStylesheets().add( SignInSceneConstants.SIGN_IN_SCENE_CSS_ABSOLUTE_STRING_PATH );
        signInStage.setScene( signInStageScene );

        return signInStage;
    }
}

