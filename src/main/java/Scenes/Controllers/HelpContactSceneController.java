package Scenes.Controllers;

import Colours.Colours;
import Constants.HelpContactSceneConstants;
import Scenes.Creators.HelpContactSceneCreator;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class HelpContactSceneController {

    private static HelpContactSceneCreator helpContactSceneInstance = HelpContactSceneCreator.getHelpContactScene();
    private static Stage signInStage = SignInSceneController.getSignInStage();

    public static void showHelpContactScene(){
        Stage helpContactStage = helpContactSceneInstance.createHelpContactScene();
        if(helpContactStage == null){
            System.out.println(Colours.ANSI_RED + "Couldn't create scene because of lack of "
                    + HelpContactSceneConstants.HELP_CONTACT_SCENE_FXML_NAME + Colours.RESET_COLOUR);
            throw new NullPointerException();
        }

        helpContactStage.show();
        // listener for closing helpContactStage
        helpContactStage.setOnCloseRequest(wE -> signInStage.show());
    }

    @FXML
    private void openLinkedinInBrowser (){
        openSiteInBrowser("https://www.linkedin.com/in/micha%C5%82-musia%C5%82owicz-553851216/");
    }

    @FXML
    private void openFacebookInBrowser(){
        openSiteInBrowser("https://www.facebook.com/michalmusialowicz01");
    }

    @FXML
    private void openGithubInBrowser(){
        openSiteInBrowser("https://github.com/micmus4");
    }

    @FXML
    private void openInstagramInBrowser(){
        openSiteInBrowser("https://www.instagram.com/muuusial_/");
    }

    private void openSiteInBrowser(String link){
        try {
            URI uri = new URI(link);
            Desktop.getDesktop().browse(uri);
        } catch (URISyntaxException | IOException e){
            Alert couldntOpenBrowserAlert = new Alert(Alert.AlertType.ERROR);
            couldntOpenBrowserAlert.setTitle("Error!");
            couldntOpenBrowserAlert.setHeaderText(Colours.ANSI_RED + "Couldn't open the browser" + Colours.RESET_COLOUR);
            couldntOpenBrowserAlert.setContentText("The program failed to open the browser with content.");
            couldntOpenBrowserAlert.show();
        }
    }




}
