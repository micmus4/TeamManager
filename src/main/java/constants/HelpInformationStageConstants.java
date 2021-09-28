package constants;

public class HelpInformationStageConstants
{
    /**
     * Height and width for HelpInformationScene.
     */

    public static Double HELP_INFORMATION_SCENE_WIDTH = 360.00D;
    public static Double HELP_INFORMATION_SCENE_HEIGHT = 405.00D;


    /**
     * Names of FXML/CSS files for HelpInformationScene.
     */

    public static final String HELP_INFORMATION_SCENE_FXML_NAME = "helpInformationStage.fxml";
    public static final String HELP_INFORMATION_SCENE_CSS_NAME = "helpInformationStageStyle.css";


    /**
     * Relative paths (from resources dir) to FXML/CSS files for HelpInformationScene.
     */

    public static String HELP_INFORMATION_SCENE_RESOURCES_FXML = ( ProjectConstants.SEP + "fxml" + ProjectConstants.SEP +
            HELP_INFORMATION_SCENE_FXML_NAME ).replace( "\\", "/" );
    public static String HELP_INFORMATION_SCENE_RESOURCES_CSS = ( ProjectConstants.SEP + "css" + ProjectConstants.SEP +
            HELP_INFORMATION_SCENE_CSS_NAME ).replace( "\\", "/" );
}
