package Scenes.Controllers;

import Scenes.Creators.SignInSceneCreator;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static Colours.Colours.ANSI_RED;
import static Constants.SignInSceneConstants.SignInSceneConstants.SIGN_IN_SCENE_FXML_NAME;


public class SignInSceneController extends Application {

    private static final SignInSceneCreator SIGN_IN_SCENE = SignInSceneCreator.getSignInSceneInstance();

    @FXML
    private TextField LOGIN_FIELD;

    @FXML
    private PasswordField PASSWORD_FIELD;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage signInStage) throws Exception {
        signInStage = SIGN_IN_SCENE.createSignInScene(signInStage);

        if(signInStage == null){
            System.out.println(ANSI_RED + "Couldn't create scene because of lack of " + SIGN_IN_SCENE_FXML_NAME);
            stop();
        }

        signInStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
