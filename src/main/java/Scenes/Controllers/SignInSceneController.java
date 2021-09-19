package Scenes.Controllers;

import Scenes.Creators.SignInSceneCreator;
import Utils.AlertUtils;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.stage.Stage;


public class SignInSceneController extends Application {

    private static final SignInSceneCreator signInSceneInstance = SignInSceneCreator.getSignInScene();

    private static Stage signInStage = null;

    public void initialize(){

    }

    public static void main( String[] args ) {
        launch( args );
    }

    @Override
    public void start( Stage signInStage ) throws Exception {
        signInStage = signInSceneInstance.createSignInScene( signInStage );

        try {
            this.signInStage = signInStage;
            signInStage.show();
        } catch ( NullPointerException e ){

            String headerText = e.getClass().getCanonicalName();
            String contentText = e.getMessage();
            AlertUtils.popUpErrorAlert( headerText, contentText );
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    @FXML
    public void showHelpContactScene(){
        HelpContactSceneController.showHelpContactScene();
        signInStage.hide();
    }

    public static Stage getSignInStage() {
        return signInStage;
    }
}
