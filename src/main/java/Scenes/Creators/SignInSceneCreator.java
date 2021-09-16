package Scenes.Creators;

import Colours.Colours;
import Constants.SignInSceneConstants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;


public class SignInSceneCreator {

    private static final SignInSceneCreator signInScene = new SignInSceneCreator();
    private final URL SIGN_IN_SCENE_URL_TO_FXML = getClass().getResource(SignInSceneConstants.SIGN_IN_SCENE_FXML_NAME);


    public SignInSceneCreator() {
    }

    public static SignInSceneCreator getSignInScene(){
        return signInScene;
    }

    public Stage createSignInScene(Stage signInStage)  {
        Parent root;

        try{
            root = FXMLLoader.load(Objects.requireNonNull(SIGN_IN_SCENE_URL_TO_FXML));
        } catch (IOException | NullPointerException e) {
            System.out.println(Colours.ANSI_RED + "No " + SignInSceneConstants.SIGN_IN_SCENE_FXML_NAME + " file found in " +
                    SignInSceneConstants.SIGN_IN_SCENE_ABSOLUTE_PATH_TO_FXML_PARENT_DIR + " directory." + Colours.RESET_COLOUR);
            return null;
        }

        signInStage.setTitle("Sign in");
        signInStage.setResizable(false);
        signInStage.setScene(new Scene(root, SignInSceneConstants.SIGN_IN_SCENE_HEIGHT, SignInSceneConstants.SIGN_IN_SCENE_WIDTH));
        return signInStage;
    }
}

