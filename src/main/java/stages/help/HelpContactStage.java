package stages.help;

import constants.help.SocialMediaConstants;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.AlertUtils;
import utils.StageUtils;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HelpContactStage
{
    private final Logger LOGGER;

    @FXML
    private Label versionLabel;


    public HelpContactStage()
    {
        LOGGER = LogManager.getLogger( HelpContactStage.class );
    }


    public void initialize()
    {
        LOGGER.info( "Initializing HelpContactStage properties" );
        StageUtils.setProjectVersionLabel( versionLabel );
    }


    @FXML
    private void openLinkedinInBrowser()
    {
        LOGGER.info( "Opening Linkedin site in browser." );
        openSiteInBrowser( SocialMediaConstants.LINKEDIN.getLink() );
    }


    @FXML
    private void openFacebookInBrowser()
    {
        LOGGER.info( "Opening Facebook site in browser." );
        openSiteInBrowser( SocialMediaConstants.FACEBOOK.getLink() );
    }


    @FXML
    private void openGithubInBrowser()
    {
        LOGGER.info( "Opening Github site in browser." );
        openSiteInBrowser( SocialMediaConstants.GITHUB.getLink() );
    }


    @FXML
    private void openInstagramInBrowser()
    {
        LOGGER.info( "Opening Instagram site in browser." );
        openSiteInBrowser( SocialMediaConstants.INSTAGRAM.getLink() );
    }


    private void openSiteInBrowser( String aLink )
    {
        try
        {
            LOGGER.info( "Creating URL from " + aLink );
            URI uri = new URI( aLink );
            Desktop.getDesktop().browse( uri );
        }
        catch ( URISyntaxException | IOException e )
        {
            LOGGER.error( e.getClass().getSimpleName() + ", failed to created url from " + aLink );
            AlertUtils.popUpErrorAlert( e );
        }
    }
}
