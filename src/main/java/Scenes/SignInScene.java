package Scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import static Colours.Colours.ANSI_RED;
import static Colours.Colours.RESET_COLOUR;
import static Constants.SignInSceneConstants.SignInSceneConstants.SIGN_IN_SCENE_ABSOLUTE_PATH_TO_FXML_PARENT_DIR;
import static Constants.SignInSceneConstants.SignInSceneConstants.SIGN_IN_SCENE_STRING_PATH;

public class SignInScene {

    private static final SignInScene SIGN_IN_SCENE = new SignInScene();
    public final URL SIGN_IN_SCENE_URL_TO_FXML = getClass().getResource(SIGN_IN_SCENE_STRING_PATH);


    private SignInScene() {
    }

    public static SignInScene getSignInSceneInstance(){
        return SIGN_IN_SCENE;
    }

    public Stage createSignInScene(Stage signInStage)  {
        Parent root = null;

        try{
            root = FXMLLoader.load(Objects.requireNonNull(SIGN_IN_SCENE_URL_TO_FXML));
        } catch (IOException | NullPointerException e) {
            System.out.println(ANSI_RED + "No " + SIGN_IN_SCENE_STRING_PATH + " file found in " +
                    SIGN_IN_SCENE_ABSOLUTE_PATH_TO_FXML_PARENT_DIR + " directory." + RESET_COLOUR);
            return null;
        }

        signInStage.setTitle("Sign in");
        signInStage.setScene(new Scene(root, 680, 420));
        return signInStage;
    }
}

