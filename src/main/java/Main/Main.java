package Main;

import Scenes.SignInScene;
import javafx.application.Application;
import javafx.stage.Stage;

import static Colours.Colours.ANSI_RED;
import static Constants.SignInSceneConstants.SignInSceneConstants.SIGN_IN_SCENE_STRING_PATH;


public class Main extends Application {

    private static final SignInScene SIGN_IN_SCENE = SignInScene.getSignInSceneInstance();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage signInStage) throws Exception {
        signInStage = SIGN_IN_SCENE.createSignInScene(signInStage);

        if(signInStage == null){
            System.out.println(ANSI_RED + "Couldn't create scene because of lack of " + SIGN_IN_SCENE_STRING_PATH);
            stop();
        }

        signInStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
