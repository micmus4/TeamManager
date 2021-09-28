package utils;

import constants.ProjectConstants;
import javafx.scene.control.Label;

public class SceneUtils
{

    public static void setProjectVersionLabel ( Label versionLabel )
    {
        versionLabel.setText( ProjectConstants.VERSION_INFO );
    }
}
