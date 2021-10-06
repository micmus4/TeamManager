package constants.help;


import constants.ProjectConstants;

public class HelpContactConstants
{

    /**
     * Height and width for HelpContactStage.
     */

    public static String HELP_CONTACT_STAGE_FXML_NAME = "helpContactStage.fxml";
    public static final String HELP_CONTACT_STAGE_CSS_NAME = "helpContactStageStyle.css";


    /**
     * Names of FXML/CSS files for HelpContactStage.
     */

    public static final Double HELP_CONTACT_STAGE_HEIGHT = 360.00D;
    public static final Double HELP_CONTACT_STAGE_WIDTH = 405.00D;



    /**
     * Relative paths (from resources dir) to FXML/CSS files for HelpContactStage.
     */

    public static String HELP_CONTACT_STAGE_RESOURCES_FXML = ( ProjectConstants.SEP + "fxml" + ProjectConstants.SEP +
            HELP_CONTACT_STAGE_FXML_NAME).replace("\\", "/" );
    public static String HELP_CONTACT_STAGE_RESOURCES_CSS = ( ProjectConstants.SEP + "css" + ProjectConstants.SEP +
            HELP_CONTACT_STAGE_CSS_NAME).replace( "\\", "/" );


}
