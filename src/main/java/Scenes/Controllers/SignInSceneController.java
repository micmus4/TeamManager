package Scenes.Controllers;

import Colours.Colours;
import Constants.SignInSceneConstants;
import Scenes.Creators.SignInSceneCreator;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class SignInSceneController extends Application {

    private static final SignInSceneCreator signInSceneInstance = SignInSceneCreator.getSignInScene();

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private MenuItem contactMenuItem;

    private static Stage signInStage = null;

    public void initialize(){

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage signInStage) throws Exception {
        signInStage = signInSceneInstance.createSignInScene(signInStage);

        if(signInStage == null){
            System.out.println(Colours.ANSI_RED + "Couldn't create scene because of lack of "
                    + SignInSceneConstants.SIGN_IN_SCENE_FXML_NAME + Colours.RESET_COLOUR);
            stop();
            return;
        }

        this.signInStage = signInStage;
        signInStage.show();
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
