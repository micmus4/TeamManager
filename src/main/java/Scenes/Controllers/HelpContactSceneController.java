package Scenes.Controllers;

import Colours.Colours;
import Constants.HelpContactSceneConstants;
import Constants.SocialMediaConstants;
import Scenes.Creators.HelpContactSceneCreator;
import Utils.AlertUtils;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class HelpContactSceneController {

    private static final HelpContactSceneCreator helpContactSceneInstance = HelpContactSceneCreator.getHelpContactScene();
    private static final Stage signInStage = SignInSceneController.getSignInStage();

    public static void showHelpContactScene(){
        Stage helpContactStage = helpContactSceneInstance.createHelpContactScene();
        if( helpContactStage == null ){
            System.out.println( Colours.ANSI_RED + "Couldn't create scene because of lack of "
                    + HelpContactSceneConstants.HELP_CONTACT_SCENE_FXML_NAME + Colours.RESET_COLOUR );
            throw new NullPointerException();
        }

        helpContactStage.show();

        // listener for closing helpContactStage
        helpContactStage.setOnCloseRequest( wE -> signInStage.show() );
    }

    @FXML
    private void openLinkedinInBrowser (){
        openSiteInBrowser( SocialMediaConstants.LINKEDIN );
    }

    @FXML
    private void openFacebookInBrowser(){
        openSiteInBrowser( SocialMediaConstants.FACEBOOK );
    }

    @FXML
    private void openGithubInBrowser(){
        openSiteInBrowser( SocialMediaConstants.GITHUB );
    }

    @FXML
    private void openInstagramInBrowser(){
        openSiteInBrowser( SocialMediaConstants.INSTAGRAM );
    }

    private void openSiteInBrowser( String link ){
        try {

            URI uri = new URI( link );
            Desktop.getDesktop().browse( uri );

        } catch ( URISyntaxException | IOException e ){

            String headerText = e.getClass().getCanonicalName();
            String contentText = e.getMessage();
            AlertUtils.popUpErrorAlert( headerText, contentText );
        }
    }

}
