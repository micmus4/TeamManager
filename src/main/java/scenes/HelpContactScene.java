package scenes;

import constants.ProjectConstants;
import constants.SocialMediaConstants;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import utils.AlertUtils;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HelpContactScene {

    @FXML
    private Label versionLabel;

    public void initialize()
    {
        versionLabel.setText( ProjectConstants.VERSION_INFO );
    }

    @FXML
    private void openLinkedinInBrowser()
    {
        openSiteInBrowser( SocialMediaConstants.LINKEDIN );
    }

    @FXML
    private void openFacebookInBrowser()
    {
        openSiteInBrowser( SocialMediaConstants.FACEBOOK );
    }

    @FXML
    private void openGithubInBrowser()
    {
        openSiteInBrowser( SocialMediaConstants.GITHUB );
    }

    @FXML
    private void openInstagramInBrowser()
    {
        openSiteInBrowser( SocialMediaConstants.INSTAGRAM );
    }

    private void openSiteInBrowser( String link )
    {
        try
        {
            URI uri = new URI( link );
            Desktop.getDesktop().browse( uri );
        }
        catch ( URISyntaxException | IOException e )
        {
            AlertUtils.popUpErrorAlert( e );
        }
    }
}
