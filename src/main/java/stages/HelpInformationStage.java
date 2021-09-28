package stages;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import utils.StageUtils;

public class HelpInformationStage
{
    @FXML
    private Label versionLabel;

    public void initialize()
    {
        StageUtils.setProjectVersionLabel( versionLabel );
    }
}
