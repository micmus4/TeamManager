package constants;

public class HelpInformationStageConstants
{
    /**
     * Height and width for HelpInformationStage.
     */

    public static Double HELP_INFORMATION_STAGE_WIDTH = 405.00D;
    public static Double HELP_INFORMATION_STAGE_HEIGHT = 360.00D;


    /**
     * Names of FXML/CSS files for HelpInformationStage.
     */

    public static final String HELP_INFORMATION_STAGE_FXML_NAME = "helpInformationStage.fxml";
    public static final String HELP_INFORMATION_STAGE_CSS_NAME = "helpInformationStageStyle.css";


    /**
     * Relative paths (from resources dir) to FXML/CSS files for HelpInformationStage.
     */

    public static String HELP_INFORMATION_STAGE_RESOURCES_FXML = ( ProjectConstants.SEP + "fxml" + ProjectConstants.SEP +
            HELP_INFORMATION_STAGE_FXML_NAME).replace( "\\", "/" );
    public static String HELP_INFORMATION_STAGE_RESOURCES_CSS = ( ProjectConstants.SEP + "css" + ProjectConstants.SEP +
            HELP_INFORMATION_STAGE_CSS_NAME).replace( "\\", "/" );
}
