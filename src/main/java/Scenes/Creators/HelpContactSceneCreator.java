package Scenes.Creators;


import Colours.Colours;
import Constants.HelpContactSceneConstants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;


public class HelpContactSceneCreator {

    private static HelpContactSceneCreator helpContactScene = new HelpContactSceneCreator();
    private final URL HELP_CONTACT_SCENE_URL_TO_FXML = getClass().getResource(HelpContactSceneConstants.HELP_CONTACT_SCENE_FXML_NAME);

    public HelpContactSceneCreator() {
    }

    public Stage createHelpContactScene(){
        Stage helpContactStage = new Stage();
        Parent root;

        try{
            root = FXMLLoader.load(Objects.requireNonNull(HELP_CONTACT_SCENE_URL_TO_FXML));
        } catch (IOException | NullPointerException e) {
            System.out.println(Colours.ANSI_RED + "No " + HelpContactSceneConstants.HELP_CONTACT_SCENE_FXML_NAME + " file found in " +
                    HelpContactSceneConstants.HELP_CONTACT_SCENE_ABSOLUTE_PATH_TO_FXML_PARENT_DIR + " directory." + Colours.RESET_COLOUR);
            return null;
        }

        helpContactStage.setTitle("Contact");
        helpContactStage.setResizable(false);
        helpContactStage.setScene(new Scene(root, HelpContactSceneConstants.HELP_CONTACT_SCENE_HEIGHT, HelpContactSceneConstants.HELP_CONTACT_SCENE_WIDTH));
        return helpContactStage;
    }

    public static HelpContactSceneCreator getHelpContactScene() {
        return helpContactScene;
    }
}
