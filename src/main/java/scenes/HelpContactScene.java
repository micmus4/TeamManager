package scenes;

import constants.SocialMediaConstants;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import utils.AlertUtils;
import utils.SceneUtils;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HelpContactScene
{

    @FXML
    private Label versionLabel;

    public void initialize()
    {
        SceneUtils.setProjectVersionLabel( versionLabel );
    }

    @FXML
    private void openLinkedinInBrowser()
    {
        openSiteInBrowser( SocialMediaConstants.LINKEDIN.getLink() );
    }

    @FXML
    private void openFacebookInBrowser()
    {
        openSiteInBrowser( SocialMediaConstants.FACEBOOK.getLink() );
    }

    @FXML
    private void openGithubInBrowser()
    {
        openSiteInBrowser( SocialMediaConstants.GITHUB.getLink() );
    }

    @FXML
    private void openInstagramInBrowser()
    {
        openSiteInBrowser( SocialMediaConstants.INSTAGRAM.getLink() );
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
