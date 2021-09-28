package scenes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import utils.SceneUtils;

public class HelpInformationScene
{
    @FXML
    private Label versionLabel;

    public void initialize()
    {
        SceneUtils.setProjectVersionLabel( versionLabel );
    }
}
