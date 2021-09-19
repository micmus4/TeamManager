package Scenes.Creators;


import Constants.HelpContactSceneConstants;
import Constants.ProjectConstants;
import Utils.AlertUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;


public class HelpContactSceneCreator {

    private static HelpContactSceneCreator helpContactScene = new HelpContactSceneCreator();
    private URL HELP_CONTACT_FXML_URL;

    public HelpContactSceneCreator() {
    }

    public Stage createHelpContactScene(){
        Stage helpContactStage = new Stage();
        Parent root;

        try{

            HELP_CONTACT_FXML_URL = new URL( ProjectConstants.PROTOCOL, ProjectConstants.HOST, HelpContactSceneConstants.HELP_CONTACT_SCENE_ABSOLUTE_STRING_PATH_TO_FXML );
            root = FXMLLoader.load( Objects.requireNonNull( HELP_CONTACT_FXML_URL ) );

        } catch ( IOException | NullPointerException e ) {

            String headerText = e.getClass().getCanonicalName();
            String contentText = e.getMessage();
            AlertUtils.popUpErrorAlert( headerText, contentText );

            return null;
        }

        helpContactStage.setTitle( "Contact" );
        helpContactStage.setResizable( false );
        Scene helpContactScene = new Scene( root, HelpContactSceneConstants.HELP_CONTACT_SCENE_HEIGHT, HelpContactSceneConstants.HELP_CONTACT_SCENE_WIDTH );

        helpContactScene.getStylesheets().clear();
        helpContactScene.getStylesheets().add( HelpContactSceneConstants.HELP_CONTACT_SCENE_CSS_ABSOLUTE_STRING_PATH );

        helpContactStage.setScene( helpContactScene );
        return helpContactStage;
    }

    public static HelpContactSceneCreator getHelpContactScene() {
        return helpContactScene;
    }
}
