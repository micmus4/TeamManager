package Scenes.Creators;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import static Colours.Colours.ANSI_RED;
import static Colours.Colours.RESET_COLOUR;
import static Constants.SignInSceneConstants.SignInSceneConstants.*;

public class SignInSceneCreator {

    private static final SignInSceneCreator SIGN_IN_SCENE = new SignInSceneCreator();
    public final URL SIGN_IN_SCENE_URL_TO_FXML = getClass().getResource(SIGN_IN_SCENE_FXML_NAME);


    private SignInSceneCreator() {
    }

    public static SignInSceneCreator getSignInSceneInstance(){
        return SIGN_IN_SCENE;
    }

    public Stage createSignInScene(Stage signInStage)  {
        Parent root;

        try{
            root = FXMLLoader.load(Objects.requireNonNull(SIGN_IN_SCENE_URL_TO_FXML));
        } catch (IOException | NullPointerException e) {
            System.out.println(ANSI_RED + "No " + SIGN_IN_SCENE_FXML_NAME + " file found in " +
                    SIGN_IN_SCENE_ABSOLUTE_PATH_TO_FXML_PARENT_DIR + " directory." + RESET_COLOUR);
            return null;
        }

        signInStage.setTitle("Sign in");
        signInStage.setResizable(false);
        signInStage.setScene(new Scene(root, SIGN_IN_SCENE_HEIGHT, SIGN_IN_SCENE_WIDTH));
        return signInStage;
    }
}

