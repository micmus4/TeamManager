package stages.help;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import stages.account.SignInStage;
import utils.StageUtils;

public class HelpInformationStage
{
    private static final Logger LOGGER = LogManager.getLogger( HelpInformationStage.class );

    @FXML
    private Label versionLabel;

    public void initialize()
    {
        LOGGER.info( "Initializing HelpInformationStage properties" );
        StageUtils.setProjectVersionLabel( versionLabel );
    }
}
