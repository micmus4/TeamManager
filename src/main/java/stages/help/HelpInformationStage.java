package stages.help;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.StageUtils;

public class HelpInformationStage
{
    private final Logger LOGGER;

    @FXML
    private Label versionLabel;


    public HelpInformationStage()
    {
        LOGGER = LogManager.getLogger( HelpInformationStage.class );
    }

    public void initialize()
    {
        LOGGER.info( "Initializing HelpInformationStage properties" );
        StageUtils.setProjectVersionLabel( versionLabel );
    }
}
