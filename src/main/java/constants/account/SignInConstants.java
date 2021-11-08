package constants.account;


import constants.other.ProjectConstants;

public class SignInConstants
{
    private SignInConstants(){}

    /**
     * Height and width for SignInStage.
     */

    public static final String SIGN_IN_STAGE_FXML_NAME = "signInStage.fxml";
    public static final String SIGN_IN_STAGE_CSS_NAME = "signInStageStyle.css";


    /**
     * Names of FXML/CSS files for SignInStage.
     */

    public static final Double SIGN_IN_STAGE_HEIGHT = 360.00D;
    public static final Double SIGN_IN_STAGE_WIDTH = 405.00D;


    /**
     * Relative paths (from resources dir) to FXML/CSS files for SignInStage.
     */

    public static String SIGN_IN_STAGE_RESOURCES_FXML = ( ProjectConstants.SEP + "fxml" + ProjectConstants.SEP +
            SIGN_IN_STAGE_FXML_NAME).replace( "\\", "/" );
    public static String SIGN_IN_STAGE_RESOURCES_CSS = ( ProjectConstants.SEP + "css" + ProjectConstants.SEP +
            SIGN_IN_STAGE_CSS_NAME).replace( "\\", "/" );

}
